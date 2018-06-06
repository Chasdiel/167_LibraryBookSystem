package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalModel extends BaseModel {
    private int bookId;
    private int clientId;
    private String rentDate;
    private String returnDate;

    // creates object from Database
    protected RentalModel(int id, int bookId, int clientId, String rentDate, String returnDate) {
        this(id, bookId, clientId, rentDate);
        setReturnDate(returnDate);
    }

    //creates object from user
    protected RentalModel(int bookId, int clientId) {
        super(-1);
        setBookId(bookId);
        setClientId(clientId);
    }

    // creates object from Database
    protected RentalModel(int id, int bookId, int clientId, String rentDate) {
        super(id);
        setBookId(bookId);
        setClientId(clientId);
        setRentDate(rentDate);
        setReturnDate(returnDate);
    }

    //only for returning purpose
    protected RentalModel(int bookId) {
        super(-1);
        setBookId(bookId);
    }

    //getters and setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
    //getters and setters END




    public PreparedStatement saveStatement(){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Rentals (BookId, ClientId) VALUES(?, ?)");

            prepStm.setInt(1, this.bookId);
            prepStm.setInt(2, this.clientId);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }

    public PreparedStatement updateStatement(){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("UPDATE Rentals SET ReturnDate = datetime('now', 'localtime') WHERE Id = ?");

            prepStm.setInt(1, getId());

        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }

    //get rent ID by BookId
//    public int getIdByBookId(int bookId) {
//        int rentId =-1;
//        ResultSet rs;
//        PreparedStatement prepStm = null;
//        try {
//            prepStm = JDBCConnection.getConnection().prepareStatement("SELECT Id WHERE BookId = ? AND ReturnDate IS NULL");
//
//            prepStm.setInt(1, bookId);
//            rs = prepStm.executeQuery();
//
//            try {
//                if (rs.next()) {
//
//                    rentId = rs.getInt("Id");
//                }
//                return rentId;
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return rentId;
//    }


        @Override
    public String toString() {
        return "ID wypożyczenia: " + getId() + "; ID książki: " + getBookId() + "; ID klienta: " + getClientId() + "; data wypożyczenia: " + getRentDate() + "; data zwrotu: " + getReturnDate() ;
    }
}
