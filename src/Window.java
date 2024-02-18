import java.util.*;

public class Window extends Tools{
    static Scanner scan = new Scanner(System.in);

    public static String BG1 = BLACK_BG, FG = CYAN_FG, BG2 = CYAN_BG;

    //This method show the loading at the start 
    public static void loading () throws InterruptedException { 
        int delay = 20;
        int barlength = 20;

        for (int i = 0; i <= 100 ; i++) {
            int pos = barlength * i / 100;

            printAtPosition(POS_X, POS_Y + 8, 0, "-=<{|", RESET, MAGENTA_FG);

            for (int j = 0; j < barlength; j++) {
                if (j < pos ) {
                    printnext(0, '#', RESET, CYAN_FG);
                } else if (j == pos) {
                    printnext(0, '>', RESET, CYAN_FG);
                } else {
                    printnext(0, ' ', RESET, CYAN_FG);
                }
            }

            printnext(0, "|}>=-", RESET, MAGENTA_FG);

            printAtPosition(POS_X + 7, POS_Y + 10, 0, "Loading... ", RESET, MAGENTA_FG);
            printnext(0, i + "%", RESET, CYAN_FG);
            Thread.sleep(delay);
            clearScreen();
        }
    }

    // This is the method for drawing the window menu
    public static String menu(String title, String[] options, String theme){
        clearScreen();

        drawwindow(title);

        for (int i = 0; i < options.length; i++) {
            printAtPosition(POS_X + 8, POS_Y + 6 + i, 0, options[i], BG1, FG);
        }

        printAtPosition(POS_X + 5, POS_Y + 14, 0, "Enter your choice: ___", BG1, FG);
        gotoXY(POS_X + 25, POS_Y + 14);
        
        return scan.nextLine();    
    }

    // Method for getting the new base in Settings
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
    
    // Method for getting the new delay in Settings
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

