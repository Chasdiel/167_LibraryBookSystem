package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public abstract class BaseController {
    public abstract void executeMenu(int option);
    protected static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));  // tylko dzieci mają do niego dostęp

    public abstract void printMenu();

}
