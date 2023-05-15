package com.javarush.cryptanalyzer.zaveyboroda.services;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

import java.io.*;
import java.nio.file.Path;

public class StatisticalAnalysis implements Function {
    @Override
    public void execute(UserInput userInput) {
        Path inputFile = userInput.getFileInput();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile.toFile()));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            encode(reader, writer);
            System.out.println("Зашифрованный текст находится в файле output.txt");
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private void encode(BufferedReader reader, BufferedWriter writer) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            char[] charLine = line.toCharArray();
            for (int i = 0; i < charLine.length; i++) {
                if (charLine[i] == '«' || charLine[i] == '»') {
                    charLine[i] = '"';
                }
                if (charLine[i] == '–') {
                    charLine[i] = '-';
                }
                if (charLine[i] == ' ') {
                    charLine[i] = ' ';
                }
            }
            writer.write(charLine);
            writer.newLine();
        }
    }
}
