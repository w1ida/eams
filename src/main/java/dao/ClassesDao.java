package dao;

import entity.Classes;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 12:39 2018/6/8
 * @ Description：
 */
public interface ClassesDao extends BaseDao<Classes>{
    public int deleteByIds(List<Integer> ids);
}
