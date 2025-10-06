public class heap {
    private int[] heap;
    private int size;
    private int capacity;
    private boolean isMaxHeap = false;

    public heap(int capacity) {
        this.capacity = capacity;
        heap = new int[capacity];
        size = 0;
    }

    private int parent(int i) { return (i - 1) / 2; }
    private int left(int i) { return 2 * i + 1; }
    private int right(int i) { return 2 * i + 2; }
    private boolean checkIsMaxHeap(){return isMaxHeap;} 

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

        isMaxHeap = true;
    }

    public void buildMaxHeapify(){
        int lastLeaf = (size/2) - 1;

        for (int i = lastLeaf; i >= 0; i--){
            maxHeapify(i);
        }
        isMaxHeap = true;
    }

    public int heapMaximum(){
        if(checkIsMaxHeap()){return heap[0];}
        else {
            int largestValue = heap[0];
            for (int heapValue : heap){
                if (heapValue > largestValue){largestValue = heapValue;}
            }
            return largestValue;
        }
    }

    public int heapExtractMax(){
        if (size == 0){return -1;}
        int maxVal = heap[0];
        heap[0] = heap[size - 1];
        size--;

        maxHeapify(0);
        return maxVal;
    }

    public static void main(String[] args) {}
}