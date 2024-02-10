import java.util.*;

public class Window extends Tools{
    static Scanner scan = new Scanner(System.in);
    static String BG1 ="", FG="", BG2="";

    public static String menu(String title, String[] options, String theme){
        
        if (theme.equals("1")) {
            BG1 = BLACK_BG;
            BG2 = CYAN_BG;
            FG = CYAN_FG;    
        } else if (theme.equals("2")) {
            BG1 = MAROON_BG;
            BG2 = GOLD_BG;
            FG = GOLD_FG;
        } else if (theme.equals("3")) {
            BG1 = TOPAZ_BG;
            BG2 = DARK_SIENNA_BG;
            FG = DARK_SIENNA_FG;
        }
        clearScreen();

        pixel_X(POS_X + 1, POS_Y, BG2, 30);
        pixel_Y(POS_X, POS_Y + 1, BG2, 17);
        pixel_X(POS_X + 1, POS_Y + 18, BG2, 30);
        pixel_Y(POS_X + 31, POS_Y + 1, BG2, 17);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 17, 30);
        printAtPosition(POS_X + 10, POS_Y + 4, 0, title, BG1, FG);

        for (int i = 0; i < options.length; i++) {
            printAtPosition(POS_X + 8, POS_Y + 6 + i, 0, options[i], BG1, FG);
        }

        printAtPosition(POS_X + 5, POS_Y + 14, 0, "Enter your choice: ___", BG1, FG);
        gotoXY(POS_X + 25, POS_Y + 14);
        
