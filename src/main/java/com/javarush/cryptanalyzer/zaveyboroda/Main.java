package com.javarush.cryptanalyzer.zaveyboroda;

import com.javarush.cryptanalyzer.zaveyboroda.app.Application;
import com.javarush.cryptanalyzer.zaveyboroda.controllers.MainController;
import com.javarush.cryptanalyzer.zaveyboroda.view.ConsoleView;
import com.javarush.cryptanalyzer.zaveyboroda.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        application.run();
    }
}
