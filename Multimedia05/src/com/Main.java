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

    public static boolean checkID3V2(int[] header) throws IOException {
        try {
            fileInput = new FileInputStream(file);
            byte[] input = fileInput.readNBytes(10);
            for (int i = 0; i < 10; i++) {
                int tmp = (int) (input[i] & 0xff);
                usign_bytes[i] = tmp;
            }
            if ((char)usign_bytes[0] == 'I') {
                if ((char)usign_bytes[1] == 'D') {
                    if ((char)usign_bytes[2] == '3') {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
        return false;
    }

    public static boolean checkID3V1(int[] header) throws IOException {
        try {
            fileInput = new FileInputStream(file);
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
        fileInput.close();
        return false;
    }

    public static void main(String[] args) throws IOException {


        tagID3V2 = checkID3V2(usign_bytes);
        tagID3V1 = checkID3V1(usign_bytes);

        if(tagID3V2){

        }
        else if(tagID3V1){

        }

        fileInput.close();

        Arrays.stream(usign_bytes).forEach(bytes -> System.out.println(bytes));

        System.out.println("TAGv2:" + tagID3V2);
        System.out.println("TAGv1:" + tagID3V1);
    }
}
