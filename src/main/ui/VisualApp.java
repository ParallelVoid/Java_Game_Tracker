package ui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
    List<Game> currentGames;

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
        if (command == buttons.get(0)) {
            addGame(catalogue);
        } else if (command == buttons.get(1)) {
            selectGame("edit");
        } else if (command == buttons.get(2)) {
            selectGame("sell");
            populateGamesInList(catalogue.getListofGames());
        } else if (command == buttons.get(3)) {
            genreGamesPrint();
        } else if (command == buttons.get(4)) {
            populateGamesInList(catalogue.getListofGames());
        } else if (command == buttons.get(5)) {
            populateGamesInList(catalogue.getUnplayedGames());
        } else if (command == buttons.get(6)) {
            save();
        } else if (command == buttons.get(7)) {
            load();
            populateGamesInList(catalogue.getListofGames());
        }
    }

    // MODIFIES: this
    // EFFECTS: Selecting a game in the list
    private void selectGame(String command) {
        int index = list.getSelectedIndex();
        if (index != -1) {
            Game selectGame = currentGames.get(index);
            switch (command) {
                case "edit":
                    editGame(selectGame);
                    break;
                case "sell":
                    catalogue.sellGame(selectGame);
                    break;

                default:
                    break;
            }
        }
    }

    // EFFECTS: Method to create a toolbar with some buttons
    private JToolBar createToolBar() {
        JToolBar toolBar = new JToolBar("Main Toolbar");
        toolBar.setFloatable(false); // Make it fixed

        buttons = new ArrayList<>();
        buttons.add("Add Game");
        buttons.add("Update Game");
        buttons.add("Sell Game");
        buttons.add("Games of Genre");
        buttons.add("Catalogue");
        buttons.add("Unplayed Games");
        buttons.add("Save");
        buttons.add("Load");

        for (int i = 0; i < 8; i++) {
            final String buttonText = buttons.get(i);
            JButton b = new JButton(buttonText);
            b.addActionListener(e -> pressButton(buttonText));
            toolBar.add(b);
        }

        return toolBar;
    }

    // EFFECTS: Creates two panes - one for the list of games, the other for details
    private JSplitPane createSplitPane() {
        detailPanel = createDetailPanel();
        listPanel = createListPanel();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, listPanel, detailPanel);
        splitPane.setDividerLocation(200); // Set initial size of the left panel (list)

        return splitPane;
    }

    // EFFECTS: Helper function for createSplitPane(), makes the list panel that has
    //          all currently filtered games listed on it 
    private JPanel createListPanel() {
        JPanel listPanel = new JPanel(new BorderLayout());
        list = new JList<>(allGames);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent evt) {
                if (!evt.getValueIsAdjusting()) {
                    if (list.getSelectedIndex() >= 0) {
                        printGameDetails(currentGames.get(list.getSelectedIndex()));
                    } else {
                        detailLabel.setText("Select an item to see details.");
                    }

                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(list);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        return listPanel;
    }

    // EFFECTS: Helper function for createSplitPane(), makes the list panel that has
    //          the details of the game selected 
    private JPanel createDetailPanel() {
        detailPanel = new JPanel(new BorderLayout());
        detailLabel = new JLabel("Select an item to see details.");
        detailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        detailPanel.add(detailLabel, BorderLayout.CENTER);

        return detailPanel;
    }

    // MODIFIES: this
    // EFFECTS: Adds game to list panel
    private void populateGamesInList(List<Game> games) {
        currentGames = games;
        allGames.clear();
        for (Game g : games) {
            allGames.addElement(g.getTitle() + "(" + g.getPlatform() + ")");
        }
    }

    // MODIFIES: this
    // EFFECTS: reads the terminal to create a new game
    @Override
    protected void addGame(Catalogue catalogue) {
        JTextField ans1 = new JTextField();
        JTextField ans2 = new JTextField();
        JTextField ans3 = new JTextField();
        JTextField ans4 = new JTextField();
        JTextField ans5 = new JTextField();
        Object[] message = {
                "Title of the Game:", ans1,
                "Print true if Physical or false if Digital:", ans2,
                "System of the game:", ans3,
                "Genre of the Game:", ans4,
                "Purchased Year:", ans5 };
        int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            createGame(ans1, ans2, ans3, ans4, ans5);
        }
    }

    // REQUIRES: ans2 must be true or false
    // MODIFIES: this
    // EFFECTS: Helper function for addGame, takes answer fields and makes a new game
    private void createGame(JTextField ans1, JTextField ans2, JTextField ans3, JTextField ans4, JTextField ans5) {
        String newGame = ans1.getText();
        boolean physicalBool = Boolean.parseBoolean(ans2.getText());
        String system = ans3.getText();
        String genre = ans4.getText();
        int whenPurchased = Integer.parseInt(ans5.getText());
        if (physicalBool) {
            Game game = new Game(newGame, system, whenPurchased, model.Type.PHYSICAL, genre);
            catalogue.addGame(game);
        } else {
            Game game = new Game(newGame, system, whenPurchased, model.Type.DIGITAL, genre);
            catalogue.addGame(game);
        }
    }

    // MODIFIES: this
    // EFFECTS: Create a new dialogue, that takes two inputs, then updates game
    private void editGame(Game game) {
        JTextField ans1 = new JTextField();
        JTextField ans2 = new JTextField();
        Object[] message = { "Percent Completed (out of 100):", ans1,
                "Rating of the game (out of 10):", ans2 };
        int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            game.updateCompletion(Integer.parseInt(ans1.getText()));
            game.rateGame(Float.parseFloat(ans2.getText()));
        }
    }

    // EFFECTS: Takes a user input of the genre and prints the list
    @Override
    protected void genreGamesPrint() {
        JTextField ans1 = new JTextField();
        Object[] message = { "What Genre are we filtering?", ans1 };
        int option = JOptionPane.showConfirmDialog(null, message, "Enter all your values",
                JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            populateGamesInList(catalogue.getGamesOfGenre(ans1.getText()));
        }
    }

    // EFFECTS: Displays the information of the game on detail panel
    private void printGameDetails(Game game) {
        String gameDesciption = "<html>Game: " + game.getTitle() + "<br/>";
        gameDesciption += Game.tagFormat + ": " + game.getFormat() + "<br/>";
        gameDesciption += Game.tagPlatform + ": " + game.getPlatform() + "<br/>";
        gameDesciption += Game.tagYearBought + ": " + game.getYearBought() + "<br/>";
        gameDesciption += Game.tagCompletion + ": " + game.getPercentCompleted() + "<br/>";
        gameDesciption += Game.tagRating + ": " + game.getRating() + "<br/>";
        gameDesciption += Game.tagGenre + ": " + game.getGenre() + "<br/>";
        detailLabel.setText(gameDesciption);
    }
}
