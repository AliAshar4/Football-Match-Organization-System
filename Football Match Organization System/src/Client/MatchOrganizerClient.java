// Import necessary classes from the Client and Server packages
package Client;

// Import the Match and Team classes from the Server package
import Server.Match;
import Server.Team;

// Import necessary IO and networking classes
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

// Create a public class MatchOrganizerClient in the Client package
public class MatchOrganizerClient
{
    // Private class variables to store the socket connection and IO streams
    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    // Constructor to initialize the MatchOrganizerClient with a socket
    public MatchOrganizerClient(Socket socket)
    {
        this.socket = socket;
        try
        {
            // Initialize the output and input streams with the socket's streams
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException e)
        {
            // Print the stack trace if an IOException occurs during stream initialization
            e.printStackTrace();
        }
    }

    // Method to organize a match
    public void organizeMatch(Scanner scanner)
    {
        try
        {
            // Request team data from the server
            outputStream.writeObject("OrganizeMatch");
            List<Team> teams = (List<Team>) inputStream.readObject();

            // Display the team names with numbers
            System.out.println("\n\n------ Organizing Match ------\n");
            for (int i = 0; i < teams.size(); i++)
            {
                System.out.println((i + 1) + ". " + teams.get(i).getName());
            }

            // Prompt user to enter team numbers for teamA and teamB
            int teamANumber;
            int teamBNumber;
            do
            {
                // Get the team numbers from the user and validate them
                teamANumber = ConsoleHelper.readInt("\nEnter team number for teamA: ");
                teamBNumber = ConsoleHelper.readInt("Enter team number for teamB: ");
            }
            while (teamANumber <= 0 || teamANumber > teams.size() || teamBNumber <= 0 || teamBNumber > teams.size() || teamANumber == teamBNumber);

            // Get the number of goals scored by each team
            int goalsTeamA = ConsoleHelper.readInt("Enter goals for teamA: ");
            int goalsTeamB = ConsoleHelper.readInt("Enter goals for teamB: ");

            // Create an array to hold the match details
            Object[] matchDetails = { teamANumber, teamBNumber, goalsTeamA, goalsTeamB };

            // Send the match details to the server
            outputStream.writeObject(matchDetails);

            // Receive and display the match result from the server
            String resultHeader = (String) inputStream.readObject();
            System.out.println(resultHeader);
            Match matchResult = (Match) inputStream.readObject();
            System.out.println("\tTeam A: " + matchResult.getTeamA() + " with " + matchResult.getGoalsTeamA() + " goals.");
            System.out.println("\tTeam B: " + matchResult.getTeamB() + " with " + matchResult.getGoalsTeamB() + " goals.");
            if (matchResult.isGameDraw())
            {
                System.out.println("\t\t\tMatch Result: Draw");
            } else
            {
                System.out.println("\t\t\tWinner: " + matchResult.getWinner());
            }
            System.out.println("\tMatch is successfully organized.");
            System.out.println("--------------------------------------");
        }
        catch (IOException | ClassNotFoundException e)
        {
            // Print the stack trace if an IOException or ClassNotFoundException occurs
            e.printStackTrace();
        }
    }

    // Method to close the socket and IO streams
    public void close()
    {
        try
        {
            // Send "Exit" to the server, then close the streams and socket
            outputStream.writeObject("Exit");
            outputStream.close();
            inputStream.close();
            socket.close();
        }
        catch (IOException e)
        {
            // Print the stack trace if an IOException occurs during closing
            e.printStackTrace();
        }
    }

    // Method to get the points table from the server
    public void getPointsTable() throws IOException, ClassNotFoundException
    {
        // Request points table data from the server
        outputStream.writeObject("GetPointsTable");

        // Receive the points table data as a string from the server
        String input = inputStream.readObject().toString();

        // Process the received string to create a 2D string array for the points table
        String[] teamData = input.substring(1, input.length() - 1).split(", ");
        String[][] pointsTable = new String[teamData.length + 1][];
        pointsTable[0] = new String[]{"Team Names ", "M", "W", "L", "D", "P"};
        for (int i = 0; i < teamData.length; i++)
        {
            String[] teamStats = teamData[i].split("\\s+");
            pointsTable[i + 1] = teamStats;
        }

        // Display the points table
        System.out.println("\n***********************************");
        for (String[] row : pointsTable)
        {
            for (String value : row)
            {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println("***********************************");
    }
}
