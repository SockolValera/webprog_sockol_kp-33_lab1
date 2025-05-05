package com.Lab1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoomService {
    private final RoomFileIO fileIO;

    public RoomService(RoomFileIO fileIO) {
        this.fileIO = fileIO;
    }

    public void exportSortedByPrice(String filename, List<Room> rooms) throws IOException {
        List<Room> sortedRooms = new ArrayList<>(rooms);
        sortedRooms.sort(Comparator.comparingDouble(Room::getPricePerNight));
        fileIO.writeRooms(sortedRooms, filename);
    }

    public List<Room> importRooms(String filename) throws IOException {
        return fileIO.readRooms(filename);
    }
}
