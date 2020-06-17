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
public class CategoryRepository {
    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @Inject
    private ServletContext sc;

    private Connection conn;

    public CategoryRepository() {
    }

    @PostConstruct
    public void init() {
        logger.info("Creating categories");

        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("postgres");
        String password = sc.getInitParameter("philipp");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);

            if (this.findAll().isEmpty()) {
                this.insert(new Category(1L, "Food"));
                this.insert(new Category(2L, "Clothes"));
                this.insert(new Category(3L, "Dishes"));

            }
            createTableIfNotExists(conn);
        } catch (SQLException ex) {
            logger.error("", ex);
            throw new RuntimeException(ex);
        }

    }


    public void insert(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into categories(name) values (?);")) {
            stmt.setString(1, category.getName());
            stmt.execute();
        }
    }

    public void update(Category category) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "update categories set name = ? where id = ?;")) {

            stmt.setString(1, category.getName());
            stmt.setLong(2, category.getId());
            stmt.execute();
        }
    }


    public void delete(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "delete from categories where id = ?;")) {
            stmt.setLong(1, id);
            stmt.execute();
        }
    }

    public Category findById(long id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, name from categoris where id = ?")) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Category(rs.getLong(1), rs.getString(2));
            }
        }
        return new Category(-1L, "");
    }

    public List<Category> findAll() throws SQLException {
        List<Category> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, name from categoris");

            while (rs.next()) {
                res.add(new Category(rs.getLong(1), rs.getString(2)));
            }
        }
        return res;
    }

    private void createTableIfNotExists(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("create table if not exists categories (\n" +
                    "\tid int not null auto_increment primary key,\n" +
                    "    name not null varchar(25),\n" +
                    ");");
        }
    }
}
