import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static String inputFile = "input.txt";
    final static String outputFile = "output.csv";
    final static int inputSize = 100;
    final static int iterations = 10;
    static int[] numbers = new int[inputSize];
    final static SortingAlgorithm[] algorithms = {new InsertionSort(), new HeapSort(), new MergeSort(), new QuickSort(), new BinarySort(), new CountingSort()};
    static long[][] results = new long[algorithms.length][iterations];

    public static void main(String[] args) {
        System.out.println("Starting to benchmark...");
        Timer timer = new Timer();
        readInputFile();
        int[] numbersUnsorted = Arrays.copyOf(numbers, inputSize);
        int i = 0;
        for (SortingAlgorithm algorithm : algorithms) {
            for (int j = 0; j < iterations; j++) {
                timer.startTimer();
                algorithm.setArrayLength(inputSize);
                algorithm.sort(numbers);
                timer.stopTimer();
                System.out.printf("Algorithm: %s Number of Comparisons: %d %s", algorithm.getClass().getName(), algorithm.getBasicOperations(), timer.toString());
                results[i][j] = timer.getTotalTime();
                timer.resetTimer();
                //printArray();
                System.out.println();
                numbers = Arrays.copyOf(numbersUnsorted, inputSize);
            }
            i++;
        }
        writeResults();
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

    private static void writeResults() {
        StringBuilder result = new StringBuilder();
        result.append("InsertionSort,HeapSort,MergeSort,QuickSort,BinarySort,CountingSort\n");
        for (int i = 0; i < iterations; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 0; j < algorithms.length; j++) {
                line.append(results[j][i]);
                if (j < algorithms.length - 1) {
                    line.append(",");
                }
            }
            if (i < iterations - 1) {
                line.append("\n");
            }
            result.append(line);
        }
        try {
            File outputFile = new File(Main.outputFile);
            if (!outputFile.exists()) {
                boolean success = outputFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Could not create the file.");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(Main.outputFile);
            fileWriter.write(result.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Could not write to the file.");
            e.printStackTrace();
        }
    }
}