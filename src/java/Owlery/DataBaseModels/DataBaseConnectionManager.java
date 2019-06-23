/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Owlery.DataBaseModels;

/**
 *
 * @author Eslam Ibrahim
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnectionManager {

    String JDBC_DRIVER;
    String DB_URL;
    String USER;
    String PASS;
    Connection conn;
    Statement stmt;
    PreparedStatement pst;

    public DataBaseConnectionManager() {
        JDBC_DRIVER = "";
        DB_URL = "";
        USER = "";
        PASS = "";
    }

    public boolean connectToDB() {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost:3306/owlerydb";
        USER = "root";
        PASS = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            return false;
        } catch (SQLException e) {
            return false;
        }

        return true;

    }

    public boolean disconnectFromDB() {
        try {
            conn.close();
            stmt.close();
            pst.close();
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}

