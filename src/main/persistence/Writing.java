package persistence;

import org.json.JSONObject;

// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public interface Writing {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
