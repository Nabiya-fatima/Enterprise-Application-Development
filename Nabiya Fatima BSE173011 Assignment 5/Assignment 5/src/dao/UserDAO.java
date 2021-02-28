package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.User;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class UserDAO {
    public void storeUser(User u)
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        session.save(u);
        tx.commit();
        session.close();
    }
    public List<User> getUsers()
    {
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();
        List<User> users  = session.createQuery("FROM User").list();
        for (Iterator iterator = users.iterator(); iterator.hasNext();){
            User user = (User) iterator.next();
            System.out.print("First Name: " + user.getFirstName());

        }
        tx.commit();
        session.close();
        return users;
    }

    public boolean validateUser(String username, String password)
    {

        boolean result=false;
        Session session= HibernateUtil.getSessionFactory().openSession();
        Transaction tx=session.beginTransaction();

        List<User> user = session.createQuery("FROM User where username=:username").setParameter("username", username).list();
      //  User user = (User) session.load(User.class, username);
        System.out.print("userName: " + user.get(0).getUsername());
        System.out.print("Password: " + user.get(0).getPassword());
        if(user!=null&&user.get(0).getUsername().matches(username)&&user.get(0).getPassword().matches(password))
        {
            return true;
        }
        else {
            return false;
        }
    }
}
