package service;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import entity.Scores;
import entity.User;
import entity.Users;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import util.HttpHelper;

import java.util.List;

import static util.HttpHelper.getReqJson;

public interface ScoreService {

    public DetachedCriteria getFilter(String sidname, String cosidname, String tidname);

    public String list(DetachedCriteria dc);

    public String list(String sidname,String cosidname,String tidname);

    public String update(String cosid,Integer tid,Integer sid,String score);
}
