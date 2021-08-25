package dao.hibernate;

import entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GetUser {
    private static SessionFactory sessionFactory;

    public static User getUser(String userName) {
        sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        //Transaction transaction = null;

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.like(root.get("userName"), userName));
        User user = session.createQuery(criteria).getSingleResult();
        session.close();
        System.out.println(user.toString());
        return user;

    }
}
