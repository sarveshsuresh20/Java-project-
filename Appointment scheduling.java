
import java.util.*;

public class AppointmentSystem {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Appointment Scheduling System");

        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter appointment date: ");
        String date = sc.nextLine();

        System.out.print("Enter time: ");
        String time = sc.nextLine();

        System.out.println("Appointment booked successfully!");
        System.out.println("Name: " + name);
        System.out.println("Reminder will be sent before " + date + " at " + time);

        sc.close();
    }
}
