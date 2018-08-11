package dao.impl;

import dao.EvaluationsDao;
import entity.Evaluations;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:36 2018/6/7
 * @ Description：
 */
@Repository("evaluationsDao")
@Transactional
public class EvaluationsDaoImpl extends BaseDaoImpl<Evaluations> implements EvaluationsDao{
    public List<Object[]> list(String cosid,Integer tid,Integer sid) {
        // 获取当前session
        Session session = this.getSessionFactory().getCurrentSession();
        // 获取命名查询对象
        String hqlwhere="";
        if(cosid!=null&&!cosid.isEmpty())hqlwhere+="and e.cosid=:cosid ";
        if(tid!=null)hqlwhere+="and e.tid=:tid ";
        if(sid!=null)hqlwhere+="and e.sid=:sid ";
        Query query = session.createQuery("select e.cosid,c.cosname,t.tname,avg(e.score) as rate,count(e.sid) as cnt"+
                " from Evaluations e left join Courses c on c.cosid=e.cosid LEFT JOIN Teachers t on t.tid=e.tid  "+
                "where 1=1 "+hqlwhere+"  group by e.cosid,e.tid  ");
        if(cosid!=null&&!cosid.isEmpty())query.setParameter("cosid",cosid);
        if(null!=tid)query.setParameter("tid",tid);
        if(null!=sid)query.setParameter("sid",sid);
        List<Object[]> resultList= query.getResultList();
        System.out.println(resultList);
        return resultList;
    }
}
