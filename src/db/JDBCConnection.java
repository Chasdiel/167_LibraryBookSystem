package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//po usunięciu ID (nawet wykasowaniu tabeli ID liczy się od kolejnych miejsc


public class JDBCConnection {
    private static Connection con = null;

    private static void connect(){
        String url = "jdbc:sqlite:C:/Git_projects/Java_projects/Various_projects/Library/db/library.db";
            try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);

            System.out.println("Udało się nawiązać połączenie.");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    public static Connection getConnection(){
        if(con ==null){
            connect();
        }
        return con;
    }
}
