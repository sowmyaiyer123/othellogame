import exception.AlreadyOccupiedTileException;
import exception.InvalidMoveException;
import model.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class OthelloTests {

    GameBoard gameBoard;

    @Before
    public void setUp() {
        gameBoard = new GameBoard();
    }

    @Test
    public void testGameBoardInitialSetup() {
        assertEquals(gameBoard.areValidMovesPending(Player.X), true);
        assertEquals(gameBoard.areValidMovesPending(Player.O), true);
        assertEquals(gameBoard.getScoreBoard().getScore(TileState.Blank).intValue(), 60);
        assertEquals(gameBoard.getScoreBoard().getScore(TileState.X).intValue(), 2);
        assertEquals(gameBoard.getScoreBoard().getScore(TileState.O).intValue(), 2);
    }


    @Test(timeout = 2000)
    @Ignore
    public void testEndToEndGameSimulation() {
        Random r = new Random();
        gameBoard.displayGameBoard();
        while (gameBoard.areValidMovesPending(Player.X) || gameBoard.areValidMovesPending(Player.O)) {
            int row = r.nextInt(8);
            int column = r.nextInt(8);
            try {
                GameBoardLogic.isValidMoveForPlayer(row, column, gameBoard.getCurrentPlayer(), gameBoard, ActionType.USER_INPUT);
                System.out.println(gameBoard.getCurrentPlayer().getTileState() + "entered Row " + (row + 1) + ", Column " + (column + 1));
                gameBoard.changePlayer();
                gameBoard.displayGameBoard();

            } catch (InvalidMoveException | AlreadyOccupiedTileException e) {
                System.out.println(e.getMessage() + " Row " + (row + 1) + ", Column " + (column + 1));
                System.out.println();
            }
        }

        gameBoard.displayGameBoard();
        gameBoard.getScoreBoard().displayScoreBoard();
        gameBoard.getScoreBoard().displayWinner();
    }

}