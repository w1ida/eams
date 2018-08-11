package dao;

import entity.Students;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 9:28 2018/6/7
 * @ Description：
 */
public interface StudentsDao extends BaseDao<Students>{
    public int deleteByIds(List<Integer> ids);
}
