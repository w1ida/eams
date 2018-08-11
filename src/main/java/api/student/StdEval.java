package api.student;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.*;
import entity.*;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.EvalService;
import service.ScoreService;
import util.HttpHelper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.HttpHelper.getReqJson;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:38 2018/6/7
 * @ Description：
 */
@Controller("stdEvalApi")
@Scope("prototype")
public class StdEval extends ActionSupport {
    @Resource
    private EvalService evalService;
    @Resource
    private ScoresDao scoresDao;
    @Resource
    private EvaluationsDao evaluationsDao;

    public String list(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        DetachedCriteria dc=DetachedCriteria.forClass(Scores.class);
        dc.add(Property.forName("sid").eq(users.getUid()));
//      dc.add(Property.forName("score").isNotEmpty());
        List<Scores> scos = scoresDao.findByCriteria(dc);
        List<Object> outs=new ArrayList<>();
        for(Scores sco : scos){
            if(sco.getScore()==null)continue;//有成绩才能评价
            HashMap<String, Object> out=new HashMap<>();
            Evaluations eval=new Evaluations();
            eval.setSid(users.getUid());
            eval.setTid(sco.getTid());
            eval.setCosid(sco.getCosid());
            out.put("score",sco);
            out.put("eval",evaluationsDao.findById(eval));
            outs.add(out);
        }
        return HttpHelper.finalResp(ac,"succ",outs);
    }

    public String eval(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        String cosid=rjson.getString("cosid");
        Integer tid=rjson.getInteger("tid");
        Integer evalSroce=rjson.getInteger("evalSroce");
        return evalService.doEval(cosid,tid,users.getUid(),evalSroce);
    }

}
