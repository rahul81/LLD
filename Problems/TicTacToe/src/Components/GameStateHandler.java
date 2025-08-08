package Components;

import interfaces.StateHandler;

public class GameStateHandler implements StateHandler {

    private GameState gameState;
    private boolean gameOver;

    // Intialise game state with playing 
    public GameStateHandler() {
        gameState = GameState.PLAYING;
    }

    @Override
    public void setState(GameState state) {
        gameState = state;
        gameOver = true;
    }

    @Override
    public GameState getState() {
        return gameState;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

}
