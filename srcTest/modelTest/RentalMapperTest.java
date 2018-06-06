package modelTest;

import model.BookMapper;
import model.BookModel;
import model.RentalMapper;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class RentalMapperTest {

//    @org.junit.Test
//    public void createFromRS() {
//
//    }
//
//    @org.junit.Test
//    public void rentBook() {
//
//    }
//
//    @org.junit.Test
//    public void tryGetLastIfNotRented() {
//        RentalMapper rM = new RentalMapper();
//
//        assertEquals(false, rM.tryGetLastIfNotRented(1));
//        assertEquals(true, rM.tryGetLastIfNotRented(4));
//    }

    @org.junit.Test
    public void bookLifeCycle() {
        RentalMapper rMap = new RentalMapper();
        BookMapper bMap = new BookMapper();

        BookModel mainBook = new BookModel("Gotuj z Andrzejem","Andrzej Jarzyna", 1993, 123 );
        //create book
        bMap.save(mainBook);

        ArrayList<BookModel> list = bMap.getAll();

        // test everything but id
        BookModel testBook = list.get(list.size()-1);
        assertEquals(mainBook.getAuthor(),testBook.getAuthor());
        assertEquals(mainBook.getTitle(),testBook.getTitle());
        assertEquals(mainBook.getReleaseYear(),testBook.getReleaseYear());
        assertEquals(mainBook.getPages(),testBook.getPages());

        mainBook = testBook;

        //getId and compare
        testBook = bMap.getById(mainBook.getId());


        assertEquals(mainBook.getId(),testBook.getId());
        assertEquals(mainBook.getAuthor(),testBook.getAuthor());
        assertEquals(mainBook.getTitle(),testBook.getTitle());
        assertEquals(mainBook.getReleaseYear(),testBook.getReleaseYear());
        assertEquals(mainBook.getPages(),testBook.getPages());

        testBook = null;

        // is rented?
        assertEquals(false, rMap.isRented(mainBook));

        // rent the book
        assertEquals(true, rMap.rentBook(mainBook.getId(), 1));

        // is rented?
        assertEquals(true, rMap.isRented(mainBook));

        // return the book
        assertEquals(true, rMap.returnBook(mainBook.getId()));
        // is rented?
        assertEquals(false, rMap.isRented(mainBook));
        // delete the book
        assertEquals(true, bMap.delete(mainBook));



    }

}