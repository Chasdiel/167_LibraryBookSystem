package controller;

import javafx.application.Application;

public class MenuController extends BaseController {
    private BookController bookCtrl = new BookController();

    public enum BookActions{
        EXIT("Wyjdź"),
        BOOK_OPTIONS("Opcje książek"),
        CLIENT_OPTIONS("Opcje klientów"),
        EMPLOYEE_OPTIONS("Opcje pracowników");

        private String description;
        BookActions(String desc){
            description = desc;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    @Override
    public void executeMenu(int i){
        executeMenu(BookActions.values()[i]);
    }


    public void executeMenu(BookActions action) {
        switch (action){
            case EXIT:
                System.exit(0);
                break;
            case BOOK_OPTIONS:
                bookCtrl.open();
                break;
//            case EMPLOYEE_OPTIONS:
//                getById();
//                break;
//            case CLIENT_OPTIONS:
//                rentBook();
//                break;
        }
    }

    @Override
    public void printMenu() {
        for(BookActions a : BookActions.values()){
            System.out.println(a.ordinal() + " - " + a);
        }
    }
}

// wykrywanie otwartego obecnie XControllera
