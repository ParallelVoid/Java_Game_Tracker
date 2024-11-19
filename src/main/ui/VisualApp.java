package ui;

import javax.swing.*;

import model.Catalogue;
import model.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Repressents a visual catalogue application, that lets the user interact with their game catalogue
// Refrenced: https://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html
public class VisualApp extends CatalogueApp {
    JPanel listPanel;
    JLabel detailLabel;
    JPanel detailPanel;
    DefaultListModel<String> allGames;
    ArrayList<String> buttons;
    JList<String> list;

    // MODIFIES: this
    // EFFECTS: Runs the catalogue application
    public VisualApp() {
        super();
    }

    // Creates an the window that runs the main logic
    @Override
    protected void runCatalogue() {
        allGames = new DefaultListModel<>();
        JFrame frame = new JFrame("Games Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        JToolBar toolBar = createToolBar();
        JSplitPane splitPane = createSplitPane();

        frame.setLayout(new BorderLayout());
        frame.add(toolBar, BorderLayout.NORTH);
        frame.add(splitPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    // EFFECTS: Processes user inputs
    private void pressButton(String command) {
        
    }

    // EFFECTS: Selecting a game in the list
    private void selectGame(String command) {
        
    }

    // EFFECTS: Method to create a toolbar with some buttons
    private JToolBar createToolBar() {
        return new JToolBar();
    }

    // EFFECTS: Creates two panes - one for the list of games, the other for details
    private JSplitPane createSplitPane() {
        return null;
    }

    // MODIFIES: this
    // EFFECTS: Adds game to list panel
    private void populateGamesInList(List<Game> games) {
        
    }

    // MODIFIES: this
    // EFFECTS: reads the terminal to create a new game
    @Override
    protected void addGame(Catalogue catalogue) {
        
    }

    // MODIFIES: this
    // EFFECTS: Create a new dialogue, that takes two inputs, then updates game
    private void editGame(Game game) {
        
    }

    // EFFECTS: Takes a user input of the genre and prints the list
    @Override
    protected void genreGamesPrint() {
        
    }

    // EFFECTS: Displays the information of the game on detail panel
    private void printGameDetails(Game game) {
        
    }
}
