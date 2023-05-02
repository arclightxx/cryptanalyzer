package com.javarush.cryptanalyzer.zaveyboroda;

import java.io.BufferedReader;

public class CaesarCipher {
    private final char[] alphabet = Alphabet.ALPHABET.toCharArray();
    private int shift;

    public void encode(BufferedReader message) {
        //        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
//             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("output.txt")))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                char[] charLine = line.toCharArray();
//                for (int i = 0; i < charLine.length; i++)
//                    if (charLine[i] == '«' || charLine[i] == '»') charLine[i] = '"';
//                writer.write(charLine);
//                writer.newLine();
//            }
//        } catch (IOException e) {
//            System.out.println(e.getMessage());
    }
}
