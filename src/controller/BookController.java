package controller;

import model.BookModel;
import view.BookView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookController extends BaseController{
    private static BookController bookController;

    public enum BookActions{
        EXIT("Wyjdź"),
        ADD_BOOK("Dodaj książkę"),
        GET_ALL("Wyświetl wszystkie książki"),
        GET_BY_ID("Pobierz książkę o danym ID"),
        RENT_BOOK("Wypożycz książkę"),
        RETURN_BOOK("Zwróć książkę"),
        REMOVE_BOOK("Usuń książkę");

        private String description;
        BookActions(String desc){
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
            BookModel newBook = BookView.addBook();   //BufferedReader dziedziczony z rodzica
            BookModel.createBook(newBook);
    }

    private void getAll(){
        BookView.getAll(BookModel.getAll());
    }

    private void getById(){
            BookView.getBook(BookModel.getBookById(BookView.getById()));

    }
    private void rentBook(){
            int[] bookClientId = BookView.rentBook();
            BookModel.rentBook(bookClientId[0], bookClientId[1]);   //BufferedReader dziedziczony z rodzica

    }

    private void returnBook(){
            int bookId = BookView.returnBook();
            BookModel.returnBook(bookId);
    }

    private void removeBook(){
            int bookId = BookView.removeBook();
            BookModel.delBookByID(bookId);
    }
}