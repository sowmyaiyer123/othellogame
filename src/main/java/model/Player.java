package model;

public enum Player {
    X(TileState.X), O(TileState.O);

    private TileState tileState;

    Player(TileState tileState) {
        this.tileState = tileState;
    }

    public TileState getTileState() {
        return tileState;
    }
}
