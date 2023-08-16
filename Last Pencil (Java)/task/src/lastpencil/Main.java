package lastpencil;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String name1 = "John";
        String name2 = "Jack";
        String input = "";
        System.out.println("How many pencils would you like to use:");

        int nPencils = getInitialPencilInput();
        String currentPlayer = getPlayerNameInput(name1, name2);
        System.out.println(pencilString(nPencils));

        while (true) {

            System.out.println(currentPlayer + "'s turn");
            int tempPencils = 0;

            while (true){
                if (currentPlayer.equals(name2)) {
                    tempPencils = getBestPencilChoice(nPencils);
                    System.out.println(tempPencils);
                } else {
                    tempPencils = getPencilInput();
                }
                if (tempPencils <= nPencils) break;
                System.out.println("Too many pencils were taken");
            }

            nPencils = nPencils - tempPencils;

            currentPlayer = currentPlayer.equals(name1) ? name2 : name1;
            if (nPencils <= 0) {
                break;
            }

            System.out.println(pencilString(nPencils));

        }

        System.out.println(currentPlayer + " won");
    }

    private static int getBestPencilChoice(int nPencils) {
        return (nPencils - 1) % 4  == 0 ? 1 : (nPencils - 1) % 4;
    }

    private static int getPencilInput() {
        Scanner scan = new Scanner(System.in);
        int toReturn = -1;
        while (true) {
            String input = scan.next();

            if (input.matches("[1-3]")) {
                return Integer.valueOf(input);
            } else {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        }
    }

    private static String getPlayerNameInput(String name1, String name2) {
        Scanner scan = new Scanner(System.in);
        String toReturn = "";

        while (true) {
            System.out.println("Who will be the first ("+ name1 +", "+ name2 +"):");

            String str = scan.next();

            if(str.equals(name1) || str.equals(name2)) {
                toReturn = str;
                break;
            }
            System.out.println("Choose between '"+ name1 +"' and '" + name2 + "'");

        }

        return toReturn;
    }

    static String pencilString(int n) {
        return new String(new char[n]).replace("\0", "|");
    }

    static int getInitialPencilInput() {
        Scanner scan = new Scanner(System.in);
        int nPencils = -1;
        while (true) {
            String str = scan.nextLine();
            if (str.matches("-?\\d+")) {
                nPencils = Integer.valueOf(str);
                if (nPencils < 1) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                break;
            }
            System.out.println("The number of pencils should be numeric");
        }
        return nPencils;
    }
}
