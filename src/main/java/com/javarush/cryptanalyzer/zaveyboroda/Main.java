package com.javarush.cryptanalyzer.zaveyboroda;

import com.javarush.cryptanalyzer.zaveyboroda.app.Application;
import com.javarush.cryptanalyzer.zaveyboroda.controllers.MainController;
import com.javarush.cryptanalyzer.zaveyboroda.view.ConsoleView;
import com.javarush.cryptanalyzer.zaveyboroda.view.View;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        View view = new ConsoleView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            application.run();
            System.out.println("Вы хотите продолжить работу? Введите \"n\", чтобы выйти или нажмите ENTER, чтобы продолжить");
            if (scanner.nextLine().equals("n")) {
                scanner.close();
                break;
            }
        }
    }
}
