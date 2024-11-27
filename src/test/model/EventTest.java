package model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EventTest {
    private Event event;
    private Date date;

    // NOTE: these tests might fail if time at which line (2) below is executed
    // is different from time that line (1) is executed. Lines (1) and (2) must
    // run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        event = new Event("Fetched unplayed games."); // (1)
        date = Calendar.getInstance().getTime(); // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Fetched unplayed games.", event.getDescription());
        assertEquals(date.toString(), event.getDate().toString());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Fetched unplayed games.", event.toString());
    }
}
