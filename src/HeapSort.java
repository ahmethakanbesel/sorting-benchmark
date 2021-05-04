public class HeapSort implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        for (int i = Math.max(0, (arrayLength / 2) - 1); i >= 0; i--) {
            sink(array, arrayLength, i);
        }
        for (int i = arrayLength - 1; i >= 0; i--) {
            swap(array, 0, i);
            sink(array, i, 0);
        }
        return array;
    }

    @Override
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    private void sink(int[] array, int n, int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;
            if (right < n && array[right] > array[largest]) largest = right;
            if (left < n && array[left] > array[largest]) largest = left;
            if (largest != i) {
                swap(array, largest, i);
                i = largest;
            } else break;
        }
    }

    private void swap(int[] ar, int i, int j) {
        int tmp = ar[i];
        ar[i] = ar[j];
        ar[j] = tmp;
        basicOperations++;
    }

    @Override
    public long getBasicOperations(){
        return basicOperations;
    }
}
