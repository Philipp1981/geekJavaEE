package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Named
public class ProductRepository {
    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @Inject
    private ServletContext sc;

    private Connection conn;

    private Category category;

    public ProductRepository() {
    }

    @PostConstruct
    public void init() {
        logger.info("Initializing application");

        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("postgres");
        String password = sc.getInitParameter("philipp");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);

            if (this.findAll().isEmpty()) {
                this.insert(new Product(1L, "Food", "Milk", 50.0));
                this.insert(new Product(2L, "Food", "Bread", 60.0));
                this.insert(new Product(3L, "Food", "Cheese", 850.0));
                this.insert(new Product(4L, "Clothes", "Shirt", 500.0));
                this.insert(new Product(5L, "Clothes", "Jeans", 2500.0));
                this.insert(new Product(6L, "Clothes", "Hat", 1000.0));
                this.insert(new Product(7L, "Dishes", "Plate", 200.0));
                this.insert(new Product(8L, "Dishes", "Cup", 150.0));
                this.insert(new Product(9L, "Dishes", "Spoon", 100.0));
            }
            createTableIfNotExists(conn);
        } catch (SQLException ex) {
            logger.error("", ex);
            throw new RuntimeException(ex);
        }

    }


    public void insert(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into products(category, title, price) values (?, ?, ?);")) {
            stmt.setString(1, product.getCategory());
            stmt.setString(2, product.getTitle());
            stmt.setDouble(3, product.getPrice());
            stmt.execute();
        }
    }

    public void update(Product product) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update products set category = ?, title = ?, price=? where id = ?;")) {

            stmt.setString(1, product.getCategory());
            stmt.setString(2, product.getTitle());
            stmt.setDouble(3, product.getPrice());
            stmt.setLong(4, product.getId());
            stmt.execute();
        }
    }


    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from products where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Product findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, category, title, price from products where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            }
        }
        return new Product(-1L, "", null, 0.0);
    }

    public List<Product> findAll() throws SQLException {
        List<Product> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, category, title, price from products");

            while (rs.next()) {
                res.add(new Product(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists products (\n" +
                    "\tid int not null auto_increment primary key,\n" +
                    "    category varchar(25) foreign key categories (name),\n" +
                    "    title not null varchar(25),\n" +
                    "    price decimal(10, 2)\n"+
                    ");");
        }
    }
}