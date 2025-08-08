package Class_Relationships.Aggregation.src;

/* Aggregation: Grouping with a Twist
Aggregation is a specialized form of association that represents a "has-a" relationship where the parts can exist independently of the whole—but they are grouped together by a container.
Think of a Team and its Players: a team has players, yet the players can exist even if the team is disbanded.
 */

public class Main {
    public static void main(String[] args) {
        Team team = new Team("Summit Ninjas");
        team.addPlayer(new Player("Rahul"));
        team.addPlayer(new Player("Rohit"));

        team.showTeam();
    }
}
