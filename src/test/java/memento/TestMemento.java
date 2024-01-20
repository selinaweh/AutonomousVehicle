package memento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestMemento {

    private Config config;
    private Map<String, String> state;
    private ConfigCaretaker caretaker;

    @BeforeEach
    public void Init(){
        caretaker = new ConfigCaretaker();
        state = new HashMap<>();
        state.put("rejectDrunkenPassenger", "true");
        state.put("stopByPoliceRequest", "true");
        state.put("allowDriveByNight", "true");
        state.put("behaviorWithNaggingPassengers", "stopAndWaitForExcuse");
        state.put("musicDuringDrive", "ac/dc");

        config = new Config(state);
    }

    @Test
    public void TestSave(){
        caretaker.setSaveConfig(config.Save());

        config.Undo(caretaker.getSaveConfig());

        Map<String, String> saved = caretaker.getSaveConfig().getState();

        String stateValue = state.get("rejectDrunkenPassenger");

        String savedValue = saved.get("rejectDrunkenPassenger");

        assertEquals(stateValue, savedValue);
    }

    @Test
    public void TestUndo(){
        caretaker.setSaveConfig(config.Save());

        config.SetParameters("rejectDrunkenPassenger", "false");

        assertEquals(state.get("rejectDrunkenPassenger"), "false");

        config.Undo(caretaker.getSaveConfig());

        assertEquals(config.getState().get("rejectDrunkenPassenger"), "true");
    }

    @Test
    public void TestSetParameters(){
        config.SetParameters("rejectDrunkenPassenger", "false");

        assertEquals(state.get("rejectDrunkenPassenger"), "false");
    }
}
