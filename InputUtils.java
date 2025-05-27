import java.util.Scanner;

public class InputUtils {
    private static Scanner scanner = new Scanner(System.in);
    
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            System.out.print(prompt);
            scanner.next(); // Consume invalid input
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return input;
    }
    
    // Method to close the scanner when the program is done
    public static void closeScanner() {
        scanner.close();
    }
}
