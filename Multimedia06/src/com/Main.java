package com;

import java.io.*;

public class Main {

    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc.wav");
    public static long fileSize = file.length();
    public static FileInputStream fileInput = null;
    public FileOutputStream fileOutput = null;

    public static String bytesToString(int[] header, int startRange, int stopRange) {
        char[] chars = new char[stopRange - startRange];
        for (int i = 0; i < stopRange - startRange; i++) {
            chars[i] = (char) header[i + startRange];
        }
        String result = new String(chars);
        return result;
    }

    public static int bytesToInt(int[] header, int startRange, int stopRange){
        int result = 0;
        for (int i = 0; i < stopRange - startRange; i++) {
            result += header[i + startRange] << (8*i);
        }
        return result;
    }

    public static void readRiffHeader() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            byte[] input = fileInput.readNBytes(12);
            int[] wavHeader = new int[12];
            for (int i = 0; i < 12; i++) {
                wavHeader[i] = (int) (input[i] & 0xff);
            }
            System.out.println("Chunk id: " + bytesToString(wavHeader, 0, 4));
            System.out.println("Chunk size :" + bytesToInt(wavHeader, 4,8) + " B");
            System.out.println("Format is: " + bytesToString(wavHeader, 8, 12));

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void readFmtHeader() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            fileInput.skipNBytes(12);
            byte[] input = fileInput.readNBytes(26);
            int[] fmtHeader = new int[26];
            int subchunkSize = 0;
            for (int i = 0; i < 26; i++) {
                fmtHeader[i] = (int) (input[i] & 0xff);
                if (i > 3 && i < 8) {
                    subchunkSize += fmtHeader[i] << (8 *  (i-4));
                }
            }
            System.out.println("Subchunk id: " + bytesToString(fmtHeader, 0, 4));
            System.out.println("Subchunk size : "+ subchunkSize + "B");
            System.out.println("Audio format: " + bytesToInt(fmtHeader, 8, 10));
            System.out.println("Num channels: " + bytesToInt(fmtHeader, 10, 12));
            System.out.println("Sample rate: " + bytesToInt(fmtHeader, 12, 16));
            System.out.println("Byte rate: " + bytesToInt(fmtHeader, 16, 20));
            System.out.println("Block align: " + bytesToInt(fmtHeader, 20, 22));
            System.out.println("Bits per sample: " + bytesToInt(fmtHeader, 22, 24));
            System.out.println("Extra param size: " + bytesToInt(fmtHeader, 24, 26));

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void main(String[] args) throws IOException {
        readRiffHeader();
        readFmtHeader();
    }
}
