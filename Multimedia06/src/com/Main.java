package com;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;

public class Main {

    public static File file = new File("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc.wav");
    public static long fileSize = file.length();
    public static FileInputStream fileInput = null;
    public static FileOutputStream fileOutput = null;
    public static byte[] data = new byte[Math.toIntExact(fileSize)];
    public static byte[] monoToStereo = new byte[(Math.toIntExact(fileSize) * 2) - 44];

    public static String bytesToString(int[] header, int startRange, int stopRange) {
        char[] chars = new char[stopRange - startRange];
        for (int i = 0; i < stopRange - startRange; i++) {
            chars[i] = (char) header[i + startRange];
        }
        String result = new String(chars);
        return result;
    }

    public static int intTabToIntValue(int[] header, int startRange, int stopRange) {
        int result = 0;
        for (int i = 0; i < stopRange - startRange; i++) {
            result += header[i + startRange] << (8 * i);
        }
        return result;
    }

    public static int bytesToInt(byte[] header, int startRange, int stopRange) {
        int result = 0;
        for (int i = 0; i < stopRange - startRange; i++) {
            result += (int) (header[i + startRange] & 0xff) << (8 * i);
        }
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

    public static void readRiffHeader(File file) throws IOException {
        try {
            fileInput = new FileInputStream(file);
            byte[] input = fileInput.readNBytes(12);
            int[] wavHeader = new int[12];
            for (int i = 0; i < 12; i++) {
                wavHeader[i] = (int) (input[i] & 0xff);
            }
            System.out.println("Chunk id: " + bytesToString(wavHeader, 0, 4));
            System.out.println("Chunk size :" + intTabToIntValue(wavHeader, 4, 8) + " B");
            System.out.println("Format is: " + bytesToString(wavHeader, 8, 12));

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void readFmtHeader(File file) throws IOException {
        try {
            fileInput = new FileInputStream(file);
            fileInput.skipNBytes(12);
            byte[] input = fileInput.readNBytes(26);
            int[] fmtHeader = new int[26];
            for (int i = 0; i < 26; i++) {
                fmtHeader[i] = (int) (input[i] & 0xff);
            }
            System.out.println("Subchunk id: " + bytesToString(fmtHeader, 0, 4));
            System.out.println("Subchunk size : " + intTabToIntValue(fmtHeader, 4, 8) + " B");
            int extraParamExists = intTabToIntValue(fmtHeader, 8, 10);
            System.out.println("Audio format: " + intTabToIntValue(fmtHeader, 8, 10));
            System.out.println("Num channels: " + intTabToIntValue(fmtHeader, 10, 12));
            System.out.println("Sample rate: " + intTabToIntValue(fmtHeader, 12, 16));
            System.out.println("Byte rate: " + intTabToIntValue(fmtHeader, 16, 20));
            System.out.println("Block align: " + intTabToIntValue(fmtHeader, 20, 22));
            System.out.println("Bits per sample: " + intTabToIntValue(fmtHeader, 22, 24));
            if (extraParamExists <= 0) {
                System.out.println("Extra param size: " + intTabToIntValue(fmtHeader, 24, 26) + " B");
            } else {
                System.out.println("No Extra params!");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void readDataHeader() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            fileInput.skipNBytes(24);
            byte[] input = fileInput.readNBytes(8);
            int[] dataHeader = new int[8];
            for (int i = 0; i < 8; i++) {
                dataHeader[i] = (int) (input[i] & 0xff);
            }
            System.out.println("Subchunk 2id: " + bytesToString(dataHeader, 0, 4));
            System.out.println("Subchunk 2size: " + intTabToIntValue(dataHeader, 4, 8) + " B");

        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void readAllData() throws IOException {
        try {
            fileInput = new FileInputStream(file);
            data = fileInput.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileInput.close();
    }

    public static void monoToStereo() throws IOException {
        fileOutput = new FileOutputStream("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc_stereo.wav");
//        TODO can be accessed via opening stream without loading whole file to data tab
//        FileChannel ch = fileOutput.getChannel();
        if (data[22] == 1) {

            data[22] = 2;

            int newByteRate = bytesToInt(data, 28, 32) * data[22];
            int newBlockAlign = bytesToInt(data, 32, 34) * data[22];
            data[31] = (byte) (newByteRate >>> 24);
            data[30] = (byte) (newByteRate >>> 16);
            data[29] = (byte) (newByteRate >>> 8);
            data[28] = (byte) newByteRate;
            data[32] = (byte) newBlockAlign;
            System.out.println("Num channels: " + data[22] + " Check byteRate function: " +
                    bytesToInt(data, 28, 32) + " , " + newBlockAlign);

            int newChunkSize = bytesToInt(data, 4, 8) * data[22];
            int newSubChunkSize2 = bytesToInt(data, 40, 44) * data[22];
            data[7] = (byte) (newChunkSize >>> 24);
            data[6] = (byte) (newChunkSize >>> 16);
            data[5] = (byte) (newChunkSize >>> 8);
            data[4] = (byte) newChunkSize;


            data[43] = (byte) (newSubChunkSize2 >>> 24);
            data[42] = (byte) (newSubChunkSize2 >>> 16);
            data[41] = (byte) (newSubChunkSize2 >>> 8);
            data[40] = (byte) newSubChunkSize2;
            System.out.println("New chunk size: " + newChunkSize + "new subchunk 2 : " + newSubChunkSize2);
//            ch.write(ByteBuffer.wrap(data));
            byte[] monoData = Arrays.copyOfRange(data, 44, Math.toIntExact(fileSize));
            byte[] stereoData = new byte[monoData.length * 2];
            for (int i = 0; i < 44; i++) {
                monoToStereo[i] = data[i];
            }
            for (int i = 0; i < stereoData.length; i += 4) {
                stereoData[i] = monoData[i / 2];
                stereoData[i + 1] = monoData[(i / 2) + 1];
                stereoData[i + 2] = stereoData[i];
                stereoData[i + 3] = stereoData[i + 1];
            }
            System.arraycopy(stereoData, 0, monoToStereo, 44, stereoData.length);
            fileOutput.write(monoToStereo);
            System.out.println("New stereo file saved!");
            fileOutput.close();
        } else System.out.println("ALREADY STEREO FILE");
    }

    public static void stereoToMono() {
//    TODO average from two channels to one
        if (data[22] == 2) {
            data[22] = 1;
            int newByteRate = bytesToInt(data, 28, 32) / 2;
            int newBlockAlign = bytesToInt(data, 32, 34) / 2;
            data[31] = (byte) (newByteRate >>> 24);
            data[30] = (byte) (newByteRate >>> 16);
            data[29] = (byte) (newByteRate >>> 8);
            data[28] = (byte) newByteRate;
            data[32] = (byte) newBlockAlign;
            System.out.println("Num channels: " + data[22] + " Check byteRate function: " + bytesToInt(data, 28, 32) + " , " + newBlockAlign);
//            ch.write(ByteBuffer.wrap(data));
            byte[] monoData = Arrays.copyOfRange(data, 44, Math.toIntExact(fileSize));
            byte[] stereoData = new byte[monoData.length * 2];
            for (int i = 0; i < 44; i++) {
                monoToStereo[i] = data[i];
            }
            for (int i = 0; i < stereoData.length; i += 4) {
                stereoData[i] = monoData[i / 2];
                stereoData[i + 1] = monoData[(i / 2) + 1];
                stereoData[i + 2] = stereoData[i];
                stereoData[i + 3] = stereoData[i + 1];
            }
            System.out.println(stereoData.length + "," + monoToStereo.length);
            System.arraycopy(stereoData, 0, monoToStereo, 44, stereoData.length);
//            fileOutput.write(monoToStereo);
            System.out.println("New stereo file saved!");
//            fileOutput.close();
        } else System.out.println("ALREADY MONO FILE");
    }

    public static void main(String[] args) throws IOException {
        readRiffHeader(file);
        readFmtHeader(file);
//        readDataHeader();
        readAllData();
        monoToStereo();
        System.out.println("======================================================");
        readRiffHeader(new File("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc_stereo.wav"));
        readFmtHeader(new File("C:\\Prace - UTP\\Multimedialne\\Lab05\\abc_stereo.wav"));
    }
}
