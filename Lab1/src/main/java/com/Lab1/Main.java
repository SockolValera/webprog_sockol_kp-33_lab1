package com.Lab1;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Hotel hotel = new Hotel();
    private static final RoomFileIO fileIO = new RoomFileIOImpl();
    private static final RoomService roomService = new RoomService(fileIO);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Виберіть пункт меню: ");

            switch (choice) {
                case 1 -> showRooms();
                case 2 -> addRoom();
                case 3 -> deleteRoom();
                case 4 -> bookRoom();
                case 5 -> showBookingStats();
                case 6 -> exportRooms();
                case 7 -> importRooms();
                case 0 -> {
                    System.out.println("Завершення роботи...");
                    return;
                }
                default -> System.out.println("Невірний вибір. Спробуйте знову.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                \n--- ГОЛОВНЕ МЕНЮ ---
                1. Переглянути всі номери
                2. Додати новий номер
                3. Видалити номер
                4. Забронювати номер
                5. Показати статистику бронювань
                6. Експортувати номери у файл
                7. Імпортувати номери з файлу
                0. Вийти
                """);
    }

    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Введіть число: ");
        }
        return scanner.nextInt();
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Введіть число: ");
        }
        return scanner.nextDouble();
    }

    private static void showRooms() {
        List<Room> rooms = hotel.getRooms();
        if (rooms.isEmpty()) {
            System.out.println("Список номерів порожній.");
            return;
        }
        for (Room room : rooms) {
            System.out.printf("Номер %d | Тип: %s | Ціна: %.2f | Доступний: %b%n",
                    room.getRoomNumber(), room.getType(), room.getPricePerNight(), room.isAvailable());
        }
    }

    private static void addRoom() {
        int number = readInt("Введіть номер кімнати: ");
        System.out.print("Тип кімнати: ");
        String type = scanner.next();
        double price = readDouble("Ціна за ніч: ");
        hotel.addRoom(new Room(number, type, price, true));
        System.out.println("Кімната додана.");
    }

    private static void deleteRoom() {
        int number = readInt("Введіть номер кімнати для видалення: ");
        hotel.removeRoomByNumber(number);
        System.out.println("Кімната видалена, якщо була знайдена.");
    }

    private static void bookRoom() {
        int roomNumber = readInt("Введіть номер кімнати для бронювання: ");
        Room room = hotel.findAvailableRoomByNumber(roomNumber);
        if (room == null) {
            System.out.println("Кімната не знайдена або недоступна.");
            return;
        }

        System.out.print("Ім'я відвідувача: ");
        String name = scanner.next();
        System.out.print("Телефон: ");
        String phone = scanner.next();
        int id = hotel.getVisitors().size() + 1;
        Visitor visitor = new Visitor(id, name, phone);
        hotel.addVisitor(visitor);

        System.out.print("Дата початку (yyyy-mm-dd): ");
        String startStr = scanner.next();
        System.out.print("Дата завершення (yyyy-mm-dd): ");
        String endStr = scanner.next();

        try {
            Booking booking = new Booking(visitor, room,
                    LocalDate.parse(startStr),
                    LocalDate.parse(endStr));
            hotel.addBooking(booking);
            room.setAvailable(false);
            System.out.println("Бронювання додано. Загальна сума: " + booking.calculateTotalPrice());
        } catch (Exception e) {
            System.out.println("Помилка в датах: " + e.getMessage());
        }
    }

    private static void showBookingStats() {
        List<Booking> bookings = hotel.getBookings();
        if (bookings.isEmpty()) {
            System.out.println("Немає жодної броні.");
            return;
        }

        System.out.println("Загальна кількість бронювань: " + bookings.size());

        long uniqueRoomCount = bookings.stream()
                .map(Booking::getRoom)
                .distinct()
                .count();
        System.out.println("Кількість заброньованих кімнат: " + uniqueRoomCount);

        System.out.println("\n--- Деталі бронювань ---");
        for (Booking booking : bookings) {
            Room room = booking.getRoom();
            Visitor visitor = booking.getVisitor();
            long nights = ChronoUnit.DAYS.between(booking.getStartDate(), booking.getEndDate());

            System.out.printf("""
                    Номер кімнати: %d | Тип: %s | Ціна за ніч: %.2f
                    Відвідувач: %s | Телефон: %s
                    Кількість ночей: %d
                    -----------------------------
                    """,
                    room.getRoomNumber(), room.getType(), room.getPricePerNight(),
                    visitor.getName(), visitor.getPhone(), nights);
        }
    }

    private static void exportRooms() {
        System.out.print("Введіть ім'я файлу для експорту: ");
        String filename = scanner.next();
        try {
            roomService.exportSortedByPrice(filename, hotel.getRooms());
            System.out.println("Експорт виконано.");
        } catch (IOException e) {
            System.out.println("Помилка експорту: " + e.getMessage());
        }
    }

    private static void importRooms() {
        System.out.print("Введіть ім'я файлу для імпорту: ");
        String filename = scanner.next();
        try {
            List<Room> imported = roomService.importRooms(filename);
            hotel.getRooms().clear();
            hotel.getRooms().addAll(imported);
            System.out.println("Імпорт виконано. Кількість кімнат: " + hotel.getRooms().size());
        } catch (IOException e) {
            System.out.println("Помилка імпорту: " + e.getMessage());
        }
    }
}
