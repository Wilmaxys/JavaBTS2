package game.tools;

import java.util.Scanner;

public class Keyboard {

    public static int getInt(){
        Scanner scanner = new Scanner(System.in);
        int returnInt = -1;

        try {
            returnInt = scanner.nextInt();
        }
        catch (NumberFormatException e){
            System.out.println("La saisie n'est pas un nombre valide, veuillez saisir un nombre entier\n");
        }
        finally {
//            scanner.close();
            return returnInt;
        }
    }

    public static String getString(){
        Scanner scanner = new Scanner(System.in);
        String returnString = scanner.nextLine();
//
//        scanner.close();
        return returnString;
    }

}
