package dao;
import entity.Teachers;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:22 2018/6/6
 * @ Description：
 */

public interface TeachersDao extends BaseDao<Teachers> {
    public int deleteByIds(List<Integer> ids);
}
