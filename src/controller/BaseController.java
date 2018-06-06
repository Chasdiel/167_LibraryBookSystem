package controller;

import utils.ConsoleRead;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class BaseController {

    public abstract void executeMenu(int option);

    public abstract void printMenu();

    //poziom 0: tworzy nowy podpoziom
    //    poziom 1: zapetlone wykonanie
    public void open() {
        int option = 1;
        while (true) {
            printMenu();
            if ((option = ConsoleRead.readInt()) != 0){
                executeMenu(option);
            } else {
                break;
            }
        }
    }
}


