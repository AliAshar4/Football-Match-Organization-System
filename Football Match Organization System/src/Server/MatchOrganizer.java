package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Importing necessary classes from the java.util package.
// These classes are used to work with collections (e.g., List) and perform sorting operations.

public class MatchOrganizer
{
    // Declaration of a private List of Team objects named "teams".
    // This list will store information about different football teams.

    private List<Team> teams;

    // Constructor of the MatchOrganizer class.
    // Initializes the "teams" list with some initial team data.

    public MatchOrganizer()
    {
        // Creating a new ArrayList to store the team objects.
        // This ArrayList is being used to initialize the "teams" list.

        teams = new ArrayList<>();

        // Adding new Team objects to the "teams" list with their respective data.
        // Each Team object represents a football team with attributes like name, team number, etc.

        teams.add(new Team("Manchester",   1, 0, 0, 0, 0, 0));
        teams.add(new Team("Barcelona",   2, 0, 0, 0, 0, 0));
        teams.add(new Team("RealMadrid",   3, 0, 0, 0, 0, 0));
        teams.add(new Team("AC Milan",   4, 0, 0, 0, 0, 0));
        teams.add(new Team("Juventus",   5, 0, 0, 0, 0, 0));
    }

    // Method to organize a match between two teams and update their stats.
    // It takes team numbers and goals scored by each team as inputs and returns the match result.

    public Match organizeMatch(int teamANumber, int teamBNumber, int goalsTeamA, int goalsTeamB)
    {
        // Finding the Team objects corresponding to the given team numbers.

        Team teamA = findTeamByNumber(teamANumber);
        Team teamB = findTeamByNumber(teamBNumber);

        // Extracting the names of the teams from the Team objects.

        String teamAName = teamA.getName();
        String teamBName = teamB.getName();

        // Creating a new Match object with the provided match details.

        Match match = new Match(teamAName, teamBName, goalsTeamA, goalsTeamB, teamANumber, teamBNumber);

        // Updating the stats of both teams based on the match result.

        updateTeamStats(match);

        // Returning the Match object representing the match result.

        return match;
    }

    // Method to update the stats of teams based on the match result.

    private void updateTeamStats(Match match)
    {
        // Finding the Team objects corresponding to the team numbers involved in the match.

        Team teamA = findTeamByNumber(match.getTeamANumber());
        Team teamB = findTeamByNumber(match.getTeamBNumber());

        // Updating the stats of teams based on the match result (e.g., wins, losses, draws).

        if (match.isGameDraw())
        {
            teamA.setDraw(teamA.getDraw() + 1);
            teamB.setDraw(teamB.getDraw() + 1);
        }
        else if (match.getWinner().equals(match.getTeamA()))
        {
            teamA.setWins(teamA.getWins() + 1);
            teamB.setLost(teamB.getLost() + 1);
        }
        else if (match.getWinner().equals(match.getTeamB()))
        {
            teamB.setWins(teamB.getWins() + 1);
            teamA.setLost(teamA.getLost() + 1);
        }

        // Updating the number of matches played by both teams.

        teamA.setNumOfPlayedMatches(teamA.getNumOfPlayedMatches() + 1);
        teamB.setNumOfPlayedMatches(teamB.getNumOfPlayedMatches() + 1);

        // Calculating and updating the points of both teams.

        teamA.calculatePoints();
        teamB.calculatePoints();

        // Sorting the "teams" list based on points in descending order.

        Collections.sort(teams, (t1, t2) -> t2.getPoints() - t1.getPoints());
    }

    // Method to get the points table (list of teams with their points) in the current order.

    public List<Team> getPointsTable()
    {
        return teams;
    }

    // Method to find and return the Team object based on the given team number.

    private Team findTeamByNumber(int teamNumber)
    {
        // Using stream and filter to find the Team object with a specific team number.
        // If the team with the specified number is found, it is returned; otherwise, null is returned.

        return teams.stream().filter(team -> team.getTeamNumber() == teamNumber).findFirst().orElse(null);
    }
}
