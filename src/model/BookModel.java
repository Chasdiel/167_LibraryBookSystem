package model;

import dbConnect.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
W tej klasie znajdują się metody indywidualne dla każdego obiektu update/save
 */
public class BookModel extends BaseModel{
    public String title;
    public String author;
    public int releaseYear;
    public int pages;

    // creates object from Database
    protected BookModel(int id, String title, String author, int releaseYear, int pages) {
        super(id);
        setTitle(title);
        setAuthor(author);
        setReleaseYear(releaseYear);
        setPages(pages);
    }

    //creates object from user
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


    public PreparedStatement updateStatement(){
        PreparedStatement prepStm = null;
        try {
            prepStm = JDBCConnection.getConnection().prepareStatement("UPDATE Books SET Title = ?, Author = ?, ReleaseYear= ?, Pages = ? WHERE Id = ?");

            prepStm.setString(1, this.title);
            prepStm.setString(2, this.author);
            prepStm.setInt(3, this.releaseYear);
            prepStm.setInt(4, this.pages);
            prepStm.setInt(5, getId());

        } catch (SQLException e){
            e.printStackTrace();
        }
        return prepStm;
    }


    @Override
    public String toString() {
        return getId() + "; " + getTitle() + "; " + getAuthor() + "; " + getReleaseYear() + "; " + getPages() ;
    }

}