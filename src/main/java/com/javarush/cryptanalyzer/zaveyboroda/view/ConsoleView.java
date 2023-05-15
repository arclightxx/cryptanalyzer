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
        Scanner scanner = new Scanner(System.in);
        int mode = getMode(scanner);

        int shift = getShift(scanner, mode);

        getInputFileMessage(mode);
        Path inputFile = getInputFile(scanner, mode);

        getOutputFileMessage();
        Path outputFile = getOutputFile(scanner);

        UserInput userInput = new UserInput(mode, shift, inputFile, outputFile);

        if (mode == 4) {
            System.out.println("Режим находится в разработке..");
//                System.out.println(InputFileConstants.DICTIONARY_FILE_MESSAGE);
//                Path dictionaryFile = getDictionaryFile(scanner);
//                System.out.printf(InputFileConstants.FILE_SUCCESS_MESSAGE + "\n", dictionaryFile);
//                userInput.setDictionaryFile(dictionaryFile);
        }

        System.out.println("*".repeat(50));

        return userInput;
    }

    private int getMode(Scanner scanner) {
        int mode;
        while (true) {
            if (scanner.hasNextInt()) {
                mode = scanner.nextInt();
                if (mode < 1 || mode > 4) {
                    System.out.println(ModeConstants.MODE_INVALID_INPUT_MESSAGE);
                } else {
                    break;
                }
            } else {
                System.out.println(ModeConstants.MODE_INVALID_INPUT_MESSAGE);
            }
            scanner.nextLine();
        }
        System.out.printf(ModeConstants.MODE_SUCCESS_MESSAGE + "\n", MenuConstants.MODES.get(mode));
        return mode;
    }

    private int getShift(Scanner scanner, int mode) {
        int shift;
        switch (mode) {
            case 2 -> {
                System.out.println(ShiftConstants.SHIFT_INPUT_MESSAGE);
                scanner.nextLine();
                shift = getShiftFromScanner(scanner);
                System.out.printf(ShiftConstants.SHIFT_INPUT_SUCCESS_MESSAGE + "\n", shift);
            }
            case 1 -> {
                shift = new Random().nextInt(Alphabet.length) + 1;
                System.out.printf(ShiftConstants.SHIFT_RANDOM_SUCCESS_MESSAGE + "\n", shift);
            }
            default -> shift = 0;
        }

        return shift;
    }

    private int getShiftFromScanner(Scanner scanner) {
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
            case 2, 4 -> System.out.printf(InputFileConstants.INPUT_FILE_MESSAGE + "\n", "расшифровать");
            case 3 -> System.out.printf(InputFileConstants.INPUT_FILE_MESSAGE + "\n", "взломать");
            default -> System.out.println("Что-то пошло не так..");
        }
    }

    private Path getInputFile(Scanner scanner, int mode) {
        Path inputFile;
        String input;

        scanner.nextLine();

        input = scanner.nextLine();
        if (input.isEmpty()) {
            input = switch (mode) {
                case 1 -> "input.txt";
                case 2, 3 -> "encoded.txt";
                case 4 -> "statistical-encoded.txt";
                default -> throw new RuntimeException("Ошибка при выборе файла по умолчанию");
            };
        }
        inputFile = Path.of(input);
        System.out.printf(InputFileConstants.FILE_SUCCESS_MESSAGE + "\n", inputFile);

        return inputFile;
    }

    private void getOutputFileMessage() {
        System.out.println(InputFileConstants.OUTPUT_FILE_MESSAGE);
    }

    private Path getOutputFile(Scanner scanner) {
        Path outputFile;
        String input;

        input = scanner.nextLine();
        if (input.isEmpty()) {
            input = "output.txt";
        }
        outputFile = Path.of(input);
        System.out.printf(InputFileConstants.FILE_SUCCESS_MESSAGE + "\n", outputFile);

        return outputFile;
    }

    private Path getDictionaryFile(Scanner scanner) {
        Path dictionaryFile;
        String input;

        input = scanner.nextLine();
        if (input.isEmpty()) {
            input = "dictionary.txt";
        }
        dictionaryFile = Path.of(input);

        return dictionaryFile;
    }
}
