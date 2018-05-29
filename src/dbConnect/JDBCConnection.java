package dbConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JDBCConnection {
    private static Connection con = null;

    private static void connect(){
        String url = "jdbc:sqlite:db/Library.db";
            try{
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(url);

            System.out.println("Udało się nawiązać połączenie z bazą danych.");
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


    public static boolean simpleExecute(PreparedStatement prepStm){
        try {
            if (prepStm.executeUpdate() !=1){
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        finally{
            try{
                prepStm.close();
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}