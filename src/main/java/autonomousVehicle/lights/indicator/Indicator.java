package autonomousVehicle.lights.indicator;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.lights.indicator.*;

public class Indicator extends Subscriber {
    private final Position position;
    private boolean isLeftIndicatorOn;
    private boolean isRightIndicatorOn;

    public Indicator(Position position) {
        this.position = position;
        this.isLeftIndicatorOn = false;
        this.isRightIndicatorOn = false;
    }

    public Position getIndicatorLocation() {
        return position;
    }

    public boolean isLeftIndicatorOn() {
        return isLeftIndicatorOn;
    }

    public boolean isRightIndicatorOn() {
        return isRightIndicatorOn;
    }

    @Subscribe
    public void receive(EventLeftIndicatorOn leftIndicatorOn) {
        if (position == Position.LEFT) {
            isLeftIndicatorOn = true;
        }
    }

    @Subscribe
    public void receive(EventLeftIndicatorOff leftIndicatorOff) {
        if (position == Position.LEFT) {
            isLeftIndicatorOn = false;
        }
    }

    @Subscribe
    public void receive(EventRightIndicatorOn rightIndicatorOn) {
        if (position == Position.RIGHT) {
            isRightIndicatorOn = true;
        }
    }

    @Subscribe
    public void receive(EventRightIndicatorOff rightIndicatorOff) {
        if (position == Position.RIGHT) {
            isRightIndicatorOn = false;
        }
    }

    @Subscribe
    public void receive(EventHazardWarningOn hazardWarningOn) {
        isLeftIndicatorOn = true;
        isRightIndicatorOn = true;
    }

    @Subscribe
    public void receive(EventHazardWarningOff hazardWarningOff) {
        isLeftIndicatorOn = false;
        isRightIndicatorOn = false;
    }
}
