package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private PriorityNode[] items;
    private HashMap<T,Integer> map;
    private int size;
    private int arraySize;

    public ArrayHeapMinPQ() {
        items = new ArrayHeapMinPQ.PriorityNode[11];
        map = new HashMap<>();
        items[0] = null;
        size = 0;
        arraySize = 10;
    }

    public void add(T item, double priority) {
        if (map.containsKey(item)) {
            throw new IllegalArgumentException("This item already exists.");
        }

        if (size == arraySize) {
            arraySize = arraySize*2;
            PriorityNode[] newArray = new ArrayHeapMinPQ.PriorityNode[arraySize+1];
            newArray[0] = null;
            for (int x = 1; x < size+1; x++) {
                newArray[x] = items[x];
            }
            items = null;
            items = newArray;
        }
        size+=1;
        PriorityNode newItem = new PriorityNode(item, priority);
        map.put(item, size);
        items[size] = newItem;
        percolateUp(size);
    }

    public T getSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items[1].getItem();
    }

    public T removeSmallest() {
        if (size() == 0) {
            throw new NoSuchElementException("PQ is empty");
        }

        if (size == 1) {
            T temp = items[1].getItem();
            map.remove(temp);
            items[1] = null;
            size-=1;
            return temp;
        }
        else if (size < arraySize/2 - 1) {
            arraySize = arraySize/2;
            PriorityNode[] newArray = new ArrayHeapMinPQ.PriorityNode[arraySize];
            newArray[0] = null;
            for (int x = 1; x < size+1; x++) {
                newArray[x] = items[x];
            }
            items = null;
            items = newArray;
        }

        T temp = items[1].getItem();
        map.remove(temp);
        items[1] = items[size];
        items[size] = null;
        size-=1;
        percolateDown(1);
        return temp;
    }

    public boolean contains(T item) {
        return map.containsKey(item);
    }

    public int size() {
        return size;
    }

    public void changePriority(T item, double priority) {

        if (map.containsKey(item)) {
            System.out.println("Change priority called");
            int index = map.get(item);
            if (items[index].getPriority() >= priority) {
                items[index].setPriority(priority);
                percolateUp(index);
            }
            else {
                items[index].setPriority(priority);
                percolateDown(index);
            }
        }
    }

    private void percolateUp(int index) {
        if (index == 1) {
            return;
        }

        int parentIndex = index / 2;

        if (items[parentIndex].getPriority() > items[index].getPriority()) {
            PriorityNode temp = items[parentIndex];
            items[parentIndex] = items[index];
            items[index] = temp;
            percolateUp(parentIndex);
        }
    }

    private void percolateDown(int index) {
        if (index > size) {
            return;
        }

        int childOne = index * 2;
        int childTwo = index * 2 + 1;

        if (childTwo > size && childOne > size) {
            return;
        }
        else if (childTwo > size) {
            if (items[index].getPriority() > items[childOne].getPriority()) {
                PriorityNode temp = items[childOne];
                items[childOne] =  items[index];
                items[index] = temp;
                return;
            }
        }

        if (items[childOne].getPriority() < items[childTwo].getPriority() && items[index].getPriority() > items[childOne].getPriority()) {
            PriorityNode temp = items[childOne];
            items[childOne] =  items[index];
            items[index] = temp;
            percolateDown(childOne);
        }
        else if (items[childOne].getPriority() > items[childTwo].getPriority() && items[index].getPriority() > items[childTwo].getPriority()){
            PriorityNode temp = items[childTwo];
            items[childTwo] = items[index];
            items[index] = temp;
            percolateDown(childTwo);
        }
    }

    public void printSimpleHeapDrawing() {
        int depth = ((int) (Math.log(size+1) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < size+1; i++) {
            System.out.printf("%s ", items[i].toString());
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }

    public void printFancyHeapDrawing() {
        String drawing = fancyHeapDrawingHelper(1, "");
        System.out.println(drawing);
    }

    /* Recursive helper method for toString. */
    private  String fancyHeapDrawingHelper(int index, String soFar) {
        if (index >= size+1 || items[index] == null) {
            return "";
        } else {
            String toReturn = "";
            int rightIndex = 2 * index + 1;
            toReturn += fancyHeapDrawingHelper(rightIndex, "        " + soFar);
            if (rightIndex < size+1 && items[rightIndex] != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + items[index] + "\n";
            int leftIndex = 2 * index;
            if (leftIndex < size+1 && items[leftIndex] != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += fancyHeapDrawingHelper(leftIndex, "        " + soFar);
            return toReturn;
        }
    }


    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        @Override
        public String toString() {
            return Double.toString(priority);
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode other) {
            if (other == null) {
                return -1;
            }
            return Double.compare(this.getPriority(), other.getPriority());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}