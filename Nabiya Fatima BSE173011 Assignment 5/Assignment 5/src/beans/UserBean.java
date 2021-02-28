package beans;

import dao.UserDAO;
import pojo.User;

import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "userBean")
public class UserBean {
    User u;

    public UserBean() {
        u= new User();
    }

    public UserBean(User u) {
        this.u = u;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }

    public void savesUser()
    {
        UserDAO udao=new UserDAO();

        udao.storeUser(u);


    }
    public List<User> loadUser()
    {
        UserDAO udao=new UserDAO();
        List<User> list = new ArrayList<User>();
        list=udao.getUsers();
        return list;
    }
    public String validteUser()
    {
        UserDAO udao=new UserDAO();
        System.out.println(u.getUsername());
        boolean result=udao.validateUser(u.getUsername(),u.getPassword());
        if(result==true)
        {
            return "success";
        }
        else {
            return "faliture";
        }
    }
}
