package com.example.assignment.priority;


public class InMemoryPriorityQueue {
    private Entry[] heap;
    private int size;
    private int capacity;

    public InMemoryPriorityQueue(int capacity) {
        this.capacity = capacity;
        heap = new Entry[capacity];
        size = 0;
    }

    public void enqueue(int data, int priority) {
        if (size == capacity) {
            System.out.println("Priority queue is full. Cannot insert more elements.");
            return;
        }

        Entry entry = new Entry(data, priority);
        size++;
        int i = size - 1;
        heap[i] = entry;

        while (i != 0 && heap[parent(i)].priority > heap[i].priority) {
            swap(i, parent(i));
            i = parent(i);
        }
    }

    public Entry dequeue() {
        if (size <= 0)
            return null;
        if (size == 1) {
            size--;
            return heap[0];
        }

        Entry root = heap[0];
        heap[0] = heap[size - 1];
        size--;
        minHeapify(0);

        return root;
    }

    public Entry peek() {
        if (size == 0)
            return null;
        return heap[0];
    }

    private void minHeapify(int i) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < size && heap[left].priority < heap[i].priority)
            smallest = left;
        if (right < size && heap[right].priority < heap[smallest].priority)
            smallest = right;

        if (smallest != i) {
            swap(i, smallest);
            minHeapify(smallest);
        }
    }

    private int parent(int i) {
        return (i - 1) / 2;
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private void swap(int i, int j) {
        Entry temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    static class Entry {
        int data;
        int priority;

        public Entry(int data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }
}
