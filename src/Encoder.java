import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by zbk on 26/3/17.
 */
public class Encoder {

    String file = "sample_input_large.txt";

    public Encoder(){

    }

    public Encoder(String file){
        this.file = file;
    }

    public void encode() throws IOException{
        long start = System.currentTimeMillis();
        FrequencyTable ft = new FrequencyTable(file);
        ArrayList<Integer> input = ft.initialize();
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        HuffmanTree ht2 = new HuffmanTree();
        ht2.initialize(ft, MaxPriorityQueue.PAIRINGHEAP);
        HashMap<Integer, String> symTab = ht2.getSymbolTable();
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        FileWriter fileWriter = new FileWriter("code_table.txt");
        BufferedWriter out = new BufferedWriter(fileWriter);
        Iterator<Map.Entry<Integer, String>> it = symTab.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, String> pairs = it.next();
            out.write(pairs.getKey() + "\t" + pairs.getValue() + "\n");
        }
        out.close();
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        StringBuilder binaryString = new StringBuilder("");
        for(Integer i:input)
            binaryString.append(symTab.get(i));
        byte[] barray = new byte[binaryString.length()/8];
        for(int i = 0; i < binaryString.length()/8; i++){
            barray[i] = (byte) Short.parseShort(binaryString.substring(8*i,8*(i+1)),2);
        }
        System.out.println(System.currentTimeMillis() - start);

        start = System.currentTimeMillis();
        OutputStream output = null;
        output = new BufferedOutputStream(new FileOutputStream("encoded.bin"));
        output.write(barray);
        output.close();
        System.out.println(System.currentTimeMillis() - start);
    }

}
