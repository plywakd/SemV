package com;

import java.io.*;

public class Main {

    public static String bytesToString(byte[] bytes, int startRange, int stopRange) {
        char[] chars = new char[stopRange - startRange + 1];
        for (int i = 0; i < stopRange - startRange; i++) {
            chars[i] = (char) bytes[i + startRange];
        }
        String result = new String(chars);
        return result;
    }

    public static byte[] stringToBytes(String str, int maxLen) {
        int strLen = str.length();
        byte[] result = new byte[maxLen];
        for (int i = 0; i < maxLen && i < strLen; i++) {
            result[i] = (byte) str.charAt(i);
        }
        return result;
    }


    public static void main(String[] args) {
//        File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio_TAG_3_1.mp3");
        File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio.mp3");
        long fileSize = file.length() - 128;
        System.out.println("Filesize is : " + fileSize);
        FileInputStream fileInput = null;
        FileOutputStream fileOutput = null;


        try {
            fileInput = new FileInputStream(file);
            fileOutput = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fileInput.skip((fileSize));
            char[] tag = new char[3];
            for (int i = 0; i < 3; i++) {
                tag[i] = (char) fileInput.read();
                System.out.println(tag[i]);
            }
            boolean tagExists = false;
            if (tag[0] == 'T') {
                if (tag[1] == 'A') {
                    if (tag[2] == 'G') {
                        tagExists = true;
                    }
                }
            }

            byte[] tagInfo = new byte[125];

            if (tagExists) {
                fileInput.read(tagInfo, 0, 125);
                System.out.println("Tytul: " + bytesToString(tagInfo, 0, 30));
                System.out.println("Artysta: " + bytesToString(tagInfo, 30, 60));
                System.out.println("Album: " + bytesToString(tagInfo, 60, 90));
                System.out.println("Rok: " + bytesToString(tagInfo, 90, 94));
                System.out.println("Komentarz: " + bytesToString(tagInfo, 94, 124));
                System.out.println("Gatunek: " + (byte) tagInfo[124]);

                System.out.println("Delete existing tag");
                fileOutput.getChannel().truncate(fileSize);
            } else {
                System.out.println("There is no TAG available");
                fileOutput.write(stringToBytes("TAG", 3));
                fileOutput.write(stringToBytes("Technolgie Multimedialne", 30));
                fileOutput.write(stringToBytes("Prowadzacy", 30));
                fileOutput.write(stringToBytes("Laboratorium numer 4", 30));
                fileOutput.write(stringToBytes("2020", 4));
                fileOutput.write(stringToBytes("Zajecia", 30));
                fileOutput.write(0);
            }

            fileInput.close();
            fileOutput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
