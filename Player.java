public class Player {
    private String playerId;
    private String name;
    private int score;

public Player(String playerId, String name) {
    this.playerId = playerId;
    this.name = name;
    this.score = 0;
}
public void incrementScore(int points) {
    this.score += points;
    if (this.score < 0) {
        this.score = 0;
    }
}
public String getPlayerId() {
    return playerId;
}
public String getName() {
    return name;
}
public int getScore(){
    return score;
}
public String toString() {
    return "Player ID: " + playerId + ", Name: " + name + ", Score: " + score;
}
}
