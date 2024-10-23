package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
import org.junit.experimental.categories.Category;

// Represents a reader that reads workroom from JSON data stored in file
// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads catalogue from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Catalogue read() throws IOException {
        return new Catalogue(); // STUB
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        return ""; // STUB
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Catalogue parseCatalogue(JSONObject jsonObject) {
        return new Catalogue(); // STUB
    }

    // MODIFIES: c
    // EFFECTS: parses games from JSON object and adds them to catalogue
    private void addThingies(Catalogue c, JSONObject jsonObject) {
        // STUB
    }

    // MODIFIES: c
    // EFFECTS: parses thingy from JSON object and adds it to the catalogue
    private void addGame(Catalogue c, JSONObject jsonObject) {
         // STUB
    }
}