package com.javarush.cryptanalyzer.zaveyboroda.entities;

import java.nio.file.Path;

public class UserInput {
    private int mode;
    private Path fileInput;
    private int shift;

    public UserInput(int mode, int shift, Path fileInput) {
        this.mode = mode;
        this.shift = shift;
        this.fileInput = fileInput;
    }

    public int getMode() {
        return mode;
    }

    public Path getFileInput() {
        return fileInput;
    }

    public int getShift() {
        return shift%86; // 86 is alphabet length
    }
}
