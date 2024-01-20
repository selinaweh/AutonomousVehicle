package autonomousVehicle.electricEngine;

public class EngineNG extends ElectricEngine {
    @Override
    public int CalculateEnergyConsumption(int rpm) {
        return 3 * rpm;
    }
}
