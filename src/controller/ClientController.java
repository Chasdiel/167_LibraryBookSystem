package controller;

import model.ClientMapper;
import model.ClientModel;
import view.ClientView;

public class ClientController extends BaseController{

        private static ClientController clientController;

        public enum ClientActions{
            EXIT("Wróć do menu głównego"),
            ADD_CLIENT("Dodaj klienta"),
            GET_ALL("Wyświetl wszystkich klientów"),
            GET_BY_ID("Pobierz dane klienta o określonym ID"),
            REMOVE_CLIENT("Usuń klienta");

            private String description;

            ClientActions(String desc){
                description = desc;
            }

            @Override
            public String toString() {
                return description;
            }
        }


        public static ClientController getInstance(){
            if (clientController==null){
                clientController = new controller.ClientController();
            }
            return clientController;
        }


        @Override
        public void executeMenu(int option) {
            executeMenu(ClientActions.values()[option]);
        }


        // tutaj wchodzą widoki
        public void executeMenu(ClientActions action) {
            switch (action){
                case ADD_CLIENT:
                    addClient();
                    break;
                case GET_ALL:
                    getAll();
                    break;
                case GET_BY_ID:
                    getById();
                    break;
                case REMOVE_CLIENT:
                    removeClient();
                    break;
            }
        }


        @Override
        public void printMenu() {
            for(ClientActions a : ClientActions.values()){
                System.out.println(a.ordinal() + " - " + a);
            }
        }


        private void addClient(){
            ClientMapper bMap = new ClientMapper();
            ClientModel newClient = ClientView.addClient();
            bMap.save(newClient);
        }


        private void getAll(){
            ClientMapper cMap = new ClientMapper();
            ClientView.getAll(cMap.getAll());
        }


        private void getById(){
            ClientMapper cMap = new ClientMapper();
            ClientView.getClient(cMap.getById(ClientView.getById()));
        }

        private void removeClient(){
            ClientMapper cMap = new ClientMapper();
            int ClientId = ClientView.removeClient();
            cMap.delete(ClientId);
        }
}

