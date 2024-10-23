package persistence;
import model.Catalogue;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of catalogue to file
// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class JsonWriter {
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        // STUB
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of catalogue to file
    public void write(Catalogue c) {
        // STUB
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        // STUB
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        // STUB
    }
}