package autonomousVehicle.lights.brakeLight;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;

import events.lights.brakeLight.EventBrakeLightOff;
import events.lights.brakeLight.EventBrakeLightOn;

public class BrakeLight extends Subscriber {
    private boolean isOn = false;
    public BrakeLight() {
    }

    public boolean isOn() {
        return isOn;
    }
    @Subscribe
    public void receive(EventBrakeLightOn brakeLightOn) {
        isOn = true;
    }
    @Subscribe
    public void receive(EventBrakeLightOff brakeLightOff) {
        isOn = false;
    }
}
