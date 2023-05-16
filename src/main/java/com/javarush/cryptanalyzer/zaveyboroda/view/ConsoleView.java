package com.javarush.cryptanalyzer.zaveyboroda.view;

import com.javarush.cryptanalyzer.zaveyboroda.constants.*;
import com.javarush.cryptanalyzer.zaveyboroda.entities.Input;
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
        Input input = Input.getInput();

        int mode = getModeValue(scanner, input);

        int shift = mode == 1 || mode == 2 ? getShiftValue(scanner, input, mode) : 0;

        getInputFileMessage(mode);
        Path inputFile = getInputFile(scanner, mode);

        getOutputFileMessage();
        Path outputFile = getOutputFile(scanner, mode);

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

    private int getModeValue(Scanner scanner, Input input) {
        int mode;
        String invalidInputMessage = ModeConstants.MODE_INVALID_INPUT_MESSAGE;
        String successMessage = ModeConstants.MODE_SUCCESS_MESSAGE;

        while (true) {
            input.setValue(scanner.nextLine());
            if (input.isDigit() && input.isInModeRange()) {
                mode = Integer.parseInt(input.getValue());
                break;
            } else {
                if (input.getValue().isEmpty()) {
                    continue;
                }
                System.out.println(invalidInputMessage);
            }
        }

        System.out.printf(successMessage + "\n", MenuConstants.MODES.get(mode));

        return mode;
    }

    private int getShiftValue(Scanner scanner, Input input, int mode) {
        int shift;
        String invalidInputMessage = ShiftConstants.SHIFT_INVALID_INPUT_MESSAGE;
        String successInputMessage = ShiftConstants.SHIFT_INPUT_SUCCESS_MESSAGE;
        String successRandomMessage = ShiftConstants.SHIFT_RANDOM_SUCCESS_MESSAGE;

        printShiftInputMessage(mode);

        while (true) {
            input.setValue(scanner.nextLine());
            if (input.isDigit() && input.isInShiftRange()) {
                shift = Integer.parseInt(input.getValue());
                break;
            } else {
                if (input.getValue().isEmpty()) {
                    shift = new Random().nextInt(Alphabet.length) + 1;
                    System.out.printf(successRandomMessage + "\n", shift);
                    break;
                }
                System.out.println(invalidInputMessage);
            }
        }

        System.out.printf(successInputMessage + "\n", shift);

        return shift;
    }

    private void printShiftInputMessage(int mode) {
        switch (mode) {
            case 1 -> System.out.println(ShiftConstants.SHIFT_INPUT_FIRST_MODE_MESSAGE);
            case 2 -> System.out.println(ShiftConstants.SHIFT_INPUT_MESSAGE);
        }
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

    private Path getOutputFile(Scanner scanner, int mode) {
        Path outputFile;
        String input;

        input = scanner.nextLine();
        if (input.isEmpty()) {
            switch (mode) {
                case 1 -> input = "encoded.txt";
                case 2, 3, 4 -> input = "output.txt";
                default -> throw new RuntimeException("Ошибка при выборе файла по умолчанию");
            }
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
