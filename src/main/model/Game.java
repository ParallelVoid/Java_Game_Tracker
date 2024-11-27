package model;

import org.json.JSONObject;

import persistence.Writing;

/* Represents a game that has a name, a physical or digital status,
for which system the game is, the year the game was purchased, a 
percent completed value, a rating of the game out of 10 and the genre */
public class Game implements Writing {
    public static final String tagTitle = "Title";
    public static final String tagFormat = "Format";
    public static final String tagPlatform = "Platform";
    public static final String tagYearBought = "Year Bought";
    public static final String tagCompletion = "Percent Completed";
    public static final String tagRating = "Rating";
    public static final String tagGenre = "Genre";

    private String title; // name of the game
    private Type format; // if the game is physical or digital
    private String platform; // the system the game plays on
    private int yearBought; // the year of purchase
    private int percentCompleted; // the percent completion value
    private float rating; // the score the user gives to the game
    private String genre; // the genre
    private String cover; // the cover string

    public Game(String title, String system, int whenBought, Type physical, String genre) {
        this.title = title.trim();
        this.format = physical;
        this.platform = system.trim();
        this.yearBought = whenBought;
        this.percentCompleted = 0;
        this.rating = 0f;
        this.genre = genre.trim();
        this.cover = this.title.replace(" ", "-") + ".png";
    }

    // REQUIRES: newRating must be between 0 and 10
    // MODIFIES: this, EventLog
    // EFFECTS: Updates the rating of the game by making rating = newRating
    public void rateGame(float newRating) {
        EventLog.getInstance().logEvent(new Event("Updated the Rating of " + this.title));
        this.rating = newRating;
    }

    // REQUIRES: newPercent must be greater or equal to 0
    // MODIFIES: this, EventLog
    // EFFECTS: Updates the percent completion value of the game
    // by making percentCompleted = newPercent
    public void updateCompletion(int newPercent) {
        EventLog.getInstance().logEvent(new Event("Updated the Percent Completion of " + this.title));
        this.percentCompleted = newPercent;
    }

    // REQUIRES:filterValue must be a string that represents a possible value for
    // appropriate variable
    // EFFECTS: Helper for filterGames() that returns true if filterValue is equal
    // to the game's value
    public boolean match(GameValues filterType, String filterValue) throws NullPointerException {
        switch (filterType) {
            case PERCENTCOMPLETED:
                return (Float.parseFloat(filterValue) == this.getPercentCompleted());
            case GENRE:
                return (filterValue.equals(this.getGenre()));
            case FORMAT:
                return (Type.valueOf(filterValue).equals(this.getFormat()));
            case PLATFORM:
                return (filterValue.equals(this.getPlatform()));
            case RATING:
                float v = Float.parseFloat(filterValue);
                float f = Math.abs(v - this.getRating());
                boolean res = f < 1;
                return res;

            default:
                return false;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(tagTitle, title);
        json.put(tagFormat, format);
        json.put(tagPlatform, platform);
        json.put(tagYearBought, yearBought);
        json.put(tagCompletion, percentCompleted);
        json.put(tagRating, rating);
        json.put(tagGenre, genre);
        return json;
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

    // EFFECTS: Returns a string of the title without spaces
    public String getCover() {
        return this.cover;
    }

}