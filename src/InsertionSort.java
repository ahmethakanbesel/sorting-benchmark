public class InsertionSort implements SortingAlgorithm {
    int basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        for (int i = 1; i < arrayLength; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0 && (array[j] > temp); j--) {
                array[j + 1] = array[j];
                basicOperations++;
            }
            array[j + 1] = temp;
        }
        return array;
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