package model;


import db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeModel {
    private int EmployeeID;
    public String name;
    private String password;
    private String login;

    public EmployeeModel(int EmployeeID, String name, String password, String login) {
        setEmployeeID(EmployeeID);
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

    public int getEmployeeID() {
        return EmployeeID;
    }

    private void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
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

    public static boolean delEmployeeByID(int employeeId){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Employees WHERE employeeId= ?");
            prepStm.setInt(1, employeeId);

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
