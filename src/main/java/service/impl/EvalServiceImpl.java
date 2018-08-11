package service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import dao.EvaluationsDao;
import dao.ScoresDao;
import entity.Evaluations;
import entity.Scores;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.EvalService;
import service.ScoreService;
import util.HttpHelper;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

import static util.HttpHelper.getReqJson;

@Transactional
@Service("evalService")

public class EvalServiceImpl implements EvalService {
    @Resource
    private EvaluationsDao evaluationsDao;
    public String doEval(String cosid,Integer tid,Integer sid,Integer evalSroce){
        ActionContext ac= ActionContext.getContext();
        try {
            Evaluations evaluations = new Evaluations();
            evaluations.setCosid(cosid);
            evaluations.setTid(tid);
            evaluations.setSid(sid);
            evaluations.setScore(evalSroce);
            evaluations.setTime(new Timestamp(System.currentTimeMillis()));
            evaluationsDao.save(evaluations);
            return HttpHelper.finalResp(ac, "succ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac, "fail",-1);
    }

    public String list(String cosid,Integer tid,Integer sid){
        ActionContext ac= ActionContext.getContext();
        try {
            List<Object[]> rst = evaluationsDao.list(cosid, tid, sid);
            return HttpHelper.finalResp(ac, "succ",rst);
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac, "fail",-1);
    }

}
