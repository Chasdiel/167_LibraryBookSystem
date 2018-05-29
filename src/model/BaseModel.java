package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseModel {
    protected int id=-1;    // if id is -1 the book doesn't exist

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    protected BaseModel(int id) {
        setId(id);
    }

    abstract PreparedStatement saveStatement();


//
//    public static ArrayList<BaseModel> getAll(){
//        PreparedStatement getBookStm = null;
//        ResultSet rs = null;
//        BaseModel book = null;
//        ArrayList<BaseModel> list = new ArrayList<>();
//        try {
//            String query = "SELECT * FROM Books";
//            getBookStm = JDBCConnection.getConnection().prepareStatement(query);
//            rs = getBookStm.executeQuery();
//
//            try {
//                while (rs.next()){
//                    int bookId = rs.getInt("BookId");
//                    String title = rs.getString("Title");
//                    String author = rs.getString("Author");
//                    int releaseYear = rs.getInt("ReleaseYear");
//                    int pages = rs.getInt("Pages");
//                    book = new BaseModel(bookId, title, author, releaseYear, pages);
//                    list.add(book);
//                }
//                return list;
//
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            } catch (NullPointerException e) {
//                System.out.println("Baza książek jest pusta.");
//                e.printStackTrace();
//            }
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        } catch (NullPointerException e) {
//            System.out.println("Zapytanie nie powiodło się.");
//        }
//        return null;
//    }
//
//    public static boolean delObject(int id, String query){
//
//        PreparedStatement prepStm = null;
//        try {
//            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Books WHERE BookId= ?");
//            prepStm.setInt(1, id);
//
//            if (prepStm.executeUpdate() !=1){
//                System.out.println("Nie udało się utworzyć książki.");
//            }
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        finally{
//            try{
//                prepStm.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        return false;
//    }
}
