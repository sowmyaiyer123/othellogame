package model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {

    private Map<TileState, Integer> scoreMap;

    public ScoreBoard() {
        this.scoreMap = new HashMap<>();
        scoreMap.put(TileState.O, 2);
        scoreMap.put(TileState.X, 2);
        scoreMap.put(TileState.Blank, 60);
    }

    public Integer getScore(TileState state) {
        return scoreMap.get(state);
    }

    public void incrementScore(Integer num, TileState state) {
        scoreMap.put(state, getScore(state) + num);
    }

    public void decrementScore(Integer num, TileState state) {
        scoreMap.put(state, getScore(state) - num);
    }

    public void displayScoreBoard() {
        System.out.println("*****************************************************");
        System.out.println("Score status is");
        Arrays.stream(TileState.values()).forEach(tileState -> {
            System.out.println(scoreMap.get(tileState) + " tiles of " + tileState.getDisplayText());
        });
        System.out.println("*****************************************************");
    }

    public void displayWinner() {
        System.out.println("*****************************************************");
        Player winner = scoreMap.get(TileState.O) > scoreMap.get(TileState.X) ? Player.O : Player.X;
        System.out.println("Winner is " + winner.getTileState().getDisplayText() + " occupying " + scoreMap.get(winner.getTileState()) + " tiles");
        System.out.println("*****************************************************");

    }
}
