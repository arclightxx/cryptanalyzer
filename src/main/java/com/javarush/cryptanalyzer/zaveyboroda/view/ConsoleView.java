package com.javarush.cryptanalyzer.zaveyboroda.view;

import com.javarush.cryptanalyzer.zaveyboroda.constants.*;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class ConsoleView implements View {
    @Override
    public void displayMenu() {
        System.out.println(MenuConstants.MENU_MESSAGE);
        System.out.println("*".repeat(100));
    }
    @Override
    public UserInput getUserInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            int mode = getMode(scanner);
            System.out.printf(ModeConstants.MODE_SUCCESS_MESSAGE + "\n", MenuConstants.MODES.get(mode));

            int shift = new Random().nextInt(Alphabet.length) + 1;
            if (mode == 2) {
                System.out.println(ShiftConstants.SHIFT_INPUT_MESSAGE);
                scanner.nextLine();
                shift = getShift(scanner);
                System.out.printf(ShiftConstants.SHIFT_INPUT_SUCCESS_MESSAGE + "\n", shift);
            } else if (mode == 1) {
                System.out.printf(ShiftConstants.SHIFT_RANDOM_SUCCESS_MESSAGE + "\n", shift);
            }

            getInputFileMessage(mode);
            scanner.nextLine();
            Path inputFile = getInputFile(scanner, mode);
            System.out.printf(InputFileConstants.INPUT_FILE_SUCCESS_MESSAGE + "\n", inputFile);

            System.out.println("*".repeat(50));

            return new UserInput(mode, shift, inputFile);
        }
    }

    private int getMode(Scanner scanner) {
        int mode;
        while (true) {
            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();
                if (mode < 1 || mode > 3) {
                    System.out.println(ModeConstants.MODE_INVALID_INPUT_MESSAGE);
                } else {
                    break;
                }
            } else {
                System.out.println(ModeConstants.MODE_INVALID_INPUT_MESSAGE);
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
                    System.out.println(ShiftConstants.SHIFT_INVALID_INPUT_MESSAGE);
                } else {
                    break;
                }
            } else {
                System.out.println(ShiftConstants.SHIFT_INVALID_INPUT_MESSAGE);
            }
            scanner.nextLine();
        }
        return shift;
    }

    private void getInputFileMessage(int mode) {
        switch (mode) {
            case 1 -> System.out.printf(InputFileConstants.INPUT_FILE_MESSAGE + "\n", "зашифровать");
            case 2 -> System.out.printf(InputFileConstants.INPUT_FILE_MESSAGE + "\n", "расшифровать");
            case 3 -> System.out.printf(InputFileConstants.INPUT_FILE_MESSAGE + "\n", "взломать");
            default -> System.out.println("Что-то пошло не так..");
        }
    }

    private Path getInputFile(Scanner scanner, int mode) {
        Path inputFile;
        String input;

        input = scanner.nextLine();
        if (input.isEmpty()) {
            if (mode == 1) {
                input = "input.txt";
            } else if (mode == 2 || mode == 3) {
                input = "encoded.txt";
            } else if (mode == 4) {
                input = "statistical-encoded.txt";
            } else {
                throw new RuntimeException("Ошибка при выборе файла по умолчанию");
            }
        }
        inputFile = Path.of(input);

        return inputFile;
    }
}
