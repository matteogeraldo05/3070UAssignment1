public class pseudocode {
    public static int maxElement(int[] A) {
        int n = A.length;
        int low = 0;
        int high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Handle boundary cases
            if (mid == 0) {
                if (A[mid] > A[mid + 1]) return A[mid];
                else low = mid + 1;
            } else if (mid == n - 1) {
                if (A[mid] > A[mid - 1]) return A[mid];
                else high = mid - 1;
            } else {
                // Peak element
                if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1])
                    return A[mid];
                // Increasing sequence
                else if (A[mid] < A[mid + 1])
                    low = mid + 1;
                // Decreasing sequence
                else
                    high = mid - 1;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        // Using the example from the question
        int[] A = {3, 5, 6, 9, 3, 1};
        System.out.println("Max element: " + maxElement(A));
    }
}
