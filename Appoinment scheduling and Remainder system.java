
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AppointmentSystem {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Appointment> appointments = new ArrayList<>();
    static int nextId = 1;

    static class Appointment {
        int id;
        String name;
        String phone;
        LocalDate date;
        LocalTime time;

        Appointment(int id, String name, String phone,
                    LocalDate date, LocalTime time) {
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.date = date;
            this.time = time;
        }

        void display() {
            System.out.println("---------------------------");
            System.out.println("Appointment ID: " + id);
            System.out.println("Name: " + name);
            System.out.println("Phone: " + phone);
            System.out.println("Date: " + date);
            System.out.println("Time: " + time);
            System.out.println("---------------------------");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== APPOINTMENT SCHEDULING SYSTEM ===");
            System.out.println("1. Book Appointment");
            System.out.println("2. View Appointments");
            System.out.println("3. Search Appointment");
            System.out.println("4. Cancel Appointment");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    bookAppointment();
                    break;
                case 2:
                    viewAppointments();
                    break;
                case 3:
                    searchAppointment();
                    break;
                case 4:
                    cancelAppointment();
                    break;
                case 5:
                    System.out.println("Thank you for using the system!");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    static void bookAppointment() {
        System.out.println("\n--- Book Appointment ---");

        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        System.out.print("Enter phone number: ");
        String phone = sc.nextLine().trim();

        if (phone.isEmpty()) {
            System.out.println("Phone number cannot be empty.");
            return;
        }

        try {
            System.out.print("Enter date (YYYY-MM-DD): ");
            LocalDate date = LocalDate.parse(sc.nextLine().trim());

            System.out.print("Enter time (HH:MM): ");
            LocalTime time = LocalTime.parse(sc.nextLine().trim());

            if (date.isBefore(LocalDate.now())) {
                System.out.println("Appointment date cannot be in the past.");
                return;
            }

            for (Appointment a : appointments) {
                if (a.date.equals(date) && a.time.equals(time)) {
                    System.out.println("This time slot is already booked.");
                    return;
                }
            }

            Appointment appointment = new Appointment(
                nextId++, name, phone, date, time
            );

            appointments.add(appointment);
            System.out.println("Appointment booked successfully!");
            appointment.display();

        } catch (DateTimeParseException e) {
            System.out.println(
                "Invalid date or time. Use YYYY-MM-DD and HH:MM."
            );
        }
    }

    static void viewAppointments() {
        System.out.println("\n--- All Appointments ---");

        if (appointments.isEmpty()) {
            System.out.println("No appointments available.");
            return;
        }

        for (Appointment a : appointments) {
            a.display();
        }
    }

    static void searchAppointment() {
        System.out.print("Enter appointment ID: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());

            for (Appointment a : appointments) {
                if (a.id == id) {
                    System.out.println("Appointment found:");
                    a.display();
                    return;
                }
            }

            System.out.println("Appointment not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid appointment ID.");
        }
    }

    static void cancelAppointment() {
        System.out.print("Enter appointment ID to cancel: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());

            for (int i = 0; i < appointments.size(); i++) {
                if (appointments.get(i).id == id) {
                    appointments.remove(i);
                    System.out.println(
                        "Appointment cancelled successfully."
                    );
                    return;
                }
            }

            System.out.println("Appointment not found.");

        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid appointment ID.");
        }
    }
}
