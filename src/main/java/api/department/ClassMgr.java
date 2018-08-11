package api.department;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.ClassesDao;
import entity.Classes;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import util.HttpHelper;

import javax.annotation.Resource;
import java.util.List;

import static util.HttpHelper.getReqJson;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:38 2018/6/7
 * @ Description：
 */
@Controller("classMgrApi")
@Scope("prototype")
public class ClassMgr extends ActionSupport {
    @Resource
    private ClassesDao classesDao;

    public String show(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        Classes std= classesDao.findById(rjson.getInteger("id"));
        if(std!=null)
            return HttpHelper.finalResp(ac,"succ", std);
        return HttpHelper.finalResp(ac,"not found", -1);
    }

    public String list(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        DetachedCriteria dc=DetachedCriteria.forClass(Classes.class);
        String filter=null;
        if(rjson!=null)
            filter=rjson.getString("filter");
        if(filter!=null&&!filter.isEmpty()){
            if(filter.matches("[0-9]+")){
                dc.add(Property.forName("cid").eq( Integer.parseInt(filter )));
            }else{
                dc.add(
                    Restrictions.like("cname", "%"+filter+"%")
                );
            }
        }
        List<Classes> std = classesDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ", std);
    }

    public String save(){
        ActionContext ac= ActionContext.getContext();
        String postData= HttpHelper.getRequestPostData(ServletActionContext.getRequest());
        Classes std= JSONObject.parseObject(postData, Classes.class);
        if(std==null)return HttpHelper.finalResp(ac,"fail", -1);
        classesDao.saveOrUpdate(std);
        return HttpHelper.finalResp(ac,"succ");
    }

    public String del(){
        ActionContext ac= ActionContext.getContext();
        JSONObject rjson=getReqJson(ServletActionContext.getRequest());
        try {
            JSONArray ids = rjson.getJSONArray("ids");
            if(classesDao.deleteByIds(ids.toJavaList(Integer.class))>=0)
                return HttpHelper.finalResp(ac,"succ");
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"invlid agremrnt",-1);
    }
}
