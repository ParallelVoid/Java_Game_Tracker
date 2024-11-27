package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of commands for the Catalogue and Games
// All have global access to single instance of the EventLog
// Refrence: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/tree/master
public class EventLog implements Iterable<Event> {
    /** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<Event> events;

    // MODIFIES: this
    // EFFECT: Makes sure only one instance of EventLog Exists
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECT: Creates singleton of EventLog if it does not already exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    /**
     * Adds an event to the event log.
     * 
     * @param e the event to be added
     */
    // MODIFIES: this
    // EFFECT: Adds Event to EventLog
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: this
    // EFFECT: Clears EventLog
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}