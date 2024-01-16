package autonomousVehicle.electricEngine;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.electricEngine.*;

public class ElectricEngine extends Subscriber {
    private int currentRPM = 0;
    private int maxRPM;
    private boolean isOn = false;

    public ElectricEngine() {

    }
    public int getCurrentRPM() {
        return currentRPM;
    }
    public int getMaxRPM() {
        return maxRPM;
    }
    public boolean isOn() {
        return isOn;
    }
    @Subscribe
    public void receive(EventEngineOn eventElectricEngineOn) {
        isOn = true;
    }
    @Subscribe
    public void receive(EventEngineOff eventElectricEngineOff) {
        isOn = false;
    }
    @Subscribe
    public void receive(EventIncreaseRPM eventIncreaseRPM) {
        // TODO: implement event logic
    }
    @Subscribe
    public void receive(EventDecreaseRPM eventDecreaseRPM) {
        // TODO: implement event logic
    }
}
