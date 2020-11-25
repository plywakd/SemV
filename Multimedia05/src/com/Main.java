package com;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio_TAG_3__2.mp3");
    public static long fileSize = file.length() - 128;
    public static FileInputStream fileInput = null;
    public FileOutputStream fileOutput = null;
    public static int[] usign_bytes = new int[10];
    public static boolean tagID3V2 = false;
    public static boolean tagID3V1 = false;

    public static boolean checkID3V2(int[] header) {
        try {
            byte[] input = fileInput.readNBytes(10);
            for (int i = 0; i < 10; i++) {
                int tmp = (int) (input[i] & 0xff);
                usign_bytes[i] = tmp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkID3V1(int[] header) {
        try {
            fileInput.skip((fileSize));
            char[] tag = new char[3];
            for (int i = 0; i < 3; i++) {
                tag[i] = (char) fileInput.read();
            }
            if (tag[0] == 'T') {
                if (tag[1] == 'A') {
                    if (tag[2] == 'G') {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws IOException {

        try {
            fileInput = new FileInputStream(file);
//            fileOutput = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        tagID3V2 = checkID3V2(usign_bytes);
        tagID3V1 = checkID3V1(usign_bytes);

        fileInput.close();

        Arrays.stream(usign_bytes).forEach(bytes -> System.out.println(bytes));

        System.out.println("TAGv2:" + tagID3V2);
        System.out.println("TAGv1:" + tagID3V1);
    }
}
