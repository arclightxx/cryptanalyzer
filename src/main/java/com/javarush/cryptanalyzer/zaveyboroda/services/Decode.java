package com.javarush.cryptanalyzer.zaveyboroda.services;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

import java.io.*;
import java.nio.file.Path;

public class Decode implements Function {
    @Override
    public void execute(UserInput userInput) {
        int shift = -userInput.getShift();
        Path inputFile = userInput.getFileInput();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output-1.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] charLine = line.toCharArray();
                for (int i = 0; i < charLine.length; i++) {
                    int index = (Alphabet.ALPHABET.indexOf(charLine[i]) + shift + Alphabet.ALPHABET.length()) % Alphabet.ALPHABET.length();
                    charLine[i] = Alphabet.ALPHABET.charAt(index);
                }
                writer.write(charLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
