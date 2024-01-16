package events.electricEngine;

public class EventIncreaseRPM {
    private final int deltaRPM;
    private final int seconds;

    public EventIncreaseRPM(int deltaRPM, int seconds) {
        this.deltaRPM = deltaRPM;
        this.seconds = seconds;
    }

    public int getDeltaRPM() {
        return deltaRPM;
    }

    public int getSeconds() {
        return seconds;
    }
}
