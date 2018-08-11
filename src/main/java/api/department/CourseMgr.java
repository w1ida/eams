package api.department;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.*;
import entity.*;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import util.HttpHelper;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static util.HttpHelper.getReqJson;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:38 2018/6/7
 * @ Description：
 */
@Controller("courseMgrApi")
@Scope("prototype")
public class CourseMgr extends ActionSupport {
    @Resource
    private CoursesDao coursesDao;
    @Resource
    private CurriculaDao curriculaDao;
    @Resource
    private StudentsDao studentsDao;
    @Resource
    private ScoresDao scoresDao;
    @Resource
    private TeachersDao teachersDao;
    public String show(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        Courses std= coursesDao.findById(rjson.getInteger("id"));
        if(std!=null)
            return HttpHelper.finalResp(ac,"succ", std);
        return HttpHelper.finalResp(ac,"not found", -1);
    }

    public String list(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        DetachedCriteria dc=DetachedCriteria.forClass(Courses.class);
        String filter=null;
        if(rjson!=null)
            filter=rjson.getString("filter");
        if(filter!=null&&!filter.isEmpty()){
            if(filter.matches("[0-9]+")){
                /////！！！！！！！！！！！ BUG
                dc.add(Property.forName("cosid").eq( Integer.parseInt(filter )));
            }else{
                dc.add(
                    Restrictions.like("cosname", "%"+filter+"%")
                );
            }
        }
        List<Courses> std = coursesDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ", std);
    }

    public String save(){
        ActionContext ac= ActionContext.getContext();
        String postData= HttpHelper.getRequestPostData(ServletActionContext.getRequest());
        Courses std= JSONObject.parseObject(postData, Courses.class);
        if(std==null)return HttpHelper.finalResp(ac,"fail", -1);
        coursesDao.saveOrUpdate(std);
        return HttpHelper.finalResp(ac,"succ");
    }

    public String del(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        try {
            JSONArray ids = rjson.getJSONArray("ids");
            if(coursesDao.deleteByIds(ids.toJavaList(String.class))>=0)
                return HttpHelper.finalResp(ac,"succ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
    }

    public String curriculalist(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        List<Curricula> cu = curriculaDao.findAll();
        return HttpHelper.finalResp(ac,"succ",cu);
    }
    public String curriculadel(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson=getReqJson(ServletActionContext.getRequest());
            List<Curricula> pks = rjson.getJSONArray("pks").toJavaList(Curricula.class);
            for (Curricula pk : pks) {
                curriculaDao.delete(pk);
            }
            return HttpHelper.finalResp(ac, "succ");
        }catch (Exception e){
            e.printStackTrace();
            return HttpHelper.finalResp(ac,"fail");
        }
    }

    public String currstdlist(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson=getReqJson(ServletActionContext.getRequest());
            if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
            Integer cid=rjson.getInteger("cid");
            String cosid=rjson.getString("cosid");
            Integer tid=rjson.getInteger("tid");
            DetachedCriteria dc=DetachedCriteria.forClass(Scores.class);
            dc.add(Property.forName("cosid").eq( cosid));
            dc.add(Property.forName("tid").eq( tid));
            dc.add(Property.forName("cid").eq( cid));
            List<Scores> scs=scoresDao.findByCriteria(dc);
            List<Students> stds=studentsDao.findByCriteria(
                    DetachedCriteria.forClass(Students.class)
                        .add(Property.forName("cid").eq(cid))
            );
            List<Integer> usedsid=new ArrayList<Integer>();
            for(Scores sc : scs){
                usedsid.add(sc.getSid());
            }
            HashMap<String,Object> out=new HashMap<String,Object>();
            out.put("usedsid",usedsid);
            out.put("stds",stds);
            return HttpHelper.finalResp(ac, "succ",out);
        }catch (Exception e){
            e.printStackTrace();
            return HttpHelper.finalResp(ac,"fail");
        }
    }

    public String updatealloccourse(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson=getReqJson(ServletActionContext.getRequest());
            if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
            Integer cid=rjson.getInteger("cid");
            Integer tid=rjson.getInteger("tid");
            String cosid=rjson.getString("cosid");
            Courses courses = coursesDao.findById(cosid);
            Teachers teacher= teachersDao.findById(tid);
            List<Integer> chooses = rjson.getJSONArray("choose").toJavaList(Integer.class);
            List<Integer> exits = rjson.getJSONArray("exit").toJavaList(Integer.class);
            for(Integer choose : chooses){
                Students std=studentsDao.findById(choose);
                Scores score=new Scores();
                score.setCid(cid);
                score.setCosid(cosid);
                score.setCosname(courses.getCosname());
                score.setSname(std.getSname());
                score.setSid(choose);
                score.setTid(tid);
                score.setTname(teacher.getTname());
                scoresDao.save(score);
            }
            for(Integer exit : exits){
                Scores score=new Scores();
                score.setSid(exit);
                score.setCid(cid);
                score.setTid(tid);
                score.setCosid(cosid);
                scoresDao.delete(score);
            }
            return HttpHelper.finalResp(ac, "succ");
        }catch (Exception e){
            e.printStackTrace();
            return HttpHelper.finalResp(ac,"fail",-1);
        }
    }

    public String opencourse(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        String cosid=rjson.getString("cosid");
        List<Integer> tids=rjson.getJSONArray("tids").toJavaList(Integer.class);
        for(Integer tid :  tids){
            Curricula cu=new Curricula();
            cu.setCosid(cosid);
            cu.setTid(tid);
            curriculaDao.save(cu);
        }
        return HttpHelper.finalResp(ac,"succ");
    }
}
