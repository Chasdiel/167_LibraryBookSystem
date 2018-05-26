/*
//  Biblioteka
1. baza danych
    1a. ksiazki
    1b. klienci
    1c. pracownicy

2. zapisywanie ksiązek w DB
3. wypozyczanie książek

 */


import controller.BookController;
import model.BookModel;
import utils.ConsoleRead;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App{
    public static void main(String[] args) {
//        LibraryController libControl = new LibraryController();
//        BookModel book = BookModel.getBookById(1);
//        System.out.println(book);
//        for (BookModel i : BookModel.getAll()){
//            System.out.println(i);
//        }

        BookController bookCon = new BookController();
        bookCon.printMenu();
        int myInt = ConsoleRead.readInt("Podaj liczbę");
        System.out.println("Podana wartość to: " + myInt);




        //        bookCon.executeMenu(BookController.BookActions.ADD_BOOK);
//        bookCon.executeMenu(BookController.BookActions.GET_ALL);
//        BookModel.rentBook(2, 3);
//        BookModel.rentBook(1, 3);
//        BookModel.rentBook(4, 2);


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
