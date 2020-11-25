package com;

import java.io.*;
import java.util.Arrays;

public class Main {

    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio_TAG_3__2.mp3");
//    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio_TAG_3_1.mp3");
//    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio_TAG_3_1_3_2.mp3");
//    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio.mp3");
    public static long fileSize = file.length() - 128;
    public static FileInputStream fileInput = null;
    public FileOutputStream fileOutput = null;
    public static int[] usign_header_id3v2 = new int[10];
    public static int[] usign_header_id3v1 = new int[128];
    public static boolean tagID3V2 = false;
    public static boolean tagID3V1 = false;

    public static boolean checkID3V2() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            byte[] input = fileInput.readNBytes(10);
            for (int i = 0; i < 10; i++) {
                int tmp = (int) (input[i] & 0xff);
                usign_header_id3v2[i] = tmp;
            }
            if ((char) usign_header_id3v2[0] == 'I') {
                if ((char) usign_header_id3v2[1] == 'D') {
                    if ((char) usign_header_id3v2[2] == '3') {
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

    public static boolean checkID3V1() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            fileInput.skip((fileSize));
            byte[] input = fileInput.readNBytes(128);
            for (int i = 0; i < 128; i++) {
                int tmp = (int) (input[i] & 0xff);
                usign_header_id3v1[i] = tmp;
            }
            if ((char) usign_header_id3v1[0] == 'T') {
                if ((char) usign_header_id3v1[1] == 'A') {
                    if ((char) usign_header_id3v1[2] == 'G') {
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

    public static String bytesToString(int[] header, int startRange, int stopRange) {
        char[] chars = new char[stopRange - startRange + 1];
        for (int i = 0; i < stopRange - startRange; i++) {
            chars[i] = (char) header[i + startRange];
        }
        String result = new String(chars);
        return result;
    }

    public static void main(String[] args) throws IOException {


        tagID3V2 = checkID3V2();
        tagID3V1 = checkID3V1();

        if(tagID3V2){

        }
        else if(tagID3V1){
            System.out.println("Tytul: " + bytesToString(usign_header_id3v1, 3, 33));
            System.out.println("Artysta: " + bytesToString(usign_header_id3v1, 33, 63));
            System.out.println("Album: " + bytesToString(usign_header_id3v1, 63, 93));
            System.out.println("Rok: " + bytesToString(usign_header_id3v1, 93, 97));
            System.out.println("Komentarz: " + bytesToString(usign_header_id3v1, 97, 127));
            System.out.println("Gatunek: " + (byte) usign_header_id3v1[127]);
        }

        fileInput.close();

        Arrays.stream(usign_header_id3v2).forEach(bytes -> System.out.println(bytes));

        System.out.println("TAGv2:" + tagID3V2);
        System.out.println("TAGv1:" + tagID3V1);
    }
}