    // Helping method for creating a window with Title
    public static void drawwindow(String title){
        pixel_X(POS_X + 1, POS_Y, BG2, 30);
        pixel_Y(POS_X, POS_Y + 1, BG2, 17);
        pixel_X(POS_X + 1, POS_Y + 18, BG2, 30);
        pixel_Y(POS_X + 31, POS_Y + 1, BG2, 17);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 17, 30);
        printAtPosition(POS_X + 10, POS_Y + 4, 0, title, BG1, FG);
    }

    // This method display the current base and serves as a form for user
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

    // Just an error message for user
    public static void printerror1(String message){
        printAtPosition(POS_X + 6, POS_Y + 8, 0, message, BG1, RED_FG);
        printAtPosition(POS_X + 6, POS_Y + 8, 1000, "_".repeat(20), BG1, FG);
    }
    public static void printerror2 (String message){
        printAtPosition(POS_X + 6, POS_Y + 14, 0, message, BG1, RED_FG);
        printAtPosition(POS_X + 6, POS_Y + 14, 1000, "_".repeat(20), BG1, FG); 
    }

    // Window for Help option and About option
    public static void windowHelp () {
        drawwindow("----HELP----");

        printAtPosition(POS_X + 5, POS_Y + 6, 0, "-| Enter key is a key", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 7, 0, "      to proceed     ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 8, 0, "-| Only positive inte", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 9, 0, "    ger is allowed   ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 10, 0, "-| You can adjust the", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 11, 0, "      Settings       ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 12, 0, "-| You can input below", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 13, 0, "    (2^63) - 1  for  ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 14, 0, "       division     ", BG1, FG);
        
        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }
    public static void windowAbout() {
        drawwindow("----ABOUT---");

        printAtPosition(POS_X + 5, POS_Y + 6, 0, "  Calc-Wiz is an easy", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 7, 0, "-to-use app for profe", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 8, 0, "ssionals and students", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 9, 0, " alike, offering simp", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 10, 0, "licity for all basic ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 11, 0, "math needs           ", BG1, FG);
        
        printAtPosition(POS_X + 5, POS_Y + 13, 0, "BY: AVILES, BERCILIA ", BG1, FG);
        printAtPosition(POS_X + 5, POS_Y + 14, 0, "       AND ESTRABO   ", BG1, FG);
        
        gotoXY(POS_X + 2, POS_Y + 2);
        printAtPosition(POS_X + 1, POS_Y + 1, 1000, "<-", BG1, FG);
        scan.nextLine();
    }

    // This method is just a pixel art penguin at the end of the program
    public static void pixelart() {
        clearScreen();

        int row = 19;
        int column = 16;

        int[][] pattern = {
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0},
            {1, 1, 1, 1, 2, 1, 1, 2, 2, 2, 2, 1, 1, 0, 0, 0},
            {1, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 1, 2, 1, 0, 0},
            {1, 1, 1, 2, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 0, 0},
            {1, 1, 1, 2, 2, 1, 1, 2, 2, 3, 3, 1, 1, 0, 0, 0},
            {1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 0, 0, 0},
            {1, 1, 1, 1, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 0, 0},
            {1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 1, 1, 1, 0},
            {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},
            {1, 1, 2, 1, 1, 1, 2, 2, 2, 2, 2, 2, 1, 1, 1, 1},
            {1, 1, 2, 2, 1, 1, 2, 2, 2, 2, 2, 2, 1, 0, 1, 1},
            {0, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0},
            {0, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0, 0, 1, 4, 4, 4, 1, 1, 4, 4, 4, 1, 0, 0, 0, 0},
            {0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0},
        };

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (pattern[i][j] == 0) {
                    pixel_X(POS_X + j*2, POS_Y + i, RESET, 2);
                } else if (pattern[i][j] == 1) {
                    pixel_X(POS_X + j*2, POS_Y + i, BLACK_BG, 2);
                } else if (pattern[i][j] == 2) {
                    pixel_X(POS_X + j*2, POS_Y + i, WHITE_BG, 2);
                } else if (pattern[i][j] == 3) {
                    pixel_X(POS_X + j*2, POS_Y + i, GOLD_BG, 2);
                } else if (pattern[i][j] == 4) {
                    pixel_X(POS_X + j*2, POS_Y + i, FULVOUS_BG, 2);
                }  
            }
        }
    }


    // ---------------------------------------------
    //    Below is the window for arithmetic
    //    it asks the user for the input and it also
    //    makes sure that the input is valid before
    //    proceeding to the algorithm 
    // -----------------------------------------------

    public static void windowAdd (int base, int delay){

        form("Enter first addend: ", "Enter second addend", "+", base);

        String addend1;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            addend1 = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, addend1) || addend1.equals("")) {
                printerror1("    INVALID INPUT   "); 
            }
        } while (!isValidNumber(base, addend1) || addend1.equals(""));

        String addend2;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            addend2 = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, addend2) || addend2.equals("")) {
                printerror2("    INVALID INPUT   ");
            }
        } while (!isValidNumber(base, addend2) || addend2.equals(""));

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
            if (!isValidNumber(base, minuend) || minuend.equals("")) {
                printerror1("    INVALID INPUT   "); 
            }
        } while (!isValidNumber(base, minuend) || minuend.equals(""));

        String subtrahend;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            subtrahend = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, subtrahend) || subtrahend.equals("")) {
                printerror2("    INVALID INPUT   ");
            } else if (stringToNumber(minuend, base) < stringToNumber(subtrahend, base)) {
                printerror2(" MUST BE <= MINUEND "); 
            }
        } while (!isValidNumber(base, subtrahend) || stringToNumber(minuend, base) < stringToNumber(subtrahend, base) || subtrahend.equals("")); 

        printAtPosition(POS_X + 15, POS_Y + 16, 0, "=", BG1, FG);

        scan.nextLine();

        int x_distance = minuend.length() + 12;
        clearScreen();
        
        pixel_X(POS_X + 1, POS_Y, BG2, x_distance);
        pixel_Y(POS_X, POS_Y + 1, BG2, 9);
        pixel_X(POS_X + 1, POS_Y + 10, BG2, x_distance);
        pixel_Y(POS_X + x_distance + 1, POS_Y + 1, BG2, 9);
        sqrpixel(POS_X + 1, POS_Y + 1, BG1, 9, x_distance);

        if (minuend.equals(subtrahend)) {
            printAtPosition(POS_X + 6, POS_Y + 3 + 1, 0, " ".repeat(2) + minuend, BG1, FG);
            printAtPosition(POS_X + 6, POS_Y + 3 + 2, 0, "- " + " ".repeat(minuend.length() - subtrahend.length()) + subtrahend, BG1, FG);
            printAtPosition(POS_X + 6, POS_Y + 3 + 3, 0, "-".repeat(minuend.length() + 2), BG1, FG);
            printAtPosition(POS_X + 6 + 2 + minuend.length() - 1, POS_Y + 3 + 4, delay, "0", BG1, FG);
        } else {
            subtraction(POS_X + 6, POS_Y + 3, base, delay, minuend, subtrahend, BG1, FG);
        }

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
            if (!isValidNumber(base, multiplier) || multiplier.equals("")){
                printerror1("    INVALID INPUT   ");    
            } 
        } while (!isValidNumber(base, multiplier) || multiplier.equals(""));

        String multiplicand;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            multiplicand = scan.nextLine().toUpperCase();
            if (!isValidNumber(base, multiplicand) || multiplicand.equals("")) {
                printerror2("    INVALID INPUT   ");
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

        long numdividend, numdivisor;
    
        String dividend;
        do {
            gotoXY(POS_X + 6, POS_Y + 8);
            dividend = scan.nextLine().toUpperCase();
            numdividend = stringToNumber(dividend, base);

            if (!isValidNumber(base, dividend) || dividend.equals("")) {
                printerror1("    INVALID INPUT   "); 
            } else if (dividend.equals("0")) {
                printerror1("   0/ANYTHING IS 0  ");
            } else if (!(numdividend > 1 && numdividend < Long.MAX_VALUE)) {
                printerror1("Too big make it less");
            }
        } while (!isValidNumber(base, dividend) || dividend.equals("") || dividend.equals("0") || !(numdividend > 1 && numdividend < Long.MAX_VALUE));

        String divisor;
        do {
            gotoXY(POS_X + 6, POS_Y + 14);
            divisor = scan.nextLine().toUpperCase();

            numdivisor = stringToNumber(divisor, base);

            if (!isValidNumber(base, divisor) || divisor.equals("")) {
                printerror2("    INVALID INPUT   "); 
            } else if (numdividend < numdivisor) {
                printerror2(" MUST BE <= Dividend ");
            } else if (numdivisor == 0 ) {
                printerror2("     MUST BE > 0    ");
            } else if (!(numdivisor > 1 && numdivisor < Long.MAX_VALUE)) {
                printerror2("Too big make it less"); 
            }
        } while (!isValidNumber(base, divisor) || numdividend < numdivisor || numdivisor == 0 || divisor.equals("") || !(numdivisor > 1 && numdivisor < Long.MAX_VALUE)); 

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


    // ------------------------------------------------
    //   Below is the Algorithm of the program
    //   the algorithm solves the basic arithmetic with
    //   SOLUTION like in elementary school
    //  ------------------------------------------------
    
    public static void addition(int pos_X, int pos_Y, int base, int delay, String addend1, String addend2, String BG, String FG){
        String longest = addend1.length() >= addend2.length() ? addend1 : addend2;
        String shortest = addend1.length() < addend2.length() ? addend1 : addend2;

        int lendiff = longest.length() - shortest.length();

        printAtPosition(pos_X, pos_Y + 1, 0, " ".repeat(2) + longest, BG, FG);
        printAtPosition(pos_X, pos_Y + 2, 0, "+ " + " ".repeat(lendiff) + shortest, BG, FG);
        printAtPosition(pos_X, pos_Y + 3, 0, "-".repeat(longest.length() + 2), BG, FG);

        int carry = 0;

        for (int i = longest.length() - 1; i >= 0; i--) {
            int lvalue = charToInt.get(longest.charAt(i));
            int svalue = (i - lendiff) >= 0 ? charToInt.get(shortest.charAt(i - lendiff)) : 0;
            int sum = lvalue + svalue + carry;

            carry = sum / base;

            if (carry != 0) {
                if (i == 0) {
                    printAtPosition(pos_X + 1, pos_Y + 4, delay, intToChar.get(carry), BG, FG);
                } else {
                    printAtPosition(pos_X + 1 + i, pos_Y, delay, intToChar.get(carry), BG, FG);
                }
            }
            printAtPosition(pos_X + 2 + i, pos_Y + 4, delay, intToChar.get(sum % base), BG, FG);
        }
    }

    public static void subtraction(int pos_X, int pos_Y, int base, int delay, String minuend, String subtrahend, String BG, String FG){
        int lendiff = minuend.length() - subtrahend.length();

        printAtPosition(pos_X, pos_Y + 1, 0, " ".repeat(2) + minuend, BG, FG);
        printAtPosition(pos_X, pos_Y + 2, 0, "- " + " ".repeat(lendiff) + subtrahend, BG, FG);
        printAtPosition(pos_X, pos_Y + 3, 0, "-".repeat(minuend.length() + 2), BG, FG);

        for (int i = minuend.length() - 1; i >= 0; i--) {
            int minvalue = charToInt.get(minuend.charAt(i));
            int subvalue = (i-lendiff) >= 0 ? charToInt.get(subtrahend.charAt(i - lendiff)) : 0;
            int difference = minvalue - subvalue;

            if (difference < 0) {
                if (minuend.charAt(i - 1) == '0') {
                    int j = 0;
                    while (minuend.charAt(i - j - 1) == '0') {
                        j++;
                    }
                    minuend = replacechar(minuend, i - j - 1, intToChar.get(charToInt.get(minuend.charAt(i - j - 1)) - 1));
                    printAtPosition(pos_X + 1 + i - j, pos_Y, delay, intToChar.get(charToInt.get(minuend.charAt(i - j - 1))), BG, FG);
                    printAtPosition(pos_X + 1 + i - j, pos_Y + 1, delay, "_", BG, FG);

                    while (j != 0) {
                        j--;
                        minuend = replacechar(minuend, i - j - 1, intToChar.get(base - 1));
                        printAtPosition(pos_X + 1 + i - j, pos_Y, delay, intToChar.get(charToInt.get(minuend.charAt(i - j - 1))), BG, FG);
                        printAtPosition(pos_X + 1 + i - j, pos_Y + 1, delay, "_", BG, FG);
                    }
                    difference += base;
                    printAtPosition(pos_X + 2 + i, pos_Y + 4, delay, intToChar.get(difference), BG, FG);
                } else {
                    difference += base;
                    minuend = replacechar(minuend, i - 1, intToChar.get(charToInt.get(minuend.charAt(i - 1)) - 1));

                    printAtPosition(pos_X + 1 + i, pos_Y, delay, intToChar.get(charToInt.get(minuend.charAt(i - 1))), BG, FG);
                    printAtPosition(pos_X + 1 + i, pos_Y + 1, delay, "_", BG, FG);
                    printAtPosition(pos_X + 2 + i, pos_Y + 4, delay, intToChar.get(difference), BG, FG);
                }
            } else {
                if (!(i == 0 && difference == 0)) {
                    printAtPosition(pos_X + 2 + i, pos_Y + 4, delay, intToChar.get(difference), BG, FG);
                }
            }
        }
    }
    
    public static void multiplication(int pos_X, int pos_Y, int base, int delay, String multiplier, String multiplicand, String BG, String FG){
        String longest = multiplier.length() >= multiplicand.length() ? multiplier : multiplicand;
        String shortest = multiplier.length() < multiplicand.length() ? multiplier : multiplicand;

        String[] product = new String[shortest.length()];
        product[0] = "";
        for (int i = 1; i < product.length; i++) {
            product[i] = addchar(product[i-1], ' ');
        }

        printAtPosition(pos_X + shortest.length() + 2, pos_Y + 1, 0, longest, BG, FG);
        printAtPosition(pos_X + shortest.length(), pos_Y + 2, 0, "x " + " ".repeat(longest.length() - shortest.length()) + shortest, BG, FG);
        printAtPosition(pos_X + shortest.length(), pos_Y + 3, 0, "-".repeat(longest.length() + 2), BG, FG);

        for (int i = 0; i < shortest.length(); i++) {
            int carry = 0;
            printAtPosition(pos_X + shortest.length() + 1, pos_Y, delay, " ".repeat(longest.length()), BG, FG);
            for (int j = longest.length() - 1; j >= 0; j--) {
                int lvalue = charToInt.get(longest.charAt(j));
                int svalue = charToInt.get(shortest.charAt(shortest.length() - 1 - i));
                int answer = lvalue * svalue + carry;

                carry = answer / base;

                if (carry != 0) {
                    if (j == 0) {
                        printAtPosition(pos_X + shortest.length() + j - i + 1, pos_Y + i + 5, delay, intToChar.get(carry), BG, FG);
                    } else {
                        printAtPosition(pos_X + shortest.length() + j + 1, pos_Y, delay, intToChar.get(carry), BG, FG);
                    }
                }

                printAtPosition(pos_X + shortest.length() + j - i + 2, pos_Y + i + 5, svalue == 0 ? 0 : delay, intToChar.get(answer % base), BG, FG);
                product[i] = addchar(product[i], intToChar.get(answer % base));

                if (carry != 0 && j == 0) {
                    product[i] = addchar(product[i], intToChar.get(carry));
                }
            }
        }

        printAtPosition(pos_X, pos_Y + shortest.length() + 4, delay, "+", BG, FG);
        printAtPosition(pos_X, pos_Y + shortest.length() + 5, delay, "-".repeat(longest.length() + shortest.length() + 2), BG, FG);

        int maxsize = 0;
        
        for (int i = 0; i < product.length; i++) {
            if (product[i].length() > maxsize) {
                maxsize = product[i].length();
            }
        }

        for (int i = 0; i < product.length; i++) {
            int tempsize = product[i].length();
            for (int j = 0; j < maxsize - tempsize; j++) {
                product[i] = addchar(product[i], ' ');
            }
        }

        int carry = 0;

        for (int i = 0; i < maxsize; i++) {
            int sum = 0;

            for (int j = 0; j < product.length; j++) {
                sum += charToInt.get(product[j].charAt(maxsize - 1 -i));
            }

            sum += carry;
            carry = sum / base;

            if (carry != 0) {
                if (i == maxsize - 1) {
                    printAtPosition(pos_X + longest.length() + shortest.length() - i, pos_Y + shortest.length() + 6, delay, intToChar.get(carry), BG, FG);
                } else {
                    printAtPosition(pos_X + longest.length() + shortest.length() - i, pos_Y + 4, delay, intToChar.get(carry), BG, FG);
                }
            }

            printAtPosition(pos_X + longest.length() + shortest.length() - i + 1, pos_Y + shortest.length() + 6, delay, intToChar.get(sum % base), BG, FG);
        }
    } 

    public static void division(int pos_X, int pos_Y, int base, int delay, String dividend, String divisor, String BG, String FG){
        printAtPosition(pos_X + divisor.length(), pos_Y + 1, 0, " |-" + "-".repeat(dividend.length()), BG, FG);
        printAtPosition(pos_X, pos_Y + 2, 0, divisor + " | " + dividend, BG, FG);

        long tempdividend = 0;
        long intdivisor = stringToNumber(divisor, base);
        long qoutient = 0;
        int i = 0;
        int y_distance = pos_Y + 3;
        int x_distance = pos_X + divisor.length() + 1;
        String difference = "";

        while (qoutient == 0) {
            tempdividend = stringToNumber(cutString(dividend, i), base);
            qoutient = tempdividend / intdivisor;
            i++;
        }

        i--;

        while (i < dividend.length()) {
            printAtPosition(pos_X + divisor.length() + 3 + i, pos_Y, delay, intToChar.get((int)qoutient), BG, FG);
            String product = numberToString((qoutient * intdivisor), base);

            int lendiff = numberToString(tempdividend, base).length() - product.length();

            printAtPosition(x_distance, y_distance, delay, "- " + " ".repeat(lendiff) + product, BG, FG);
            printAtPosition(x_distance, y_distance + 1, delay, "-".repeat(numberToString(tempdividend, base).length() + 2), BG, FG);

            difference = numberToString(tempdividend - stringToNumber(product, base), base);
            int lendiff2 = numberToString(tempdividend, base).length() - difference.length();

            printAtPosition(x_distance, y_distance + 2, delay, " ".repeat(lendiff2 + 2) + difference, BG, FG);
            x_distance += lendiff2;

            if (i < dividend.length() - 1) {
                printnext(delay + delay, dividend.charAt(i + 1), BG, FG);
                tempdividend = stringToNumber(difference + dividend.charAt(i + 1), base);
                qoutient = tempdividend / intdivisor;
                y_distance += 3;
            }
            i++;
            if (stringToNumber(difference, base) == 0) {
                x_distance++;
            }
        }

        printAtPosition(pos_X + divisor.length() + 3 + i, pos_Y, delay, " r." + difference, BG, FG);
    }

}
