import java.util.LinkedList;

/**
 * Created by zbk on 25/3/17.
 */
public class PairingHeap extends MaxPriorityQueue {
    PairingHeap firstChild;
    PairingHeap leftSibling, rightSibling;
    HuffmanTree data;
    int size;

    public PairingHeap(HuffmanTree r){
        data = r;
        size = 1;
    }

    public PairingHeap(){

    }

    @Override
    public void heapify(FrequencyTable ft){
        for(int i = 0;i < ft.get_size(); i++){
            if(ft.get(i)!=0)
                this.insert(new HuffmanTree(i,ft.get(i)));
        }

    }

    @Override
    public void insert(HuffmanTree r){
        if(data == null) {
            data = r;
            size = 1;
        }
        else
            this.meld(new PairingHeap(r));
    }

    public void meld(PairingHeap other) {
        if (other.data.isLessThan(this.data)) {
            // make this the leftmost child of other
            exchangeNodes(this,other);
        }
        this.addLeftMostChild(other);
        this.size += other.getSize();
    }

    private void exchangeNodes(PairingHeap pairingHeap, PairingHeap other) {
        PairingHeap tmp = new PairingHeap();
        tmp.data = pairingHeap.data;
        tmp.leftSibling = pairingHeap.leftSibling;
        tmp.rightSibling = pairingHeap.rightSibling;
        tmp.firstChild = pairingHeap.firstChild;
        tmp.size = pairingHeap.size;
        pairingHeap.firstChild = other.firstChild;
        pairingHeap.rightSibling = other.rightSibling;
        pairingHeap.leftSibling = other.leftSibling;
        pairingHeap.data = other.data;
        pairingHeap.size = other.size;
        other.size = tmp.size;
        other.data = tmp.data;
        other.leftSibling = tmp.leftSibling;
        other.rightSibling = tmp.rightSibling;
        other.firstChild = tmp.firstChild;
    }

    @Override
    public HuffmanTree pop() {
        HuffmanTree record = null;
        if(data != null){
            record = data;
            if(null != firstChild) {
                LinkedList<PairingHeap> queue = new LinkedList<>();
                LinkedList<PairingHeap> queue2 = new LinkedList<>();
                PairingHeap candidate = firstChild;
                while (candidate != null) {
                    queue.addLast(candidate);
                    candidate = candidate.rightSibling;
                }
                int queueSize = queue.size();
                int i = 0;
                PairingHeap newPairingHeap;
                while (queue.size()>1) {
                    // 1 pass
                    newPairingHeap = queue.remove(0);
                    newPairingHeap.meld(queue.remove(0));
                    queue.addLast(newPairingHeap);
                }
//                if (queueSize % 2 == 1 && queue.size() > 0) {
//                    newPairingHeap = queue.remove(0);
//                    if(queue2.size() > 0)
//                        newPairingHeap.meld(queue2.remove(queue2.size() - 1));
//                    queue2.addLast(newPairingHeap);
//                }
//                for (int j = 1; j < queue2.size(); j++) {
//                    queue2.get(0).meld(queue2.get(j));
//                }
                this.firstChild = queue.get(0).firstChild;
                this.data = queue.get(0).data;
            }
            else{
                this.size = 0;
                this.data = null;
            }
            this.size--;
        }
        return record;
    }

    private void addLeftMostChild(PairingHeap newChild){
        PairingHeap prevChild = this.firstChild;
        this.firstChild = newChild;
        newChild.leftSibling = this;
        newChild.rightSibling = prevChild;
        if(null!=prevChild)
            prevChild.leftSibling = newChild;
    }

    @Override
    public void printHeap() {


    }

    @Override
    public boolean isNotEmpty() {
        return data != null;
    }

    @Override
    public int getSize(){
        return this.size;
    }
}
