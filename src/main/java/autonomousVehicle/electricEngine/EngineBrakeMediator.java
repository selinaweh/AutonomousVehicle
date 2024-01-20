package autonomousVehicle.electricEngine;

import autonomousVehicle.Subscriber;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import events.brake.EventBrakeSet;
import events.electricEngine.EventEngineStop;

public class EngineBrakeMediator extends Subscriber {

    private final EventBus eventBus;

    public EngineBrakeMediator(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Subscribe
    public void receive(EventBrakeSet event) {
        if (event.getPercentage() == 100) {
            eventBus.post(new EventEngineStop());
        }
    }
}

