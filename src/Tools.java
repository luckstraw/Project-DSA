
import java.util.*;


public class Tools {
    public static final String RESET = "\u001B[0m";

    public static final String BLACK_BG = "\u001B[48;2;0;0;0m";
    public static final String RED_BG = "\u001B[48;2;255;0;0m";
    public static final String CYAN_BG = "\u001B[48;2;0;255;255m";
    public static final String MAROON_BG = "\u001B[48;2;123;17;19m";
    public static final String GOLD_BG = "\u001B[48;2;255;213;0m";
    public static final String TOPAZ_BG = "\u001B[48;2;255;191;128m";
    public static final String DARK_SIENNA_BG = "\u001B[48;2;77;38;0m";
    
    public static final String DARK_SIENNA_FG = "\u001B[38;2;77;38;0m";
    public static final String GOLD_FG = "\u001B[38;2;255;213;0m";
    public static final String CYAN_FG = "\u001B[38;2;0;255;255m";
    public static final String GREEN_FG = "\u001B[38;2;0;255;0m";
    public static final String RED_FG = "\u001B[38;2;255;0;0m";

    public static final int POS_Y = 1;
    public static final int POS_X = 1;

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


    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
    }
    public static void gotoXY(int x, int y) {
        System.out.print("\033[" + y + ";" + x + "H");
    }
    public static void sleep (int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

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
    public static void printnext (int delay, char ch, String BG, String FG){
        sleep(delay);
        System.out.printf(BG + FG + "%C" + RESET, ch);
    }

    public static boolean isValidNumber(int base, String number) {
        String validDigits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".substring(0, base);

        for (char digit : number.toCharArray()) {
            if (!validDigits.contains(String.valueOf(Character.toUpperCase(digit)))) {
                return false;
            }
        }
        return true;
    }

    public static String addchar(String str, char ch) {
        return ch + str;
    }
    public static String replacechar (String string, int index, char character) {
        return string.substring(0, index) + character + string.substring(index + 1);
    }
    public static String cutString(String string, int index) {
        return string.substring(0, index + 1);
    }
    public static int stringToNumber(String strnumber, int base) {
        int number = 0;
        for (int i = 0; i < strnumber.length(); i++) {
            number = number * base + charToInt.get(strnumber.charAt(i));
        }
        return number;
    }
    public static String numberToString (int number, int base) {
        if (number == 0) {
            return "0";
        }
        
        String result = "";

        while (number > 0) {
            int digit = number % base;
            result = addchar(result, intToChar.get(digit));
            number /= base;
        }

        return result;
    }
    

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
                if (i == 0) {
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

        int tempdividend = 0;
        int intdivisor = stringToNumber(divisor, base);
        int qoutient = 0;
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
            printAtPosition(pos_X + divisor.length() + 3 + i, pos_Y, delay, intToChar.get(qoutient), BG, FG);
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

    public static int y_division(int base, String dividend, String divisor){

        int tempdividend = 0;
        int intdivisor = stringToNumber(divisor, base);
        int qoutient = 0;
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
            String product = numberToString((qoutient * intdivisor), base);
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

}
