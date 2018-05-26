package view;

import controller.BaseController;
import model.BookModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class BookView {

    public static BookModel addBook(BufferedReader br) throws IOException{
        System.out.println("Podaj informacje o książce:");
        System.out.println("Tytuł: ");
        String title = br.readLine();
        System.out.println("Autor: ");
        String author = br.readLine();
        System.out.println("Rok wydania: ");
        int releaseYear =  Integer.parseInt(br.readLine());
        System.out.println("Ilość stron: ");
        int pages = Integer.parseInt(br.readLine());

        return new BookModel(title, author, releaseYear, pages);
    }
    public static void getAll(ArrayList<BookModel> books){
        for (BookModel book : books){
            System.out.println(book);
        }
    }

    public static int getById(BufferedReader br) throws IOException{
        System.out.println("Podaj id książki: ");
        int bookId = Integer.parseInt(br.readLine());
        return bookId;
    }

    public static void getBook(BookModel book){
        System.out.println(book);
    }

    public static int[] rentBook(BufferedReader br) throws IOException{
        System.out.println("Podaj id książki: ");
        int bookId = Integer.parseInt(br.readLine());

        System.out.println("Podaj id klienta: ");
        int clientId = Integer.parseInt(br.readLine());
        return new int[]{bookId, clientId};
    }

    public static int returnBook(BufferedReader br) throws IOException {
        System.out.println("Podaj id książki: ");
        return Integer.parseInt(br.readLine());
    }

    public static int removeBook(BufferedReader br) throws IOException {
        System.out.println("Podaj id książki: ");
        return Integer.parseInt(br.readLine());
    }
}
