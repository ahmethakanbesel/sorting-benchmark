public class Timer {
    private long startTime;
    private long endTime;
    private long totalTime;
    private long elapsedTime;
    private String unit = "ns";

    public Timer() {
    }

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
        totalTime = endTime - startTime;
    }

    public void resetTimer() {
        startTime = 0;
        endTime = 0;
        totalTime = 0;
        elapsedTime = 0;
        unit = "ns";
    }

    public long getElapsedTime() {
        final String[] units = {"ns", "ms", "s"};
        int i = 0;
        elapsedTime = totalTime;
        while (elapsedTime > 1000 && i < 3) {
            elapsedTime /= 1000;
            unit = units[i];
            i++;
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