package memento;

public class ConfigCaretaker {
    private ConfigMemento configMemento;

    public ConfigMemento getSaveConfig() {
        return configMemento;
    }

    public void setSaveConfig(ConfigMemento configMemento) {
        this.configMemento = configMemento;
    }
}
