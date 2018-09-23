import java.util.Scanner;
import java.util.Date;
 
public class BusReservextendsThread {
 
    private static int[] seats = new int[60]; // 30 window and 30 aisle seats
    private int maxSeatCount; // max canbe 4
 
    private int promptForSeatChoice() {
        System.out.print("Please enter 1 for window, " +
                "2 for aisle, or 0 to exit: ");
        Scanner s = new Scanner(System.in);
        return s.nextInt();
    }
 
    private int determineSeatNumber(int choice) {
        switch (choice) {
            case 1: {
                int seatNumber = bookWindow();
                if (seatNumber == -1) { // no window seat
                    seatNumber = bookAisle();
                    if (seatNumber != -1) { // aisle seat available
                        System.out.println("No window seat but aisle seat.");
                        printBoardingPass(seatNumber);
                    }
                    else {
                        System.out.println("No window or aisle seats.");
                    }
                }
                System.out.println("Window seat booked.");
                return seatNumber;
            }
            case 2: {
                int seatNumber = bookAisle();
                if (seatNumber == -1) { // no aisle seat
                    seatNumber = bookWindow();
                    if (seatNumber != -1) { // window seat available
                        System.out.println("No aisle seat but window seat.");
                        printBoardingPass(seatNumber);
                    }
                    else {
                        System.out.println("No window or aisle seats.");
                    }
                }
                System.out.println("Aisle seat booked.");
                return seatNumber;
            }
            default: return 0;
        }
    }
 
    @Override
    public void run() {
        while (maxSeatCount < 4) { // check max 4
            int choice = promptForSeatChoice();
            if (choice == 0) {
                System.exit(0);
            } else {
                while (choice > 2) { // prompt for input
                    choice = promptForSeatChoice();
                }
                int seatNumber = determineSeatNumber(choice);
                if (seatNumber != -1) {
                    ++maxSeatCount; // increment max count 
                    printBoardingPass(seatNumber);
                }
            }
        }
        System.out.println("Maximum 4 seats");
    }
 
    public static void main(String args[]) {
        // Lets start by setting all seats equal to 0
        for (int i = 0; i < 60; i++) {
            seats[i] = 0;
        }
        // Creating threads for booking tickets
        BusReserv b1 = new BusReserv();
        b1.start(); // only 1 thread is used
    }
     
    // This function checks for window seats and returns
    // seat number or -1 if full.
    private static synchronized int bookWindow() {
        for (int i = 0; i < 30; i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }
     
    // This function checks to see if aisle seats were available, -1 if full.
    private static synchronized int bookAisle() {
        for (int i = 30; i < 60; i++) {
            if (seats[i] == 0) {
                seats[i] = 1;
                return i + 1;
            }
        }
        return -1;
    }
 
    private static void printBoardingPass(int seatNumber) {
        Date timenow = new Date();
        System.out.println();
        System.out.println("Date: " + timenow.toString());
        System.out.println("Boarding pass for seat number: " + seatNumber);
        System.out.println();
    }
 
}
