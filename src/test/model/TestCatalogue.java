package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCatalogue {
    Catalogue testCatalogue;
    ArrayList<Game> testListOfGames;
    Game testGame;

    @BeforeEach
    void runBefore() {
        testCatalogue = new Catalogue();
        testListOfGames = new ArrayList<Game>();
        testGame = new Game("Title", "MAC", 2024, Type.DIGITAL, "test");
    }

    @Test
    void testConstructor() {
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
        assertEquals(testCatalogue.getListofGames().size(), 0);
    }

    @Test
    void testAddGame() {
        testListOfGames.add(testGame);
        testCatalogue.addGame(testGame);
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
        testCatalogue.addGame(testGame);
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
    }

    @Test
    void testAddMultipleGames() {
        Game testGame2 = new Game("Title2", "MAC", 2024, Type.DIGITAL, "test2");
        testListOfGames.add(testGame);
        testListOfGames.add(testGame2);
        testCatalogue.addGame(testGame);
        testCatalogue.addGame(testGame2);
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
        testCatalogue.addGame(testGame2);
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
    }

    @Test
    void testGetUnplayedGames() {
        testCatalogue.addGame(testGame);
        testListOfGames.add(testGame);
        assertEquals(testCatalogue.getUnplayedGames(), testListOfGames);
        testGame.updateCompletion(25);
        testListOfGames.clear();
        assertEquals(testCatalogue.getUnplayedGames(), testListOfGames);
        assertEquals(testCatalogue.getUnplayedGames().size(), 0);
    }

    @Test
    void testGetGamesOfGenre() {
        testCatalogue.addGame(testGame);
        Game testGame2 = new Game("Title2", "MAC", 2024, Type.DIGITAL, "test2");
        Game testGame3 = new Game("Title3", "MAC", 2024, Type.DIGITAL, "test2");
        testCatalogue.addGame(testGame2);
        testCatalogue.addGame(testGame3);
        testListOfGames.add(testGame);
        assertEquals(testCatalogue.getGamesOfGenre("test"), testListOfGames);
        testListOfGames.clear();
        testListOfGames.add(testGame2);
        testListOfGames.add(testGame3);
        assertEquals(testCatalogue.getGamesOfGenre("test2"), testListOfGames);
    }

    @Test
    void testSellGame() {
        testCatalogue.addGame(testGame);
        Game testGame2 = new Game("Title2", "MAC", 2024, Type.DIGITAL, "test");
        testCatalogue.addGame(testGame2);
        testCatalogue.sellGame(testGame2);
        testListOfGames.add(testGame);
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
        testCatalogue.sellGame(testGame);
        testListOfGames.clear();
        assertEquals(testCatalogue.getListofGames(), testListOfGames);
        assertEquals(testCatalogue.getListofGames().size(), 0);
    }
}
