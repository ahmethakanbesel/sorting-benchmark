public class QuickSort implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        quicksort(array, 0, arrayLength-1);
        return array;
    }

    public void quicksort(int[] array, int l, int r) {
        if (l < r) {
            int splitPosition = partition(array, l, r);
            quicksort(array, l, splitPosition);
            quicksort(array, splitPosition + 1,r);
        }
    }

    private int partition(int[] array, int l, int r) {
        int pivot = array[l];
        int i = l - 1, j = r + 1;
        while (true) {
            do {
                i++;
            } while (array[i] < pivot);
            do {
                j--;
            } while (array[j] > pivot);
            if (i < j) swap(array, i, j);
            else return j;
            basicOperations++;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    @Override
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    @Override
    public long getBasicOperations(){
        return basicOperations;
    }
}
