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
import java.nio.file.Path;
import java.util.Locale;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author No1
 */
public class MutexThread extends Thread {

//    BufferedReader br;
    String textPath;
    String[] givenWords;
    String resultPath;
    int id;
    Mutex mutex;

    public MutexThread(String textPath, String[] words, String resultPath, int id, Mutex mutex) {
//        this.br = br;
        this.textPath = textPath;
        this.givenWords = words;
        this.resultPath = resultPath;
        this.id = id;
        this.mutex = mutex;

    }

    @Override
    public void run() {

        File textFile = new File(textPath);
        FileReader fr = null;
        try {
            fr = new FileReader(textFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SearchEngine.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader br = new BufferedReader(fr);

        int lineCounter = 0;
        System.out.println("start");
        String currentLine;

        try {
            while ((currentLine = br.readLine()) != null) {
                lineCounter++;
//                currentLine = currentLine.replaceAll("[.,]*", "").toLowerCase(Locale.ROOT);
                currentLine = currentLine.toLowerCase(Locale.ROOT);
                for (String word : givenWords) {
                    if (currentLine.contains(word)) {
                        String result = word + " found in line " + lineCounter + " in thread " + id + " at " + java.time.LocalTime.now();

                        mutex.acquire();

                        FileWriter fileWriter = new FileWriter(resultPath, true);
                        fileWriter.append(result + " wrote at " + java.time.LocalTime.now() + "\n");
                        fileWriter.close();

                        mutex.release();
                    }
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(MutexThread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(MutexThread.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
