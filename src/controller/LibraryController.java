package controller;

import model.BookModel;
import model.ClientModel;
import model.EmployeeModel;

import java.io.IOException;

public class LibraryController {
    public static final int EXIT = 0;
    public static final int RENT_BOOK = 1;
    public static final int RETURN_BOOK = 2;
    public static final int ADD_BOOK = 3;
    public static final int ADD_CLIENT = 4;
    public static final int ADD_EMPLOYEE = 5;
    public static final int REMOVE_BOOK = 6;
    public static final int REMOVE_CLIENT = 7;
    public static final int REMOVE_EMPLOYEE = 8;

    private DataReader dataReader;

    public LibraryController(){
        dataReader = new DataReader();
    }

    public void menuControl(){
        int option;

        printOptions();

        try{while(( option = dataReader.getInt()) !=EXIT){
            switch(option){
                case ADD_BOOK:
                    BookModel.createBook(dataReader.readBookData());
                    break;
                case ADD_CLIENT:
                    ClientModel.createClient(dataReader.readClientData());
                    break;
                case ADD_EMPLOYEE:
                    EmployeeModel.createEmployee(dataReader.readEmployeeData());
                    break;
                case RENT_BOOK:
//                    rentBook(dataReader.readRentalData());
//                    System.out.println(RentalModel.isRented(1));
                    break;
                case REMOVE_BOOK:
                    System.out.println("Usunąłbym książkę, gdybym umiał.");
                    break;
                case REMOVE_CLIENT:
                    System.out.println("Usunąłbym klienta, gdybym umiał.");
                    break;
                case REMOVE_EMPLOYEE:
                    System.out.println("Usunąłbym pracownika, gdybym umiał.");
                    break;
                default:
                    System.out.println("Nie ma takiej opcji, wprowadź ponownie:");
            }
            printOptions();
        }

        } catch (IOException e) {
            System.out.println("Błąd przyjmowania wartości.");
        } catch (NumberFormatException e){
            System.out.println("Nie podałeś liczby!");
            menuControl();         // jak zrobić, żeby to działało w loopie nawet po rzuceniu wyjątkiem?
        }

    }

    private void printOptions() {
        System.out.println("Wybierz opcję:" );
        System.out.println(EXIT + " - Wyjdź");
        System.out.println(RENT_BOOK + " - Wypożycz książkę.");
        System.out.println(RETURN_BOOK + " - Oddaj ksiażkę.");
        System.out.println(ADD_BOOK + " - Dodaj książkę do bibiloteki.");
        System.out.println(ADD_CLIENT + " - Dodaj klienta biblioteki.");
        System.out.println(ADD_EMPLOYEE + " - Dodaj pracownika biblioteki.");

        System.out.println(REMOVE_BOOK + " - Usuń książkę z blibioteki.");
        System.out.println(REMOVE_CLIENT + " - Usuń klienta z bliblioteki.");
        System.out.println(REMOVE_EMPLOYEE + " - Usuń pracownika z biblioteki.");
    }
    private void printOptions1(){
        System.out.println(RENT_BOOK + " - Wypożycz książkę.");
        //tu przy case RENT_BOOK:
            //wyświetl funkcje subMenu();

        System.out.println(RETURN_BOOK + " - Oddaj ksiażkę.");
        System.out.println(ADD_BOOK + " - Dodaj książkę do bibiloteki.");
    }

}
