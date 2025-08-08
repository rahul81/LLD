package Components;

import java.util.Scanner;

import interfaces.PlayerStratergy;

public class HumanPlayerStratergy implements PlayerStratergy {

    private Scanner scanner;
    private String playerName;

    public HumanPlayerStratergy(String name) {
        playerName = name;
        scanner = new Scanner(System.in);
    }

    @Override
    public Position makeMove(Board board, Symbol symbol) {

        while (true) {

            try {

                System.out.printf("%s, of Symbol %s, Enter your move row [0-2] and column [0-2] : ", playerName, symbol );
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                Position move = new Position(row, col);

                System.out.println("is valid move : " + board.isValidMove(move));

                if (board.isValidMove(move)) {
                    return move;
                }

                System.out.println("Invalid move. Try Again.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter row and columns in numbers");
                scanner.nextLine(); // clear buffer
            }

        }
    }
}
