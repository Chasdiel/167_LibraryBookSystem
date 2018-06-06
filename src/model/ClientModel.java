package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel extends BaseModel {
    private int id;
    public String name;


    protected ClientModel(int id, String name) {
        super(id);
        setName(name);
    }
    public ClientModel(String name){
        super(-1);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    PreparedStatement saveStatement() {
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Clients (Name) VALUES(?)");

            prepStm.setString(1, this.name);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }

    @Override
    PreparedStatement updateStatement() {
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("UPDATE Clients SET Name = ? WHERE Id = ?");
            prepStm.setString(1, this.name);
            prepStm.setInt(2, getId());

        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }

    @Override
    public String toString() {
        return getId() + "; " + getName() ;
    }
}
