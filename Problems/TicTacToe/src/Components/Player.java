package Components;

import interfaces.PlayerStratergy;

public class Player {

    Symbol symbol;
    PlayerStratergy playerStratergy;

    public Player(Symbol symbol, PlayerStratergy playerStratergy) {
        this.symbol = symbol;
        this.playerStratergy = playerStratergy;
    }
    

    public Symbol getSymbol() {
        return symbol;
    }

    public PlayerStratergy getPlayerStratergy() {
        return playerStratergy;
    }

}
