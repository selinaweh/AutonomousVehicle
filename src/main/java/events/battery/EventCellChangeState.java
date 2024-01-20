package events.battery;

public class EventCellChangeState {
    private final int oldState;
    private final int newState;

    public EventCellChangeState(int oldState, int newState) {
        this.oldState = oldState;
        this.newState = newState;
    }

    public int getOldState() {
        return oldState;
    }

    public int getNewState() {
        return newState;
    }
}
