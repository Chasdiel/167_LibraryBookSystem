package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseMapper <T extends BaseModel> {
   protected String msgCantCreate = "Nie udało się utworzyć rekordu.";
   protected String msgCreateDone = "Rekord dodany.";
   protected String tableName;


   public BaseMapper(String tableName){
        this.tableName = tableName;
    }

    public abstract T createFromRS(ResultSet rs);

    public boolean save(T model){
        if (model == null){
            return false;
        }
        PreparedStatement prepStm =  model.saveStatement();
       return JDBCConnection.simpleExecute(prepStm);
    }


    public boolean delByID(int id){
        PreparedStatement prepStm = null;
        try {
            String query = "DELETE FROM " + tableName + " WHERE Id = ?";
            prepStm = JDBCConnection.getConnection().prepareStatement(query);
//            prepStm.setString(1, tableName);
            prepStm.setInt(1, id);

            if (prepStm.executeUpdate() !=1){
                System.out.println(msgCantCreate);
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

    //TODO
    public T getById(int id){
        T model = null;
        PreparedStatement getStm;
        ResultSet rs;

        try {
            String query = "SELECT * FROM " + tableName + " WHERE Id = ?";

            getStm = JDBCConnection.getConnection().prepareStatement(query);
//            getStm.setString (1, tableName);
            getStm.setInt(1, id);

            rs = getStm.executeQuery();

            try {
                if (rs.next()) {

//                    String title = rs.getString("Title");
//                    String author = rs.getString("Author");
//                    int releaseYear = rs.getInt("ReleaseYear");
//                    int pages = rs.getInt("Pages");
//                    book = new BookModel(bookId, title, author, releaseYear, pages);
                    model = createFromRS(rs);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Nie ma takiego obiektu w bazie danych.");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Zapytanie nie powiodło się.");
        }
        return model;
    }


    public ArrayList<T> getAll(){
        PreparedStatement getStm = null;
        ResultSet rs = null;
        T model = null;
        ArrayList<T> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + tableName;
            getStm = JDBCConnection.getConnection().prepareStatement(query);
            rs = getStm.executeQuery();

            try {
                while (rs.next()){
//                    int id = rs.getInt("Id");
//                    String title = rs.getString("Title");
//                    String author = rs.getString("Author");
//                    int releaseYear = rs.getInt("ReleaseYear");
//                    int pages = rs.getInt("Pages");
//                    book = new BookModel(id, title, author, releaseYear, pages);
                    model = createFromRS(rs);
                    list.add(model);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Baza danych jest pusta.");
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Zapytanie nie powiodło się.");
        }
        return list;
    }
}
