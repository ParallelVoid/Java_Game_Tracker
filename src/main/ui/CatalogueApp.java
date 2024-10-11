package ui;

import java.util.*;

import org.junit.platform.engine.TestDescriptor.*;

import model.*;

// Repressents the catalogue application, that lets the user interact with their game catalogue
public class CatalogueApp {
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
    private void runCatalogue() {
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
        String str8 = "- quit: quits the program";

        System.out.println(str1);
        System.out.println(str2);
        System.out.println(str3);
        System.out.println(str4);
        System.out.println(str5);
        System.out.println(str6);
        System.out.println(str7);
        System.out.println(str8);
    }

    // EFFECTS: Prints the title of games in list with corresponding system
    private void printGamesInList(List<Game> games) {
        // Stub
    }

    // MODIFIES: this
    // EFFECTS: reads the terminal to create a new game
    private void addGame(Catalogue catalogue) {
        // Stub
    }

    // EFFECTS: Finds the game in catalogue through title, allows user to 
    //          preform actions to the game
    private void getGame() {
        // Stub
    }

    // MODIFIES: this
    // EFFECTS: Finds the game by title (taken from terminal), and removes from Catalogue
    private void sell() {
        // Stub
    }

    // EFFECTS: Takes a user input of the genre and prints the list
    private void genreGamesPrint() {
        // Stub
    }
}
