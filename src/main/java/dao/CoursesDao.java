package dao;

import entity.Courses;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:29 2018/6/7
 * @ Description：
 */
public interface CoursesDao extends BaseDao<Courses>{
    public int deleteByIds(List<String> ids);
}
