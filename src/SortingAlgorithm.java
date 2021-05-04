public interface SortingAlgorithm {
    long basicOperations = 0;
    int arrayLength = 0;
    int[] sort(int[] array);
    void setArrayLength(int arrayLength);
    long getBasicOperations();
}