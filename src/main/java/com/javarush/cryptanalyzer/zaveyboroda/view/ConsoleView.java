package com.javarush.cryptanalyzer.zaveyboroda.view;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;
import com.javarush.cryptanalyzer.zaveyboroda.constants.MenuConstants;

import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class ConsoleView implements View {
    @Override
    public void displayMenu() {
        System.out.println(MenuConstants.MENU_MESSAGE);
        System.out.println();
    }
    @Override
    public UserInput getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int mode = getMode(scanner);
            System.out.printf(MenuConstants.MODE_SUCCESS_MESSAGE + "\n", MenuConstants.MODES.get(mode));

            int shift = new Random().nextInt(Alphabet.length) + 1;
            if (mode == 2) {
                System.out.println(MenuConstants.SHIFT_INPUT_MESSAGE);
                shift = getShift(scanner);
                System.out.printf(MenuConstants.SHIFT_INPUT_SUCCESS_MESSAGE + "\n", shift);
            } else {
                System.out.printf(MenuConstants.SHIFT_RANDOM_SUCCESS_MESSAGE + "\n", shift);
            }

            getInputFileMessage(mode);
            scanner.nextLine();
            Path inputFile = getInputFile(scanner);
            System.out.printf(MenuConstants.INPUT_FILE_SUCCESS_MESSAGE + "\n", inputFile);

            return new UserInput(mode, shift, inputFile);
        }
    }

    private int getMode(Scanner scanner) {
        int mode;
        while (true) {
            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();
                if (mode < 1 || mode > 3) {
                    System.out.println(MenuConstants.MODE_INVALID_INPUT_MESSAGE);
                } else {
                    break;
                }
            } else {
                System.out.println(MenuConstants.MODE_INVALID_INPUT_MESSAGE);
            }
            scanner.nextLine();
        }
        return mode;
    }

    private int getShift(Scanner scanner) {
        int shift;
        while (true) {
            if (scanner.hasNextInt()) {
                shift = scanner.nextInt();
                if (shift < 1) {
                    System.out.println(MenuConstants.SHIFT_INVALID_INPUT_MESSAGE);
                } else {
                    break;
                }
            } else {
                System.out.println(MenuConstants.MODE_INVALID_INPUT_MESSAGE);
                scanner.nextLine();
            }
        }
        return shift;
    }

    private void getInputFileMessage(int mode) {
        switch (mode) {
            case 1 -> System.out.printf(MenuConstants.INPUT_FILE_MESSAGE + "\n", "зашифровать");
            case 2 -> System.out.printf(MenuConstants.INPUT_FILE_MESSAGE + "\n", "расшифровать");
            case 3 -> System.out.printf(MenuConstants.INPUT_FILE_MESSAGE + "\n", "взломать");
            default -> System.out.println("Что-то пошло не так..");
        }
    }

    private Path getInputFile(Scanner scanner) {
        Path inputFile;
        String input;

        input = scanner.nextLine();
        if (input.isEmpty()) {
            input = "input.txt";
        }
        inputFile = Path.of(input);

        return inputFile;
    }
}
