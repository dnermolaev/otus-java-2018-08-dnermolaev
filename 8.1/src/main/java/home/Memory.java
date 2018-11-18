package home;

import java.util.Map;

public class Memory {

    private final Map atmState;

    public Memory(Map atmStateToSave) {
        atmState = atmStateToSave;
    }

    public Map getSavedState() {
        return atmState;
    }
}

