package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    public static void main(String args[]) {
        ArrayHeapMinPQ heap = new ArrayHeapMinPQ();
        heap.add(5, 7.0);
        heap.add(4, 5.0);
        heap.add(3, 1.0);
        heap.add(2, 2.0);
        heap.add(1, 8.0);

        System.out.println(heap.getSmallest());
    }
}
