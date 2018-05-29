/*
//  Biblioteka
1. baza danych
    1a. ksiazki
    1b. klienci
    1c. pracownicy

2. zapisywanie ksiązek w DB
3. wypozyczanie książek

 */


import com.sun.org.apache.xpath.internal.SourceTree;
import controller.BookController;
import controller.MenuController;
import model.BookMapper;
import model.BookModel;
import utils.ConsoleRead;
import view.BookView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App{
    public static void main(String[] args) {
//        LibraryController libControl = new LibraryController();
//        BookModel book = BookModel.getBookById(1);
//        System.out.println(book);
//        for (BookModel i : BookModel.getAll()){
//            System.out.println(i);
//        }

//        MenuController mc = new MenuController();
//        mc.open();
//        System.out.println("Miłego dnia.");

        BookMapper bm = new BookMapper();
//        bm.save(new BookModel("W poszukiwaniu wyciętego lasu", "Marcel Proust", 1913, 14566));
        bm.getAll().forEach( (x)-> System.out.println(x));
//        System.out.println(bm.getById(6));
        bm.delByID(12);

//        System.out.println(ConsoleRead.readInt());
//        System.out.println();
//        System.out.println(ConsoleRead.readInt());



//        System.out.println(ConsoleRead.readString());
//        System.out.println();
//        System.out.println(ConsoleRead.readString());



//        bookCon.executeMenu(BookController.BookActions.ADD_BOOK);

//        bookCon.executeMenu(BookController.BookActions.valueOf("GET_BY_ID"));
//        bookCon.executeMenu(BookController.BookActions.GET_ALL);



//        libControl.menuControl();
//        ClientModel client = new ClientModel("Jan Kowalski");
//        ClientModel.createClient(client);
//        System.out.println(ClientModel.createClient(client));
//        System.out.println(ClientModel.delClientByID(3));
//        BookModel book = new BookModel("Przeminęło z wiatrem", "Jan Kowalski", 1990, 312);
//        System.out.println(BookModel.createBook(book));

    }
}

// zrobić w utilsach klasę zawierajacą bufferedReader wraz z metodamy statycznymi dla każdego typu danych
