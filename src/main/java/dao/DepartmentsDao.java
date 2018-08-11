package dao;

import entity.Departments;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 9:30 2018/6/7
 * @ Description：
 */
public interface DepartmentsDao extends BaseDao<Departments> {
    public int deleteByIds(List<Integer> ids);
}
