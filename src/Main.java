import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static String inputFile = "input.txt";
    final static int inputSize = 100;
    static int[] numbers = new int[inputSize];
    final static SortingAlgorithm[] algorithms = {new InsertionSort(), new HeapSort(), new MergeSort(), new QuickSort(), new BinarySort(), new CountingSort()};

    public static void main(String[] args) {
        System.out.println("Starting to benchmark...");
        Timer timer = new Timer();
        readInputFile();
        int[] numbersUnsorted = Arrays.copyOf(numbers, inputSize);
        for (SortingAlgorithm algorithm : algorithms) {
            timer.startTimer();
            algorithm.setArrayLength(inputSize);
            algorithm.sort(numbers);
            timer.stopTimer();
            System.out.printf("Algorithm: %s Number of Comparisons: %d %s", algorithm.getClass().getName(), algorithm.getBasicOperations(), timer.toString());
            timer.resetTimer();
            printArray();
            System.out.println();
            numbers = Arrays.copyOf(numbersUnsorted, inputSize);
        }
    }

    private static void readInputFile() {
        java.io.File file = new java.io.File(Main.inputFile);
        // Create a Scanner for the file
        try {
            Scanner input = new Scanner(file);
            // Read data from a file
            int i = 0;
            while (input.hasNextLine() && i < inputSize) {
                String line = input.nextLine();
                if (!line.isEmpty()) {
                    numbers[i] = Integer.parseInt(line);
                    i++;
                }
            }
            // Close the file
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printArray() {
        for (int number : Main.numbers) {
            System.out.printf(" %d", number);
        }
    }
}
