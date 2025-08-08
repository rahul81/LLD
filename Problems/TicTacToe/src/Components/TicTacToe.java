package Components;

import interfaces.PlayerStratergy;

public class TicTacToe {

    private Board board;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;
    private GameStateHandler gameStateHandler;

    public TicTacToe(PlayerStratergy xStratergy, PlayerStratergy oStratergy, int rows, int columns) {

        board = new Board(rows, columns);
        playerX = new Player(Symbol.X, xStratergy);
        playerO = new Player(Symbol.O, oStratergy);
        currentPlayer = playerX;
        gameStateHandler = new GameStateHandler();

    }

    public void play() {

        do {
            board.printBoard();

            Position pos = currentPlayer.getPlayerStratergy().makeMove(board, currentPlayer.getSymbol());
            board.makeMove(pos, currentPlayer.getSymbol());

            board.checkGameState(gameStateHandler, currentPlayer);

            switchPlayer();

        } while (!gameStateHandler.isGameOver());

        board.printBoard();

        annouceResult();

    }

    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
    }

    private void annouceResult() {
        GameState state = gameStateHandler.getState();
        if (state == GameState.X) {
            System.out.println("Player X wins !");
        } else if (state == GameState.O) {
            System.out.println("Player O wins !");
        } else {
            System.out.println("Its a Draw !");
        }
    }

}
