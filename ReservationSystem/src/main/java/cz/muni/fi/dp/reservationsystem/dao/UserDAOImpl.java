/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.dp.reservationsystem.dao;

import cz.muni.fi.dp.reservationsystem.hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Andrej
 */
public class UserDAOImpl implements UserDAO {
    Session hSession = null;
    
    public UserDAOImpl() {
        this.hSession = HibernateUtil.getSessionFactory().getCurrentSession();
    }
    
    @Override
    public boolean checkUser(String userName, String password) {
        Transaction tx = hSession.beginTransaction();
        Query q = hSession.createQuery("select user from User as user where userName = '" + userName + "' and password = '" + password + "'");
        if (!q.list().isEmpty()) {
            return true;
        }
        else {
            return false;
        }
    }
}
