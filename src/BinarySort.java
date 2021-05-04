public class BinarySort implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    public int[] sort(int[] array) {
        for (int i = 1; i < arrayLength; ++i) {
            int key = array[i];
            int insertedPosition = findPosition(array, 0, i - 1, key);
            if (i - insertedPosition >= 0)
                System.arraycopy(array, insertedPosition, array, insertedPosition + 1, i - insertedPosition);
            array[insertedPosition] = key;
        }
        return array;
    }

    public int findPosition(int[] array, int start, int end, int key) {
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (key < array[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
            basicOperations++;
        }
        return start;
    }

    @Override
    public void setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
    }

    @Override
    public long getBasicOperations() {
        return basicOperations;
    }
}
