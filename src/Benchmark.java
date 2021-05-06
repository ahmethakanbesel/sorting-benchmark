import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Benchmark {
    private final SortingAlgorithm algorithm;
    private final String inputFile;
    private int[] numbers;
    private final int inputSize;
    private final int iterations;
    private long elapsedTime;

    public Benchmark(SortingAlgorithm algorithm, String inputFile, int inputSize, int iterations) {
        this.algorithm = algorithm;
        this.inputFile = inputFile;
        this.iterations = iterations;
        this.inputSize = inputSize;
        numbers = new int[inputSize];
        readInputFile();
    }

    public void startBenchmark(){
        int[] numbersUnsorted = Arrays.copyOf(numbers, inputSize);
        Timer timer = new Timer();
        long totalTime = 0;
        for(int i = 0; i < iterations; i++){
            timer.startTimer();
            algorithm.setArrayLength(inputSize);
            algorithm.sort(numbers);
            timer.stopTimer();
            totalTime += timer.getTotalTime();
            timer.resetTimer();
            //resetNumbers();
            numbers = Arrays.copyOf(numbersUnsorted, inputSize);
        }
        elapsedTime = totalTime / iterations;
    }

    public long getElapsedTime(){
        return elapsedTime;
    }

    public long getBasicOperations(){
        return algorithm.getBasicOperations();
    }

    private void readInputFile() {
        java.io.File file = new java.io.File(inputFile);
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
}
