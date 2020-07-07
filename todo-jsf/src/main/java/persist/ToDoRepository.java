package persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persist.ToDo;
import services.ToDoDto;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.servlet.ServletContext;
import javax.transaction.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Stateless
public class ToDoRepository {

    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public void insert(ToDo toDo) {
        em.persist(toDo);
    }

    public void update(ToDo toDo) {
        em.merge(toDo);
    }

    public void delete(long id) {
        ToDo toDo = em.find(ToDo.class, id);
        if (toDo != null) {
            em.remove(toDo);
        }
    }

    public ToDo findById(long id) {
        return em.find(ToDo.class, id);
    }

    public ToDoDto findToDoDtoById(long id) {
        return em.createQuery("select new services.ToDoDto(t.id, t.description, t.category.id, t.category.name) from ToDo t where t.id = :id", ToDoDto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public List<ToDo> findAll() {
        return em.createQuery("from ToDo", ToDo.class).getResultList();
    }

    public List<ToDoDto> findAllToDoDto() {
        return em.createQuery("select new services.ToDoDto(t.id, t.description, t.category.id, t.category.name) from ToDo t", ToDoDto.class)
                .getResultList();
    }
}



//    private static final Logger logger = LoggerFactory.getLogger(ToDoRepository.class);
//
//    @PersistenceContext(unitName="ds")
//    private EntityManager em;
//
//    @Inject
//    private UserTransaction ut;
//
//    @PostConstruct
//    public void init() {
//             if (this.findAll().isEmpty()) {
//                 try {
//                 ut.begin();
//                this.insert(new ToDo(-1L, "First", LocalDate.now()));
//                this.insert(new ToDo(-1L, "Second", LocalDate.now().plusDays(1)));
//                this.insert(new ToDo(-1L, "Third", LocalDate.now().plusDays(2)));
//                ut.commit();
//            } catch (Exception e) {
//                     try {
//                         ut.rollback();
//                     } catch (SystemException s) {
//                         logger.error("", s);
//                     }
//                 }
//            }
//    }
//
//    @Transactional
//    public void insert(ToDo toDo) {
//         em.persist(toDo);
//    }
//
//    @Transactional
//    public void update(ToDo toDo) {
//       em.merge(toDo);
//    }
//
//    @Transactional
//    public void delete(long id)  {
//       ToDo toDo=em.find(ToDo.class, id);
//       if(toDo != null) em.remove(toDo);
//    }
//
//    @Transactional
//    public List<ToDo> findByDescr(String description) {
//        List<ToDo> list = em.createQuery("SELECT c FROM ToDo c WHERE c.description LIKE :description").setParameter("description", description).getResultList();
//        return list;
//    }
//
//    @Transactional
//    public List<ToDo> getToDos(){
//        Query query = em.createNamedQuery("ToDo.findAll", ToDo.class);
//        List<ToDo> result = query.getResultList();
//        return result;
//    }
//
////    @Transactional
////    public void view(){
////        CriteriaBuilder cb = em.getCriteriaBuilder();
////        CriteriaQuery<ToDo> query = cb.createQuery(ToDo.class);
////        Root<ToDo> c = query.from(ToDo.class);
////        ParameterExpression<Integer> p = cb.parameter(Integer.class);
////        Predicate condition = cb.equal(c.get(ToDo.getTargetDate()), p);
////        query.select(c).where(condition);
////        TypedQuery<ToDo> q = em.createQuery(query);
////        q.setParameter(p, 1);
////        List<ToDo> todos = q.getResultList();
////    }
//
//    public ToDo findById(long id)  {
//       return em.find(ToDo.class, id);
//    }
//
//    public List<ToDo> findAll() {
//        return em.createQuery("from ToDo", ToDo.class).getResultList();
//    }
//}
