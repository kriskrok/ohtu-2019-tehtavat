package ohtu;

public class TennisGame {

    private int scorePlayer1 = 0;
    private int scorePlayer2 = 0;
    private String player1;
    private String player2;
    String[] scoreOptions = {"Love", "Fifteen", "Thirty", "Forty"};

    public TennisGame(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void wonPoint(String player) {
        if (player == "player1")
            scorePlayer1 += 1;
        else
            scorePlayer2 += 1;
    }

    public String getScore() {
        if (isEven()) {
            return evenscore();
        }
        if (scorePlayer1 >= 4 || scorePlayer2 >= 4) {
            return deuce();
        }
        return unEvenScore();
    }

    private String evenscore() {
        if (scorePlayer1 > 3) {
            return "Deuce";
        }
        return scoreOptions[scorePlayer1] + "-All";
    }

    private String deuce() {
        if (scorePlayer1 > scorePlayer2) {
            return scorePlayer1 - scorePlayer2 > 1 ? "Win for player1" : "Advantage player1";
        }
        return scorePlayer2 - scorePlayer1 > 1 ? "Win for player2" : "Advantage player2";
    }

    private String unEvenScore() {
        return scoreOptions[scorePlayer1] + "-" + scoreOptions[scorePlayer2];
    }

    private boolean isEven() {
        return scorePlayer1 == scorePlayer2;
    }
}