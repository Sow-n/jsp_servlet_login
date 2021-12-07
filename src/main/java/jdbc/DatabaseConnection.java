/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Admin
 */
public class DatabaseConnection {
    private final static BasicDataSource dataSource = new BasicDataSource();
    
    static {
        dataSource.setUrl("jdbc:mysql://localhost:3306/web_login?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("admin");
        dataSource.setPassword("09092018");
        
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(10);
        dataSource.setMaxOpenPreparedStatements(100);
        
        
    }
    
    public static Connection getConnection()throws SQLException{
        
        return dataSource.getConnection();
    }
}
