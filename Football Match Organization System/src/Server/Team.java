package Server;

import java.io.Serializable;

public class Team implements Serializable {
    private String name;
    private int teamNumber;
    private int numOfPlayedMatches;
    private int wins;
    private int lost;
    private int draw;
    private int points;

    public Team(String name, int teamNumber, int numOfPlayedMatches, int wins, int lost, int draw, int points) {
        this.name = name;
        this.teamNumber = teamNumber;
        this.numOfPlayedMatches = numOfPlayedMatches;
        this.wins = wins;
        this.lost = lost;
        this.draw = draw;
        this.points = points;
    }

    public Team(String name, int teamNumber) {
        this.name = name;
        this.teamNumber = teamNumber;
    }

    // Getters and setters...

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getNumOfPlayedMatches() {
        return numOfPlayedMatches;
    }

    public void setNumOfPlayedMatches(int numOfPlayedMatches) {
        this.numOfPlayedMatches = numOfPlayedMatches;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    // Method to calculate points based on wins and draws
    public void calculatePoints() {
        points = (wins * 10) + (draw * 5);
    }

    // Other methods if needed...


    @Override
    public String toString()
    {
        return name +  "\t" + numOfPlayedMatches + "\t" + wins + "\t" + lost + "\t" + draw + "\t" + points + "\n";
    }
}
