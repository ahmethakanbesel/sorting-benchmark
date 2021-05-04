import java.util.ArrayList;

public class CountingSort implements SortingAlgorithm {
    int basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] arr) {
        int arrayLength = arr.length;
        if (arrayLength == 0)
            return arr;
        /** find maximum and minimum values **/
        int max = arr[0], min = arr[0];
        for (int i = 1; i < arrayLength; i++) {
            if (arr[i] > max)
                max = arr[i];
            if (arr[i] < min)
                min = arr[i];
            basicOperations += 2;
        }
        int range = max - min + 1;

        int[] count = new int[range];
        /** initialize the occurrence of each element in the count array **/
        for (int i = 0; i < arrayLength; i++)
            count[arr[i] - min]++;
        /** calculate sum of indexes **/
        for (int i = 1; i < range; i++)
            count[i] += count[i - 1];
        /** modify original array according to the sum count **/
        int j = 0;
        for (int i = 0; i < range; i++)
            while (j < count[i]) {
                arr[j++] = i + min;
                basicOperations++;
            }
        return arr;
    }

    @Override
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    @Override
    public int getBasicOperations(){
        return basicOperations;
    }
}
