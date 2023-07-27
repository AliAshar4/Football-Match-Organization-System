// Declare the package for the class "ConsoleHelper" under the "Client" package.
package Client;

// Import the "Scanner" class from the "java.util" package to handle user input.
import java.util.Scanner;

// Declare the public class "ConsoleHelper".
public class ConsoleHelper {

    // Declare a static "Scanner" object named "scanner" to handle input from the console.
    private static Scanner scanner = new Scanner(System.in);

    // Declare a public static method "readInt" that takes a String message as input and returns an integer.
    public static int readInt(String message) {

        // Print the provided message to the console.
        System.out.print(message);

        // Start a loop that continues until the user enters a valid integer.
        while (!scanner.hasNextInt()) {

            // Print an error message if the user input is not an integer.
            System.out.print("Invalid input. Please enter a valid number: ");

            // Consume the invalid input to avoid an infinite loop.
            scanner.next();
        }

        // If a valid integer is entered, store it in the "input" variable.
        int input = scanner.nextInt();

        // Consume the newline character left in the input buffer after reading the integer.
        scanner.nextLine();

        // Return the valid integer input to the caller.
        return input;
    }
}
