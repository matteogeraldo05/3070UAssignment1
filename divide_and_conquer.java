public class divide_and_conquer {
    static int dominant(int[] list, int start, int end){

        if (start == end) {return list[start];}

        int midpoint = (start + end) / 2;

        int leftside = dominant(list, start, midpoint);
        int rightside = dominant(list, midpoint + 1, end);

        int leftcount = 0;
        for (int i = start; i <= end; i++){
            if (list[i] == leftside){
                leftcount++;
            }
        }

        int rightcount = 0;
        for (int i = start; i <= end; i++){
            if (list[i] == rightside){
                rightcount++;
            }
        }

        if (leftcount > (end - start + 1) / 2) {
            return leftside;
        } else if (rightcount > (end - start + 1) / 2) {
            return rightside;
        }

        return -1;
    }    


    public static void main(String[] args) {
        int[][] testArrays = {
            {3, 3, 4, 3, 5, 3, 3},
            {3, 1, 2, 3},
            {1, 1, 1, 1, 2, 3},
            {2, 2, 2, 2, 2},
            {5},
            {1, 2, 3, 4, 5},
            {7, 7, 7, 8, 8, 7, 7, 7},
            {9, 9, 1, 2, 9, 9, 9},
            {0, 0, 0, 1, 1, 0, 0},
            {1, 2, 3, 1, 2, 3, 1, 1}
        };

        for (int i = 0; i < testArrays.length; i++) {
            int result = dominant(testArrays[i], 0, testArrays[i].length - 1);
            System.out.println("Test case " + (i+1) + " dominant: " + (result != -1 ? result : "None"));
        }

    }
}

