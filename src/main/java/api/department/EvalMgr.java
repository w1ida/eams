package api.department;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.ScoresDao;
import entity.Scores;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.EvalService;
import util.HttpHelper;

import javax.annotation.Resource;
import java.util.List;

import static util.HttpHelper.getReqJson;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 22:38 2018/6/7
 * @ Description：
 */
@Controller("evalMgrApi")
@Scope("prototype")
public class EvalMgr extends ActionSupport {
    @Resource
    private ScoresDao scoresDao;

    @Resource
    private EvalService evalService;

    public String list(){
        ActionContext ac= ActionContext.getContext();
        try {
            JSONObject rjson = getReqJson(ServletActionContext.getRequest());
            if(rjson==null)return HttpHelper.finalResp(ac,"arg error", -1);
            return evalService.list(rjson.getString("cosid"),rjson.getInteger("tid"),rjson.getInteger("sid"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return HttpHelper.finalResp(ac,"fail", -1);
    }

}
