package com.Lab1;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private final List<Room> rooms;
    private final List<Visitor> visitors;
    private final List<Booking> bookings;

    public Hotel() {
        this.rooms = new ArrayList<>();
        this.visitors = new ArrayList<>();
        this.bookings = new ArrayList<>();
    }

    // Геттери
    public List<Room> getRooms() {
        return rooms;
    }

    public List<Visitor> getVisitors() {
        return visitors;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public Room findAvailableRoomByNumber(int number) {
        return rooms.stream()
                .filter(r -> r.getRoomNumber() == number && r.isAvailable())
                .findFirst()
                .orElse(null);
    }

    public void removeRoomByNumber(int number) {
        rooms.removeIf(r -> r.getRoomNumber() == number);
    }
}

