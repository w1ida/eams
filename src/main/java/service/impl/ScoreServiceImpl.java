package service.impl;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import dao.ScoresDao;
import entity.*;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.ScoreService;
import util.HttpHelper;

import javax.annotation.Resource;
import java.util.List;

import static util.HttpHelper.getReqJson;

@Transactional
@Service("scoreService")

public class ScoreServiceImpl implements ScoreService {
    @Resource
    private ScoresDao scoresDao;

    public DetachedCriteria getFilter(String sidname,String cosidname,String tidname){
        DetachedCriteria dc=DetachedCriteria.forClass(Scores.class);
        if(sidname!=null&&!sidname.isEmpty()){
            if(sidname.matches("[0-9]+")){
                dc.add(Property.forName("sid").eq( Integer.parseInt(sidname)));
            }else{
                dc.add(
                        Restrictions.like("sname", "%"+sidname+"%")
                );
            }
        }

        if(cosidname!=null&&!cosidname.isEmpty()){
            if(cosidname.matches("-[0-9]-")){
                dc.add(Property.forName("cosid").eq(cosidname));
            }else{
                dc.add(
                        Restrictions.like("cosname", "%"+cosidname+"%")
                );
            }
        }
        if(tidname!=null&&!tidname.isEmpty()){
            if(tidname.matches("[0-9]+")){
                dc.add(Property.forName("tid").eq( Integer.parseInt(tidname)));
            }else{
                dc.add(
                        Restrictions.like("tname", "%"+tidname+"%")
                );
            }
        }
        return dc;
    }

    public String list(DetachedCriteria dc){
        ActionContext ac= ActionContext.getContext();
        List<Scores> scos = scoresDao.findByCriteria(dc);
        return HttpHelper.finalResp(ac,"succ", scos);
    }

    public String list(String sidname,String cosidname,String tidname){
        return list(getFilter(sidname,cosidname,tidname));
    }

    public String update(String cosid,Integer tid,Integer sid,String score){
        ActionContext ac= ActionContext.getContext();
        try {
            Scores scopk = new Scores();
            scopk.setCosid(cosid);
            scopk.setTid(tid);
            scopk.setSid(sid);
            Scores sco = scoresDao.findById(scopk);
            if (sco == null) return HttpHelper.finalResp(ac, "fail", -1);
            if(score==null||score.isEmpty()){
                sco.setScore(null);
            }else{
                sco.setScore(Double.parseDouble(score));
            }
            scoresDao.update(sco);
            return HttpHelper.finalResp(ac,"succ");
        }catch (Exception e)
        {
            e.printStackTrace();
            return HttpHelper.finalResp(ac,"fail",-1);
        }
    }

}
