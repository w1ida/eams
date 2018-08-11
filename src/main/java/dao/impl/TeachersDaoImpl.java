package dao.impl;

import dao.TeachersDao;
import entity.Teachers;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 23:13 2018/6/6
 * @ Description：
 */

@Repository("teachersDao") //进行注入
@Transactional

public class TeachersDaoImpl extends BaseDaoImpl<Teachers> implements TeachersDao{

    public int deleteByIds(List<Integer> ids) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="delete from Teachers where tid in :ids";
        return session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
