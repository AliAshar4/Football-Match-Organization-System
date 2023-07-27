// Import necessary classes from the java.io package for handling input/output operations and the java.net package for networking.
package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Define the main class for the server program.
public class ServerMain {
    // The entry point of the program.
    public static void main(String[] args) {
        // Try-with-resources: Create a ServerSocket and automatically close it after the block of code ends.
        // The ServerSocket is bound to port 12345, and will listen for incoming client connections.
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            // Display a message indicating that the server is waiting for connections.
            System.out.println("Server: Waiting for connections...");

            // Create a thread pool of ExecutorService to manage client threads.
            // The newCachedThreadPool() creates a pool that automatically adjusts the number of threads based on demand.
            ExecutorService executor = Executors.newCachedThreadPool();

            // Infinite loop to accept and handle incoming client connections.
            while (true) {
                // Accept a client connection and block until a client connects.
                Socket clientSocket = serverSocket.accept();
                // Display a message indicating that a client has connected.
                System.out.println("Server: Client connected.");

                // Create a new instance of the ClientHandler class, passing the clientSocket to handle the client's requests.
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                // Execute the clientHandler in a separate thread from the thread pool.
                executor.execute(clientHandler);
            }
        } catch (IOException e) {
            // If an exception occurs during the execution of the server, print the stack trace.
            e.printStackTrace();
        }
    }
}
