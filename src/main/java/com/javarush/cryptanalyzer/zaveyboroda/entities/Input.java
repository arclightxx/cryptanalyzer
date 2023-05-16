package com.javarush.cryptanalyzer.zaveyboroda.entities;

public class Input {
    private Input input;
    private String value;

    public Input(String value) {
        this.value = value;
    }

    public static Input getInput() {
        return new Input("dummy");
    }

    public boolean isDigit() {
        return value.matches("\\d+");
    }

    public boolean isInModeRange() {
        return Integer.parseInt(this.value) >= 1 && Integer.parseInt(this.value) <= 4;
    }

    public boolean isInShiftRange() {
        return Integer.parseInt(this.value) > 0;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
