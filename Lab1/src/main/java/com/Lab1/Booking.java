package com.Lab1;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Booking {
    private Visitor visitor;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Booking(Visitor visitor, Room room, LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("Дата закінчення не може бути раніше за дату початку");
        }
        this.visitor = visitor;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Visitor getVisitor() { return visitor; }
    public void setVisitor(Visitor visitor) { this.visitor = visitor; }

    public Room getRoom() { return room; }
    public void setRoom(Room room) { this.room = room; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    // Бізнес логіка: розрахунок вартості
    public double calculateTotalPrice() {
        long nights = ChronoUnit.DAYS.between(startDate, endDate);
        return nights * room.getPricePerNight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Booking)) return false;
        Booking booking = (Booking) o;
        return Objects.equals(visitor, booking.visitor) &&
                Objects.equals(room, booking.room) &&
                Objects.equals(startDate, booking.startDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(visitor, room, startDate);
    }
}