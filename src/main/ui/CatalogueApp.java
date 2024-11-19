package ui;

import java.io.IOException;
import java.util.*;

// import org.junit.platform.engine.TestDescriptor.*;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

// Repressents the catalogue application, that lets the user interact with their game catalogue
// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class CatalogueApp {
    protected static final String JSON_STORE = "./data/catalogue.json";
    Catalogue catalogue;
    boolean running;
    Scanner in;

    // MODIFIES: this
    // EFFECTS: Runs the catalogue application
    public CatalogueApp() {
        in = new Scanner(System.in);
        catalogue = new Catalogue();
        runCatalogue();
    }

    // EFFECTS: Processes user inputs
    @SuppressWarnings("methodlength")
    protected void runCatalogue() {
        running = true;
        while (running) {
            System.out.println("What would you like to do (press enter for help):");
            String newCommand = in.nextLine().toLowerCase();
            if (newCommand.equals("quit")) {
                running = false;
                break;
            } else if (newCommand.equals("add game")) {
                addGame(catalogue);
            } else if (newCommand.equals("get game")) {
                getGame();
            } else if (newCommand.equals("catalogue")) {
                printGamesInList(catalogue.getListofGames());
            } else if (newCommand.equals("unplayed games")) {
                printGamesInList(catalogue.getUnplayedGames());
            } else if (newCommand.equals("games of genre")) {
                genreGamesPrint();
            } else if (newCommand.equals("sell game")) {
                sell();
            } else if (newCommand.equals("save")) {
                save();
            } else if (newCommand.equals("load")) {
                load();
            } else {
                help();
            }
        }
        in.close();
    }

    // EFFECTS: Prints all possible commands of Catalogue Application
    private void help() {
        String str1 = "Commands:";
        String str2 = "- add game: add game to your Catalogue";
        String str3 = "- catalogue: prints the Catalogue";
        String str4 = "- unplayed games: prints the unplayed games in the Catalogue";
        String str5 = "- games of genre: prints the games in the Catalogue of chosen genre";
        String str6 = "- sell game: removes game from your Catalogue";
        String str7 = "- get game: edit a game status";
        String str9 = "- save catalogue: saves the catalogue";
        String str10 = "- load catalogue: loads your catalogue from json";
        String str8 = "- quit: quits the program";

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str6);
        System.out.println(str7);
        System.out.println(str9);
        System.out.println(str10);
        System.out.println(str8);
    }

    // EFFECTS: Finds a game from the list by name
    private Game findGame(String name) {
        for (int i = 0; i < catalogue.getListofGames().size(); i++) {
            if (name.equals(catalogue.getListofGames().get(i).getTitle())) {
                return catalogue.getListofGames().get(i);
            }
        }
        System.out.println("Game does not exist");
        return null;
    }

    // EFFECTS: Prints the title of games in list with corresponding system
    private void printGamesInList(List<Game> games) {
        for (int i = 0; i < games.size(); i++) {
            System.out.println(games.get(i).getTitle() + "(" + games.get(i).getPlatform() + ")");
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the terminal to create a new game
    protected void addGame(Catalogue catalogue) {
        System.out.println("What game do you have?");
        String newGame = in.nextLine();
        System.out.println("Is your copy of " + newGame + " digital or physical?\n If physical enter true, else false");
        boolean physicalBool = Boolean.parseBoolean(in.nextLine());
        System.out.println("For what system is " + newGame + "?");
        String system = in.nextLine();
        System.out.println("What Genre is " + newGame + "?");
        String genre = in.nextLine();
        System.out.println("When was this game purchase?");
        int whenPurchased = Integer.parseInt(in.nextLine());
        if (physicalBool) {
            Game game = new Game(newGame, system, whenPurchased, model.Type.PHYSICAL, genre);
            catalogue.addGame(game);
        } else {
            Game game = new Game(newGame, system, whenPurchased, model.Type.DIGITAL, genre);
            catalogue.addGame(game);
        }
    }

    // EFFECTS: Finds the game in catalogue through title, allows user to 
    //          preform actions to the game
    private void getGame() {
        System.out.println("What game are you looking for?");
        String gameTitle = in.nextLine();
        Game game = findGame(gameTitle);
        gameFunctions(game);
    }

    // EFFECTS: Helper for getGame(), that prints the details of the game and allows the user
    //          to call specific game functions from this point on
    private void gameFunctions(Game game) {
        System.out.println(Game.tagTitle + ": " + game.getTitle());
        System.out.println(Game.tagPlatform + ": " + game.getPlatform());
        System.out.println(Game.tagFormat + ": " + game.getFormat());
        System.out.println(Game.tagGenre + ": " + game.getGenre());
        System.out.println(Game.tagYearBought + ": " + game.getYearBought());
        System.out.println(Game.tagCompletion + ": " + game.getPercentCompleted());
        System.out.println(Game.tagRating + ": " + game.getRating() + "\n");

        System.out.println(
                "You may select 1 to update percent completion, 2 to update rating, or 3 to return to main menu");
        String choice = in.nextLine();

        switch (choice) {
            case "1":
                updatePercent(game);
                break;
            case "2":
                updateRating(game);
                break;

            default:
                break;
        }
    }

    // EFFECTS: Helper for gameFunctions(), that takes a terminal input, then updates game value
    private void updatePercent(Game game) {
        System.out.println("How much of " + game.getTitle() + " have you played?");
        int percent = Integer.parseInt(in.nextLine());
        game.updateCompletion(percent);
    }

    // EFFECTS: Helper for gameFunctions(), that takes a terminal input, then updates game value
    private void updateRating(Game game) {
        System.out.println("What would you rate " + game.getTitle() + " out of 10?");
        float rating = Float.parseFloat(in.nextLine());
        game.rateGame(rating);
    }

    // MODIFIES: this
    // EFFECTS: Finds the game by title (taken from terminal), and removes from Catalogue
    private void sell() {
        System.out.println("What Game are you selling?");
        String sellGame = in.nextLine();
        Game sellingGame = findGame(sellGame);
        catalogue.sellGame(sellingGame);
    }

    // EFFECTS: Takes a user input of the genre and prints the list
    protected void genreGamesPrint() {
        System.out.println("What Genre are we filtering?");
        String filterGenre = in.nextLine();
        printGamesInList(catalogue.getGamesOfGenre(filterGenre));
    }

    // EFFECTS: saves the catalogue to file
    protected void save() {
        try {
            JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
            jsonWriter.open();
            jsonWriter.write(catalogue);
            jsonWriter.close();
            System.out.println("Successfully saved catalogue to " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to save");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads catalogue from file
    protected void load() {
        try {
            JsonReader jsonReader = new JsonReader(JSON_STORE);
            catalogue = jsonReader.read();
            System.out.println("Successfully loaded your Catalogue from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Failed to load catalogue");
        }
    }

}
