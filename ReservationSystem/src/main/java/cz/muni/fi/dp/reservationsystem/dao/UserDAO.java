/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.dp.reservationsystem.dao;

/**
 *
 * @author Andrej
 */
public interface UserDAO {
    public boolean checkUser(String userName, String password);
}
