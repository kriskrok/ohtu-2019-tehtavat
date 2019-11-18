package ohtu;

public class Player implements Comparable<Player> {
    private String name;
    private String team;
    private String nationality;
    private int goals;
    private int assists;

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() { return assists+goals; }

    @Override
    public String toString() {
        return String.format("%-25s %-4s %d %s %-2d %s %s",
                name, team, goals, "+", assists, "=", getScore());
    }

    @Override
    public int compareTo(Player player) {
        if (this.getScore() == player.getScore()) {
            return this.goals > player.goals ? 1 : -1;
        }

        return this.getScore() > player.getScore() ? 1 : -1;
    }
      
}
