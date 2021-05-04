import java.util.Arrays;

public class QuickSort3 implements SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;

    @Override
    public int[] sort(int[] array) {
        medianQuickSort(array, 0, array.length - 1);
        return array;
    }

    public void QuickSort(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);
            QuickSort(array, low, pi - 1);
            QuickSort(array, pi + 1, high);
        }
    }

    public int medianPivot(int[] array, int low, int high) {
        int first = array[low];
        int last = array[array.length - 1];
        int mid = (high) / 2;
        int[] sortingArr = {array[low], array[mid], array[high]};
        Arrays.sort(sortingArr);
        int middleValue = sortingArr[1];
        int temp = array[high];
        array[high] = middleValue;
        if (middleValue == array[low]) {
            array[low] = temp;
        } else if (middleValue == array[mid]) {
            array[mid] = temp;
        }
        return partition(array, low, high);
    }

    public void medianQuickSort(int[] array, int low, int high) {
        if (low >= high) {
            return;
        }
        int pi = medianPivot(array, low, high);
        QuickSort(array, low, high);

    }

    public int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

            }
            basicOperations++;
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
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