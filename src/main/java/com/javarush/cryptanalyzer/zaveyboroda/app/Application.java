package com.javarush.cryptanalyzer.zaveyboroda.app;

import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;
import com.javarush.cryptanalyzer.zaveyboroda.controllers.MainController;
import com.javarush.cryptanalyzer.zaveyboroda.services.*;
import com.javarush.cryptanalyzer.zaveyboroda.view.View;

public class Application {
    private final MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public void run() {
        View view = mainController.getView();
        view.displayMenu();

        UserInput userInput = view.getUserInput();
        int mode = userInput.getMode();
        Function function = getFunction(mode);
        function.execute(userInput);
    }

    private Function getFunction(int mode) {
        return switch (mode) {
            case 1 -> new Encode();
            case 2 -> new Decode();
            case 3 -> new BruteForce();
            default -> throw new IllegalStateException("Unexpected value: " + mode);
        };
    }
}
