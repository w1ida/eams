package dao.impl;

import dao.UserDao;
import entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;
import java.util.List;

@Transactional
//出现Exception异常回滚
@Repository("userDao") //进行注入

public class UserDaoImpl implements UserDao{
    @Resource(name="sessionFactory")
    private SessionFactory sf;



    @Override
    public void add(User user) {
        Session sessionFactory = sf.openSession();
        sessionFactory.save(user);
    }

    @Override
    public boolean login(User user) {
        Session sessionFactory = sf.openSession();
        Iterator<User> it;
        if(user==null||user.getName().isEmpty()||user.getLoginpwd().isEmpty())return false;

        String hsql="FROM User u where u.name=?1 and u.loginpwd=?2";

        System.out.println(hsql);
        Query query = sessionFactory.createQuery(hsql);
        query.setParameter(1, user.getName());
        query.setParameter(2, user.getLoginpwd());
        System.out.println(user.getName());
        it=query.iterate();
        if(it.hasNext()) {
            System.out.println("true");
            return true;
        } else {
            System.out.println("false");
            return false;
        }
    }

    @Override
    public List getUser() {
        Session sessionFactory = sf.openSession();
        return sessionFactory.createQuery("FROM User").list();
    }

    @Override
    public User getUser(int id) {

        Session sessionFactory = sf.openSession();
        return (User)sessionFactory.get(User.class, id);
    }

    @Override
    public void update(User user) {
        Session sessionFactory = sf.openSession();
        sessionFactory.update(user);
    }

    @Override
    public void delete(int id) {
        Session sessionFactory = sf.openSession();
        sessionFactory.delete(
                sessionFactory.get(User.class, id)
        );
    }

    public void setSf(SessionFactory sf) {
        this.sf = sf;
    }

    public SessionFactory getSf() {
        return sf;
    }
}
