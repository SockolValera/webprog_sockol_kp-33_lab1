package com.Lab1;

import java.io.IOException;
import java.util.List;

public interface RoomFileIO {
    List<Room> readRooms(String filename) throws IOException;
    void writeRooms(List<Room> rooms, String filename) throws IOException;
}

