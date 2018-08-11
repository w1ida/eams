package api.teacher;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.CurriculaDao;
import dao.StudentsDao;
import entity.Curricula;
import entity.Scores;
import entity.Students;
import entity.Users;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.EvalService;
import service.ScoreService;
import util.HttpHelper;

import javax.annotation.Resource;

import java.util.List;

import static util.HttpHelper.getReqJson;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:38 2018/6/7
 * @ Description：
 */
@Controller("teacherApi")
@Scope("prototype")
public class Teacher extends ActionSupport {
    @Resource
    private ScoreService scoreService;
    @Resource
    private EvalService evalService;
    @Resource
    private CurriculaDao curriculaDao;
    @Resource
    private StudentsDao studentsDao;

    public String evallist(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        try {
            return evalService.list(null,users.getUid(),null);
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"fail", -1);
    }

    public String scorelist(){
        ActionContext ac= ActionContext.getContext();
        DetachedCriteria dc=DetachedCriteria.forClass(Scores.class);
        Users users=(Users)ac.getSession().get("user");
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        JSONObject rjson = getReqJson(ServletActionContext.getRequest());
        try {
            if (rjson != null) {
                String sidname = rjson.getString("sidname");
                String cosidname = rjson.getString("cosidname");
//                String tidname = rjson.getString("tidname");
                String tidname = String.valueOf(users.getUid());
                return scoreService.list(sidname, cosidname, tidname);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac, "fail", -1);
    }

    public String update(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        try {
            JSONObject rjson = getReqJson(ServletActionContext.getRequest());
            return scoreService.update(
                rjson.getString("cosid"),
//                rjson.getInteger("tid"),
                  users.getUid(),
                rjson.getInteger("sid"),
                rjson.getString("score")
            );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"fail",-1);
    }


    public String curriculalist(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        DetachedCriteria dc=DetachedCriteria.forClass(Curricula.class);
        dc.add(Property.forName("tid").eq(users.getUid()));
        List<Curricula> cu = curriculaDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ",cu);
    }

    public String stdlist(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        DetachedCriteria dc=DetachedCriteria.forClass(Students.class);
        String filter=null;
        if(rjson!=null)
            filter=rjson.getString("filter");
        if(filter!=null&&!filter.isEmpty()){
            if(filter.matches("[0-9]+")){
                dc.add(Property.forName("sid").eq( Integer.parseInt(filter )));
            }else{
                dc.add(
                        Restrictions.or(
                                Restrictions.like("sname", "%"+filter+"%"),
                                Restrictions.like("cname", "%"+filter+"%")
                        )
                );
            }
        }
        List<Students> std = studentsDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ", std);
    }

}
