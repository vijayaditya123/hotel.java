import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Room {
    private int roomNumber;
    private boolean isAvailable;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isAvailable = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}

class Guest {
    private String name;
    private String contactNumber;

    public Guest(String name, String contactNumber) {
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }
}

class Reservation {
    private Guest guest;
    private Room room;

    public Reservation(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }
}

class HotelManagementSystem {
    private List<Room> rooms;
    private List<Reservation> reservations;

    public HotelManagementSystem(int numberOfRooms) {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        for (int i = 1; i <= numberOfRooms; i++) {
            rooms.add(new Room(i));
        }
    }

    public void bookRoom(String guestName, String contactNumber) {
        Room availableRoom = null;
        for (Room room : rooms) {
            if (room.isAvailable()) {
                availableRoom = room;
                break;
            }
        }

        if (availableRoom != null) {
            Guest guest = new Guest(guestName, contactNumber);
            Reservation reservation = new Reservation(guest, availableRoom);
            reservations.add(reservation);
            availableRoom.setAvailable(false);
            System.out.println("Room " + availableRoom.getRoomNumber() + " booked successfully for " + guestName);
        } else {
            System.out.println("No rooms available.");
        }
    }

    public void checkoutRoom(int roomNumber) {
        for (Reservation reservation : reservations) {
            if (reservation.getRoom().getRoomNumber() == roomNumber) {
                reservation.getRoom().setAvailable(true);
                reservations.remove(reservation);
                System.out.println("Room " + roomNumber + " checked out successfully.");
                return;
            }
        }
        System.out.println("Room " + roomNumber + " not found or not currently booked.");
    }

    public void listAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println("Room " + room.getRoomNumber());
            }
        }
    }

    public void listReservations() {
        System.out.println("Current Reservations:");
        for (Reservation reservation : reservations) {
            System.out.println("Room " + reservation.getRoom().getRoomNumber() + " booked by " + reservation.getGuest().getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        HotelManagementSystem hotel = new HotelManagementSystem(10);
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nHotel Management System:");
            System.out.println("1. Book Room");
            System.out.println("2. Checkout Room");
            System.out.println("3. List Available Rooms");
            System.out.println("4. List Reservations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter guest name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter contact number: ");
                    String contactNumber = scanner.nextLine();
                    hotel.bookRoom(name, contactNumber);
                    break;
                case 2:
                    System.out.print("Enter room number to checkout: ");
                    int roomNumber = scanner.nextInt();
                    hotel.checkoutRoom(roomNumber);
                    break;
                case 3:
                    hotel.listAvailableRooms();
                    break;
                case 4:
                    hotel.listReservations();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
