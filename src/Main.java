import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    final static int[] sizes = {10, 99, 100, 1000, 10001};
    final static int[][] ranges = {{0, 100}, {0, 1000}, {0, 10000}};
    final static String[] inputFiles = new String[ranges.length];
    final static String outputFile = "output.csv";
    final static int inputSize = 100000;
    final static int iterations = 1;
    static int[] numbers = new int[inputSize];
    final static SortingAlgorithm[] algorithms = {new InsertionSort(), new HeapSort(), new MergeSort(), new QuickSort(), new QuickSort3(), new BinarySort(), new CountingSort()};
    static long[][] results = new long[algorithms.length][sizes.length * ranges.length];

    public static void main(String[] args) {
        generateInputFileNames();
        for (int i = 0; i < algorithms.length; i++) {
            int k = 0;
            for (String file : inputFiles) {
                for (int size : sizes) {
                    Benchmark benchmark = new Benchmark(algorithms[i], file, size, iterations);
                    benchmark.startBenchmark();
                    System.out.printf("Algorithm:%s #ofBO:%d N:%d I:%s T:%sns", algorithms[i].getClass().getName(), algorithms[i].getBasicOperations(), size, file, benchmark.getElapsedTime());
                    System.out.println();
                    results[i][k] = benchmark.getElapsedTime();
                    k++;
                }
            }
        }
        writeResults();
    }

    private static void writeResults() {
        StringBuilder result = new StringBuilder();
        result.append("Algorithm,");
        for (int i = 0; i < ranges.length; i++) {
            for (int j = 0; j < sizes.length; j++) {
                result.append(sizes[j]).append(" ").append("[").append(ranges[i][0]).append("-").append(ranges[i][1]).append("]");
                if (i == ranges.length - 1 && j == sizes.length - 1) {
                    break;
                } else {
                    result.append(",");
                }
            }
        }
        result.append("\n");
        for (int i = 0; i < algorithms.length; i++) {
            result.append(algorithms[i].getClass().getName()).append(",");
            for (int j = 0; j < results[i].length; j++) {
                result.append(results[i][j]);
                if (j < results[i].length - 1) {
                    result.append(",");
                } else {
                    result.append("\n");
                }
            }
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

    private static void generateInputFileNames() {
        int k = 0;
        for (int[] range : ranges) {
            inputFiles[k] = String.format("input10001.%d-%d.txt", range[0], range[1]);
            k++;
        }
    }

    private static void printArray() {
        for (int number : Main.numbers) {
            System.out.printf(" %d", number);
        }
    }
}