import Components.HumanPlayerStratergy;
import Components.TicTacToe;
import interfaces.PlayerStratergy;

public class Main {

    public static void main(String[] args) {
        PlayerStratergy playerX = new HumanPlayerStratergy("Phil");
        PlayerStratergy playerO = new HumanPlayerStratergy("Mike");
        TicTacToe game = new TicTacToe(playerX, playerO, 3, 3);
        game.play();
    }

}
