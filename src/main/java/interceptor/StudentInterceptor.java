package interceptor;

/**
 * @ Author     ：viete.
 * @ Date       ：Created in 20:54 2018/6/11
 * @ Description：
 */
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import entity.Users;
import util.HttpHelper;

import java.util.Map;

public class StudentInterceptor implements Interceptor{

    @Override
    public void destroy() {

    }

    @Override
    public void init() {

    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {
        Map<String, Object> session= actionInvocation.getInvocationContext().getSession();
        ActionContext  ac=actionInvocation.getInvocationContext();
        Users users=(Users)session.get("user");
        if(users==null){
            return HttpHelper.finalResp(ac,"unlogin",40001);
        }
        if(users.getType()!=3){
            return HttpHelper.finalResp(ac,"user type error",40002);
        }
        return actionInvocation.invoke();
    }
}
