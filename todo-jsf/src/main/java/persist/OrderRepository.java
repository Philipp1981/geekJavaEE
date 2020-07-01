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
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
public class OrderRepository {
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
                this.insert(new Order(1000.0, "1234555", "Lenina 1", Order.Status.CREATED));
                this.insert(new Order(1200.0, "1234556", "Lenina 2", Order.Status.CREATED));
                this.insert(new Order(1500.0, "1234557", "Lenina 3", Order.Status.PAID));
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
    public void insert(Order order) {
        em.persist(order);
    }

    @Transactional
    public void update(Order order) {
        em.merge(order);
    }


    @Transactional
    public void delete(long id) {
        Order order = em.find(Order.class, id);
        if (order != null) em.remove(order);
    }

    public Order findById(long id) {
        return em.find(Order.class, id);
    }

    public List<Order> findAll() {
        return em.createQuery("from Order", Order.class).getResultList();
    }
}
