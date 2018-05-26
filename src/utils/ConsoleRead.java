package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;

public class ConsoleRead {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static String readString(String demand){
        if(!(demand.equals(""))){
            System.out.println(demand);
        }

        String answer = "";
        while (answer.equals("")){
            try {
                answer = br.readLine();
            } catch (IOException e) {
                System.out.println("Zła wartość.");
            }
        }
        try {
            br.close();
        } catch (IOException e) {
            System.out.println("Nie udało się zamknąć BufferedReadera.");;
        }
        return answer;
    }

    public static int readInt(String demand) {
        if (!(demand.equals(""))) {
            System.out.println(demand);
        }

        String answer ="a";

    while (true) {
            try {
                answer = br.readLine();
                if((Object)Integer.parseInt(answer) !=null){
                    return Integer.parseInt(answer);
                }
            }
            catch (Exception e) {
                System.out.println("Zła wartość.");
            }
        }
    }

}

//    char ans = '2';
//                char chars[] = answer.toCharArray();
//                for(int i = 0; i< chars.length; i++){
//                    isNotNumber &= Character.isDigit(chars[i]);
//                    System.out.println(isNotNumber);
//                }
//                if(isNotNumber){
//                    System.out.println("zamieniam");
//                    return Integer.parseInt(answer);
//                }


//    public static int readInt() {
//        int out = 0;
//        boolean ok = false;
//
//        do{
//            try {
//                out = Integer.parseInt(br.readLine());
//                ok = true;
//            } catch (Exception e) {
//                System.out.println("Coś nie pykło ;(");
//            }
//        }
//        while(!ok);
//
//        return out;
//    }