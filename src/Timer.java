import java.util.concurrent.TimeUnit;

public class Timer {
    private long startTime;
    private long endTime;
    private long totalTime;
    private long elapsedTime;
    private String unit = "ms";

    public Timer() {
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
    }

    public void resetTimer(){
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        elapsedTime = 0;
        unit = "ms";
    }

    public long getElapsedTime() {
        elapsedTime = TimeUnit.MILLISECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
        if (elapsedTime > 1000) {
            elapsedTime = TimeUnit.SECONDS.convert(totalTime, TimeUnit.NANOSECONDS);
            unit = "s";
        }
        return elapsedTime;
    }

    @Override
    public String toString() {
        return "Elapsed time " +
                getElapsedTime() +
                unit;
    }

    public void setElapsedTime(long elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(long totalTime) {
        this.totalTime = totalTime;
    }
}