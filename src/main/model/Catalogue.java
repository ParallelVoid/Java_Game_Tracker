package model;

import java.util.*;

// Represents a collection of games
public class Catalogue {
    ArrayList<Game> listOfGames; // collection of games

    public Catalogue() {
        listOfGames = new ArrayList<Game>();
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the catalogue if not already in it
    public void addGame(Game game) {
        if (!(listOfGames.contains(game))) {
            listOfGames.add(game);
        }
    }

    // EFFECTS: Filters the catalogue and gets all the unfinished games
    public List<Game> getUnplayedGames() {
        return filterGames(GameValues.PERCENTCOMPLETED, "0");
    }

    // EFFECTS: Filters the catalogue and gets all the games of a certain genre
    public List<Game> getGamesOfGenre(String genre) {
        return filterGames(GameValues.GENRE, genre);
    }

    // EFFECTS: Helper function for getUnplayedGames and getGamesOfGenre that
    //          filters the list of Games
    public List<Game> filterGames(GameValues filterType, String filterValue) {
        ArrayList<Game> result = new ArrayList<>();
        for (int i = 0; i < listOfGames.size(); i++) {
            if (listOfGames.get(i).match(filterType,filterValue)) {
                result.add(listOfGames.get(i));
            }
        }
        return result;
    }

    // REQUIRES: Game must be in the listOfGames
    // MODIFIES: this
    // EFFECTS: Deletes game from the listOfGames
    public void sellGame(Game game) {
        listOfGames.remove(game);
    }

    public ArrayList<Game> getListofGames() {
        return this.listOfGames;
    }

}
