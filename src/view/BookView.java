package view;

import controller.BaseController;
import model.BookModel;
import utils.ConsoleRead;

import java.util.ArrayList;

public class BookView {

    public static BookModel addBook(){
        System.out.println("Podaj informacje o książce:");

        String title = ConsoleRead.readString("Tytuł: ");
        String author = ConsoleRead.readString("Autor: ");
        int releaseYear = ConsoleRead.readInt("Rok wydania: ");
        int pages= ConsoleRead.readInt("Ilość stron: ");
        if ((releaseYear ==-1) || (pages==-1)){
            return null;
        }
        return new BookModel(title, author, releaseYear, pages);
    }
    public static void getAll(ArrayList<BookModel> books){
        for (BookModel book : books){
            System.out.println(book);
        }
    }

    public static int getById(){
        System.out.println("Podaj id książki: ");
        return ConsoleRead.readInt();
    }

    public static void getBook(BookModel book){
        if(book == null){
            System.out.println("Książka o podanym id nie istnieje.");
        } else {
            System.out.println(book);
        }
    }

    public static int[] rentBook(){
        int bookId = ConsoleRead.readInt("Podaj id książki: ");
        int clientId = ConsoleRead.readInt("Podaj id klienta: ");

        return new int[]{bookId, clientId};
    }

    public static int returnBook(){
        return ConsoleRead.readInt("Podaj id książki: ");
    }

    public static int removeBook() {
        return ConsoleRead.readInt("Podaj id książki: ");
    }
}
