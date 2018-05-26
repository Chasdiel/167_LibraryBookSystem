package model;

import db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*
Czy rzeczywiście chcemy mieć dostęp przez settery do zmiany jakichkolwiek pól?
 */
public class BookModel {
    private int bookId =-1;     // if bookId is -1 the book doesn't exist
    public String title;
    public String author;
    public int releaseYear;
    public int pages;

    private BookModel(int bookId, String title, String author, int releaseYear, int pages) {
        setBookId(bookId);
        setTitle(title);
        setAuthor(author);
        setReleaseYear(releaseYear);
        setPages(pages);
    }

    public BookModel(String title, String author, int releaseYear, int pages) {
        setTitle(title);
        setAuthor(author);
        setReleaseYear(releaseYear);
        setPages(pages);
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public int getBookId() {
        return bookId;
    }

    private void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public static boolean createBook(BookModel newBook){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Books (Title, Author, ReleaseYear, Pages) VALUES(?, ?, ?, ?)");
            prepStm.setString(1, newBook.title);
            prepStm.setString(2, newBook.author);
            prepStm.setInt(3, newBook.releaseYear);
            prepStm.setInt(4, newBook.pages);

            if (prepStm.executeUpdate() !=1){
                System.out.println("Nie udało się utworzyć książki.");
            }
            return true;
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
        return false;
    }
    public static boolean delBookByID(int id){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Books WHERE BookId= ?");
            prepStm.setInt(1, id);

            if (prepStm.executeUpdate() !=1){
                System.out.println("Nie udało się utworzyć książki.");
            }
            return true;
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
        return false;
    }


    public static BookModel getBookById(int bookId){
        PreparedStatement getBookStm = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Books WHERE BookId = ?";
            getBookStm = JDBCConnection.getConnection().prepareStatement(query);
            getBookStm.setInt(1, bookId);

            rs = getBookStm.executeQuery();

            try {
                if (rs.next()) {
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int releaseYear = rs.getInt("ReleaseYear");
                    int pages = rs.getInt("Pages");
                    return new BookModel(bookId, title, author, releaseYear, pages);
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
        return null;
    }

    public static ArrayList<BookModel> getAll(){
        PreparedStatement getBookStm = null;
        ResultSet rs = null;
        BookModel book = null;
        ArrayList<BookModel> bookList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Books";
            getBookStm = JDBCConnection.getConnection().prepareStatement(query);
            rs = getBookStm.executeQuery();

            try {
                while (rs.next()){
                    int bookId = rs.getInt("BookId");
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int releaseYear = rs.getInt("ReleaseYear");
                    int pages = rs.getInt("Pages");
                    book = new BookModel(bookId, title, author, releaseYear, pages);
                    bookList.add(book);
                }
                return bookList;

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Baza książek jest pusta.");
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Zapytanie nie powiodło się.");
        }
        return null;
    }

    public static boolean rentBook(int bookId, int clientId){
        BookModel book = getBookById(bookId);
        if(book==null){
            System.out.println("Książka o takim id nie istnieje.");
            return false;
        }
        if(book.isRented()){
            return false;
        }

//        String date = String.valueOf(LocalDate.now());
        PreparedStatement insStm;
        String insertQuery = "INSERT INTO Rentals (BookId, ClientId) VALUES (?, ?) ";

        try{
            insStm = JDBCConnection.getConnection().prepareStatement(insertQuery);
            insStm.setInt(1, bookId);
            insStm.setInt(2, clientId);


            if (insStm.executeUpdate() != 1){
                System.out.println("Nie udało się utworzyć wypożyczenia.");
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

//    public boolean rentBook (BookModel book, ClientModel client){
////        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
//        return false;
//    }

    public boolean isRented(){
        return BookModel.isRented(bookId);
    }

    public static boolean isRented (int bookId) {

        String retDateQuery = "SELECT ReturnDate FROM Rentals WHERE BookId = ? ORDER BY RentDate DESC LIMIT 1";
        try (PreparedStatement nullStm =JDBCConnection.getConnection().prepareStatement(retDateQuery))
        {
            nullStm.setInt(1, bookId);
            ResultSet rs = nullStm.executeQuery();

            if(!rs.isClosed()){
                rs.getString("ReturnDate");

                if (rs.wasNull()) {
                    System.out.println("Is rented: true ");
                    rs.close();
                    return true;
                }
            }
            System.out.println("Is rented: false");

            rs.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e){
            System.out.println("Taki obiekt nie istnieje");
        }

        return false;
    }

    public boolean isValid() {
        return bookId >= 0;
    }

    public static boolean returnBook(int bookId){
        BookModel book = getBookById(bookId);
        if(book==null){
            System.out.println("Książka o takim id nie istnieje.");
            return false;
        }
        if(book.isRented()){
            String date = String.valueOf(LocalDate.now());
            PreparedStatement insStm;
            String insertQuery = "UPDATE Rentals SET ReturnDate = ? WHERE BookId=? AND ReturnDate IS NULL";

            try{
                insStm = JDBCConnection.getConnection().prepareStatement(insertQuery);
                insStm.setString(1, date);
                insStm.setInt(2, bookId);

                if (insStm.executeUpdate() != 1){
                    System.out.println("Nie udało się zwrócić książki.");
                }
                System.out.println("Książka zwrócona.");
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Nie udało się zwrócić książki.");
        return false;
    }


    @Override
    public String toString() {
        return getBookId() + "; " + getTitle() + "; " + getAuthor() + "; " + getReleaseYear() + "; " + getPages() ;
    }

}