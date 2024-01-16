package autonomousVehicle.lights.ledHeadlight;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.lights.ledHeadlight.*;
public class LEDHeadlight extends Subscriber{
    private boolean isLEDOn;
    private boolean isLEDDimmed;
    private boolean isLEDHighBeam;
    public LEDHeadlight() {
        this.isLEDOn = false;
        this.isLEDDimmed = false;
        this.isLEDHighBeam = false;
    }
    public boolean isLEDOn() {
        return isLEDOn;
    }
    public boolean isLEDDimmed() {
        return isLEDDimmed && !isLEDHighBeam && isLEDOn;
    }
    public boolean isLEDHighBeam() {
        return isLEDHighBeam && !isLEDDimmed && isLEDOn;
    }
    @Subscribe
    public void receive(EventLEDOn ledOn) {
        isLEDOn = true;
        isLEDDimmed = false;
        isLEDHighBeam = false;
    }
    @Subscribe
    public void receive(EventLEDOff ledOff) {
        isLEDOn = false;
        isLEDDimmed = false;
        isLEDHighBeam = false;
    }
    @Subscribe
    public void receive(EventLEDDimmed ledDimmed) {
        if (isLEDOn) {
            isLEDDimmed = true;
            isLEDHighBeam = false;
        }
    }
    @Subscribe
    public void receive(EventLEDHighBeam ledHighBeam) {
        if (isLEDOn) {
            isLEDHighBeam = true;
            isLEDDimmed = false;
        }
    }
}

