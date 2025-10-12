public class heap {
    private int[] heap;
    private int size;
    private int capacity;

    public heap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    private int parent(int i){return (i - 1) / 2;}
    private int left(int i){return 2 * i + 1;}
    private int right(int i){return 2 * i + 2;}

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        heap[size] = value;
        size++;
    }


    public void printAsArray() {
        System.out.print("[");
        for (int i = 0; i < size; i++){
            System.out.print(heap[i]);
            if (i < size - 1){System.out.print(", ");}
        }
        System.out.print("]");
        System.out.println();
    }

    public void printAsTree(){
        printAsTreeHelper(0,0);
    }

    public void printAsTreeHelper(int index,int level){
        if (index >= size){return;}

        printAsTreeHelper(2 * index + 2, level + 1); // right child

        for (int i = 0; i < level; i++){System.out.print("       ");}
        System.out.println(heap[index]); // parent

        printAsTreeHelper(2 * index + 1, level + 1); // left child
    }

    public void maxHeapInsert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");
        size++;
        heap[size - 1] = value;
        int i = size - 1;

        while (i > 0 && heap[parent(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[parent(i)];
            heap[parent(i)] = temp;
            i = parent(i);
        }
    }

    public void maxHeapify(int index){
        if (index < 0 || index > size){return;}
        
        int largestValue = index;

        if (left(index) < size && heap[largestValue] < heap[left(index)]){
            largestValue = left(index);
        }
        if (right(index) < size && heap[largestValue] < heap[right(index)]){
            largestValue = right(index);
        }

        if (largestValue != index){
            int temp = heap[index];
            heap[index] = heap[largestValue];
            heap[largestValue] = temp;

            maxHeapify(largestValue);
        }
    }

    public void buildMaxHeap(){
        int lastLeaf = (size/2) - 1;

        for (int i = lastLeaf; i >= 0; i--){
            maxHeapify(i);
        }
    }

    public int heapMaximum(){
        return heap[0];
    }

    public int heapExtractMax(){
        if (size == 0){return -1;}
        int maxVal = heap[0];
        heap[0] = heap[size - 1];
        size--;

        maxHeapify(0);
        return maxVal;
    }

    public void heapSort() {
        buildMaxHeap();
        int originalSize = size;
        for (int i = size - 1; i > 0; i--) {
            int temp = heap[0];
            heap[0] = heap[i];
            heap[i] = temp;
            size--;
            maxHeapify(0);
        }
        size = originalSize;
    }

    public static void main(String[] args) {
    // Create a heap with enough capacity
        heap h = new heap(10);

        // Insert arbitrary elements (unsorted)
        h.insert(9);
        h.insert(4);
        h.insert(7);
        h.insert(1);
        h.insert(3);
        h.insert(6);
        h.insert(8);
        h.insert(2);

        System.out.println("Before HeapSort:");
        h.printAsArray();

        // Perform heap sort
        h.heapSort();

        System.out.println("After HeapSort:");
        h.printAsArray();
    }

}