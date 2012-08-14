/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.dp.reservationsystem.beans;

import cz.muni.fi.dp.reservationsystem.dao.UserDAO;
import cz.muni.fi.dp.reservationsystem.dao.UserDAOImpl;
import java.io.Serializable;
import javax.faces.context.FacesContext;

/**
 *
 * @author Andrej
 */
public class UserBean implements Serializable {
    private UserDAO udao;
    private String userName;
    private String password;
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Creates a new instance of UserBean
     */
    public UserBean() {
        udao = new UserDAOImpl();
    }

    
    public String login() {
        if (udao.checkUser(userName, password)) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userName", userName);
            return "loginvalid";
        } else {
            return "logininvalid";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "logoutvalid";
    }
}
