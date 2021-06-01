import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class ThraedHandlerTrieTree extends Thread{
    private String[] input_words = null;
    private  int start = 0;
    private  int finish = 0;
    private int thread_number = 0;
    private Semaphore semaphore;

    public ThraedHandlerTrieTree(Semaphore semaphore, String[] input_words, int start, int finish, int thread_number) {
        this.input_words = input_words;
        this.start = start;
        this.finish = finish;
        this.semaphore = semaphore;
        this.thread_number = thread_number;
    }

    public void run(){
        try{
            File f1 = new File("C:\\Users\\Elahe\\IdeaProjects\\os_second_project\\sample\\input.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            String files_line;
            String keys[] = null;
            int line_count = 0;
            while((files_line = br.readLine()) != null) {
                Trie searchTree = new Trie();
                line_count++;
                keys = files_line.toLowerCase(Locale.ROOT).replaceAll("[.,]*", "").split(" ");
                searchTree.root = new Trie.TrieNode();
                // Construct trie
                for (int i = 0; i < keys.length ; i++) {
                    searchTree.insert(keys[i]);
                }
                // Search for different keys
                for(int i = start ; i < finish ; i++) {
                    if (searchTree.search(input_words[i]) == true){
                        String output = "The word «" + input_words[i] + "» found in line " + line_count +
                                " by thread " + thread_number + "found at " + java.time.LocalTime.now();
                        semaphore.acquire();
                        FileWriter myWriter = new FileWriter("C:\\Users\\Elahe\\IdeaProjects\\os_second_project\\sample\\trie-semaphore-out.txt", true);
                        output += " saved at " + java.time.LocalTime.now() + "\n";
                        myWriter.append(output);
                        myWriter.close();
                        semaphore.release();
                    }
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
