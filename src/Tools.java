
import java.util.*;

public class Tools {

    // Escape sequence for reseting color background and foreground
    public static final String RESET = "\u001B[0m";

    // All Background color
    public static final String BLACK_BG = "\u001B[48;2;0;0;0m";
    public static final String RED_BG = "\u001B[48;2;255;0;0m";
    public static final String CYAN_BG = "\u001B[48;2;0;255;255m";
    public static final String MAROON_BG = "\u001B[48;2;123;17;19m";
    public static final String GOLD_BG = "\u001B[48;2;255;213;0m";
    public static final String TOPAZ_BG = "\u001B[48;2;255;191;128m";
    public static final String DARK_SIENNA_BG = "\u001B[48;2;77;38;0m";
    public static final String WHITE_BG = "\u001B[48;2;255;255;255m";
    public static final String FULVOUS_BG = "\u001B[48;2;230;155;0m";
    
    // All Foreground color
    public static final String DARK_SIENNA_FG = "\u001B[38;2;77;38;0m";
    public static final String GOLD_FG = "\u001B[38;2;255;213;0m";
    public static final String CYAN_FG = "\u001B[38;2;0;255;255m";
    public static final String GREEN_FG = "\u001B[38;2;0;255;0m";
    public static final String RED_FG = "\u001B[38;2;255;0;0m";
    public static final String MAGENTA_FG = "\u001B[38;2;255;0;255m";
    
    // This is the position of the actual program you can adjust this if you want
    public static final int POS_Y = 4;
    public static final int POS_X = 10;

    // Hashmap for the values of letters in number and numbers in letter
    public static HashMap<Character, Integer> charToInt = new HashMap<>();
    public static HashMap<Integer, Character> intToChar = new HashMap<>();

    static {
        for (int i = 0; i <= 9; i++) {
            charToInt.put((char) (i + '0'), i);
            intToChar.put(i, (char) (i + '0'));
        }
        for (int i = 10; i <= 36; i++) {
            charToInt.put((char) (i - 10 + 'A'), i);
            intToChar.put(i, (char) (i - 10 + 'A'));
        }
        charToInt.put(' ', 0);
    }

    // Method for clearing the screen
    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }

    // Method for going to specific location in console in terms of X and Y axis
    public static void gotoXY(int x, int y) {
        System.out.print("\033[" + y + ";" + x + "H");
    }

    // Method for delaying the process of the program
    public static void sleep (int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Method to print something at specific coordinates on console
    public static void printAtPosition(int posX, int posY, int delay, String str, String BG, String FG) {
        gotoXY(posX, posY);
        sleep(delay);
        System.out.printf(BG + FG + "%s" + RESET, str);
    }
    public static void printAtPosition(int posX, int posY, int delay, char c, String BG, String FG) {
        gotoXY(posX, posY);
        sleep(delay);
        System.out.printf(BG + FG + "%C" + RESET, c);
    }

    // Method to print next character or string just like normal print but with color haha
    public static void printnext (int delay, char ch, String BG, String FG){
        sleep(delay);
        System.out.printf(BG + FG + "%C" + RESET, ch);
    }
    public static void printnext (int delay, String str, String BG, String FG){
        sleep(delay);
        System.out.printf(BG + FG + "%s" + RESET, str);
    }

    // Method to add character at the front of the String
    public static String addchar(String str, char ch) {
        return ch + str;
    }

    // Method to replace a character at specific index on the String
    public static String replacechar (String string, int index, char character) {
        return string.substring(0, index) + character + string.substring(index + 1);
    }

    // Method to cut a String from the beggining to the specified index
    public static String cutString(String string, int index) {
        return string.substring(0, index + 1);
    }

    // Method to convert String with specified base to base10(decimal) number
    public static Long stringToNumber(String strnumber, int base) {
        Long number = (long) 0;
        for (int i = 0; i < strnumber.length(); i++) {
            number = number * base + charToInt.get(strnumber.charAt(i));
        }
        return number;
    }

    // Method to convert base10(decimal) number to String with specified base
    public static String numberToString (long number, int base) {
        if (number == 0) {
            return "0";
        }
        
        String result = "";

        while (number > 0) {
            int digit = (int) (number % base);
            result = addchar(result, intToChar.get( digit));
            number /= base;
        }

        return result;
    }

    // Method to get the height of the window in the windowdiv at Window class so it's relative to the input
    public static int y_division(int base, String dividend, String divisor){

        long tempdividend = 0;
        long intdivisor = stringToNumber(divisor, base);
        long qoutient = 0;
        int i = 0;
        int y_distance = 3;
        String difference = "";

        while (qoutient == 0) {
            tempdividend = stringToNumber(cutString(dividend, i), base);
            qoutient = tempdividend / intdivisor;
            i++;
        }
        i--;
        while (i < dividend.length()) {
            String product = numberToString((int)(qoutient * intdivisor), base);
            difference = numberToString(tempdividend - stringToNumber(product, base), base);  
            if (i < dividend.length() - 1) {
                tempdividend = stringToNumber(difference + dividend.charAt(i + 1), base);
                qoutient = tempdividend / intdivisor;
                y_distance += 3;
            }
            i++;  
        }
        return y_distance;
    }

    // Method to check if the input with specified base is VALID
    public static boolean isValidNumber(int base, String number) {
        String validDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, base);

        for (char digit : number.toCharArray()) {
            if (!validDigits.contains(String.valueOf(Character.toUpperCase(digit)))) {
                return false;
            }
        }
        return true;
    }

    // Just a method to print horizontal line pixel
    public static void pixel_X (int pos_X, int pos_Y, String color, int count){
        for (int i = 0; i < count; i++) {
            gotoXY(pos_X, pos_Y);
            System.out.printf(color + " " + RESET);
            pos_X++;
        }
    }

    // Just a method to print vertical line pixel
    public static void pixel_Y (int pos_X, int pos_Y, String color, int count){
        for (int i = 0; i < count; i++) {
            gotoXY(pos_X, pos_Y);
            System.out.printf(color + " " + RESET);
            pos_Y++;
        }
    }

    // Just a method to create a square pixel with specific height and width
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
