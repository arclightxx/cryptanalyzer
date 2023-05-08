package com.javarush.cryptanalyzer.zaveyboroda.app;

import com.javarush.cryptanalyzer.zaveyboroda.CaesarCipher;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;
import com.javarush.cryptanalyzer.zaveyboroda.controllers.MainController;
import com.javarush.cryptanalyzer.zaveyboroda.view.View;

import java.nio.file.Path;

public class Application {
    private MainController mainController;

    public Application(MainController mainController) {
        this.mainController = mainController;
    }

    public void run() {
        View view = mainController.getView();
        view.displayMenu();
        UserInput userInput = view.getUserInput();
        int mode = userInput.getMode();
        Path fileInput = userInput.getFileInput();
        CaesarCipher caesarCipher = new CaesarCipher();
        int shift = caesarCipher.encode(userInput.getFileInput());
        System.out.println("Шифрование прошло успешно! Ключ для расшифровки: " + shift);
        caesarCipher.decode(Path.of("output.txt"), shift);
        System.out.println("Расшифровка прошла успешно!");
    }
}
