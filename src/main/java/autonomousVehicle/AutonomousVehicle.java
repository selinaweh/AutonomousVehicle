package autonomousVehicle;

import autonomousVehicle.battery.Battery;
import autonomousVehicle.electricEngine.ElectricEngine;
import autonomousVehicle.gps.GPS;
import autonomousVehicle.lidar.Lidar;
import autonomousVehicle.lights.brakeLight.BrakeLight;
import autonomousVehicle.lights.indicator.Indicator;
import autonomousVehicle.lights.indicator.Position;
import autonomousVehicle.lights.ledHeadlight.LEDHeadlight;
import autonomousVehicle.brake.Brake;
import main.Configuration;

public class AutonomousVehicle {

    private final Chassis chassis;
        private final ElectricEngine electricEngine;
        private final Battery battery;
        private final LEDHeadlight [] ledHeadLight;
        private final BrakeLight[] brakeLight;
        private final Indicator[] indicator;
        private final Door [] door;
        private final Seat [] seat;
        private final   Wheel [] wheel;
        private final Brake [] brake;
        private final GPS[] gps;
        private final Camera [] camera;
        private final Lidar[] lidar;

    private AutonomousVehicle(Builder builder){

        this.chassis = builder.chassis;
        this.electricEngine = builder.electricEngine;
        this.battery = builder.battery;
        this.brake = builder.brake;
        this.brakeLight = builder.brakeLight;
        this.camera = builder.camera;
        this.door = builder.door;
        this.gps = builder.gps;
        this.indicator = builder.indicator;
        this.ledHeadLight = builder.ledHeadLight;
        this.lidar = builder.lidar;
        this.seat = builder.seat;
        this.wheel = builder.wheel;


    }




    public static class Builder {

        private Chassis chassis;
        private ElectricEngine electricEngine;
        private Battery battery;
        private LEDHeadlight [] ledHeadLight;
        private BrakeLight[] brakeLight;
        private Indicator[] indicator;
        private Door [] door;
        private Seat [] seat;
        private   Wheel [] wheel;
        private Brake [] brake;
        private GPS[] gps;
        private Camera [] camera;
        private Lidar[] lidar;

        public Builder Chassis(){
            chassis = new Chassis();
            return this;
        }

        public Builder ElectricEngine(){
            electricEngine = Configuration.INSTANCE.engineType;
            return this;
        }

        public Builder Battery(){

            battery = new Battery();
            return this;
        }

        public Builder LEDHeadlight(){
            ledHeadLight = new LEDHeadlight[4];
            for(int i = 0; i<4; i++){
                ledHeadLight[i] = new LEDHeadlight();
            }
            return this;
        }

        public Builder BrakeLight(){
            brakeLight = new BrakeLight[4];
            for(int i = 0; i<4; i++){
                brakeLight[i] = new BrakeLight();
            }
            return this;
        }

        public Builder Indicator(){
            indicator = new Indicator[4];
            for(int i = 0; i<2; i++){
                indicator[i] = new Indicator(Position.LEFT);
            }
            for(int i = 2; i<4; i++){
                indicator[i] = new Indicator(Position.RIGHT);
            }
            return this;
        }

        public Builder Door(){
            door = new Door[4];
            for (int i = 0; i<4; i++){
                door[i] = new Door();
            }
            return this;
        }

        public Builder Seat(){
            seat = new Seat[2];
            seat[0] = new Seat();
            seat[1] = new Seat();
            return this;
        }

        public Builder Wheel(){
            wheel = new Wheel[4];
            for (int i = 0; i<4; i++){
                wheel[i] = new Wheel();
            }
            return this;
        }

        public Builder Brake(){
            brake = new Brake[4];
            for (int i = 0; i<4; i++){
                brake[i] = new Brake();
            }
            return this;
        }

        public Builder GPS(){
            gps = new GPS[2];
            gps[0] = new GPS();
            gps[1] = new GPS();
            return this;
        }

        public Builder Camera(){
            //TODO: add camera
            return this;
        }

        public Builder Lidar(){
            lidar = new Lidar[2];
            lidar[0] = Configuration.INSTANCE.lidar;
            lidar[1] = Configuration.INSTANCE.lidar;
            return this;
        }

        public AutonomousVehicle build(){
            return new AutonomousVehicle(this);
        }

    }



}
