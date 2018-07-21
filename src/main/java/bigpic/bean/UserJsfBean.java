package bigpic.bean;

import javax.enterprise.context.*;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "UserBean")
@RequestScoped

public class UserJsfBean {
    private String login;
    private String password;

    public UserJsfBean() {
        login = "empty";
        password = "empty";
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
