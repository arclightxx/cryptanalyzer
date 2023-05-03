package com.javarush.cryptanalyzer.zaveyboroda;

import java.io.*;
import java.util.Random;

public class CaesarCipher {
    private int shift;

    public int encode(String inputFile) {
        shift = getRandomShift();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] charLine = line.toCharArray();
                for (int i = 0; i < charLine.length; i++) {
                    int index = (Alphabet.ALPHABET.indexOf(charLine[i]) + shift) % Alphabet.ALPHABET.length();
                    charLine[i] = Alphabet.ALPHABET.charAt(index);
                }
                writer.write(charLine);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return shift;
    }

    private int getRandomShift() {
        return new Random().nextInt(Alphabet.ALPHABET.length()) + 1;
    }

    public void decode(String inputFile, int shift) {
        this.shift = -shift;
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                char[] charLine = line.toCharArray();
                for (int i = 0; i < charLine.length; i++) {
                    int index = (Alphabet.ALPHABET.indexOf(charLine[i]) + this.shift + Alphabet.ALPHABET.length()) % Alphabet.ALPHABET.length();
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
