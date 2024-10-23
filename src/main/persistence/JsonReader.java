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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCatalogue(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private Catalogue parseCatalogue(JSONObject jsonObject) {
        Catalogue c = new Catalogue();
        addGames(c, jsonObject);
        return c;
    }

    // MODIFIES: c
    // EFFECTS: parses games from JSON object and adds them to catalogue
    private void addGames(Catalogue c, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("catalogue");
        for (Object json : jsonArray) {
            JSONObject nextGame = (JSONObject) json;
            addGame(c, nextGame);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses thingy from JSON object and adds it to the catalogue
    private void addGame(Catalogue c, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        Type format = Type.valueOf(jsonObject.getString("format"));
        String platform = jsonObject.getString("platform");
        int yearBought = jsonObject.getInt("yearBought");
        int percentCompleted = jsonObject.getInt("percentCompleted");
        float rating = jsonObject.getFloat("rating");
        String genre = jsonObject.getString("genre");

        Game game = new Game(title, platform, yearBought, format, genre);
        game.updateCompletion(percentCompleted);
        game.rateGame(rating);
        c.addGame(game);
    }
}