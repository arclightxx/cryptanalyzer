package com.javarush.cryptanalyzer.zaveyboroda;

import java.util.*;

public class Application {
    private CaesarCipher caesarCipher;
    Map<Integer, String> modes = new HashMap<>();
    {
        modes.put(1, "Шифрование");
        modes.put(2, "Расшифровка");
        modes.put(3, "Взлом");
    }
    private int mode;
    private String inputFile;

    public void run() {
        printMenuMessage();

        try (Scanner scanner = new Scanner(System.in)) {
            setMode(scanner);
            System.out.printf(Menu.MODE_INFO_MESSAGE + "\n", modes.get(mode));
            setInputFile(scanner);
        }
    }

    private void printMenuMessage() {
        System.out.println(Menu.MENU_MESSAGE);
        System.out.println();
    }

    private void setMode(Scanner scanner) {
        try {
            mode = scanner.nextInt();
        } catch (InputMismatchException e) { // TODO Создать исключение, которое выбрасывается при вводе всех цифр, кроме 1, 2, 3
            System.out.println("Похоже, что Вы ввели символ. Пожалуйста, используйте цифры 1, 2 и 3 для корректной работы программы");
        }
    }

    private void setInputFile(Scanner scanner) {
        try {
            inputFile = scanner.next();
            while (!inputFile.equals("")){
                inputFile = scanner.nextLine();
            }
        } catch (Exception e) { // TODO Создать исключение, которое выбрасывается при некорректном вводе имени файла
            System.out.println(e.getMessage());
        }
    }
}
