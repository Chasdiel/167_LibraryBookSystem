package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

public class RentalMapper extends BaseMapper <RentalModel> {


    public RentalMapper() {
        super("Rentals");
    }

//TODO tu coś próbuję
    @Override
    public RentalModel createFromRS(ResultSet rs) {
        try {
            int rentalId = rs.getInt("Id");
            int bookId = rs.getInt("BookId");
            int clientId = rs.getInt("ClientId");
            String rentDate = rs.getString("RentDate");
            String returnDate = rs.getString("ReturnDate");

            return new RentalModel(rentalId, bookId, clientId, rentDate, returnDate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean rentBook(int bookId, int clientId){

        //validate
        BookMapper bMap = new BookMapper();
        ClientMapper cMap = new ClientMapper();

        if(!bMap.existsInDb(bookId)){
            System.out.println("Książka o takim id nie istnieje.");
            return false;
        }

        if(!cMap.existsInDb(clientId)){
            System.out.println("Klient o takim id nie istnieje.");
            return false;
        }
//TODO
        if(isRented(bookId)){
            System.out.println("Książka wypożyczona do: " + checkReturnDate((bookId)) + ". Wybierz inną pozycję.");
            System.out.println();
            return false;
        }
        //validate END

        RentalModel rM = new RentalModel(bookId, clientId);
        boolean result = save(rM);
        if (result){
            System.out.println("Książka wypożyczona pomyślnie.");
        }  else {
            System.out.println("Nie udało się wypożyczyć książki.");
        }
        return result;
    }


    public boolean isRented(BookModel book){
        return book != null && isRented(book.getId());
    }
    public boolean isRented(int bookId){
        return (findIfRented(bookId) != null);
    }


    // try to find and retrieve last rented and not returned record
    public RentalModel findIfRented(int bookId) {
        RentalModel rentM = null;
        if(bookId < 0){
            return null;
        }

        String retDateQuery = "SELECT * FROM Rentals WHERE BookId = ? AND ReturnDate IS NULL";
        try (PreparedStatement nullStm = JDBCConnection.getConnection().prepareStatement(retDateQuery))
        {
            nullStm.setInt(1, bookId);
            ResultSet rs = nullStm.executeQuery();

            if (rs.next()){
                rentM = createFromRS(rs);
            }

            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Taki obiekt nie istnieje");
        }

        return rentM;
    }


    public boolean returnBook(int bookId) {
        boolean result = false;
        if(bookId < 0) {
            return false;
        }
        RentalModel rentM =  findIfRented(bookId);
        if(rentM != null){

//        if(isRented(bookId)){
//            ArrayList<RentalModel> rentList = getAll();
//
//            RentalModel rm = null;
//
//            for (RentalModel rentM : rentList){
//                if ((rentM.getBookId() == bookId) && rentM.getReturnDate()==null){
//                    rm = rentM;
//                }
//                System.out.println(rentM);
//            }
//            if(rm == null){
//                return false;
//            }

            boolean rentalUpdate = update(rentM);

            if (rentalUpdate){
                result = true;
            } else{
                result = false;
            }

        } else {
            System.out.println("Książka nie była ostatnio wypożyczana.");
        }

        if (result) {
            System.out.println("Książka zwrócona pomyślnie.");
        } else {
            System.out.println("Nie udało się zwrócić książki.");
        }
        return result;
    }



    // method returns a deadline for returning a book as the String type
    public String checkReturnDate(int bookId){
        RentalModel rentM = findIfRented(bookId);

        if( rentM == null){
            return "Książka nie istnieje.";
        }

        String rentDate = rentM.getRentDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

        // get 1st element of the array and find date from it
        LocalDate date = LocalDate.parse(rentDate.split(" ")[0], formatter);
        date = date.plusDays(30);
        rentDate = date.toString();

        return rentDate;
    }
}

