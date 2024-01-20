package observer;

import jdk.jshell.execution.JdiInitiator;

public class UltrasonicSensor implements IObserver {
    private int batteryTemp;
    private int distance;

    public UltrasonicSensor(int batteryTemp, int distance) {
        this.batteryTemp = batteryTemp;
        this.distance = distance;
    }

    @Override
    public void Update(int batteryTemperature, int dist) {
        if (batteryTemp != batteryTemperature){
            batteryTemp = batteryTemperature;
            System.out.println("Temperature changed");
        }
        if (distance != dist){
            distance = dist;
            System.out.println("Distance changed");
        }

    }

    public void SetTempAndDist(int temp, int dist){
        Update(temp, dist);
    }

    public int getBatteryTemp() {
        return batteryTemp;
    }

    public void setBatteryTemp(int batteryTemp) {
        this.batteryTemp = batteryTemp;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
