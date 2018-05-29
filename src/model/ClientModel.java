package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientModel {
    private int Id;
    public String name;
    // ilość wypożyczonych książek? - po co za każdym razem przeszukiwac całą bazę, skoro wystarczy przechowywac dane

    public ClientModel(int Id, String name) {
        setId(Id);
        setName(name);
    }
    public ClientModel(String name){
        setName(name);
    }

    public int getId() {
        return Id;
    }

    private void setId(int Id) {
        this.Id = Id;
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

    public static boolean delClientByID(int Id){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Clients WHERE Id= ?");
            prepStm.setInt(1, Id);

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
