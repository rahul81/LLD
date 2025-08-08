package Class_Relationships.Aggregation.src;

import java.util.List;
import java.util.ArrayList;

public class Team {
    String TeamName;

    List<Player> players = new ArrayList<>();

    Team(String TeamName) {
        this.TeamName = TeamName;
    }

    void addPlayer(Player player) {
        players.add(player);
    }

    void display(Player player){
        System.out.println(player.name);
    }

    void showTeam() {
        System.out.println("Team " + TeamName + " has players:");
        
        for (Player p : players){
            display(p);
        }
    }

}
