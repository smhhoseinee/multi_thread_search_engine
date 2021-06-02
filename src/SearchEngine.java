/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 *
 * @author Mohamad Hosein Hoseinee
 */
public class SearchEngine {

    /**
     * @param args the command line arguments
     */
    static int numberOfThreads = 4;

    public static void main(String[] args) {

        String textPath = "./src/res/input.txt";
        String keyWordsPath = "./src/res/words.txt";

//        fileSample fs = new fileSample();
//        Scanner scanner = new Scanner(System.in);
//        String[] text = readAllBytes(textPath).toLowerCase(Locale.ROOT).replaceAll("[.,]*", "").split(" ");
        String stringOfKeyWords = readAllBytes(keyWordsPath).toLowerCase(Locale.ROOT);
        System.out.println("st = " + stringOfKeyWords);
        String[] keyWords = stringOfKeyWords.split("\r\n");

//        String stringOfText = readAllBytes(textPath).toLowerCase(Locale.ROOT);
//        String[] text = stringOfText.split("\r\n");
//        File textFile = new File(textPath);
//        FileReader fr = null;
//        try {
//            fr = new FileReader(textFile);
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        BufferedReader br = new BufferedReader(fr);
        for (String word : keyWords) {
            System.out.println(word);
        }

        //only a single thread 
//        SingleThreadSearchWithoutSemaphore thread = new SingleThreadSearchWithoutSemaphore(br, keyWords, "./src/res/result.txt", 1);
        //Semaphore Threads 
        int numberOfWordsGivenToEachThread = keyWords.length / numberOfThreads;
        int remainedNumberOfWordsGivenToEachThread = keyWords.length % numberOfThreads;

        String[] threadWords;
        SemaphoreThread[] thread = new SemaphoreThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
//            threadWords[i] = 
            if (i == numberOfThreads) {
                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, keyWords.length);
            } else {
                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, numberOfWordsGivenToEachThread * (i + 1));
            }
            thread[i] = new SemaphoreThread(textPath, threadWords, "./src/res/result.txt", i, 1);
            thread[i].start();
        }

    }

    private static String readAllBytes(String filePath) {
        String content = "";

        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content;
    }

    public static void test() {

    }

}
