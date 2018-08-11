package action;

import entity.User;
import service.UsersService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import service.impl.UserServiceImpl;
import javax.annotation.Resource;
import java.util.Map;

@Controller("userAction")
@Scope("prototype")
@Deprecated
public class UserAction extends ActionSupport {
    @Resource
    private UsersService usersService;
    private User user;
    private UserServiceImpl ns;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String login() {

        if(usersService.login(user)) {
            Map session = ActionContext.getContext().getSession();
            session.put("user", user);
            return SUCCESS;
        } else {
            return ERROR;
        }
    }

    public void setNs(UserServiceImpl ns) {
        this.ns = ns;
    }

    public UserServiceImpl getNs() {
        return ns;
    }
}
