public class App {
    public static void main(String[] args) throws Exception {
        Tools.clearScreen();

        Window.loading();

        String[] menuoptions = {"1.Operation ", "2.Settings", "3.Help", "4.About", "5.Exit"};
        String[] oprtnoptions = {"1.Addition  ", "2.Subtraction", "3.Multiplication", "4.Division", "5.Back"};
        String[] setoptions = {"1.Delay(ms)", "2.Themes", "3.Base(2-36)", "4.Back"};
        String[] themeoptions = {"1.Future", "2.EARIST", "3.OLD school", "4.Default", "5.Back"};

        String choice = "0";
        String theme = "1";
        int delay = 100;
        int base = 10;

        while (!choice.equals("5")) {
            choice = Window.menu("--Calc-Wiz--", menuoptions, theme);

            if (choice.equals("1")) {
                String choice1 = "0";

                while (!choice1.equals("5")) {
                    choice1 = Window.menu(" -OPERATION-", oprtnoptions, theme);

                    if (choice1.equals("1")) {
                        Window.windowAdd(base, delay);
                    } else if (choice1.equals("2")) {
                        Window.windowSub(base, delay);
                    } else if (choice1.equals("3")) {
                        Window.windowMul(base, delay);
                    } else if (choice1.equals("4")) {
                        Window.windowDiv(base, delay);
                    }
                }

            } else if (choice.equals("2")) {
                String choice2 = "0";

                while (!choice2.equals("4")) {
                    choice2 = Window.menu(" -SETTINGS- ", setoptions, theme);

                    if (choice2.equals("1")) {
                        delay = Window.getdelay(delay); 
                    } else if (choice2.equals("2")) {

                        String choice2_2 = "0";

                        while (!choice2_2.equals("5")) {
                            choice2_2 = Window.menu("  -THEMES-  ", themeoptions, theme);

                            if (choice2_2.equals("1")) {
                                Window.BG1 = Tools.BLACK_BG;
                                Window.BG2 = Tools.CYAN_BG;
                                Window.FG = Tools.CYAN_FG;;
                            } else if (choice2_2.equals("2")) {
                                Window.BG1 = Tools.MAROON_BG;
                                Window.BG2 = Tools.GOLD_BG;
                                Window.FG = Tools.GOLD_FG;
                            } else if (choice2_2.equals("3")) {
                                Window.BG1 = Tools.TOPAZ_BG;
                                Window.BG2 = Tools.DARK_SIENNA_BG;
                                Window.FG = Tools.DARK_SIENNA_FG;
                            } else if (choice2_2.equals("4")) {
                                Window.BG1 = "";
                                Window.BG2 = "";
                                Window.FG = "";
                            }
                        }
                    } else if (choice2.equals("3")) {
                        base = Window.getbase(base);
                    } 
                }  
            } else if (choice.equals("3")) {
                Window.windowHelp();
            } else if (choice.equals("4")) {
                Window.windowAbout();
            } 
        }

        Window.pixelart();
    }
}
