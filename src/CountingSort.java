import java.util.ArrayList;

public class CountingSort implements SortingAlgorithm {
    int basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        int[] count = new int[10000];
        for (int i = arrayLength - 1; i > 0; i--) {
            count[array[i]]++;
            basicOperations++;
        }
        return count;
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
