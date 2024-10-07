package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGame {
    Game testGame;

    @BeforeEach
    void runBefore() {
        testGame = new Game("Title", "MAC", 2024, Type.DIGITAL, "test");
    }

    @Test
    void testConstructor() {
        assertEquals(testGame.getTitle(), "Title");
        assertEquals(testGame.getPlatform(), "MAC");
        assertEquals(testGame.getYearBought(), 2024);
        assertEquals(testGame.getFormat(), Type.DIGITAL);
        assertEquals(testGame.getGenre(), "test");
        assertEquals(testGame.getPercentCompleted(), 0);
        assertEquals(testGame.getRating(), 0f);
    }

    @Test
    void testRateGame() {
        testGame.rateGame(5f);
        assertEquals(testGame.getRating(), 5f);
        testGame.rateGame(0f);
        assertEquals(testGame.getRating(), 0f);
        testGame.rateGame(9.9f);
        assertEquals(testGame.getRating(), 9.9f);
    }

    @Test
    void testUpdateCompletion() {
        testGame.updateCompletion(50);
        assertEquals(testGame.getPercentCompleted(), 50);
        testGame.updateCompletion(25);
        assertEquals(testGame.getPercentCompleted(), 25);
    }
}
