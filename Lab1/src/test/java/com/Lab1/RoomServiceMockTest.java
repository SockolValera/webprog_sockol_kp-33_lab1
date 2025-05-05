package com.Lab1;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class RoomServiceMockTest {

    @Test
    void testExportSortedByPriceUsesSortedRooms() throws IOException {
        // Arrange
        RoomFileIO mockFileIO = mock(RoomFileIO.class);
        RoomService service = new RoomService(mockFileIO);

        List<Room> unsortedRooms = List.of(
                new Room(1, "Double", 250.0, true),
                new Room(2, "Single", 150.0, false),
                new Room(3, "Suite", 400.0, true)
        );

        // Act
        service.exportSortedByPrice("rooms.txt", unsortedRooms);

        // Assert
        ArgumentCaptor<List<Room>> captor = ArgumentCaptor.forClass(List.class);
        verify(mockFileIO).writeRooms(captor.capture(), eq("rooms.txt"));

        List<Room> sortedRooms = captor.getValue();
        assertEquals(2, sortedRooms.get(0).getRoomNumber()); // найменша ціна
        assertEquals(1, sortedRooms.get(1).getRoomNumber());
        assertEquals(3, sortedRooms.get(2).getRoomNumber());
    }

    @Test
    void testImportRoomsReturnsWhatFileIOProvides() throws IOException {
        // Arrange
        RoomFileIO mockFileIO = mock(RoomFileIO.class);
        RoomService service = new RoomService(mockFileIO);

        List<Room> fakeData = List.of(
                new Room(5, "Test", 123.0, true)
        );

        when(mockFileIO.readRooms("fake.txt")).thenReturn(fakeData);

        // Act
        List<Room> result = service.importRooms("fake.txt");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Test", result.get(0).getType());
        verify(mockFileIO).readRooms("fake.txt");
    }
}

