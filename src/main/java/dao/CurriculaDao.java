package dao;

import dao.impl.BaseDaoImpl;
import entity.Curricula;
import entity.CurriculaPK;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:31 2018/6/7
 * @ Description：
 */
@Repository("curriculaDao")
@Transactional
public interface CurriculaDao extends BaseDao<Curricula> {

}
