package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BaseMapper <T extends BaseModel> {
    protected String tableName;
    protected String msgCantCreate = "Nie udało się utworzyć rekordu.";
    protected String msgDeleteDone = "Rekord usunięty pomyślnie.";
    protected String msgCreateDone = "Rekord dodany.";
    protected String msgUpdateDone = "Rekord zaktualizowany.";
    protected String msgQueryError = "Zapytanie nie powiodło się.";
    protected String msgObjNotFound = "Nie ma takiego obiektu w bazie danych.";
    protected String msgEmptyDb ="Baza danych jest pusta.";


   public BaseMapper(String tableName){
        this.tableName = tableName;
    }
    private void msg(String textMsg){
        System.out.println(tableName + " " + textMsg);
    }

    public abstract T createFromRS(ResultSet rs);



    public boolean update(T model){
        boolean result = false;
        if (model == null){
            return result;
        }
        PreparedStatement prepStm =  model.updateStatement();
        result = JDBCConnection.simpleExecute(prepStm);

        if(result){
            msg(msgUpdateDone);
        } else {
            msg(msgCantCreate);
        }
        return result;
    }


    public boolean save(T model){
        boolean result;
        if (model == null){
            return false;
        }
        PreparedStatement prepStm =  model.saveStatement();
        result = JDBCConnection.simpleExecute(prepStm);

        if(result){
            msg(msgCreateDone);
        } else {
            msg(msgCantCreate);
        }
        return result;
    }


    public boolean delete(BaseModel model){
        return model != null && delete(model.getId());
    }


    public boolean delete(int id){
        boolean result = false;
        PreparedStatement prepStm = null;
        try {
            String query = "DELETE FROM " + tableName + " WHERE Id = ?";
            prepStm = JDBCConnection.getConnection().prepareStatement(query);
            prepStm.setInt(1, id);

            if (prepStm.executeUpdate() == 1) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally{
            try{
                prepStm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(result){
            msg(msgDeleteDone);
        } else{
            msg(msgCantCreate);
        }

        return result;
    }


    public T getById(int id){
        T model = null;
        PreparedStatement getStm;
        ResultSet rs;

        try {
            String query = "SELECT * FROM " + tableName + " WHERE Id = ?";

            getStm = JDBCConnection.getConnection().prepareStatement(query);
            getStm.setInt(1, id);

            rs = getStm.executeQuery();

            try {
                if (rs.next()) {

                    model = createFromRS(rs);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                msg(msgObjNotFound);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            msg(msgQueryError);
        }
        return model;
    }


    public ArrayList<T> getAll(){
        PreparedStatement getStm;
        ResultSet rs;
        T model;
        ArrayList<T> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM " + tableName;
            getStm = JDBCConnection.getConnection().prepareStatement(query);
            rs = getStm.executeQuery();

            try {
                while (rs.next()){
                    model = createFromRS(rs);
                    list.add(model);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                msg(msgEmptyDb);
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            msg(msgQueryError);
        }
        return list;
    }


    public boolean existsInDb(int id) {
        if (id < 0) {
            msg("Książka o takim id nie istnieje.");
            return false;
        }
        T model = getById(id);
        return isValid(model);
    }


    public boolean isValid(BaseModel model) {
        return model != null;
    }

}
