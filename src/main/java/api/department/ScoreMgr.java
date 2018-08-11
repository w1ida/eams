package api.department;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ScoresDao;
import entity.Scores;
import entity.ScoresPK;
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
@Controller("scoreMgrApi")
@Scope("prototype")
public class ScoreMgr extends ActionSupport {
    @Resource
    private ScoreService scoreService;
    public String list(){
        ActionContext ac= ActionContext.getContext();
        DetachedCriteria dc=DetachedCriteria.forClass(Scores.class);
        JSONObject rjson = getReqJson(ServletActionContext.getRequest());
        try {
            if (rjson != null) {
                String sidname = rjson.getString("sidname");
                String cosidname = rjson.getString("cosidname");
                String tidname = rjson.getString("tidname");
                return scoreService.list(sidname, cosidname, tidname);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac, "fail", -1);
    }

    public String update(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson = getReqJson(ServletActionContext.getRequest());
            return scoreService.update(
                rjson.getString("cosid"),
                rjson.getInteger("tid"),
                rjson.getInteger("sid"),
                rjson.getString("score")
            );
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"fail",-1);
    }

}
