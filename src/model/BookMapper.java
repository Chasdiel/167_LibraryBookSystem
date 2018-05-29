package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper extends BaseMapper <BookModel> {

    public BookMapper() {
        super("Books");
    }

    @Override
    public BookModel createFromRS(ResultSet rs) {

        try {
            int bookId = rs.getInt("Id");
            String title = rs.getString("Title");
            String author = rs.getString("Author");
            int releaseYear = rs.getInt("ReleaseYear");
            int pages = 0;
            pages = rs.getInt("Pages");
            return new BookModel(bookId, title, author, releaseYear, pages);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
