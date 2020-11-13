package com;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        FileInputStream fileInput = null;

        boolean isRead = false;
        int[] usign_bytes = new int[4];

        try{
            fileInput = new FileInputStream("C:\\Prace - UTP\\Multimedialne\\Lab03\\audio.mp3");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            byte[] input = fileInput.readNBytes(4);
            for (int i=0; i<4;i++) {
                int tmp = (int) (input[i] & 0xff);
                usign_bytes[i] = tmp;
            }
            isRead = true;
            fileInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (isRead){
            String mpeg_ver = null;
            switch(usign_bytes[1] & 0x18){
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
            switch(usign_bytes[1] & 0x06){
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
            switch(usign_bytes[1] & 0x01){
                case 0:
                    System.out.println("Protected by CRC");
                    break;
                case 1:
                    System.out.println("Not protected");
                    break;
            }
            switch(usign_bytes[2] & 0x0c){
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
            switch(usign_bytes[3] & 0xc0){
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
            switch(usign_bytes[3] & 0x08){
                case 0:
                    System.out.println("Not copyrighted");
                    break;
                case 8:
                    System.out.println("Copyrighted");
                    break;
            }
            switch(usign_bytes[3] & 0x04){
                case 0:
                    System.out.println("Copy of ori");
                    break;
                case 4:
                    System.out.println("Original");
                    break;
            }
            System.out.println("Byte 0:"+usign_bytes[0]+" Byte 1:"+usign_bytes[1]+" Byte 2:"+usign_bytes[2]+" Byte 3:"+usign_bytes[3]);
        }
        // write your code here
    }
}
