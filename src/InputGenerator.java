import java.io.*;
import java.util.concurrent.TimeUnit;

public class InputGenerator {
    static int[] sizes;
    static int[][] ranges;

    public static void main(String[] args) {
        sizes = Main.sizes;
        ranges = Main.ranges;
        long startTime = System.nanoTime();
        for (int size : sizes) {
            for (int[] range : ranges) {
                short[] numbers = generateArray(size, range[0], range[1]);
                writeToTxt(numbers, String.format("input%d.%d-%d.txt", size, range[0], range[1]));
                //System.out.printf("%d numbers generated in range [%d, %d] and std. deviation is %f.\n", size, range[0], range[1], calculateSD(numbers));
            }
        }
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        long elapsedTime = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
        //System.out.printf("\nElapsed time: %dms.", elapsedTime);
    }

    public static short[] generateArray(int size, int lowest, int highest) {
        short[] numbers = new short[size];
        int range = highest - lowest + 1;
        for (int i = 0; i < size; i++) {
            short random = (short) ((short) (Math.random() * range) + lowest);
            numbers[i] = random;
        }
        return numbers;
    }

    public static void writeToTxt(short[] numbers, String fileName) {
        StringBuilder outputStr = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            String str = Integer.toString(numbers[i]);
            outputStr.append(str);
            if (i != numbers.length - 1) {
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
            //System.out.printf("%d numbers generated with in range [%d, %d] and standard deviation %f.", size, lowerLimit, upperLimit, calculateSD(numbers));
        } catch (IOException e) {
            System.out.println("Could not write to the file.");
            e.printStackTrace();
        }
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
