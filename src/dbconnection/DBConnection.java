/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author NUWAA
 */
public class DBConnection {

    private static Connection dbConnection;

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3307/shoe_store_db?useSSL=false", "root", "ass34");
        } catch (Exception e) {
            dbConnection = null;
        }
        return dbConnection;
    }

//    public static void main(String[] args) {
//        try {
//            System.out.println(getConnection().getCatalog());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
