import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by zbk on 26/3/17.
 */
public class HuffmanTree implements Comparable {
    HuffmanTree leftSubTree, rightSubTree;
    Record data;
    HuffmanTree parent;
    private String huffmanCode;
    public int dec_data = -1;


    public HuffmanTree(){
        this.leftSubTree = null;
        this.rightSubTree = null;
        this.data = null;
        this.parent = null;
        this.dec_data = -1;
    }

    public HuffmanTree(int numb, int freq) {
        this.data = new Record(numb, freq);
        this.leftSubTree = null;
        this.rightSubTree = null;
    }

    public HuffmanTree(HuffmanTree ht1, HuffmanTree ht2) {
        if(ht1.isGreaterThan(ht2))
        {
            this.leftSubTree = ht2;
            this.rightSubTree = ht1;
        }
        else
        {
            this.leftSubTree = ht1;
            this.rightSubTree = ht2;
        }
        this.leftSubTree.parent = this;
        this.rightSubTree.parent = this;
        data = new Record(ht1.data, ht2.data);
    }

    public void initialize(FrequencyTable ft, int priorityQueue){
        MaxPriorityQueue maxPriorityQueue = null;
        switch(priorityQueue){
            case MaxPriorityQueue.BINARYHEAP:
                maxPriorityQueue = new BinaryHeap();
                break;
            case MaxPriorityQueue.FOURWAYHEAP:
                maxPriorityQueue = new FourWayHeap();
                break;
            case MaxPriorityQueue.PAIRINGHEAP:
                maxPriorityQueue = new PairingHeap();
                break;
        }
        long start = System.currentTimeMillis();
        maxPriorityQueue.heapify(ft);
        System.out.println("bin tree heapify" + (System.currentTimeMillis() - start));
        while(maxPriorityQueue.getSize()>1){
            HuffmanTree ht1 = maxPriorityQueue.pop();
            HuffmanTree ht2 = maxPriorityQueue.pop();
            // Update Key
            maxPriorityQueue.insert(merge(ht1,ht2));
        }

        HuffmanTree ht = maxPriorityQueue.pop();
        this.leftSubTree = ht.leftSubTree;
        this.rightSubTree = ht.rightSubTree;
        this.data = ht.data;


    }

    public HashMap<Integer, String> getSymbolTable(){
        HashMap<Integer, String> symtab = new HashMap<>();
        LinkedList<HuffmanTree> htList = new LinkedList<>();
        this.huffmanCode = "";
        htList.add(this);
        HuffmanTree ht;
        while(!htList.isEmpty()){
            ht = htList.remove(0);
            ht.leftSubTree.huffmanCode = ht.huffmanCode + "0";
            ht.rightSubTree.huffmanCode = ht.huffmanCode + "1";
            if(ht.leftSubTree.data.getNumb() == -1){
                htList.add(ht.leftSubTree);
            }
            else{
                symtab.put(ht.leftSubTree.data.getNumb(),ht.leftSubTree.huffmanCode);
            }
            if(ht.rightSubTree.data.getNumb() == -1){
                htList.add(ht.rightSubTree);
            }
            else{
                symtab.put(ht.rightSubTree.data.getNumb(),ht.rightSubTree.huffmanCode);
            }
        }
        return symtab;
    }

    private HuffmanTree merge(HuffmanTree ht1, HuffmanTree ht2) {
        return new HuffmanTree(ht1,ht2);
    }

    public void getHuffmanCodes(){
        int k = 0;
        if(this.hasData())
        {
        }
    }

    public boolean isGreaterThan(Object that) {
        return data.compareTo(((HuffmanTree) that).data) > 0;
    }

    public boolean isLessThan(Object that) {
        return data.compareTo(((HuffmanTree) that).data) < 0;
    }

    public boolean isGreaterThanEqualTo(Object that) {
        return data.compareTo(((HuffmanTree) that).data) >= 0;
    }

    public boolean isLessThanEqualTo(Object that) {
        return data.compareTo(((HuffmanTree) that).data) <= 0;
    }

    public boolean hasData(){
        return null != data;
    }

    @Override
    public String toString() {
        if(null!=data)
            return data.toString();
        return null;
    }

    @Override
    public int compareTo(Object that) {
        return this.data.compareTo(((HuffmanTree) that).data);
    }
}
