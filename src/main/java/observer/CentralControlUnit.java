package observer;

public class CentralControlUnit implements ISubject{
    private final UltrasonicSensor[] sensors;
    private int batteryTemperature;
    private int distance;

    public CentralControlUnit(){
        sensors = new UltrasonicSensor[8];
        for (int i = 0; i < 8; i++){
            sensors[i] = new UltrasonicSensor(0, 0);
        }
    }

    @Override
    public void NotifyControlUnit(int index, int temp, int dist) {
        sensors[index].SetTempAndDist(temp, dist);
        int i = 0;
        for (UltrasonicSensor sensor : sensors){
            sensors[i].setBatteryTemp(sensor.getBatteryTemp());
            sensors[i].setDistance(sensor.getDistance());
            i++;
        }
    }

    public UltrasonicSensor[] getSensors() {
        return sensors;
    }

    public int getBatteryTemperature() {
        return batteryTemperature;
    }

    public void setBatteryTemperature(int batteryTemperature) {
        this.batteryTemperature = batteryTemperature;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
