package dao.impl;

import dao.ScoresDao;
import entity.Scores;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 19:38 2018/6/7
 * @ Description：
 */

@Repository("ScoresDaoImpl")
@Transactional
public class ScoresDaoImpl extends BaseDaoImpl<Scores> implements ScoresDao{

}
