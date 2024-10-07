package model;

import java.util.*;

// Represents a collection of games
public class Catalogue {
    ArrayList<Game> listOfGames;    // collection of games

    public Catalogue() {
        // Stub
    }

    // MODIFIES: this
    // EFFECTS: adds a game to the catalogue if not already in it
    public void addGame(Game game) {
        // STUB
        }

    // EFFECTS: Filters the catalogue and gets all the unfinished games
    public List<Game> getUnplayedGames() {
        return listOfGames;
    }

    // EFFECTS: Filters the catalogue and gets all the games of a certain genre
    public List<Game> getGamesOfGenre(String genre) {
        return listOfGames;
    }

    // REQUIRES: Game must be in the listOfGames 
    // MODIFIES: this
    // EFFECTS: Deletes game from the listOfGames
    public void sellGame(Game game) {
        // STUB
    }

    public ArrayList<Game> getListofGames() {
        return this.listOfGames;
    }


}
