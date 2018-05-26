package controller;

import model.BookModel;
import model.ClientModel;
import model.EmployeeModel;
import model.RentalModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DataReader {
    private BufferedReader br;

    public DataReader(){
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public void close(){
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Nie udało się zamknąć BufferedReader");
        }
    }

    public int getInt() throws IOException,NumberFormatException {
        int option = Integer.parseInt(br.readLine());
        return option;
    }

    public BookModel readBookData() throws IOException {
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

    public ClientModel readClientData() throws IOException {
        System.out.println("Podaj informacje o kliencie:");
        System.out.println("Imię i nazwisko: ");
        String name = br.readLine();

        return new ClientModel(name);
    }

    public EmployeeModel readEmployeeData() throws IOException {
        System.out.println("Podaj imię i nazwisko:");
        String name = br.readLine();
//        System.out.println("Podaj login: ");
//        String login = br.readLine();
//        System.out.println("Podaj hasło: ");
//        String password = br.readLine();

        return new EmployeeModel(name);
    }

    public RentalModel readRentalData() throws IOException {
        System.out.println("Podaj id książki");
        int bookId = Integer.parseInt(br.readLine());
        System.out.println("Podaj id klienta: ");
        int clientId = Integer.parseInt(br.readLine());

        return new RentalModel(bookId, clientId);
    }


}
