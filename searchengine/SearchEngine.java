/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchengine;

import java.io.File;

/**
 *
 * @author No1
 */
public class SearchEngine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File input = new File("C:\\Users\\No1\\OneDrive\\edu\\OS\\project\\searchEngine\\SearchEngine\\src\\searchengine\\testDir");
        fileSample fs = new fileSample();
        
        fs.getNameOfFile(input);
    }
    
}
