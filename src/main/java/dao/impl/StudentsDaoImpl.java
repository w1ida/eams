package dao.impl;

import dao.StudentsDao;
import entity.Students;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 9:29 2018/6/7
 * @ Description：
 */
@Repository("studentsDao") //进行注入
@Transactional
public class StudentsDaoImpl extends BaseDaoImpl<Students> implements StudentsDao{

    public int deleteByIds(List<Integer> ids) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="delete from Students where sid in :ids";
        return session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
