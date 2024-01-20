package autonomousVehicle;

import autonomousVehicle.battery.Battery;
import autonomousVehicle.body.Chassis;
import autonomousVehicle.body.Door;
import autonomousVehicle.body.Seat;
import autonomousVehicle.body.Wheel;
import autonomousVehicle.camera.CameraBuilder;
import autonomousVehicle.centralUnit.CentralUnit;
import autonomousVehicle.electricEngine.EngineBrakeMediator;
import com.google.common.eventbus.EventBus;
import autonomousVehicle.camera.CameraMediator;
import autonomousVehicle.electricEngine.ElectricEngine;
import autonomousVehicle.gps.GPS;
import autonomousVehicle.lidar.Lidar;
import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.lights.indicator.Indicator;
import autonomousVehicle.lights.indicator.Position;
import autonomousVehicle.lights.ledHeadlight.LEDHeadlight;
import autonomousVehicle.brake.Brake;
import configuration.Configuration;
import exceptions.InvalidVehicleStateException;
import exceptions.SignatureVerificationException;

import java.util.ArrayList;

public class AutonomousVehicle {

    //region PROPERTIES
    private final Chassis chassis;
    private final ElectricEngine electricEngine;
    private final Battery battery;
    private final LEDHeadlight[] ledHeadLight;
    private final BrakeLight[] brakeLight;
    private final Indicator[] indicator;
    private final Door[] door;
    private final Seat[] seat;
    private final Wheel[] wheel;
    private final Brake[] brake;
    private final GPS[] gps;
    private final ArrayList<Object> cameraPorts;
    private final Lidar[] lidar;
    private final EventBus eventBus;
    private final CentralUnit centralUnit;

    //endregion

    //region GETTERS
    public Chassis getChassis() {
        return chassis;
    }

    public Battery getBattery() {
        return battery;
    }

    public LEDHeadlight[] getLedHeadLight() {
        return ledHeadLight;
    }

    public BrakeLight[] getBrakeLight() {
        return brakeLight;
    }

    public Indicator[] getIndicator() {
        return indicator;
    }

    public Door[] getDoor() {
        return door;
    }

    public Seat[] getSeat() {
        return seat;
    }

    public Wheel[] getWheel() {
        return wheel;
    }

    public Brake[] getBrake() {
        return brake;
    }

    public GPS[] getGps() {
        return gps;
    }

    public ArrayList<Object> getCameraPorts() {
        return cameraPorts;
    }

    public Lidar[] getLidar() {
        return lidar;
    }
    public ElectricEngine getElectricEngine() {
        return electricEngine;
    }

    public CentralUnit getCentralUnit() {
        return centralUnit;
    }
    //endregion

    //region CONSTRUCTOR
    private AutonomousVehicle(Builder builder) {
        this.chassis = builder.chassis;
        this.electricEngine = builder.electricEngine;
        this.battery = builder.battery;
        this.brake = builder.brake;
        this.brakeLight = builder.brakeLight;
        this.cameraPorts = builder.cameraPorts;
        this.door = builder.door;
        this.gps = builder.gps;
        this.indicator = builder.indicator;
        this.ledHeadLight = builder.ledHeadLight;
        this.lidar = builder.lidar;
        this.seat = builder.seat;
        this.wheel = builder.wheel;
        this.eventBus = builder.eventBus;
        this.centralUnit = builder.centralUnit;
    }
    //endregion

    //region BUILDER
    public static class Builder {
        private EventBus eventBus;
        private CentralUnit centralUnit;
        private Chassis chassis;
        private ElectricEngine electricEngine;
        private Battery battery;
        private LEDHeadlight[] ledHeadLight;
        private BrakeLight[] brakeLight;
        private Indicator[] indicator;
        private Door[] door;
        private Seat[] seat;
        private Wheel[] wheel;
        private Brake[] brake;
        private GPS[] gps;
        private ArrayList<Object> cameraPorts;
        private Lidar[] lidar;
        public Builder(){
            this.eventBus = new EventBus();
            this.centralUnit = new CentralUnit(this.eventBus);
        }

