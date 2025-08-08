package Components;

public class Board {

    private final int rows;
    private final int columns;
    private Symbol[][] grid;
    private int vaccantCellCount;

    public Board(int rows, int cols) {
        this.rows = rows;
        this.columns = cols;
        this.vaccantCellCount = rows * cols;

        grid = new Symbol[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                grid[i][j] = Symbol.EMPTY;
            }
        }
    }

    public boolean isValidMove(Position pos) {
        return pos.row >= 0 && pos.row <= rows && pos.col >= 0 && pos.col <= columns
                && grid[pos.row][pos.col] == Symbol.EMPTY;
    }

    public void makeMove(Position pos, Symbol symbol) {
        grid[pos.row][pos.col] = symbol;
        this.vaccantCellCount--; // Once a cell is filled with Symbol, reduce the vacant cell count
    }

    public void checkGameState(GameStateHandler gameStateHandler, Player currentPlayer) {

        Symbol currentSymbol = currentPlayer.getSymbol();

        for (int i = 0; i < rows; i++) {
            if (grid[i][0] != Symbol.EMPTY && isWinning(grid[i])) {
                setWinnerState(currentSymbol, gameStateHandler);
                return;
            }
        }

        for (int i = 0; i < rows; i++) {
            Symbol column[] = new Symbol[rows];
            for (int j = 0; j < columns; j++) {
                column[j] = grid[j][i];
            }

            if (column[0] != Symbol.EMPTY && isWinning(column)) {
                setWinnerState(currentSymbol, gameStateHandler);
                return;
            }
        }

        Symbol[] diagnol1 = new Symbol[Math.min(rows, columns)];
        Symbol[] diagnol2 = new Symbol[Math.min(rows, columns)];

        for (int i = 0; i < Math.min(rows, columns); i++) {
            diagnol1[i] = grid[i][i];
            diagnol2[i] = grid[i][columns - i - 1];
        }

        if (diagnol1[0] != Symbol.EMPTY && isWinning(diagnol1)) {
            setWinnerState(currentSymbol, gameStateHandler);
            return;
        }

        if (diagnol2[0] != Symbol.EMPTY && isWinning(diagnol2)) {
            setWinnerState(currentSymbol, gameStateHandler);
            return;
        }

        // Add logic to handle draw

        if (vaccantCellCount < 1){
            gameStateHandler.setState(GameState.DRAW);
            return;
        }

    }

    public void setWinnerState(Symbol sym, GameStateHandler gameStateHandler) {

        if (sym == Symbol.X) {
            gameStateHandler.setState(GameState.X);
            return;
        }

        gameStateHandler.setState(GameState.O);

    }

    public boolean isWinning(Symbol line[]) {
        Symbol first = line[0]; // take the first element in line

        // if next 2 are same then its a win

        for (Symbol s : line) {
            if (s != first) {
                return false;
            }
        }

        return true;
    }

    public void printBoard() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Symbol symbol = grid[i][j];

                switch (symbol) {
                    case X:
                        System.out.print(" X ");
                        break;
                    case O:
                        System.out.print(" O ");
                        break;
                    case EMPTY:
                        System.out.print(" . ");

                    default:
                        break;
                }

                if (j < columns - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < rows - 1) {
                System.out.println("---+---+---");
            }
        }
        System.out.println();
    }

}
