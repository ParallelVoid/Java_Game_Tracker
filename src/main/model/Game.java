package model;

enum Type {
    PHYSICAL,
    DIGITAL
  }

  
/* Represents a game that has a name, a physical or digital status,
for which system the game is, the year the game was purchased, a 
percent completed value, a rating of the game out of 10 and the genre */
public class Game {
    private String title;           // name of the game
    private Type type;              // if the game is physical or digital
    private String platform;        // the system the game plays on
    private int yearBought;         // the year of purchase
    private int percentCompleted;   // the percent completion value
    private float rating;           // the score the user gives to the game
    private String genre;           // the genre

    public Game(String title, String system, int whenBought, Type physical, String genre) {
        this.title = title;
        this.type = physical;
        this.platform = system;
        this.yearBought = whenBought;
        this.percentCompleted = 0;
        this.rating = 0f;
        this.genre = genre;
    }

    // REQUIRES: newRating must be between 0 and 10
    // MODIFIES: this
    // EFFECTS: Updates the rating of the game by making rating = newRating
    public void rateGame(float newRating) {
        // STUB
    }

    // REQUIRES: newPercent must be 0, 25, 50, 80 or 100; 0 means the game
    //           is unplayed, 25 means briefly tried, 50 is mostly complete,
    //           80 is beat the game and 100 is completed the game
    // MODIFIES: this
    // EFFECTS: Updates the percent completion value of the game
    //          by making percentCompleted = newPercent
    public void updateCompletion(int newPercent) {
        // STUB
    }

    public String getTitle() {
        return this.title;
    }

    public Type getType() {
        return this.type;
    }

    public String getPlatform() {
        return this.platform;
    }

    public int getYearBought() {
        return this.yearBought;
    }

    public float getPercentCompleted() {
        return this.percentCompleted;
    }

    public float getRating() {
        return this.rating;
    }

    public String getGenre() {
        return this.genre;
    }

}