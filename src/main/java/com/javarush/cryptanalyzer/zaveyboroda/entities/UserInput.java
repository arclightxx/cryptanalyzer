package com.javarush.cryptanalyzer.zaveyboroda.entities;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;

import java.nio.file.Path;

public class UserInput {
    private final int mode;
    private final Path fileInput;
    private final Path fileOutput;
    private Path dictionaryFile;
    private final int shift;

    public UserInput(int mode, int shift, Path fileInput, Path fileOutput) {
        this.mode = mode;
        this.shift = shift;
        this.fileInput = fileInput;
        this.fileOutput = fileOutput;
    }

    public int getMode() {
        return mode;
    }

    public Path getFileInput() {
        return fileInput;
    }

    public Path getFileOutput() {
        return fileOutput;
    }

    public void setDictionaryFile(Path dictionaryFile) {
        this.dictionaryFile = dictionaryFile;
    }

    public Path getDictionaryFile() {
        return dictionaryFile;
    }

    public int getShift() {
        return shift % Alphabet.length;
    }
}
