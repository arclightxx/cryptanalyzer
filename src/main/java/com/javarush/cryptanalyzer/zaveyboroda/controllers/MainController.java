package com.javarush.cryptanalyzer.zaveyboroda.controllers;

import com.javarush.cryptanalyzer.zaveyboroda.view.View;

public class MainController {
    private View view;
    public MainController(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }
}
