package autonomousVehicle.gps;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.gps.*;
public class GPS extends Subscriber{
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
        // TODO: implement event logic
    }
}
