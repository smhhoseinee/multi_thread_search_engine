package src;

import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class FileWordSearch {
    public static void main(String[] args) throws IOException, InterruptedException {
        final long startTime = System.nanoTime();

        //read input files **shearch words
        File f1=new File("..\\sample\\words.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        String word;
        String all_words = "";
        int words_count = 0;
        while((word = br.readLine()) != null) {
            word = word.toLowerCase(Locale.ROOT);
            all_words += word + " ";
            words_count++;
        }
        String[] input_words = all_words.toLowerCase(Locale.ROOT).split(" ");

        Semaphore semaphore = new Semaphore(1);
        Mutex mutex = new Mutex();

        //semaphre driver
        /*ThreadHandelSemaphore first_thread = new ThreadHandelSemaphore(semaphore, input_words, 0, words_count/4, 1);
        ThreadHandelSemaphore second_thread = new ThreadHandelSemaphore(semaphore, input_words, words_count/4, (words_count/4)*2, 2);
        ThreadHandelSemaphore third_thread = new ThreadHandelSemaphore(semaphore, input_words, (words_count/4)*2, (words_count/4)*3, 3);
        ThreadHandelSemaphore forth_thread = new ThreadHandelSemaphore(semaphore, input_words, (words_count/4)*3, words_count, 4);*/

        //mutex driver
        /*ThreadHandelMutex first_thread = new ThreadHandelMutex(mutex, input_words, 0, words_count/4, 1);
        ThreadHandelMutex second_thread = new ThreadHandelMutex(mutex, input_words, words_count/4, (words_count/4)*2, 2);
        ThreadHandelMutex third_thread = new ThreadHandelMutex(mutex, input_words, (words_count/4)*2, (words_count/4)*3, 3);
        ThreadHandelMutex forth_thread = new ThreadHandelMutex(mutex, input_words, (words_count/4)*3, words_count, 4);*/

        //semaphore trie tree
        ThraedHandlerTrieTree first_thread = new ThraedHandlerTrieTree(semaphore, input_words, 0, words_count/4, 1);
        ThraedHandlerTrieTree second_thread = new ThraedHandlerTrieTree(semaphore, input_words, words_count/4, (words_count/4)*2, 2);
        ThraedHandlerTrieTree third_thread = new ThraedHandlerTrieTree(semaphore, input_words, (words_count/4)*2, (words_count/4)*3, 3);
        ThraedHandlerTrieTree forth_thread = new ThraedHandlerTrieTree(semaphore, input_words, (words_count/4)*3, words_count, 4);

        first_thread.start();
        second_thread.start();
        third_thread.start();
        forth_thread.start();

        final long endTime = System.nanoTime() - startTime;
        //System.out.println("Mutex: " + endTime + "ns");
        //System.out.println("Semaphore: " + endTime + "ns");
    }


}
