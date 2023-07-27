// Import statements - these import necessary classes from different packages.
package Client;

import Server.Team; // Importing the Team class from the Server package.
import java.io.IOException; // Importing IOException class from java.io package.
import java.io.ObjectInputStream; // Importing ObjectInputStream class from java.io package.
import java.io.ObjectOutputStream; // Importing ObjectOutputStream class from java.io package.
import java.net.Socket; // Importing Socket class from java.net package.
import java.util.Scanner; // Importing Scanner class from java.util package.
import java.util.List; // Importing List interface from java.util package.

// Definition of the ClientMain class
public class ClientMain
{
    // The main method of the program
    public static void main(String[] args)
    {
        // Using try-with-resources to automatically close the socket at the end
        try (Socket socket = new Socket("localhost", 12345))
        {
            // Printing a message indicating that the client is connected to the server.
            System.out.println("Connected to the server.");

            // Creating an instance of MatchOrganizerClient and passing the socket to it.
            MatchOrganizerClient matchOrganizerClient = new MatchOrganizerClient(socket);

            // Creating a Scanner object to read user input.
            Scanner scanner = new Scanner(System.in);

            // Starting an infinite loop for the main menu.
            while (true)
            {
                // Displaying the main menu options to the user.
                System.out.println("\n------ Main Menu ------");
                System.out.println("1. Organize a match");
                System.out.println("2. See points table");
                System.out.println("3. Exit");
                System.out.print("\n\nEnter your choice (1/2/3): ");

                // Reading the user's choice from the console using the ConsoleHelper class.
                int choice = ConsoleHelper.readInt("");

                // Switch statement to perform different actions based on the user's choice.
                switch (choice)
                {
                    case 1:
                        // If the user chose option 1, call the organizeMatch method of matchOrganizerClient.
                        matchOrganizerClient.organizeMatch(scanner);
                        break;
                    case 2:
                        // If the user chose option 2, call the getPointsTable method of matchOrganizerClient.
                        matchOrganizerClient.getPointsTable();
                        break;
                    case 3:
                        // If the user chose option 3, exit the program with System.exit(0).
                        System.exit(0);
                        break;
                    default:
                        // If the user chose an invalid option, inform the user and continue the loop.
                        System.out.println("Invalid choice. Please select a valid option.");
                        break;
                }
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            // Exception handling in case there is an issue with connecting to the server or receiving data.
            System.out.println("Client: Can't connect to the server.");
            System.out.println("Client: Make sure the server is live.");
        }
    }
}
