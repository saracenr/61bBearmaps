package Bearmaps;

import java.util.ArrayList

public class ArrayHeapMinPQ implements ExtrinsicMinPQ<T> {

	private ArrayList<PriorityNode> items;
	private int size;

	public NaiveMinPQ() {
        items = new ArrayList<>();
        size = 0;
    }

	public void add(T item, double priority) {
		items.add(new PriorityNode(item, priority));
		size+=1;
		percolateUp(size-1);
	}

	public T getSmallest() {
		return items[0];
	}

	public T removeSmallest() {
		if (size == 1) {
			items[0] = null;
			size-=1;
			return items[0];
		}

		temp = items[0];
		items[0] = items[size - 1];
		items[size-1] = null;
		size-=1;
		percolateDown(0);
		return temp;
	}

	public boolean contains(T item) {

	}

	public int size() {

	}

	public void changePriority(T item, double priority) {

	}

	private void percolateUp(int index) {
		if (index == 0) {
			return;
		}

		int parentIndex = (index - 1) / 2;

		if (items[parentIndex].getPriority() > items[index].getPriority()) {
			temp = items[parentIndex];
			items[parentIndex] = items[index];
			items[index] = temp;
			return percolateUp(parentIndex);
		}
		else {
			return;
		}

	}

	private void percolateDown(int index) {
		if (index > size-1) {
			return;
		}

		int childOne = index * 2 + 1;
		int childTwo = index * 2 + 2;

		if (items[childOne].getPriority() < items[childTwo].getPriority()) {
			temp = items[childOne];
			items[childOne] = items[index];
			items[index] = childOne;
			return percolateDown(childOne);
		}
		else if (items[childOne].getPriority() > items[childTwo].getPriority()){
			temp = items[childTwo];
			items[childTwo] = items[index];
			items[index] = childTwo;
			return percolateDown(childTwo);
		}
		else {
			return;
		}

	}

	private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
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