package persistence;

import org.json.JSONObject;

public interface Writable { // referred to jsondemo
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
