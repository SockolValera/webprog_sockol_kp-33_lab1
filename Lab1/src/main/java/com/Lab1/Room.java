package com.Lab1;

import java.util.Objects;

public class Room {
    private int roomNumber;
    private String type;
    private double pricePerNight;
    private boolean isAvailable;

    public Room(int roomNumber, String type, double pricePerNight, boolean isAvailable) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = isAvailable;
    }

    public int getRoomNumber() { return roomNumber; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }

    public String getType() {
        return type == null ? "Невідомий тип" : type;
    }
    public void setType(String type) { this.type = type; }

    public double getPricePerNight() { return pricePerNight; }
    public void setPricePerNight(double pricePerNight) {
        if (pricePerNight <= 0) {
            throw new IllegalArgumentException("Ціна має бути більшою за 0");
        }
        this.pricePerNight = pricePerNight;
    }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    // Бізнес логіка: змінити доступність
    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return roomNumber == room.roomNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNumber);
    }
}
