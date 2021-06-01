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

/**
 *
 * @author No1
 */
public class SearchEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

//        String basePath = new File("C:\\Users\\No1\\OneDrive\\edu\\OS\\project\\emSearchEngine\\sample").getAbsolutePath();
//        System.out.println(basePath);
        String textPath = "./src/res/input.txt";
        String keyWordsPath = "./src/res/words.txt";

        
        fileSample fs = new fileSample();
//        Scanner scanner = new Scanner(System.in);

        String[] words = {"abc", "def"};

        Thread thread = new SingleThread();

        String[] text = readAllBytes(textPath).toLowerCase(Locale.ROOT).replaceAll("[.,]*", "").split(" ");
        String[] keyWords = readAllBytes(keyWordsPath).toLowerCase(Locale.ROOT).replaceAll("[.,]*", "").split(" ");

//        System.out.println("text:");
//        for (String string : text) {
//            System.out.println(string);
//        }
//        System.out.println("");
//        System.out.println("");
        for (String string : keyWords) {
            System.out.println(string);
        }

//        thread.start();
//        fs.getNameOfFile(text);
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
