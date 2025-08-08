package interfaces;

import Components.GameState;

public interface StateHandler {
    
    boolean isGameOver();
    void setState(GameState state);
    GameState getState();
}
