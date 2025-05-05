package com.Lab1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {

    @Test
    void testToggleAvailability() {
        Room room = new Room(101, "Single", 100.0, true);
        room.toggleAvailability();
        assertFalse(room.isAvailable());
    }

    @Test
    void testEqualsAndHashCode() {
        Room r1 = new Room(101, "Single", 100.0, true);
        Room r2 = new Room(101, "Double", 150.0, false); // roomNumber той самий
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testSetInvalidPrice() {
        Room room = new Room(102, "Double", 200.0, true);
        assertThrows(IllegalArgumentException.class, () -> room.setPricePerNight(-5.0));
    }
}

