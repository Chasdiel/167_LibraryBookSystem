package model;

import db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RentalModel {
    public String rentDate;
    public String returnDate;
    public int bookId;
    public int clientId;

    public RentalModel(int bookId, int clientId){ //rentDate nie jest potrzebne w parametrze konstruktora, bo i tak użyjemy set - można tak?
        setRentDate();
        setBookId(bookId);
        setClientId(clientId);
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

    public void setRentDate() {
        this.rentDate = String.valueOf(LocalDate.now());;
    }

    public String getReturnDate() {

        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }


}
