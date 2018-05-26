package controller;

import model.BookModel;
import view.BookView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookController extends BaseController{
    private static BookController bookController;

    public enum BookActions{
        ADD_BOOK("Dodaj książkę"),
        GET_ALL("Wyświetl wszystkie książki"),
        GET_BY_ID("Pobierz książkę o danym ID"),
        RENT_BOOK("Wypożycz książkę"),
        RETURN_BOOK("Zwróć książkę"),
        REMOVE_BOOK("Usuń książkę");

        private String description;
        private BookActions(String desc){
            description = desc;
        }
        @Override
        public String toString() {
            return description;
        }
    }

    public static BookController getInstance(){
        if (bookController==null){
            bookController = new BookController();
        }
        return bookController;
    }


    @Override
    public void executeMenu(int option) {
        executeMenu(BookActions.values()[option]);
    }

    // tutaj wchodzą widoki
    // tutaj wchodzą widoki
    public void executeMenu(BookActions action) {
        switch (action){
            case ADD_BOOK:
                addBook();
                break;
            case GET_ALL:
                getAll();
                break;
            case GET_BY_ID:
                getById();
                break;
            case RENT_BOOK:
                rentBook();
                break;
            case RETURN_BOOK:
                returnBook();
                break;
            case REMOVE_BOOK:
                removeBook();
                break;
        }
    }

    @Override
    public void printMenu() {
        for(BookActions a : BookActions.values()){
            System.out.println(a.ordinal() + " - " + a);
        }
    }

    private void addBook(){
        try{
            BookModel newBook = BookView.addBook(br);   //BufferedReader dziedziczony z rodzica
            BookModel.createBook(newBook);
        } catch(IOException e){
            System.out.println("Nie udało się dodać książki.");
            e.printStackTrace();
        }
        System.out.println("Książka dodana.");

    }

    private void getAll(){
        BookView.getAll(BookModel.getAll());
    }
    private void getById(){
        try{
            BookView.getBook(BookModel.getBookById(BookView.getById(br)));
        } catch (IOException e) {
            System.out.println("");
        }
    }
    private void rentBook(){
        try {
            int[] bookClientId = BookView.rentBook(br);
            BookModel.rentBook(bookClientId[0], bookClientId[1]);   //BufferedReader dziedziczony z rodzica

        } catch (IOException e) {
            System.out.println("Nie udało się wczytać danych.");
        }
    }

    private void returnBook(){
        try {
            int bookId = BookView.returnBook(br);
            BookModel.returnBook(bookId);
        } catch (IOException e) {
            System.out.println("Nie udało się wczytać danych.");
        }
    }
    private void removeBook(){
        try {
            int bookId = BookView.removeBook(br);
            BookModel.delBookByID(bookId);
        } catch (IOException e) {
            System.out.println("Nie udało się wczytać danych.");
        }
    }
}