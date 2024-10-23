package persistence;

import model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;
// import org.junit.experimental.categories.Category;

// Represents a reader that reads workroom from JSON data stored in file
// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class JsonReader {
    private String path;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads catalogue from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Catalogue read() throws IOException {
        String jsonData = readFile(path);
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
        JSONArray games = jsonObject.getJSONArray("catalogue");
        for (Object game : games) {
            JSONObject nextGame = (JSONObject) game;
            addGame(c, nextGame);
        }
    }

    // MODIFIES: c
    // EFFECTS: parses thingy from JSON object and adds it to the catalogue
    private void addGame(Catalogue c, JSONObject jsonObject) {
        String title = jsonObject.getString(Game.tagTitle);
        Type format = Type.valueOf(jsonObject.getString(Game.tagFormat));
        String platform = jsonObject.getString(Game.tagPlatform);
        int yearBought = jsonObject.getInt(Game.tagYearBought);
        int percentCompleted = jsonObject.getInt(Game.tagCompletion);
        float rating = jsonObject.getFloat(Game.tagRating);
        String genre = jsonObject.getString(Game.tagGenre);

        Game game = new Game(title, platform, yearBought, format, genre);
        game.updateCompletion(percentCompleted);
        game.rateGame(rating);
        c.addGame(game);
    }
}
