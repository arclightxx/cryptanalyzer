package com.javarush.cryptanalyzer.zaveyboroda;

import java.util.*;

public class Application {
    private Menu menu;
    private UserInput userInput;
    private CaesarCipher caesarCipher;
    public static Map<Integer, String> modes = new HashMap<>();
    static {
        modes.put(1, "Шифрование");
        modes.put(2, "Расшифровка");
        modes.put(3, "Взлом");
    }
    private int mode;
    private String inputFile;

    public Application() {
        menu = new Menu();
    }

    public void run() {
        menu.displayMenu();
        init();
    }

    private void init() {
        userInput = menu.getUserInput();
        mode = userInput.getMode();
        inputFile = userInput.getFileInput();
    }
}
