import model.GameBoard;

public class Main {
    public static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.displayGameBoard();
        gameBoard.startGame();
        gameBoard.getScoreBoard().displayWinner();
        gameBoard.getScoreBoard().displayScoreBoard();
    }
}
