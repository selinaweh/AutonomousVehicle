package events.brake;

public class EventBrakeSet {
    private final int percentage;

    public EventBrakeSet(int percentage) {
        this.percentage = percentage;
    }

    public int getPercentage() {
        return percentage;
    }
}
