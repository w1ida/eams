package dao.impl;

import dao.ClassesDao;
import entity.Classes;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 12:39 2018/6/8
 * @ Description：
 */
@Transactional
//出现Exception异常回滚
@Repository("classesDao") //进行注入
public class ClassesDaoImpl extends BaseDaoImpl<Classes> implements ClassesDao {
    public int deleteByIds(List<Integer> ids) {
        Session session = this.getSessionFactory().getCurrentSession();
        String hql="delete from Classes where cid in :ids";
        return session.createQuery(hql).setParameterList("ids",ids).executeUpdate();
    }
}
