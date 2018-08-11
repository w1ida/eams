package service;

import org.hibernate.criterion.DetachedCriteria;

public interface EvalService {


    public String doEval(String cosid,Integer tid,Integer sid,Integer evalSroce);//学生评价

    public String list(String cosid,Integer tid,Integer sid);//显示评价


}
