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
    public static int[] mpeg_id3v2 = new int[4];
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

    public static int calcID3V2size(){
        int[] sizeTab = new int[4];
        int size = 0;
        for(int i=0;i<4;i++){
            sizeTab[i] = usign_header_id3v2[i+6] & 0x7F;
            size += sizeTab[i];
        }
        return size + 10;
    }

    public static void getMPEGHeaderID3V2() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            fileInput.skip(10);
            byte[] input = fileInput.readNBytes(4);
            for (int i = 0; i < 4; i++) {
                int tmp = (int) (input[i] & 0xff);
                mpeg_id3v2[i] = tmp;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void main(String[] args) throws IOException {


        tagID3V2 = checkID3V2();
        tagID3V1 = checkID3V1();

        if(tagID3V2){
            System.out.println("Wersja");
            System.out.println("Flaga");
            System.out.println("Rozmiar: "+ calcID3V2size());
            String mpeg_ver = null;
            switch(mpeg_id3v2[1] & 0x18){
                case 0:
                    mpeg_ver = "MPEG Ver. 2.5";
                    System.out.println(mpeg_ver);
                    break;
                case 8:
                    System.out.println("Reserved");
                    break;
                case 16:
                    mpeg_ver = "MPEG Ver. 2";
                    System.out.println(mpeg_ver);
                    break;
                case 24:
                    mpeg_ver = "MPEG Ver. 1";
                    System.out.println(mpeg_ver);
                    break;
            }
            switch(mpeg_id3v2[1] & 0x06){
                case 0:
                    System.out.println("Reserved");
                    break;
                case 2:
                    System.out.println("Layer 3");
                    break;
                case 4:
                    System.out.println("Layer 2");
                    break;
                case 6:
                    System.out.println("Layer 1");
                    break;
            }
            switch(mpeg_id3v2[1] & 0x01){
                case 0:
                    System.out.println("Protected by CRC");
                    break;
                case 1:
                    System.out.println("Not protected");
                    break;
            }
            switch(mpeg_id3v2[2] & 0x0c){
                case 0:
                    if(mpeg_ver.equals("MPEG Ver. 2.5")){
                        System.out.println("44100");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 2")){
                        System.out.println("22050");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 1")){
                        System.out.println("11025");
                    }
                    break;
                case 4:
                    if(mpeg_ver.equals("MPEG Ver. 2.5")){
                        System.out.println("48000");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 2")){
                        System.out.println("24000");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 1")){
                        System.out.println("12000");
                    }
                    break;
                case 8:
                    if(mpeg_ver.equals("MPEG Ver. 2.5")){
                        System.out.println("32000");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 2")){
                        System.out.println("16000");
                    }
                    else if(mpeg_ver.equals("MPEG Ver. 1")){
                        System.out.println("8000");
                    }
                    break;
                case 12:
                    System.out.println("Reserved");
                    break;
            }
            switch(mpeg_id3v2[3] & 0xc0){
                case 0:
                    System.out.println("Stereo");
                    break;
                case 64:
                    System.out.println("Joint stereo");
                    break;
                case 128:
                    System.out.println("Dual channel");
                    break;
                case 192:
                    System.out.println("Single channel");
                    break;
            }
            switch(mpeg_id3v2[3] & 0x08){
                case 0:
                    System.out.println("Not copyrighted");
                    break;
                case 8:
                    System.out.println("Copyrighted");
                    break;
            }
            switch(mpeg_id3v2[3] & 0x04){
                case 0:
                    System.out.println("Copy of ori");
                    break;
                case 4:
                    System.out.println("Original");
                    break;
            }
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

        System.out.println("TAGv2:" + tagID3V2);
        System.out.println("TAGv1:" + tagID3V1);
    }
}
