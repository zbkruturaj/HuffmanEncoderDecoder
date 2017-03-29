/**
 * Created by zbk on 25/3/17.
 */
public class Record implements Comparable{
    private int numb;
    private int freq;
    private int huffmanCode;

    public Record(int numb, int freq) {
        this.numb = numb;
        this.freq = freq;
    }

    public Record(Record data1, Record data2) {
        this.numb = -1;
        this.freq = data1.getFreq() + data2.getFreq();
    }

    public int getFreq() {
        return freq;
    }

    public int getNumb() {
        return numb;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public void setNumb(int numb) {
        this.numb = numb;
    }

    @Override
    public String toString() {
        return Integer.toString(numb) + " : " + Integer.toString(freq);
    }

    @Override
    public int compareTo(Object that) {
        return this.freq - ((Record) that).getFreq();
    }

    public boolean isGreaterThan(Object that) {
        return this.compareTo(that) > 0;
    }

    public boolean isLessThan(Object that) {
        return this.compareTo(that) < 0;
    }

    public boolean isGreaterThanEqualTo(Object that) {
        return this.compareTo(that) >= 0;
    }

    public boolean isLessThanEqualTo(Object that) {
        return this.compareTo(that) <= 0;
    }

    public void setHuffmanCode(int huffmanCode){
        this.huffmanCode = huffmanCode;
    }

    public int getHuffmanCode() {
        return huffmanCode;
    }
}
