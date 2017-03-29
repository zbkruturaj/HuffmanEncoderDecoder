import java.util.ArrayList;

/**
 * Created by zbk on 25/3/17.
 */
public class FourWayHeap extends MaxPriorityQueue {

    private ArrayList<HuffmanTree> heap;
    private static int SHIFT = 3;

    public FourWayHeap(){
        heap = new ArrayList<>();
        for(int i = 0; i < SHIFT; i++){
            heap.add(new HuffmanTree(1000000+i, -1));
        }
    }

    @Override
    public void heapify(FrequencyTable ft){
        for(int i = 0;i < ft.get_size(); i++){
            if(ft.get(i)!=0)
                heap.add(new HuffmanTree(i,ft.get(i)));
        }
        for(int i = parentIndex(heap.size()-1); i >= SHIFT; i--){
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
        HuffmanTree rec =peek();
        swap(SHIFT,heap.size()-1);
        heap.remove(heap.size()-1);
        bubbleDown(SHIFT);
        return rec;
    }

    public HuffmanTree peek(){
        return heap.get(SHIFT);
    }

    private void bubbleUp(int index){
        while (hasParent(index) && heap.get(parentIndex(index)).isGreaterThan(heap.get(index))) {
            swap(index, parentIndex(index));
            index = parentIndex(index);
        }
    }

    private void bubbleDown(int index){
        while (hasChild(index, 1)) {
            // iterate over all children maintain index of the largest node among parent and its children.

            int smallestIndex = index;
            for (int i = 0; i < 4; i++)
                if (hasChild(index, i) && heap.get(childIndex(index, i)).isLessThan(heap.get(smallestIndex)))
                    smallestIndex = childIndex(index, i);
            if (smallestIndex != index) {
                swap(index, smallestIndex);
                index = smallestIndex;
            } else {
                return;
            }
        }
    }

    private void swap(int i, int j) {
        HuffmanTree tmp;
        tmp = heap.get(i);
        heap.set(i,heap.get(j));
        heap.set(j,tmp);
    }

    private int childIndex(int i, int c){
        return 4*(i-SHIFT)+c + SHIFT;
    }

    private int parentIndex(int i){
        return (i-1)/4 + SHIFT;
    }

    private boolean hasParent(int i){ return parentIndex(i) >= SHIFT;    }

    private boolean hasChild(int i, int c){ return childIndex(i, c) < heap.size();}

    @Override
    public int getSize(){
        return heap.size() - SHIFT;
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
