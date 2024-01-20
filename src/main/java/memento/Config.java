package memento;

import java.util.Map;

public class Config {
    private Map<String, String> state;

    public Config(Map<String, String> state) {
        super();
        this.state = state;
    }

    public ConfigMemento Save() {
        return new ConfigMemento(state);
    }

    public void Undo(ConfigMemento saveConfig) {
        state = saveConfig.getState();
    }

    public void PrintConfig() {
        System.out.println("Config: ");
        for (Map.Entry<String, String> entry : state.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
    }

    public Map<String, String> getState() {
        return state;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }

    public void SetParameters(String paramName, String value) {
        state.put(paramName, value);
    }


}
