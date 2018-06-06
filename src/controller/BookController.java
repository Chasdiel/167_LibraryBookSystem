package controller;

import model.BookMapper;
import model.BookModel;
import model.RentalMapper;
import view.BookView;

public class BookController extends BaseController{
    private static BookController bookController;

    public enum BookActions{
        EXIT("Wróć do menu głównego"),
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
        BookMapper bMap = new BookMapper();
        BookModel newBook = BookView.addBook();   //BufferedReader dziedziczony z rodzica
        bMap.save(newBook);
    }

    private void getAll(){
        BookMapper bMap = new BookMapper();
        BookView.getAll(bMap.getAll());
    }

    private void getById(){
        BookMapper bMap = new BookMapper();
            BookView.getBook(bMap.getById(BookView.getById()));

    }
    private void rentBook(){
        RentalMapper rMap = new RentalMapper();
            int[] bookClientId = BookView.rentBook();
            rMap.rentBook(bookClientId[0], bookClientId[1]);
    }

    private void returnBook(){
        RentalMapper rMap = new RentalMapper();
        int bookId = BookView.returnBook();
        rMap.returnBook(bookId);
    }

    private void removeBook(){
        BookMapper bMap = new BookMapper();
            int bookId = BookView.removeBook();
            bMap.delete(bookId);
    }
}