package events.gps;

public class EventGPSConnectSatellite {
    private final String frequency;

    public EventGPSConnectSatellite(String frequency) {
        this.frequency = frequency;
    }

    public String getFrequency() {
        return frequency;
    }
}
