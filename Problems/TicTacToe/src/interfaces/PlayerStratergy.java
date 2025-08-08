package interfaces;

import Components.Board;
import Components.Position;
import Components.Symbol;

public interface PlayerStratergy {

    Position makeMove(Board board, Symbol symbol);
}
