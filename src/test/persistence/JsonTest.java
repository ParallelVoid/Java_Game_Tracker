package persistence;

import model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Test that compares the different values to the game
public class JsonTest {

    // EFFECT: compares the game to values
    protected void checkGame(String title, Type format, String platform, int yearBought,
            int completed, float rating, String genre, Game game) {
        assertEquals(title, game.getTitle());
        assertEquals(format, game.getFormat());
        assertEquals(platform, game.getPlatform());
        assertEquals(yearBought, game.getYearBought());
        assertEquals(completed, game.getPercentCompleted());
        assertEquals(rating, game.getRating());
        assertEquals(genre, game.getGenre());
    }
}
