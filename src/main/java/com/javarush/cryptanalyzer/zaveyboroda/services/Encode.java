package com.javarush.cryptanalyzer.zaveyboroda.services;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

import java.io.*;
import java.nio.file.Path;

public class Encode implements Function {
    @Override
    public void execute(UserInput userInput) {
        int shift = userInput.getShift();
        Path inputFile = userInput.getFileInput();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            encode(reader, writer, shift);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void encode(BufferedReader reader, BufferedWriter writer, int shift) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            char[] charLine = line.toCharArray();
            for (int i = 0; i < charLine.length; i++) {
                int posInAlphabet = Alphabet.ALPHABET.indexOf(charLine[i]);
                if (posInAlphabet == -1) {
                    continue;
                }
                int index = (Alphabet.ALPHABET.indexOf(charLine[i]) + shift) % Alphabet.ALPHABET.length();
                charLine[i] = Alphabet.ALPHABET.charAt(index);
            }
            writer.write(charLine);
            writer.newLine();
        }
    }
}
