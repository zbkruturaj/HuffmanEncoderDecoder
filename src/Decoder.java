import java.io.*;
import java.util.*;

/**
 * Created by zbk on 28/3/17.
 */
public class Decoder {
    String enc_file = "encoded.bin";
    String code_table = "code_table.txt";
    public Decoder(){

    }

    public Decoder(String enc_file, String code_table){

    }

    public void decode() throws IOException {
        long start = System.currentTimeMillis();
        File encFile = new File(enc_file);
        byte[] encoded = new byte[(int)encFile.length()];
        int totalBytesRead = 0;
        InputStream input = null;
        input = new BufferedInputStream(new FileInputStream(encFile));
        while(totalBytesRead < encoded.length){
            int bytesRemaining = encoded.length - totalBytesRead;
            int bytesRead = input.read(encoded, totalBytesRead, bytesRemaining);
            if (bytesRead > 0){
                totalBytesRead = totalBytesRead + bytesRead;
            }
        }
        input.close();
        System.out.println(System.currentTimeMillis() - start);


        HuffmanTree decodeTree = new HuffmanTree();
        start = System.currentTimeMillis();
        FileReader fileReader = new FileReader(code_table);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        int value;
        String key;
        HashMap<String,Integer> reverseCodeTable = new HashMap<>();
        String[] strArr;
        HuffmanTree currNode;
        String line = bufferedReader.readLine();
        while(null!=line){
            strArr = line.split("\t");
            currNode = decodeTree;
            for(int i = 0; i < strArr[1].length(); i++){
                switch(strArr[1].charAt(i)){
                    case '0':
                        if(currNode.leftSubTree==null)
                            currNode.leftSubTree = new HuffmanTree();
                        currNode = currNode.leftSubTree;
                        break;
                    case '1':
                        if(currNode.rightSubTree==null)
                            currNode.rightSubTree = new HuffmanTree();
                        currNode = currNode.rightSubTree;
                        break;
                    default:
                        break;
                }
            }
            currNode.dec_data = Integer.parseInt(strArr[0]);
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        System.out.println(System.currentTimeMillis() - start);





//        start = System.currentTimeMillis();
//        StringBuilder sb = new StringBuilder();
//        for(byte b: encoded){
////            int i = Byte.toUnsignedInt(b);
//
//            int zeros = Integer.numberOfLeadingZeros(0xff&b)-24;
//            for(int i=0; i<zeros; i++){
//                sb.append('0');
//            }
//            sb.append(Integer.toBinaryString(0xff&b));
//        }
//        System.out.println(System.currentTimeMillis() - start);



//        System.out.println(sb);
        start = System.currentTimeMillis();
        FileWriter fileWriter = new FileWriter("decoded.txt");
        BufferedWriter out = new BufferedWriter(fileWriter);
        BitIterator bitIterator = new BitIterator(encoded);
        HuffmanTree traversalTree = decodeTree;
        while(bitIterator.hasNext()){
            if(bitIterator.getNextBit()) {
                traversalTree = traversalTree.rightSubTree;
            }
            else {
                traversalTree = traversalTree.leftSubTree;
            }
            if(-1 != traversalTree.dec_data){
                out.write(traversalTree.dec_data+"\n");
                traversalTree = decodeTree;
            }
        }

        out.close();
        System.out.println("\n" +(System.currentTimeMillis() - start));
    }
}

class BitIterator{
    byte[] byteArray;
    int arrayIndex = 0;
    int bitIndex = 0;
    final static char[] masks = {0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};

    public BitIterator(byte[] byteArray){
        this.byteArray = byteArray;
    }

    boolean getNextBit(){
        if(bitIndex==8) {
            arrayIndex++;
            bitIndex=0;
        }
        return (byteArray[arrayIndex]&masks[bitIndex++]) > 0;
    }

    boolean hasNext() {
        return !(bitIndex == 8 && arrayIndex == (byteArray.length-1));
    }
}
