/**
 * Created by zbk on 26/3/17.
 */
public abstract class MaxPriorityQueue {
    public static final int BINARYHEAP = 0;
    public static final int FOURWAYHEAP = 1;
    public static final int PAIRINGHEAP = 2;

    public abstract void heapify(FrequencyTable ft);
    public abstract void insert(HuffmanTree r);
    public abstract HuffmanTree pop();
    public abstract void printHeap();

    public abstract boolean isNotEmpty();

    public abstract int getSize();
}
