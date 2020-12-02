package com;

import java.io.*;

public class Main {

    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc.wav");
    public static long fileSize = file.length();
    public static FileInputStream fileInput = null;
    public FileOutputStream fileOutput = null;

    public static void readWaveHeader() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            byte[] input = fileInput.readNBytes(12);
            int[] wavHeader = new int[12];
            for (int i = 0; i < 12; i++) {
                wavHeader[i] = (int) (input[i] & 0xff);
            }
            for (int i = 0; i < 3; i += 4) {
                switch (i) {
                    case 0:
                        StringBuilder sb = new StringBuilder();
                        sb.append((char) wavHeader[0]);
                        sb.append((char) wavHeader[1]);
                        sb.append((char) wavHeader[2]);
                        sb.append((char) wavHeader[3]);
                        String chunkId = sb.toString();
                        System.out.println("Chunk id: " + chunkId);
                    case 1:
                        int chunkSize = wavHeader[4] + wavHeader[5] + wavHeader[6] + wavHeader[7];
                        System.out.println("Chunk size :" + chunkSize + " B");
                    case 2:
                        StringBuilder sbd = new StringBuilder();
                        sbd.append((char) wavHeader[8]);
                        sbd.append((char) wavHeader[9]);
                        sbd.append((char) wavHeader[10]);
                        sbd.append((char) wavHeader[11]);
                        String format = sbd.toString();
                        System.out.println("Format is: " + format);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void main(String[] args) throws IOException {
        readWaveHeader();
    }
}
