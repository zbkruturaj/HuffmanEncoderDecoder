import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zbk on 25/3/17.
 */
public class Main {

    public static void main(String args[]){
        long time = System.currentTimeMillis();
        Encoder e = new Encoder();
        Decoder d = new Decoder();
        try {
            e.encode();
            d.decode();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        System.out.println(time-System.currentTimeMillis());

//        List<Record> records = new ArrayList<>();
//        records.add(new Record(1,2));
//        records.add(new Record(2,1));
//        records.add(new Record(3,7));
//
//        Collections.sort(records);
//
//        for(Record r:records){
//            System.out.println(r);
//        }
////
//        FrequencyTable ft = new FrequencyTable("sample_input_small.txt");
//        ft.initialize();
//        for(int i = 0;i < ft.get_size(); i++){
//            if(ft.get(i)!=0)
//                System.out.println(i + " " +ft.get(i));
//        }
//        BinaryHeap bh = new BinaryHeap();
//        bh.heapify(ft);
//        for(int i = 0; i < 6; i++)
//            System.out.println(bh.pop());
//
//
//        FourWayHeap fwh = new FourWayHeap();
//        fwh.heapify(ft);
//        for(int i = 0; i < 6; i++)
//            System.out.println(fwh.pop());
//
//
//        System.out.println("PairingHeap");
//        PairingHeap ph = new PairingHeap();
//        ph.heapify(ft);
//        for(int i = 0; i < 6; i++)
//            System.out.println(ph.pop());
//
//        HuffmanTree ht = new HuffmanTree();
//        ht.initialize(ft, MaxPriorityQueue.FOURWAYHEAP);
//        System.out.print(ht.getSymbolTable());
//        HuffmanTree ht1 = new HuffmanTree();
//        ht1.initialize(ft, MaxPriorityQueue.BINARYHEAP);
////        System.out.print(ht1.getSymbolTable());
//        HuffmanTree ht2 = new HuffmanTree();
//        ht2.initialize(ft, MaxPriorityQueue.PAIRINGHEAP);
//        System.out.print(ht2.getSymbolTable());
    }
}