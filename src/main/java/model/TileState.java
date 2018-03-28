package model;

public enum TileState {
    O(" O "), X(" X "), Blank(" * ");

    private String displayText;

    TileState(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}
