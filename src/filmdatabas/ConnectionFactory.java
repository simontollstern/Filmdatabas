/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdatabas;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Simon Tollstern
 */
public class ConnectionFactory {
    public static Connection getConnection() throws SQLException{
        String url = "jdbc:mysql://localhost/moviedb";
        String username = "root";
        String pass = "";
        
        Connection conn = (com.mysql.jdbc.Connection)DriverManager.getConnection(url, username, pass);
        return conn;
    }
}
