package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
   @Autowired
   private SessionFactory sessionFactory;
   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> getUsersByCarModel(String model,int series) {
      List<User> users;
      try (Session session = sessionFactory.openSession()) {
         String hql = "SELECT u FROM User u WHERE u.car.models = :carModel and u.car.series = :carSeries";
         users = session.createQuery(hql, User.class)
                 .setParameter("carModel", model)
                 .setParameter("carSeries", series)
                 .list();
      }
      return users;
   }

}
