package model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writing;

// Represents a collection of games
public class Catalogue implements Writing {
    ArrayList<Game> listOfGames; // collection of games

    public Catalogue() {
        listOfGames = new ArrayList<Game>();
    }

    // MODIFIES: this, EventLog
    // EFFECTS: adds a game to the catalogue if not already in it
    public void addGame(Game game) {
        if (!(listOfGames.contains(game))) {
            listOfGames.add(game);
        }
        EventLog.getInstance().logEvent(new Event("Added " + game.getTitle() + " to the Catalogue"));
    }

    // MODIFIES: EventLog
    // EFFECTS: Filters the catalogue and gets all the unfinished games
    public List<Game> getUnplayedGames() {
        EventLog.getInstance().logEvent(new Event("Fetched unplayed games."));
        return filterGames(GameValues.PERCENTCOMPLETED, "0");
    }

    // MODIFIES: EventLog
    // EFFECTS: Filters the catalogue and gets all the games of a certain genre
    public List<Game> getGamesOfGenre(String genre) {
        EventLog.getInstance().logEvent(new Event("Filtered Games by Genre: " + genre));
        return filterGames(GameValues.GENRE, genre);
    }

    // EFFECTS: Helper function for getUnplayedGames and getGamesOfGenre that
    // filters the list of Games
    public List<Game> filterGames(GameValues filterType, String filterValue) {
        ArrayList<Game> result = new ArrayList<>();
        for (int i = 0; i < listOfGames.size(); i++) {
            if (listOfGames.get(i).match(filterType, filterValue)) {
                result.add(listOfGames.get(i));
            }
        }
        return result;
    }

    // REQUIRES: Game must be in the listOfGames
    // MODIFIES: this, EventLog
    // EFFECTS: Deletes game from the listOfGames
    public void sellGame(Game game) {
        EventLog.getInstance().logEvent(new Event("Sold " + game.getTitle()));
        listOfGames.remove(game);
    }

    public ArrayList<Game> getListofGames() {
        return this.listOfGames;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("catalogue", gamesToJson());
        EventLog.getInstance().logEvent(new Event("Saved Catalogue to Json"));
        return json;
    }

    // EFFECTS: returns games in the catalogue as a JSON array
    private JSONArray gamesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Game g : listOfGames) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }

}
