package autonomousVehicle.gps;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.gps.EventGPSConnectSatellite;
import events.gps.EventGPSOff;
import events.gps.EventGPSOn;

public class GPS extends Subscriber {
    private boolean isOn = false;
    private String frequency;

    public GPS() {
    }

    public boolean isOn() {
        return isOn;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    @Subscribe
    public void receive(EventGPSOn eventGPSOn) {
        isOn = true;
    }

    @Subscribe
    public void receive(EventGPSOff eventGPSOff) {
        isOn = false;
    }

    @Subscribe
    public void receive(EventGPSConnectSatellite eventGPSConnectSatellite) {
        frequency = eventGPSConnectSatellite.getFrequency();
    }
}
