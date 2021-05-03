public interface SortingAlgorithm {
    int basicOperations = 0;
    int arrayLength = 0;
    int[] sort(int[] array);
    void setArrayLength(int arrayLength);
    int getBasicOperations();
}