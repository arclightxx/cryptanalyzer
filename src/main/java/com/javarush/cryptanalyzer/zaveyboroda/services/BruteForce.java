package com.javarush.cryptanalyzer.zaveyboroda.services;

import com.javarush.cryptanalyzer.zaveyboroda.constants.Alphabet;
import com.javarush.cryptanalyzer.zaveyboroda.entities.UserInput;

import java.io.*;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BruteForce implements Function {
    private int maxMatches;
    private int resultShift;
    private StringBuilder resultDecryptedText;

    @Override
    public void execute(UserInput userInput) {
        Path inputFile = userInput.getFileInput();
        Decode decode = new Decode();
        Pattern wordPattern = Pattern.compile("[а-яА-Я]+[\\.,!?:\\\"'\\-]*\\s");

        findResultDecryptedText(inputFile, decode, wordPattern);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            writer.write(resultDecryptedText.toString());
            System.out.println("Взломанный текст находится в файле output.txt\nКлюч шифрования: " + resultShift);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void findResultDecryptedText(Path inputFile, Decode decode, Pattern wordPattern) {
        int shift;
        for (shift = 0; shift < Alphabet.length; shift++) {
            StringBuilder decryptedText = decode.execute(inputFile, shift);
            Matcher matcher = wordPattern.matcher(decryptedText.toString());

            int count = 0;
            while (matcher.find()) {
                count++;
            }

            if (count > maxMatches) {
                maxMatches = count;
                resultShift = shift;
                resultDecryptedText = decryptedText;
            }
        }
    }
}
