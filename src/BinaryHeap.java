import java.util.ArrayList;

/**
 * Created by zbk on 25/3/17.
 */
public class BinaryHeap extends MaxPriorityQueue {

    private ArrayList<HuffmanTree> heap;

    public BinaryHeap(){
        heap = new ArrayList<>();
    }

    @Override
    public void heapify(FrequencyTable ft){
        int size = ft.get_size();
        for(int i = 0;i < size; i++){
            if(ft.get(i)!=0)
                heap.add(new HuffmanTree(i,ft.get(i)));
        }
        for(int i = parentIndex(size-1); i >= 0; i--){
            bubbleDown(i);
        }
    }

    @Override
    public void insert(HuffmanTree r){
        heap.add(r);
        bubbleUp(heap.size()-1);
    }

    @Override
    public HuffmanTree pop(){
        HuffmanTree ht =peek();
        swap(0,heap.size()-1);
        heap.remove(heap.size()-1);
        bubbleDown(0);
        return ht;
    }

    public HuffmanTree peek(){
        return heap.get(0);
    }

    private void bubbleUp(int index){
        while (hasParent(index) && heap.get(parentIndex(index)).isGreaterThan(heap.get(index))) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void bubbleDown(int index){
        while (hasLeftChild(index))
        {
            if(!hasRightChild(index)){
                if(heap.get(index).isGreaterThan(heap.get(leftChildIndex(index)))){
                    swap(index, leftChildIndex(index));
                    index = leftChildIndex(index);
                }
                else{
                    return;
                }
            }
            else if (heap.get(leftChildIndex(index)).isLessThan(heap.get(rightChildIndex(index)))) {
                if(heap.get(index).isGreaterThan(heap.get(leftChildIndex(index)))){
                    swap(index, leftChildIndex(index));
                    index = leftChildIndex(index);
                }
                else{
                    return;
                }
            }
            else if(hasRightChild(index) && heap.get(rightChildIndex(index)).isLessThanEqualTo(heap.get(leftChildIndex(index)))) {
                if(heap.get(index).isGreaterThan(heap.get(rightChildIndex(index)))){
                    swap(index, rightChildIndex(index));
                    index = rightChildIndex(index);
                }
                else{
                    return;
                }
            }
            else
                return;
        }
    }

    private void swap(int i, int j) {
        HuffmanTree tmp;
        tmp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,tmp);
    }

    private int leftChildIndex(int i){
        return 2*i+1;
    }

    private int rightChildIndex(int i){
        return 2*i+2;
    }

    private int parentIndex(int i){
        return (i-1)/2;
    }

    private boolean hasParent(int i){ return parentIndex(i) >= 0;    }

    private boolean hasLeftChild(int i){ return leftChildIndex(i) < heap.size();}

    private boolean hasRightChild(int i){ return rightChildIndex(i) < heap.size(); }

    @Override
    public int getSize(){
        return heap.size();
    }

    @Override
    public boolean isNotEmpty() {
        return !heap.isEmpty();
    }

    @Override
    public void printHeap() {
        System.out.println(heap);
    }
}
