package com.Lab1;

import java.io.*;
import java.util.*;

public class RoomFileIOImpl implements RoomFileIO {

    @Override
    public List<Room> readRooms(String filename) throws IOException {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 4) continue;
                int roomNumber = Integer.parseInt(parts[0]);
                String type = parts[1];
                double price = Double.parseDouble(parts[2]);
                boolean isAvailable = Boolean.parseBoolean(parts[3]);
                rooms.add(new Room(roomNumber, type, price, isAvailable));
            }
        }
        return rooms;
    }

    @Override
    public void writeRooms(List<Room> rooms, String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Room room : rooms) {
                writer.write(room.getRoomNumber() + "," +
                        room.getType() + "," +
                        room.getPricePerNight() + "," +
                        room.isAvailable());
                writer.newLine();
            }
        }
    }
}