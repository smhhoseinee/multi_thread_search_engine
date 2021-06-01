import com.sun.corba.se.impl.orbutil.concurrent.Mutex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class ThreadHandelMutex extends Thread{
    private String[] input_words = null;
    private  int start = 0;
    private  int finish = 0;
    private int thread_number = 0;
    private Mutex mutex;

    public ThreadHandelMutex(Mutex mutex, String[] input_words, int start, int finish, int thread_number) {
        this.input_words = input_words;
        this.start = start;
        this.finish = finish;
        this.mutex = mutex;
        this.thread_number = thread_number;
    }

    public void run(){
        try {
            //read file
            File f1=new File("C:\\Users\\Elahe\\IdeaProjects\\os_second_project\\sample\\input.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String[] words = null;
            String files_line;
            int line_count = 0 ;
            while((files_line = br.readLine()) != null) {
                files_line = files_line.replaceAll("[.,]*", "").toLowerCase(Locale.ROOT);
                line_count++;
                words = files_line.split(" ");

                for(int i = start ; i < finish ; i++){
                    for (String word : words) {
                        if (word.equals(input_words[i])) {
                            String output = "The word «" + input_words[i] + "» found in line " + line_count +
                                    " by thread " + thread_number + "found at " + java.time.LocalTime.now();
                            mutex.acquire();
                            FileWriter myWriter = new FileWriter("C:\\Users\\Elahe\\IdeaProjects\\os_second_project\\sample\\mutex-out.txt", true);
                            output += " saved at " + java.time.LocalTime.now() + "\n";
                            myWriter.append(output);
                            myWriter.close();
                            mutex.release();
                        }
                    }
                }
            }
            fr.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
