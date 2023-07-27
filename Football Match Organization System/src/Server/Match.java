package Server;

import java.io.Serializable;

public class Match implements Serializable
{
    private String teamA;
    private String teamB;
    private int goalsTeamA;
    private int goalsTeamB;
    private String winner;
    private boolean isGameDraw;
    private int teamANumber; // Team A number
    private int teamBNumber; // Team B number

    public Match(String teamA, String teamB, int goalsTeamA, int goalsTeamB, int teamANumber, int teamBNumber)
    {
        this.teamA = teamA;
        this.teamB = teamB;
        this.goalsTeamA = goalsTeamA;
        this.goalsTeamB = goalsTeamB;
        this.teamANumber = teamANumber;
        this.teamBNumber = teamBNumber;
        decideWinner();
    }

    public Match(String teamAName, String teamBName, int goalsTeamA, int goalsTeamB)
    {
        this.teamA = teamAName;
        this.teamB = teamAName;
        this.goalsTeamA = goalsTeamA;
        this.goalsTeamB = goalsTeamB;
    }

    // Getters and setters for the Match class

    public String getTeamA() {
        return teamA;
    }

    public void setTeamA(String teamA) {
        this.teamA = teamA;
    }

    public String getTeamB() {
        return teamB;
    }

    public void setTeamB(String teamB) {
        this.teamB = teamB;
    }

    public int getGoalsTeamA() {
        return goalsTeamA;
    }

    public void setGoalsTeamA(int goalsTeamA) {
        this.goalsTeamA = goalsTeamA;
    }

    public int getGoalsTeamB() {
        return goalsTeamB;
    }

    public void setGoalsTeamB(int goalsTeamB) {
        this.goalsTeamB = goalsTeamB;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public boolean isGameDraw() {
        return isGameDraw;
    }

    public void setGameDraw(boolean gameDraw) {
        isGameDraw = gameDraw;
    }

    public int getTeamANumber() {
        return teamANumber;
    }

    public int getTeamBNumber() {
        return teamBNumber;
    }

    // Method to decide the winner and set the winner and isGameDraw fields
    public void decideWinner() {
        if (goalsTeamA > goalsTeamB) {
            winner = teamA;
            isGameDraw = false;
        } else if (goalsTeamA < goalsTeamB) {
            winner = teamB;
            isGameDraw = false;
        } else {
            winner = null;
            isGameDraw = true;
        }
    }

}
