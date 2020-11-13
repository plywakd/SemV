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
            System.out.println("Res:"+(usign_bytes[1] & 0x24));
            System.out.println("Res 2 :"+(usign_bytes[1] & 0x12));
            switch(usign_bytes[1] & 0x24){
                case 0:
                    System.out.println("MPEG Ver. 2.5");
                    break;
                case 8:
                    System.out.println("Reserved");
                    break;
                case 16:
                    System.out.println("MPEG Ver. 2");
                    break;
                case 24:
                    System.out.println("MPEG Ver. 1");
                    break;
            }
            switch(usign_bytes[1] & 0x12){
                case 0:
                    System.out.println("Reserved");
                    break;
                case 4:
                    System.out.println("Layer 3");
                    break;
                case 8:
                    System.out.println("Layer 2");
                    break;
                case 12:
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
            System.out.println("Byte 0:"+usign_bytes[0]+" Byte 1:"+usign_bytes[1]+" Byte 2:"+usign_bytes[2]+" Byte 3:"+usign_bytes[3]);
        }
        // write your code here
    }
}