        public Builder Chassis() {
            this.chassis = new Chassis();
            return this;
        }

        public Builder ElectricEngine() {
            this.electricEngine = Configuration.INSTANCE.engineType;
            return this;
        }

        public Builder Battery() {
            this.battery = new Battery();
            return this;
        }

        public Builder LEDHeadlight() {
            this.ledHeadLight = new LEDHeadlight[4];
            for (int i = 0; i < 4; i++) {
                this.ledHeadLight[i] = new LEDHeadlight();
            }
            return this;
        }

        public Builder BrakeLight() {
            this.brakeLight = new BrakeLight[4];
            for (int i = 0; i < 4; i++) {
                this.brakeLight[i] = new BrakeLight();
            }
            return this;
        }

        public Builder Indicator() {
            this.indicator = new Indicator[4];
            for (int i = 0; i < 2; i++) {
                this.indicator[i] = new Indicator(Position.LEFT);
            }
            for (int i = 2; i < 4; i++) {
                this.indicator[i] = new Indicator(Position.RIGHT);
            }
            return this;
        }

        public Builder Door() {
            this.door = new Door[4];
            for (int i = 0; i < 4; i++) {
                this.door[i] = new Door();
            }
            return this;
        }

        public Builder Seat() {
            this.seat = new Seat[2];
            this.seat[0] = new Seat();
            this.seat[1] = new Seat();
            return this;
        }

        public Builder Wheel() {
            this.wheel = new Wheel[4];
            for (int i = 0; i < 4; i++) {
                this.wheel[i] = new Wheel();
            }
            return this;
        }

        public Builder Brake() {
            this.brake = new Brake[4];
            for (int i = 0; i < 4; i++) {
                this.brake[i] = new Brake();
            }
            return this;
        }

        public Builder GPS() {
            this.gps = new GPS[2];
            this.gps[0] = new GPS();
            this.gps[1] = new GPS();
            return this;
        }

        public Builder Camera() {
            try {
                CameraBuilder cameraBuilder = new CameraBuilder();
                this.cameraPorts = cameraBuilder.buildCameras(Configuration.INSTANCE.pathToJavaArchive);
            } catch (SignatureVerificationException e) {
                System.out.println("Error: " + e.getMessage());
                System.exit(1);
            }
            return this;
        }

        public Builder Lidar() {
            this.lidar = new Lidar[2];
            this.lidar[0] = Configuration.INSTANCE.lidar;
            this.lidar[1] = Configuration.INSTANCE.lidar;
            return this;
        }

        public AutonomousVehicle build() {
            registerSubscribers();
            return new AutonomousVehicle(this);
        }
        private <T> void registerComponents(T[] components) {
            if (components != null) {
                for (T component : components) {
                    eventBus.register(component);
                }
            }
        }
        private void registerSubscribers(){
            registerComponents(this.lidar);
            registerComponents(this.gps);
            registerComponents(this.brake);
            registerComponents(this.indicator);
            registerComponents(this.brakeLight);
            registerComponents(this.ledHeadLight);
            eventBus.register(this.battery);
            eventBus.register(new CameraMediator(this.cameraPorts));
            eventBus.register(this.electricEngine);
            eventBus.register(new EngineBrakeMediator(this.centralUnit.getEventBus()));
        }
    }
    //endregion
    public void startSimulation(){
        try{

            this.centralUnit.startup();
            this.centralUnit.move(50, 1);
            this.centralUnit.leftTurn(30, 1);
            this.centralUnit.rightTurn(30, 1);
            this.centralUnit.move(50, 1);
            this.centralUnit.stop();
            this.centralUnit.shutdown();
        }catch(InvalidVehicleStateException e){
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
