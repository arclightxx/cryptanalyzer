package com.javarush.cryptanalyzer.zaveyboroda;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static final String GREETINGS_MESSAGE = "Вас приветстует приложение по работе с шифром Цезаря!\n";
    private static final String APP_INFO = "Приложение поддерживает 3 режима:\n";
    private static final String FIRST_MODE_INFO = "1. Шифрование - программа зашифровывает текст, используя случайно сгенерированный ключ\n";
    private static final String SECOND_MODE_INFO = "2. Расшифровка - программа расшифровывает текст, используя заданный ключ\n";
    private static final String THIRD_MODE_INFO = "3. Взлом - программа взламывает зашифрованный текст\n";
    private static final String MODE_INPUT_MESSAGE = "Пожалуйста, введите желаемый режим работы, используя цифры 1, 2 и 3";
    public static final String MENU_MESSAGE = GREETINGS_MESSAGE + APP_INFO + FIRST_MODE_INFO + SECOND_MODE_INFO + THIRD_MODE_INFO + MODE_INPUT_MESSAGE;
    public static String MODE_INFO_MESSAGE = "Вы выбрали режим %s. Пожалуйста, введите путь к исходному текстовому файлу или введите default, чтобы выбрать файл по умолчанию";

    public void displayMenu() {
        System.out.println(Menu.MENU_MESSAGE);
        System.out.println();
    }

    public UserInput getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int mode = setMode(scanner);
            System.out.printf(MODE_INFO_MESSAGE + "\n", Application.modes.get(mode));
            String inputFile = getInputFile(scanner);
            return new UserInput(mode,inputFile);
        }
    }

    private int setMode(Scanner scanner) {
        try {
            int mode = scanner.nextInt();
            scanner.nextLine();
            return mode;
        } catch (InputMismatchException e) { // TODO Создать исключение, которое выбрасывается при вводе всех цифр, кроме 1, 2, 3
            System.out.println("Похоже, что Вы ввели символ. Пожалуйста, используйте цифры 1, 2 и 3 для корректной работы программы");
            return -1;
        }
    }

    private String getInputFile(Scanner scanner) {
        try {
            return scanner.next();
        } catch (Exception e) { // TODO Создать исключение, которое выбрасывается при некорректном вводе имени файла
            System.out.println(e.getMessage());
            return "";
        }
    }
}
