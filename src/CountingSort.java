public class CountingSort implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        if (arrayLength == 0)
            return array;
        int max = array[0], min = array[0];
        for (int i = 1; i < arrayLength; i++) {
            if (array[i] > max)
                max = array[i];
            if (array[i] < min)
                min = array[i];
            basicOperations += 2;
        }
        int range = max - min + 1;

        int[] count = new int[range];
        for (int i = 0; i < arrayLength; i++) {
            count[array[i] - min]++;
        }
        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }
        int j = 0;
        for (int i = 0; i < range; i++)
            while (j < count[i]) {
                array[j++] = i + min;
                basicOperations++;
            }
        return array;
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