        return scan.nextLine();    
    }

    public static int getbase(int current){

        int base;
        do {
            printAtPosition(POS_X + 5, POS_Y + 13, 0, "=".repeat(22), BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 14, 0, "   Current base: " + current + "     ", BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 15, 0, " Enter new base: ___      ", BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 16, 0, "=".repeat(22), BG1, FG);
            gotoXY(POS_X + 23, POS_Y + 15);
            
            while (!scan.hasNextInt()) {
                printAtPosition(POS_X + 5, POS_Y + 13, 0, "=".repeat(22), BG1, RED_FG);
                printAtPosition(POS_X + 5, POS_Y + 14, 0, " !! NOT VALID BASE !! ", BG1, RED_FG);
                printAtPosition(POS_X + 5, POS_Y + 15, 0, " Enter new base: ___     ", BG1, FG);
                printAtPosition(POS_X + 5, POS_Y + 16, 0, "=".repeat(22), BG1, RED_FG);
                gotoXY(POS_X + 23, POS_Y + 15);
                scan.next();
            }
            base = scan.nextInt();

        } while (base < 2 || base > 36);

        
        return base;
        
    }
    public static int getdelay(int current) {
        int speed;
        
            printAtPosition(POS_X + 5, POS_Y + 13, 0, "=".repeat(22), BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 14, 0, " Current delay: " + current + "     ", BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 15, 0, " Enter new delay: ___     ", BG1, FG);
            printAtPosition(POS_X + 5, POS_Y + 16, 0, "=".repeat(22), BG1, FG);
            gotoXY(POS_X + 24, POS_Y + 15);

            while (!scan.hasNextInt()) {
                printAtPosition(POS_X + 5, POS_Y + 13, 0, "=".repeat(22), BG1, RED_FG);
                printAtPosition(POS_X + 5, POS_Y + 14, 0, "!! NOT VALID DELAY !! ", BG1, RED_FG);
                printAtPosition(POS_X + 5, POS_Y + 15, 0, " Enter new delay: ___     ", BG1, FG);
                printAtPosition(POS_X + 5, POS_Y + 16, 0, "=".repeat(22), BG1, RED_FG);
                gotoXY(POS_X + 24, POS_Y + 15);
                scan.next();
            }
            speed = scan.nextInt();

        
        return speed;
    }

    public static void form(String message1, String message2, String operation, int base){
        pixel_X(POS_X + 1, POS_Y, BG2, 30);
        pixel_Y(POS_X, POS_Y + 1, BG2, 17);
        pixel_X(POS_X + 1, POS_Y + 18, BG2, 30);
        pixel_Y(POS_X + 31, POS_Y + 1, BG2, 17);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 17, 30);

        printAtPosition(POS_X + 7, POS_Y + 3, 0, "Current Base: " + base, BG1, FG);
        printAtPosition(POS_X + 6, POS_Y + 6, 0, message1, BG1, FG);
        printAtPosition(POS_X + 6, POS_Y + 8, 0, "_".repeat(20), BG1, FG);

        printAtPosition(POS_X + 15, POS_Y + 10, 0, operation, BG1, FG);
        printAtPosition(POS_X + 6, POS_Y + 12, 0, message2, BG1, FG);
        printAtPosition(POS_X + 6, POS_Y + 14, 0, "_".repeat(20), BG1, FG);
    }

    public static void windowAdd (int base, int delay){

        form("Enter first addend: ", "Enter second addend", "+", base);

        String addend1;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            addend1 = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, addend1)) {
                printAtPosition(POS_X + 6, POS_Y + 8, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 8, 1000, "_".repeat(20), BG1, FG);    
            }
        } while (!isValidNumber(base, addend1));

        String addend2;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            addend2 = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, addend2)) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            }
        } while (!isValidNumber(base, addend2));

        printAtPosition(POS_X + 15, POS_Y + 16, 0, "=", BG1, FG);

        scan.nextLine();

        int x_distance = (addend1.length() >= addend2.length() ? addend1.length() : addend2.length()) + 12;
        clearScreen();
        
        pixel_X(POS_X + 1, POS_Y, BG2, x_distance);
        pixel_Y(POS_X, POS_Y + 1, BG2, 9);
        pixel_X(POS_X + 1, POS_Y + 10, BG2, x_distance);
        pixel_Y(POS_X + x_distance + 1, POS_Y + 1, BG2, 9);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 9, x_distance);

        addition(POS_X + 6, POS_Y + 3, base, delay, addend1, addend2, BG1, FG);

        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void windowSub (int base, int delay){ 
        form("Enter Minuend: ", "Enter Subtrahend: ", "-", base);

        String minuend;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            minuend = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, minuend)) {
                printAtPosition(POS_X + 6, POS_Y + 8, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 8, 1000, "_".repeat(20), BG1, FG);  
            }
        } while (!isValidNumber(base, minuend));

        String subtrahend;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            subtrahend = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, subtrahend)) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            } else if (stringToNumber(minuend, base) < stringToNumber(subtrahend, base)) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, " MUST BE <= MINUEND ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            }

        } while (!isValidNumber(base, subtrahend) || stringToNumber(minuend, base) < stringToNumber(subtrahend, base)); 

        printAtPosition(POS_X + 15, POS_Y + 16, 0, "=", BG1, FG);

        scan.nextLine();

        int x_distance = minuend.length() + 12;
        clearScreen();
        
        pixel_X(POS_X + 1, POS_Y, BG2, x_distance);
        pixel_Y(POS_X, POS_Y + 1, BG2, 9);
        pixel_X(POS_X + 1, POS_Y + 10, BG2, x_distance);
        pixel_Y(POS_X + x_distance + 1, POS_Y + 1, BG2, 9);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 9, x_distance);

        subtraction(POS_X + 6, POS_Y + 3, base, delay, minuend, subtrahend, BG1, FG);

        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void windowMul (int base, int delay){
        form("Enter Multiplier: ", "Enter Multiplicand: ", "x", base);

        String multiplier;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            multiplier = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, multiplier) || multiplier.equals("")) {
                printAtPosition(POS_X + 6, POS_Y + 8, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 8, 1000, "_".repeat(20), BG1, FG);    
            } 
        } while (!isValidNumber(base, multiplier) || multiplier.equals(""));

        String multiplicand;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            multiplicand = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, multiplicand) || multiplicand.equals("")) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            } 
        } while (!isValidNumber(base, multiplicand) || multiplicand.equals(""));

        printAtPosition(POS_X + 15, POS_Y + 16, 0, "=", BG1, FG);

        scan.nextLine();

        int x_distance = multiplicand.length() + multiplier.length() + 12;
        int y_distance = (multiplicand.length() < multiplier.length() ? multiplicand.length() : multiplier.length()) + 11; 
        clearScreen();
        
        pixel_X(POS_X + 1, POS_Y, BG2, x_distance);
        pixel_Y(POS_X, POS_Y + 1, BG2, y_distance);
        pixel_X(POS_X + 1, POS_Y + y_distance + 1, BG2, x_distance);
        pixel_Y(POS_X + x_distance + 1, POS_Y + 1, BG2, y_distance);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, y_distance, x_distance);

        multiplication(POS_X + 6, POS_Y + 3, base, delay, multiplier, multiplicand, BG1, FG);
        
        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void windowDiv (int base, int delay){
        form("Enter Dividend: ", "Enter Divisor: ", "/", base);

        String dividend;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            dividend = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, dividend) || dividend.equals("")) {
                printAtPosition(POS_X + 6, POS_Y + 8, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 8, 1000, "_".repeat(20), BG1, FG);  
            }
        } while (!isValidNumber(base, dividend) || dividend.equals(""));

        String divisor;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            divisor = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, divisor) || divisor.equals("")) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, "    INVALID INPUT   ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            } else if (stringToNumber(dividend, base) < stringToNumber(divisor, base)) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, " MUST BE <= Dividend ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            } else if (stringToNumber(divisor, base) == 0 ) {
                printAtPosition(POS_X + 6, POS_Y + 14, 0, "     MUST BE > 0    ", BG1, RED_FG);
                printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
            }

        } while (!isValidNumber(base, divisor) || stringToNumber(dividend, base) < stringToNumber(divisor, base) || stringToNumber(divisor, base) == 0 || divisor.equals("")); 

        printAtPosition(POS_X + 15, POS_Y + 16, 0, "=", BG1, FG);

        scan.nextLine();

        clearScreen();

        int x_distance = divisor.length() + dividend.length() + divisor.length() + 16;
        int y_distance = y_division(base, dividend, divisor) + 7;
        
        pixel_X(POS_X + 1, POS_Y, BG2, x_distance);
        pixel_Y(POS_X, POS_Y + 1, BG2, y_distance);
        pixel_X(POS_X + 1, POS_Y + y_distance + 1, BG2, x_distance);
        pixel_Y(POS_X + x_distance + 1, POS_Y + 1, BG2, y_distance);

        sqrpixel(POS_X + 1, POS_Y + 1, BG1, y_distance, x_distance);

        division(POS_X + 6, POS_Y + 3, base, delay, dividend, divisor, BG1, FG);

        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void windowHelp () {
        pixel_X(POS_X + 1, POS_Y, BG2, 30);
        pixel_Y(POS_X, POS_Y + 1, BG2, 17);
        pixel_X(POS_X + 1, POS_Y + 18, BG2, 30);
        pixel_Y(POS_X + 31, POS_Y + 1, BG2, 17);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 17, 30);
        printAtPosition(POS_X + 10, POS_Y + 4, 0, "----HELP----", BG1, FG);

        printAtPosition(POS_X + 5, POS_Y + 6, 0, "-| Enter key is a key", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 7, 0, "      to proceed     ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 8, 0, "-| Only positive inte", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 9, 0, "    ger is allowed   ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 10, 0, "-| You can adjust the", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 11, 0, "      Settings       ", BG1, FG);

        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void windowAbout() {
        pixel_X(POS_X + 1, POS_Y, BG2, 30);
        pixel_Y(POS_X, POS_Y + 1, BG2, 17);
        pixel_X(POS_X + 1, POS_Y + 18, BG2, 30);
        pixel_Y(POS_X + 31, POS_Y + 1, BG2, 17);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 17, 30);
        printAtPosition(POS_X + 10, POS_Y + 4, 0, "----ABOUT---", BG1, FG);

        printAtPosition(POS_X + 5, POS_Y + 6, 0, "  Calc-Wiz is an easy", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 7, 0, "-to-use app for profe", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 8, 0, "ssionals and students", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 9, 0, " alike, offering simp", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 10, 0, "licity for all basic ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 11, 0, "math needs           ", BG1, FG);
        
        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    public static void pixel_X (int pos_X, int pos_Y, String color, int count){
        for (int i = 0; i < count; i++) {
            gotoXY(pos_X, pos_Y);
            System.out.printf(color + " " + RESET);
            pos_X++;
        }
    }
    public static void pixel_Y (int pos_X, int pos_Y, String color, int count){
        for (int i = 0; i < count; i++) {
            gotoXY(pos_X, pos_Y);
            System.out.printf(color + " " + RESET);
            pos_Y++;
        }
    }
    public static void sqrpixel (int pos_X, int pos_Y, String color, int height, int length){
        for (int i = 0; i < height; i++) {
            int temp = pos_X; 
            for (int j = 0; j < length; j++) {
                gotoXY(temp, pos_Y);
                System.out.printf(color + " " + RESET);
                temp++;
            }
            pos_Y++;
        }
    }

}
