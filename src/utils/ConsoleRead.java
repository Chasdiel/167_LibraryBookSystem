package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleRead {
//    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner sc = new Scanner(System.in);

    public static String readString(String demand){
        if (!(demand.trim().equals(""))) {
            System.out.println(demand);
        }
        return readString();       // wywołaj metodę czytającą Stringa - bez parametru
    }

    public static String readString() {
        String answer = "";
//        try {
//            while (answer.equals("")) {
//                    answer = br.readLine().trim();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

            while (answer.equals("")) {
                answer =  sc.nextLine().trim();
            }
        return answer;
    }


    public static int readInt(String demand){
        if (!(demand.trim().equals(""))) {
            System.out.println(demand);
        }
        return readInt();       // wywołaj metodę czytającą liczby - bez parametru
    }

    public static int readInt() {
        int temp =-1;

//        while (temp ==-1) {
//            try {
//                temp = Integer.parseInt(br.readLine().trim());
//                if (!isIntValid(temp)) {
//                    temp =-1;
//                    System.out.println("Wartość musi być dodatnia.");
//                }
//            }
//            catch (NumberFormatException e){
//                System.out.println("Podaj liczbę dodatnią i całkowitą.");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
        while (temp ==-1) {
            try {
                temp = sc.nextInt();
                if (!isIntValid(temp)) {
                    temp =-1;
                    System.out.println("Wartość musi być dodatnia.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Podaj liczbę dodatnią i całkowitą.");
                e.printStackTrace();
                sc.next();
            }
        }
        return temp;
    }

    private static boolean isIntValid(int number){
        return number>= 0;
    }
}