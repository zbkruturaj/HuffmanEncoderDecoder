import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by zbk on 17/3/17.
 */
public class FrequencyTable {

    public int[] freq_table;
    private String input_file = "sample_input_large.txt";

    public FrequencyTable(String file){
        // Frequency table is initialized with 0s by default in java because it is a field.
        // Read input file and update frequency table
        this.input_file = file;
        this.freq_table = new int[1000000];
    }

    public FrequencyTable(String file, int size){
        // Frequency table is initialized with 0s by default in java because it is a field.
        // Read input file and update frequency table
        this.input_file = file;
        this.freq_table = new int[size];
    }

    public ArrayList<Integer> initialize(){
        ArrayList<Integer> result = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(input_file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int tmp;
            String line = bufferedReader.readLine();
            while(null!=line){
                         tmp = Integer.parseInt(line);
                         freq_table[tmp]++;
                         result.add(tmp);
                         line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int get_size() { return freq_table.length; }

    public int get(int i){
        return freq_table[i];
    }
}
