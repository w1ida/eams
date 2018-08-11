package api.student;

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
@Controller("stdCosApi")
@Scope("prototype")
public class StdCos extends ActionSupport {
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
    @Resource
    private ScoreService scoreService;
    @Resource
    private TeachersDao teacherDao;

    public String list(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        List<Curricula> cus = curriculaDao.findAll();
        List<CurriculaStd> cuss=new ArrayList<CurriculaStd>();
        for(Curricula cu : cus){
            Scores oid=new Scores();
            oid.setTid(cu.getTid());
            oid.setCosid(cu.getCosid());
            oid.setSid(users.getUid());
            CurriculaStd curriculaStd=new CurriculaStd();
            curriculaStd.setCosid(cu.getCosid());
            curriculaStd.setTid(cu.getTid());
            curriculaStd.setCourses(cu.getCourses());
            curriculaStd.setTeacher(cu.getTeacher());
            if(scoresDao.findById(oid)!=null){
                curriculaStd.setChoose(true);
            }else{
                curriculaStd.setChoose(false);
            }
            cuss.add(curriculaStd);
        }
        cuss.sort((CurriculaStd h1,CurriculaStd h2) -> h1.isChoose()?1:-1 );
        return HttpHelper.finalResp(ac,"succ",cuss);
    }

    public String allscore(){
        ActionContext ac= ActionContext.getContext();
        Users users=(Users)ac.getSession().get("user");
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
        if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
        return scoreService.list(String.valueOf(users.getUid()), null, null);
    }


    public String tealist(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        DetachedCriteria dc=DetachedCriteria.forClass(Teachers.class);
        String filter=null;
        if(rjson!=null)
            filter=rjson.getString("filter");
        if(filter!=null&&!filter.isEmpty()){
            if(filter.matches("[0-9]+")){
                dc.add(Property.forName("tid").eq( Integer.parseInt(filter )));
            }else{
                dc.add(
                        Restrictions.like("tname", "%"+filter+"%")
                );
            }
        }
        List<Teachers> std = teacherDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ", std);
    }


    public String chorqcos(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson=getReqJson(ServletActionContext.getRequest());
            if(rjson==null)return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
            Users users=(Users)ac.getSession().get("user");
            if(users==null)return HttpHelper.finalResp(ac,"unlogin",40001);
            Integer tid=rjson.getInteger("tid");
            String cosid=rjson.getString("cosid");
            Courses courses = coursesDao.findById(cosid);
            Teachers teacher= teachersDao.findById(tid);
            Integer cid=((Other<Students>)users.getOther()).getData().getCid();
            List<Integer> chooses = new ArrayList<>();
            List<Integer> exits = new ArrayList<>();
            if(rjson.getString("type").equals("choose")){
                chooses.add(users.getUid());
            }else if(rjson.getString("type").equals("exit")){
                exits.add(users.getUid());
            }else{
                return HttpHelper.finalResp(ac,"type error",40001);
            }
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
                score.setTid(tid);
                score.setCosid(cosid);
                scoresDao.delete(score);
            }
            return HttpHelper.finalResp(ac, "succ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"fail",-1);
    }

}
