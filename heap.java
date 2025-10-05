public class heap {
    private int[] heap;
    private int size;
    private int capacity;

    public heap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        heap[size] = value;
        size++;
        // you can call heapifyUp(size - 1) here if you want
    }

    public int getMin() {
        if (size == 0) throw new IllegalStateException("Heap is empty");
        return heap[0];
    }

    public void printHeap() {
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        heap h = new heap(10);
        h.insert(10);
        h.insert(5);
        h.insert(7);
        h.printHeap();
    }
}