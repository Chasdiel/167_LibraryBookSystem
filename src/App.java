/*
//  Biblioteka
1. baza danych
    1a. ksiazki
    1b. klienci
    1c. pracownicy

2. zapisywanie ksiązek w DB
3. wypozyczanie książek

 */


import controller.ClientController;
import controller.MenuController;

public class App{
    public static void main(String[] args) {
        ClientController cc = new ClientController();
//        cc.printMenu();


        MenuController mc = new MenuController();
        mc.open();
        System.out.println("Miłego dnia.");
//        RentalMapper rm = new RentalMapper();

//        rm.rentBook(6,4);
//        rm.returnBook(6,4);

    }
}


