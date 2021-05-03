import java.io.*;
import java.util.concurrent.TimeUnit;

public class InputGenerator {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        int lowerLimit = 0;
        int upperLimit = 100;
        int range = upperLimit - lowerLimit + 1;
        int size = 100;
        String fileName = "input.txt";
        StringBuilder outputStr = new StringBuilder();
        short[] numbers = new short[size];
        for (int i = 0; i < size; i++) {
            short random = (short) ((short) (Math.random() * range) + lowerLimit);
            numbers[i] = random;
            String str = Integer.toString(random);
            outputStr.append(str);
            if (i != size - 1) {
                outputStr.append("\n");
            }
        }
        try {
            File outputFile = new File(fileName);
            if (!outputFile.exists()) {
                boolean success = outputFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Could not create the file.");
            e.printStackTrace();
        }
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            fileWriter.write(outputStr.toString());
            fileWriter.close();
            System.out.printf("%d numbers generated with in range [%d, %d] and standard deviation %f.", size, lowerLimit, upperLimit, calculateSD(numbers));
        } catch (IOException e) {
            System.out.println("Could not write to the file.");
            e.printStackTrace();
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        long elapsedTime = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
        System.out.printf("\nElapsed time: %dms.", elapsedTime);
    }

    public static double calculateSD(short[] numArray) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = numArray.length;
        for (int num : numArray) {
            sum += num;
        }
        double mean = sum / length;
        for (double num : numArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }
        return Math.sqrt(standardDeviation / length);
    }
}
