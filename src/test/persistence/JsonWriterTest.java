package persistence;

import model.*;

// import org.junit.experimental.categories.Category;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Catalogue c = new Catalogue();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyCatalogue() {
        try {
            Catalogue c = new Catalogue();
            JsonWriter writer = new JsonWriter("./data/testCatalogueEmpty.json");
            writer.open();
            writer.write(c);
            writer.close();

            JsonReader reader = new JsonReader("./data/testCatalogueEmpty.json");
            c.getListofGames().size();
            c = reader.read();
            assertEquals(0, c.getListofGames().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCatalogue() {
        try {
            Catalogue c = new Catalogue();
            Game g1 = new Game("Tekken 3", "PS1", 2018, Type.PHYSICAL, "fighting");
            g1.rateGame(9);
            g1.updateCompletion(50);
            Game g2 = new Game("MVC3", "Steam", 2023, Type.DIGITAL, "fighting");
            g2.rateGame(8.8f);
            g2.updateCompletion(60);
            c.addGame(g1);
            c.addGame(g2);
            JsonWriter writer = new JsonWriter("./data/testGeneralCatalogue.json");
            writer.open();
            writer.write(c);
            writer.close();
            JsonReader reader = new JsonReader("./data/testGeneralCatalogue.json");
            c = reader.read();
            List<Game> games = c.getListofGames();
            assertEquals(2, games.size());
            checkGame("Tekken 3", Type.PHYSICAL, "PS1", 2018, 50, 9, "fighting", games.get(0));
            checkGame("MVC3", Type.DIGITAL, "Steam", 2023, 60, 8.8f, "fighting", games.get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
