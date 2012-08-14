/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.dp.reservationsystem.dao;

/**
 *
 * @author Andrej
 */
public class User {
    private long id;
    private String userName;
    private String password;

    public long getId() {
        return id;
    }

    private void setId(long id) {
        this.id = id;
    }

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
}
