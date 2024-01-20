package observer;

import command.ElectronicKey;

public class CentralControlUnit implements ISubject{
    private final UltrasonicSensor[] sensors;
    private int batteryTemperature;
    private int distance;
    private boolean isActive;
    private String encryptedPassword;

    public CentralControlUnit(String encryptedPassword){
        this.encryptedPassword = encryptedPassword;
        this.isActive = false;
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

    public void activate(String encryptedPassword, ElectronicKey eKey) throws Exception{
        if (this.encryptedPassword.equals(encryptedPassword)){
            this.isActive = !this.isActive;
        } else {
            throw new Exception("Wrong password");
        }
    }

    public boolean isActive(){
        return this.isActive;
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
