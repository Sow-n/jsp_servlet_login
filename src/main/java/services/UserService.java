/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;
import models.User;

/**
 *
 * @author Admin
 */
public class UserService {

    private DataSource dataSource;

    public UserService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    public void addStudent(User user) throws Exception {
//        try {
//            myConn = dataSource.getConnection();
//
//            String sql = "SELECT * FROM tblAdmin WHERE user_name=? ADN password=?";
//
//            myStmt = myConn.prepareStatement(sql);
//
//            //set param value
//            myStmt.setString(1, user.getUserName());
//            myStmt.setString(2, user.getPassword());
//
//            myStmt.execute();
//        } finally {
//            close(myConn, myStmt, null);
//        }
//    }
//
    private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
       if (myRs != null) try {
            myRs.close();
        } catch (SQLException ignore) {
            //irgone
        }

        if (myStmt != null) try {
            myStmt.close();
        } catch (SQLException ignore) {
            //irgone
        }

        if (myConn != null) try {
            myConn.close();
        } catch (SQLException ignore) {
            //irgone
        }
    }    //close
     
    
    public User checkLogin(User theUser) throws Exception {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            myConn = dataSource.getConnection();

            String sql = "SELECT * FROM tblAdmin WHERE user_name=? AND password=?";

            myStmt = myConn.prepareStatement(sql);
            myStmt.setString(1, theUser.getUserName());
            myStmt.setString(2, theUser.getPassword());

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                User u = new User(myRs.getString(1), myRs.getString(2));
                return u;
            }
        } finally {
            close(myConn, myStmt, null);
        }
        return null;
    }
}
