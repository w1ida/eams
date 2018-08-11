package dao.impl;

import dao.BaseDao;
import dao.DepartmentsDao;
import entity.Departments;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resources;
import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 9:30 2018/6/7
 * @ Description：
 */
@Repository("departmentsDao") //进行注入
@Transactional
public class DepartmentsDaoImpl extends BaseDaoImpl<Departments> implements DepartmentsDao {
    public int deleteByIds(List<Integer> ids) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="delete from Departments where did in :ids";
        return session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
