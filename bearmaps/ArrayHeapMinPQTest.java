package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ArrayHeapMinPQTest {

    public static  void testAdd(int n, int range, int numberOfTimes) {
        int slower = 0;
        Random random = new Random();
        int randomNumber;
        for (int z = 0; z < numberOfTimes; z++) {
            ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
            Stopwatch sw = new Stopwatch();
            for (int x = 1; x < n; x++) {
                randomNumber = random.nextInt(range);
                if (!heap.contains(randomNumber)) {
                    heap.add(randomNumber, randomNumber);
                }
            }
            double heapTime = sw.elapsedTime();

            NaiveMinPQ<Integer> naiveHeap = new NaiveMinPQ<>();
            Stopwatch nw = new Stopwatch();
            for (int x = 1; x < n; x++) {
                randomNumber = random.nextInt(range);
                naiveHeap.add(randomNumber, randomNumber);
            }
            double naiveTime = nw.elapsedTime();

            if (naiveTime > heapTime) {
                slower += 1;
            }
        }
            System.out.println("Heap was slower than Naive: " + slower + " times out of " + numberOfTimes);
    }

    public static void main(String args[]) {
        testAdd(1000, 100000, 1000);
    }
}
