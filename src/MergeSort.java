import java.util.Arrays;

public class MergeSort implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        int n = array.length;
        if (n <= 1) return array;
        int[] left = sort(Arrays.copyOfRange(array, 0, (int)Math.floor(n / 2.0)));
        int[] right = sort(Arrays.copyOfRange(array, (int)Math.ceil(n / 2.0), n));
        return merge(left, right);
    }

    /*
    public int[] recursiveSort(int[] array) {
        int n = array.length;
        if (n <= 1) return array;
        int[] left = recursiveSort(Arrays.copyOfRange(array, 0, n / 2));
        int[] right = recursiveSort(Arrays.copyOfRange(array, n / 2, n));
        return merge(left, right);
    }*/

    @Override
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    /* Different Merging Algorithm, Not Completed! */
    private int[] merge2(int[] array1, int[] array2) {
        int i = 0, j = 0, k = 0;
        int length1 = array1.length, length2 = array2.length;
        int totalLength = length1 + length2;
        int[] mergedArray = new int[totalLength];
        while (i < length1 && j < length2) {
            if (array1[i] <= array2[j]) {
                mergedArray[k] = array1[i];
                i++;
            } else {
                mergedArray[k] = array2[j];
                j++;
            }
            k++;
        }
        /*
        if i = p
        copy C[j..q − 1] to A[k..p + q − 1]
        else copy B[i..p − 1] to A[k..p + q − 1]
         */
        return mergedArray;
    }

    private int[] merge(int[] array1, int[] array2) {
        int length1 = array1.length, length2 = array2.length;
        int totalLength = length1 + length2, j = 0, k = 0;
        int[] mergedArray = new int[totalLength];
        for (int i = 0; i < totalLength; i++) {
            if (j == length1) {
                mergedArray[i] = array2[k++];
            } else if (k == length2) {
                mergedArray[i] = array1[j++];
            } else {
                if (array1[j] < array2[k]) {
                    mergedArray[i] = array1[j++];
                } else {
                    mergedArray[i] = array2[k++];
                }
            }
            basicOperations++;
        }
        return mergedArray;
    }

    @Override
    public long getBasicOperations(){
        return basicOperations;
    }
}
