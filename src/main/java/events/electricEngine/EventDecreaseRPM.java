package events.electricEngine;

public class EventDecreaseRPM {
    private final int deltaRPM;
    private final int seconds;

    public EventDecreaseRPM(int deltaRPM, int seconds) {
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
