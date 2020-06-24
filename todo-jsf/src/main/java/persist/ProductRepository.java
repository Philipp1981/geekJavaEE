package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
@Named
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @PersistenceContext(unitName="ds")
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    @PostConstruct
    public void init() {
            if (this.findAll().isEmpty()) {
                try {
                    ut.begin();
                    this.insert(new Product(1L, "Food", "Milk", 50.0));
                    this.insert(new Product(2L, "Food", "Bread", 60.0));
                    this.insert(new Product(3L, "Food", "Cheese", 850.0));
                    this.insert(new Product(4L, "Clothes", "Shirt", 500.0));
                    this.insert(new Product(5L, "Clothes", "Jeans", 2500.0));
                    this.insert(new Product(6L, "Clothes", "Hat", 1000.0));
                    this.insert(new Product(7L, "Dishes", "Plate", 200.0));
                    this.insert(new Product(8L, "Dishes", "Cup", 150.0));
                    this.insert(new Product(9L, "Dishes", "Spoon", 100.0));
                    ut.commit();
                } catch (Exception e) {
                    try {
                        ut.rollback();
                    } catch (SystemException s) {
                        logger.error("", s);
                    }
                }
            }
        }



    @Transactional
    public void insert(Product product) {
        em.persist(product);
    }

    @Transactional
    public void update(Product product) {
        em.merge(product);
    }


    @Transactional
    public void delete(long id)  {
        Product product=em.find(Product.class, id);
        if(product != null) em.remove(product);
    }

    public Product findById(long id)  {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("from Product", Product.class).getResultList();
    }

}





