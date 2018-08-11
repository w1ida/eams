package dao.impl;

import dao.CoursesDao;
import entity.Courses;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:30 2018/6/7
 * @ Description：
 */
@Repository("coursesDao") //进行注入
@Transactional
public class CoursesDaoImpl extends BaseDaoImpl<Courses> implements CoursesDao{
    public int deleteByIds(List<String> ids) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="delete from Courses where cosid in :ids";
        return session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
