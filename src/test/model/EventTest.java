package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e1;
    private Event e2;
    //private Date d;

    //NOTE: these tests might fail if time at which line (2) below is executed
    //is different from time that line (1) is executed.  Lines (1) and (2) must
    //run in same millisecond for this test to make sense and pass.

    @BeforeEach
    public void runBefore() {
        e1 = new Event("Added account: Jayden");   // (1)
        e2 = new Event("Removed account: Joe");
        //d = Calendar.getInstance().getTime();   // (2)
    }

    @Test
    public void testEvent() {
        assertEquals("Added account: Jayden", e1.getDescription());
        assertEquals("Removed account: Joe", e2.getDescription());
        //assertEquals(d, e.getDate());
    }

//    @Test
//    public void testToString() {
//        assertEquals(d.toString() + "\n" + "Sensor open at door", e.toString());
//    }
}
