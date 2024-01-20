package memento;

import java.util.HashMap;
import java.util.Map;

public class ConfigMemento {
    private Map<String, String> state;

    public ConfigMemento(Map<String, String> state) {
        super();
        this.state = new HashMap<>(state);
    }

    public Map<String, String> getState() {
        return state;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }
}
