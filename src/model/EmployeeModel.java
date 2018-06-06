package model;


import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

//TODO need to be rewritten or removed
public class EmployeeModel {
    private int Id;
    public String name;
    private String password;
    private String login;

    public EmployeeModel(int Id, String name, String password, String login) {
        setId(Id);
        setName(name);
    }
    public EmployeeModel(String name){
        setName(name);
    }

    public String getPassword() {       // to raczej powinno być private
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {    // to raczej powinno być private
        this.login = login;
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

    public static boolean createEmployee (EmployeeModel newEmployee){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Employees (name) VALUES (?, ?, ?)");
            prepStm.setString(1, newEmployee.getName());
            prepStm.setString(2, newEmployee.getPassword());
            prepStm.setString(3, newEmployee.getLogin());

            if (prepStm.executeUpdate()!=1){
                throw new SQLException();
            }

            return true;
        } catch (SQLException e) {
            System.out.println("Nie udało się utworzyć pracownika.");
        } finally{
            try {
                prepStm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean delEmployeeByID(int Id){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Employees WHERE Id= ?");
            prepStm.setInt(1, Id);

            if (prepStm.executeUpdate()!=1){
                throw new SQLException();
            }
            return true;

        } catch (SQLException e) {
            System.out.println("Nie udało się usunąć pracownika.");
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
