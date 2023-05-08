package com.javarush.cryptanalyzer.zaveyboroda.view;

import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

public interface View {
    void displayMenu();
    UserInput getUserInput();
}
