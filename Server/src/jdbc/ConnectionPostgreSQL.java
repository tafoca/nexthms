/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Adrien MOMO
 */
public class ConnectionPostgreSQL {
    private static String url = "jdbc:postgresql://localhost:5432/bdhospital";
    private static String user = "postgres";
    private static String passwd = "anita";
    private static Connection connect;

    public static Connection getInstance(){
        if(connect==null){
            try {
                connect = DriverManager.getConnection(url, user, passwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connect;
    }
}
