package api;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import entity.Users;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.UsersService;
import com.alibaba.fastjson.*;
import util.HttpHelper;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("userApi")
@Scope("prototype")
//@Transactional
public class UsersApi extends ActionSupport {
    @Resource
    private UsersService usersService;

/*    private UserServiceImpl ns;*/

    public String login() {
        ActionContext  ac= ActionContext.getContext();
        HttpServletRequest req= ServletActionContext.getRequest();
        String postData=HttpHelper.getRequestPostData(req);

        System.out.println("postData: "+postData);

        if(postData==null||postData.isEmpty()) return HttpHelper.finalResp(ac,"用户名或密码不能为空", 0);

        try {
            JSONObject jsonObject = JSONObject.parseObject(postData);
            Integer usertype=jsonObject.getInteger("usertype");
            if(usertype==3||usertype==6){
                try {
                    Integer.parseInt(jsonObject.getString("name"));
                }catch (Exception e){
                    e.printStackTrace();
                    return HttpHelper.finalResp(ac,"账号只能是ID ^_^", -1);
                }
            }
            System.out.println("login jsonObject: "+jsonObject);

            Users users= usersService.login(
                    jsonObject.getString("name"),
                    jsonObject.getString("pwd"),
                    usertype
            );

            if (users!=null) {
                ac.getSession().put("user",users);
                return HttpHelper.finalResp(ac,"登录成功", users);
            } else {
                return HttpHelper.finalResp(ac,"用户名或密码错误", -1);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }


    public String logout(){
        ActionContext  ac= ActionContext.getContext();
        ac.getSession().clear();
        return HttpHelper.finalResp(ac,"退出成功");
    }


    public String profile(){
        ActionContext  ac= ActionContext.getContext();
        HttpServletRequest req= ServletActionContext.getRequest();
        String postData=HttpHelper.getRequestPostData(req);
        JSONObject jsonObject = JSONObject.parseObject(postData);
        Users users=(Users)ac.getSession().get("user");
        System.out.println("ac.getSession():"+ac.getSession());
        System.out.println("profile->users: "+users);
        if(users==null){
            return HttpHelper.finalResp(ac,"未登录",40001);
        }
        usersService.profile(users.getUid(),users.getType(),jsonObject.getString("info"));
        return HttpHelper.finalResp(ac,"修改成功");
    }

    public String changepwd(){
        ActionContext  ac= ActionContext.getContext();
        HttpServletRequest req= ServletActionContext.getRequest();
        String postData=HttpHelper.getRequestPostData(req);
        JSONObject jsonObject = JSONObject.parseObject(postData);
        Users users=(Users)ac.getSession().get("user");
        if(users==null){
            return HttpHelper.finalResp(ac,"未登录",40001);
        }
        usersService.changepwd(users.getUid(),users.getType(),jsonObject.getString("newpwd"));
        return HttpHelper.finalResp(ac,"修改成功");
    }
    /*    public void setNs(UserServiceImpl ns) {
        this.ns = ns;
    }

    public UserServiceImpl getNs() {
        return ns;
    }*/
}
