package dao;

import entity.Evaluations;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:33 2018/6/7
 * @ Description：
 */
public interface EvaluationsDao extends BaseDao<Evaluations>{
    public List<Object[]> list(String cosid, Integer tid, Integer sid);//查询
}
