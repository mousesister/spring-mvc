package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDao {

    @PersistenceContext
    private EntityManager em;

    public List<User> index() { return em.createQuery("SELECT u FROM User u", User.class).getResultList(); }

    public User show(int count) { return em.find(User.class, count); }

    public void save(User user) {
        em.persist(user);
    }

    @Transactional
    public void update(int count, User updatedUser) {
        em.merge(updatedUser);
    }

    @Transactional
    public void delete(int count) {
        em.remove(em.find(User.class, count));
    }
}
