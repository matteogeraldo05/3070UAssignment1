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
        System.out.println("===== ASSIGNMENT 1 - HEAP TEST CASES =====\n");

        // -------------------------------------------------------------------
        // CASE 1: BUILD-MAX-HEAP
        System.out.println("CASE 1: BUILD-MAX-HEAP");
        heap h1 = new heap(10);
        int[] arr1 = {3, 16, 2, 10, 14, 7, 9, 1, 4, 8};
        for (int val : arr1) h1.insert(val);

        System.out.println("Before buildMaxHeap:");
        h1.printAsArray();

        h1.buildMaxHeap();
        System.out.println("After buildMaxHeap:");
        h1.printAsArray();
        h1.printAsTree();
        System.out.println();

        // -------------------------------------------------------------------
        // CASE 2: MAX-HEAPIFY
        System.out.println("CASE 2: MAX-HEAPIFY");
        heap h2 = new heap(6);
        int[] arr2 = {16, 14, 10, 8, 7, 3};
        for (int val : arr2) h2.insert(val);
        h2.heap[1] = 5; // deliberately break heap property at index 1
        System.out.println("Before maxHeapify (violation at index 1):");
        h2.printAsArray();
        h2.maxHeapify(1);
        System.out.println("After maxHeapify:");
        h2.printAsArray();
        System.out.println();

        // -------------------------------------------------------------------
        // CASE 3: HEAP-MAXIMUM
        System.out.println("CASE 3: HEAP-MAXIMUM");
        System.out.println("Maximum value in heap h1: " + h1.heapMaximum());
        System.out.println();

        // -------------------------------------------------------------------
        // CASE 4: HEAP-EXTRACT-MAX
        System.out.println("CASE 4: HEAP-EXTRACT-MAX");
        System.out.println("Before extractMax:");
        h1.printAsArray();
        int max = h1.heapExtractMax();
        System.out.println("Extracted max = " + max);
        System.out.println("After extractMax:");
        h1.printAsArray();
        h1.printAsTree();
        System.out.println();

        // -------------------------------------------------------------------
        // CASE 5: MAX-HEAP-INSERT
        System.out.println("CASE 5: MAX-HEAP-INSERT");
        heap h3 = new heap(10);
        int[] arr3 = {10, 7, 8, 3, 2};
        for (int val : arr3) h3.maxHeapInsert(val);
        System.out.println("After inserting {10,7,8,3,2} into heap:");
        h3.printAsArray();
        h3.printAsTree();
        System.out.println("Now inserting 15...");
        h3.maxHeapInsert(15);
        h3.printAsArray();
        h3.printAsTree();
        System.out.println();

        // -------------------------------------------------------------------
        // CASE 6: HEAPSORT
        System.out.println("CASE 6: HEAPSORT");
        heap h4 = new heap(8);
        int[] arr4 = {9, 4, 7, 1, 3, 6, 8, 2};
        for (int val : arr4) h4.insert(val);

        System.out.println("Before sorting:");
        h4.printAsArray();
        h4.heapSort();
        System.out.println("After heapSort:");
        h4.printAsArray();

        System.out.println("\n===== END OF TESTS =====");
    }
}