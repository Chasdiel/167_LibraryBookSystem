package model;

import db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel {
    private int clientId;
    public String name;
    // ilość wypożyczonych książek? - po co za każdym razem przeszukiwac całą bazę, skoro wystarczy przechowywac dane

    public ClientModel(int clientId, String name) {
        setClientId(clientId);
        setName(name);
    }
    public ClientModel(String name){
        setName(name);
    }

    public int getClientId() {
        return clientId;
    }

    private void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean createClient(ClientModel newClient){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Clients (Name) VALUES (?)");
            prepStm.setString(1, newClient.getName());


            if (prepStm.executeUpdate()!=1){
                throw new SQLException();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Nie udało się utworzyć klienta.");
        } finally{
            try {
                prepStm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return false;
    }

    public static boolean delClientByID(int clientId){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Clients WHERE clientId= ?");
            prepStm.setInt(1, clientId);

            if (prepStm.executeUpdate()!=1){
                throw new SQLException();
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Nie udało się usunąć klienta");
            } finally{
            try {
                prepStm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
