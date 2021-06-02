/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
import java.util.concurrent.Semaphore;

/**
 *
 * @author Mohamad Hosein Hoseinee
 */
public class SearchEngine {

    /**
     * @param args the command line arguments
     */
    static int numberOfThreads = 4;

    public static void main(String[] args) throws InterruptedException, IOException {
        final long startTime = System.nanoTime();

        String textPath = "./src/res/shakespeare.txt";
        String keyWordsPath = "./src/res/words.txt";
        String resultPath;

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
//
//
//
//
//        //only a single thread 
//        resultPath = "./src/res/singleThreadResult.txt";
//        SingleThreadSearchWithoutSemaphore thread = new SingleThreadSearchWithoutSemaphore(textPath, keyWords, resultPath, 1);
//        thread.start();
//        thread.join();
//
//
//
//
//        //Threads without semaphore or mutex lock (possibly race condition)
//        int numberOfWordsGivenToEachThread = keyWords.length / numberOfThreads;
//        int remainedNumberOfWordsGivenToEachThread = keyWords.length % numberOfThreads;
//        resultPath = "./src/res/multiThreadsWithoutLockResult.txt";
//        String[] threadWords;
//        SingleThreadSearchWithoutSemaphore[] thread = new SingleThreadSearchWithoutSemaphore[numberOfThreads];
//        for (int i = 0; i < numberOfThreads; i++) {
////            threadWords[i] = 
//            if (i == numberOfThreads - 1) {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, keyWords.length);
//            } else {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, numberOfWordsGivenToEachThread * (i + 1));
//            }
//            thread[i] = new SingleThreadSearchWithoutSemaphore(textPath, threadWords, resultPath, i);
//            thread[i].start();
//        }
//        for (int i = 0; i < numberOfThreads; i++) {
//            thread[i].join();
//        }
//
//
//        //Semaphore Threads 
//        resultPath = "./src/res/SemaphoreResult.txt";
//        Semaphore semaphore = new Semaphore(1);
//
//        int numberOfWordsGivenToEachThread = keyWords.length / numberOfThreads;
//        int remainedNumberOfWordsGivenToEachThread = keyWords.length % numberOfThreads;
//
//        String[] threadWords;
//        SemaphoreThread[] thread = new SemaphoreThread[numberOfThreads];
//
//        for (int i = 0; i < numberOfThreads; i++) {
////            threadWords[i] = 
//            if (i == numberOfThreads - 1) {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, keyWords.length);
//            } else {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, numberOfWordsGivenToEachThread * (i + 1));
//            }
//            thread[i] = new SemaphoreThread(textPath, threadWords, resultPath, i, semaphore);
//            thread[i].start();
//        }
//        for (int i = 0; i < numberOfThreads; i++) {
//            thread[i].join();
//        }
//
//
//
//
//        //Mutex Threads 
//        resultPath = "./src/res/MutexResult.txt";
//        Mutex mutex = new Mutex();
//
//        int numberOfWordsGivenToEachThread = keyWords.length / numberOfThreads;
//        int remainedNumberOfWordsGivenToEachThread = keyWords.length % numberOfThreads;
//
//        String[] threadWords;
//        MutexThread[] thread = new MutexThread[numberOfThreads];
//
//        for (int i = 0; i < numberOfThreads; i++) {
////            threadWords[i] = 
//            if (i == numberOfThreads - 1) {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, keyWords.length);
//            } else {
//                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, numberOfWordsGivenToEachThread * (i + 1));
//            }
//            thread[i] = new MutexThread(textPath, threadWords, resultPath, i, mutex);
//            thread[i].start();
//        }
//        for (int i = 0; i < numberOfThreads; i++) {
//            thread[i].join();
//        }
//
//
//
//
        //TrieTree Threads 
        resultPath = "./src/res/TrieTreeResult.txt";
        Semaphore semaphore = new Semaphore(1);

        int numberOfWordsGivenToEachThread = keyWords.length / numberOfThreads;
        int remainedNumberOfWordsGivenToEachThread = keyWords.length % numberOfThreads;

        String[] threadWords;
        TrieTreeSemaphoreThread[] thread = new TrieTreeSemaphoreThread[numberOfThreads];

        for (int i = 0; i < numberOfThreads; i++) {
            if (i == numberOfThreads - 1) {
                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, keyWords.length);
            } else {
                threadWords = Arrays.copyOfRange(keyWords, i * numberOfWordsGivenToEachThread, numberOfWordsGivenToEachThread * (i + 1));
            }
            thread[i] = new TrieTreeSemaphoreThread(textPath, threadWords, resultPath, i, semaphore);
            thread[i].start();
        }
        for (int i = 0; i < numberOfThreads; i++) {
            thread[i].join();
        }
//
//
//
//
        final long endTime = System.nanoTime();
        long timeTaken = (endTime - startTime);
        FileWriter myWriter = new FileWriter(resultPath, true);
        String output = "\nTime taken = " + timeTaken + "\n";
        String output2 = "" + timeTaken / 1000000 + "ms\n";
        myWriter.append(output);
        myWriter.append(output2);
        myWriter.close();

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
