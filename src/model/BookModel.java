package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;


/*
Czy rzeczywiście chcemy mieć dostęp przez settery do zmiany jakichkolwiek pól?
 */
public class BookModel extends BaseModel{
    public String title;
    public String author;
    public int releaseYear;
    public int pages;

    protected BookModel(int id, String title, String author, int releaseYear, int pages) {
        super(id);
        setTitle(title);
        setAuthor(author);
        setReleaseYear(releaseYear);
        setPages(pages);
    }

    public BookModel(String title, String author, int releaseYear, int pages) {
        super(-1);
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

    public PreparedStatement saveStatement(){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("INSERT INTO Books (Title, Author, ReleaseYear, Pages) VALUES(?, ?, ?, ?)");

            prepStm.setString(1, this.title);
            prepStm.setString(2, this.author);
            prepStm.setInt(3, this.releaseYear);
            prepStm.setInt(4, this.pages);
        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }

    //TODO obsolete code
    public static boolean createBook(BookModel newBook){
        if (newBook == null){
            return false;
        }
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
            System.out.println("Książka dodana.");
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


    public static boolean delBookByID(int bookId){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("DELETE FROM Books WHERE Id= ?");
            prepStm.setInt(1, bookId);

            if (prepStm.executeUpdate() !=1){
                System.out.println("Nie udało się usunąć książki.");
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
        BookModel book = null;
        PreparedStatement getBookStm = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM Books WHERE Id = ?";
            getBookStm = JDBCConnection.getConnection().prepareStatement(query);
            getBookStm.setInt(1, bookId);

            rs = getBookStm.executeQuery();

            try {
                if (rs.next()) {
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int releaseYear = rs.getInt("ReleaseYear");
                    int pages = rs.getInt("Pages");
                    book= new BookModel(bookId, title, author, releaseYear, pages);
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
        return book;
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
                    int id = rs.getInt("Id");
                    String title = rs.getString("Title");
                    String author = rs.getString("Author");
                    int releaseYear = rs.getInt("ReleaseYear");
                    int pages = rs.getInt("Pages");
                    book = new BookModel(id, title, author, releaseYear, pages);
                    bookList.add(book);
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("Baza książek jest pusta.");
                e.printStackTrace();
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Zapytanie nie powiodło się.");
        }
        return bookList;
    }

    public static boolean rentBook(int bookId, int clientId){
        BookModel book = getBookById(bookId);
        if(book==null){
            System.out.println("Książka o takim id nie istnieje.");
            return false;
        }
        if(book.isRented()){
            System.out.println(checkReturnDate((bookId)) + ". Wybierz inną pozycję.");
            System.out.println();
            return false;
        }

        PreparedStatement insStm;
        String insertQuery = "INSERT INTO Rentals (BookId, ClientId) VALUES (?, ?) ";

        try{
            insStm = JDBCConnection.getConnection().prepareStatement(insertQuery);
            insStm.setInt(1, bookId);
            insStm.setInt(2, clientId);


            if (insStm.executeUpdate() != 1){
                System.out.println("Nie udało się utworzyć wypożyczenia.");
            }
            System.out.println("Operacja wypożyczenia zakończona pomyślnie.");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    public boolean isRented(){
        return BookModel.isRented(id);
    }

//TODO coś nie działa
    public static boolean isRented (int bookId) {
        if(!isValid(bookId)){
            return false;
        }

        String retDateQuery = "SELECT ReturnDate FROM Rentals WHERE Id = ? ORDER BY RentDate DESC LIMIT 1";
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


    public static boolean isValid(int bookId) {
        BookModel book = getBookById(bookId);
        if (book == null || bookId < 0) {
            System.out.println("Książka o takim id nie istnieje.");
            return false;
        }
        return true;
    }


    public static boolean returnBook(int bookId){
        if(!isValid(bookId)){
            return false;
        }

        if(getBookById(bookId).isRented()){
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
        System.out.println("Książka nie była ostatnio wypożyczana.");
        return false;
    }

    // method returns a deadline for returning a book as the String type
    public static String checkReturnDate(int bookId){
        if(!isValid(bookId)){
            return "";
        }

         String getRentDateQuery = "SELECT datetime(RentDate, '+1 month') as RentDate FROM Rentals WHERE BookId=? AND ReturnDate IS NULL";

        try(PreparedStatement prepStatement = JDBCConnection.getConnection().prepareStatement(getRentDateQuery))
        {
            prepStatement.setInt(1, bookId);

            ResultSet rs = prepStatement.executeQuery();
            if (rs.next()) {

                String result  = rs.getString("RentDate");
                // call addMethod function that adds +1 month and return String with date
                return "Książka wypożyczona do: " + result.split(" ")[0] ;

                } else {
                return "Książka jest aktualnie dostepna";
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Książka nie istnieje.";
    }


    @Override
    public String toString() {
        return getId() + "; " + getTitle() + "; " + getAuthor() + "; " + getReleaseYear() + "; " + getPages() ;
    }

}