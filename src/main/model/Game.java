package model;

/* Represents a game that has a name, a physical or digital status,
for which system the game is, the year the game was purchased, a 
percent completed value, a rating of the game out of 10 and the genre */
public class Game {
    private String title;           // name of the game
    private Type format;            // if the game is physical or digital
    private String platform;        // the system the game plays on
    private int yearBought;         // the year of purchase
    private int percentCompleted;   // the percent completion value
    private float rating;           // the score the user gives to the game
    private String genre;           // the genre

    public Game(String title, String system, int whenBought, Type physical, String genre) {
        this.title = title;
        this.format = physical;
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
        this.rating = newRating;
    }

    // REQUIRES: newPercent must be greater or equal to 0
    // MODIFIES: this
    // EFFECTS: Updates the percent completion value of the game
    //          by making percentCompleted = newPercent
    public void updateCompletion(int newPercent) {
        this.percentCompleted = newPercent;
    }

    // REQUIRES:filterValue must be a string that represents a possible value for appropriate variable
    // EFFECTS: Helper for filterGames() that returns true if filterValue is equal to the game's value
    public boolean match(GameValues filterType, String filterValue) throws NullPointerException {
        switch (filterType) {
            case GameValues.PERCENTCOMPLETED:
                return (Float.parseFloat(filterValue) == this.getPercentCompleted());
            case GameValues.GENRE:
                return (filterValue.equals(this.getGenre()));
            case GameValues.FORMAT:
                return (Type.valueOf(filterValue).equals(this.getFormat()));
            case GameValues.PLATFORM:
                return (filterValue.equals(this.getPlatform()));
            case GameValues.RATING:
                float v = Float.parseFloat(filterValue);
                float f = Math.abs(v - this.getRating());
                boolean res = f < 1;
                return res;

            default:
                return false;
        }
    }

    public String getTitle() {
        return this.title;
    }

    public Type getFormat() {
        return this.format;
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