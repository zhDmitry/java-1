package controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbController {
    public static Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/deanery123","root","12345");
            return conn;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
