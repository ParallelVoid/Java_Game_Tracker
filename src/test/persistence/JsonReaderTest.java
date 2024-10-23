package persistence;

import model.*;

import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Catalogue c = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCatalogue() {
        JsonReader reader = new JsonReader("./data/testCatalogueEmpty.json");
        try {
            Catalogue c = reader.read();
            assertEquals(0, c.getListofGames().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCatalogue() {
        JsonReader reader = new JsonReader("./data/testGeneralCatalogue.json");
        try {
            Catalogue c = reader.read();
            List<Game> games = c.getListofGames();
            assertEquals(2, games.size());
            checkGame("Tekken 3", Type.PHYSICAL, "PS1", 2018,
                    50, 9, "fighting", games.get(0));
            checkGame("MVC3", Type.DIGITAL, "Steam", 2023,
                    60, 8.8f, "fighting", games.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
