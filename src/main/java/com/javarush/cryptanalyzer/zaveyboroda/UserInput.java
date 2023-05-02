package com.javarush.cryptanalyzer.zaveyboroda;

public class UserInput {
    private int mode;
    private String fileInput;

    public UserInput(int mode, String fileInput) {
        this.mode = mode;
        this.fileInput = fileInput;
    }

    public int getMode() {
        return mode;
    }

    public String getFileInput() {
        return fileInput;
    }
}
