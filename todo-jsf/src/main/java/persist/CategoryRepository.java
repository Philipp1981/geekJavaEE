package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
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


@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Inject
    private UserTransaction ut;

    @PostConstruct
    public void init() {
        if (this.findAll().isEmpty()) {
            try {
                ut.begin();
                this.insert(new Category(1L, "Food"));
                this.insert(new Category(2L, "Clothes"));
                this.insert(new Category(3L, "Dishes"));
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
    public void insert(Category category) {
        em.persist(category);
    }

    @Transactional
    public void update(Category category) {
        em.merge(category);
    }


    @Transactional
    public void delete(long id) {
        Category category = em.find(Category.class, id);
        if (category != null) em.remove(category);
    }

    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    public List<Category> findAll() {
        return em.createQuery("from Category", Category.class).getResultList();
    }
}
