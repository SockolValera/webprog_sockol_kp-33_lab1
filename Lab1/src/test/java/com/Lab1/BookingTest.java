package com.Lab1;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    void testCalculateTotalPrice() {
        Room room = new Room(201, "Suite", 300.0, true);
        Visitor visitor = new Visitor(3, "Oleg", "+380671119933");
        Booking booking = new Booking(visitor, room, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 4));
        assertEquals(900.0, booking.calculateTotalPrice());
    }

    @Test
    void testBookingEquality() {
        Visitor visitor = new Visitor(4, "Mariya", "+380671119933");
        Room room = new Room(202, "Deluxe", 400.0, true);
        Booking b1 = new Booking(visitor, room, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 3));
        Booking b2 = new Booking(visitor, room, LocalDate.of(2025, 5, 1), LocalDate.of(2025, 5, 4));
        assertEquals(b1, b2);
    }

    @Test
    void testInvalidDateRange() {
        Room room = new Room(203, "Double", 200.0, true);
        Visitor visitor = new Visitor(5, "Petro", "+380671119933");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> new Booking(visitor, room, LocalDate.of(2025, 5, 10), LocalDate.of(2025, 5, 5))
        );
        assertEquals("Дата закінчення не може бути раніше за дату початку", exception.getMessage());
    }
}
