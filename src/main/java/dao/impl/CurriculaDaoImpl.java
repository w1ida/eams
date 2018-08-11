package dao.impl;

import dao.CurriculaDao;
import entity.Curricula;
import entity.CurriculaPK;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:32 2018/6/7
 * @ Description：
 */
@Repository("curriculaDao") //进行注入
@Transactional
public class CurriculaDaoImpl extends BaseDaoImpl<Curricula>  implements CurriculaDao{

}
