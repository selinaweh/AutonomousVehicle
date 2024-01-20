package autonomousVehicle.electricEngine;
import autonomousVehicle.Subscriber;
import com.google.common.eventbus.Subscribe;
import events.electricEngine.*;

public abstract class ElectricEngine extends Subscriber implements IEngine {
    private int currentRPM = 0;
    private final int maxRPM = 300;
    private boolean isOn = false;

    public ElectricEngine() {

    }
    public int getCurrentRPM() {
        return currentRPM;
    }
    public int getMaxRPM() {
        return maxRPM;
    }
    public void setCurrentRPM(int currentRPM) {
        this.currentRPM = currentRPM;
    }
    public boolean isOn() {
        return isOn;
    }
    public void stop(){
        setCurrentRPM(0);
    }
    @Subscribe
    public void receive(EventEngineOn eventEngineOn) {
        isOn = true;
    }
    @Subscribe
    public void receive(EventEngineOff eventEngineOff) {
        isOn = false;
    }
    @Subscribe
    public void receive(EventIncreaseRPM eventIncreaseRPM) {
        if (isOn){
            setCurrentRPM(Math.min(getCurrentRPM() + eventIncreaseRPM.getDeltaRPM(), getMaxRPM()));
        }else{
            System.out.println("Engine is off");
        }
    }
    @Subscribe
    public void receive(EventDecreaseRPM eventDecreaseRPM) {
        if(isOn){
            setCurrentRPM(Math.max(getCurrentRPM() - eventDecreaseRPM.getDeltaRPM(), 0));
        }else{
            System.out.println("Engine is off");
        }
    }
    @Subscribe
    public void receive(EventEngineStop eventEngineStop) {
        stop();
    }

    @Override
    public int CalculateEnergyConsumption(int rpm) {
        return 0;
    }
}
