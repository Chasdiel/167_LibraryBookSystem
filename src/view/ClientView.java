package view;

import model.ClientModel;
import utils.ConsoleRead;

import java.util.ArrayList;

public class ClientView {
    public static ClientModel addClient(){
        System.out.println("Podaj informacje o kliencie:");
        String name = ConsoleRead.readString("Nazwisko i imię: ");
        return new ClientModel(name);
    }


    public static void getAll(ArrayList<ClientModel> clients){
        for (ClientModel client : clients){
            System.out.println(client);
        }
    }


    public static int getById(){
        System.out.println("Podaj id klienta: ");
        return ConsoleRead.readInt();
    }


    public static void getClient(ClientModel client){
        if(client == null){
            System.out.println("Klient o podanym id nie istnieje.");
        } else {
            System.out.println(client);
        }
    }


    public static int removeClient() {
        return ConsoleRead.readInt("Podaj id klienta: ");
    }
}
