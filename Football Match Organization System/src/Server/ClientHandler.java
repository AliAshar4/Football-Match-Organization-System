package Server;

// Import statements for necessary classes
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;

// ClientHandler class implementing the Runnable interface
public class ClientHandler implements Runnable
{
    // Instance variables
    private Socket clientSocket; // Socket representing the client connection
    private MatchOrganizer matchOrganizer; // Instance of the MatchOrganizer class

    // Constructor to initialize the ClientHandler object with the client socket
    public ClientHandler(Socket clientSocket)
    {
        this.clientSocket = clientSocket;
        matchOrganizer = new MatchOrganizer(); // Create a new MatchOrganizer object
    }

    // Overriding the run() method from the Runnable interface
    @Override
    public void run()
    {
        // Using try-with-resources to automatically close the input and output streams
        try
                (
                        ObjectInputStream inputStream = new ObjectInputStream(clientSocket.getInputStream());
                        ObjectOutputStream outputStream = new ObjectOutputStream(clientSocket.getOutputStream())
                )
        {
            // Flag to keep the server running
            boolean running = true;

            // Main loop to handle client requests
            while (running)
            {
                // Read the operation type sent by the client
                String operationType = (String) inputStream.readObject();

                // If the client wants to organize a match
                if (operationType.equals("OrganizeMatch"))
                {
                    System.out.println("Client request: OrganizeMatch");
                    // Get the points table of teams from the MatchOrganizer
                    List<Team> teams = matchOrganizer.getPointsTable();
                    // Send the list of teams to the client
                    outputStream.writeObject(teams);

                    // Receive the match details from the client
                    Object[] matchDetails = (Object[]) inputStream.readObject();
                    int teamANumber = (int) matchDetails[0];
                    int teamBNumber = (int) matchDetails[1];
                    int goalsTeamA = (int) matchDetails[2];
                    int goalsTeamB = (int) matchDetails[3];

                    // Organize the match and get the match result
                    Match match = matchOrganizer.organizeMatch(teamANumber, teamBNumber, goalsTeamA, goalsTeamB);

                    // Send the match result to the client
                    outputStream.writeObject("\n\n------------ Match result ------------");
                    outputStream.writeObject(match);
                }
                // If the client wants to get the points table
                else if (operationType.equals("GetPointsTable"))
                {
                    System.out.println("Client request: GetPointsTable");
                    // Get the points table as a String representation
                    String teams = matchOrganizer.getPointsTable().toString();
                    // Send the points table to the client
                    outputStream.writeObject(teams);
                }
                // If the client wants to exit
                else if (operationType.equals("Exit"))
                {
                    // Client wants to exit, set the running flag to false
                    running = false;
                    System.out.println("Server: Client disconnected.");
                }
                // If an unknown operation is received
                else
                {
                    System.out.println("Server: Unknown operation received.");
                }
            }
        }
        // Catching specific exceptions related to client disconnection or data handling
        catch (EOFException | SocketException e)
        {
            // Client closed the socket, no need to print the stack trace
            System.out.println("Server: Client disconnected unexpectedly.");
        }
        // Catching IOException (input/output errors) and ClassNotFoundException (invalid class during deserialization)
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace(); // Print the stack trace in case of an exception
        }
        // Finally block to clean up resources and close the client socket
        finally
        {
            try
            {
                clientSocket.close(); // Close the client socket
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
