package hiber.dao;

import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsersByCarModel(String model, int series) {
        List<User> users;
        try (Session session = sessionFactory.openSession()) {
            String hql = "select u from User u JOIN FETCH u.car c where c.models = :carModel and c.series = :carSeries";
            users = session.createQuery(hql, User.class)
                    .setParameter("carModel", model)
                    .setParameter("carSeries", series)
                    .list();
        }
        return users;
    }
}
