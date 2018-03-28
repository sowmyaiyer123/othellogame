package model;

import exception.AlreadyOccupiedTileException;
import exception.IncorrectTileAddressException;
import exception.InvalidMoveException;
import util.AppConstants;
import util.InputUtils;

import java.util.stream.IntStream;

public class GameBoard {

    public static final int boardSize = AppConstants.BOARD_SIZE;
    private TileState boardTiles[][];
    private ScoreBoard scoreBoard;
    private Player currentPlayer;

    public GameBoard() {
        configureInitialBoardState();
        configureCurrentPlayer();
        configureScoreBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void configureScoreBoard() {
        scoreBoard = new ScoreBoard();
    }

    private void configureCurrentPlayer() {
        currentPlayer = Player.X;
    }

    public void changePlayer() {
        currentPlayer = currentPlayer.equals(Player.X) ? Player.O : Player.X;
    }

    private void configureInitialBoardState() {
        boardTiles = new TileState[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boardTiles[i][j] = TileState.Blank;
            }
        }
        boardTiles[3][3] = TileState.O;
        boardTiles[4][4] = TileState.O;
        boardTiles[3][4] = TileState.X;
        boardTiles[4][3] = TileState.X;
    }

    public TileState[][] getBoardTiles() {
        return boardTiles;
    }

    public void updateBoardTile(int row, int column, TileState tileState) {
        this.boardTiles[row][column] = tileState;

    }

    public void displayGameBoard() {
        System.out.println();
        System.out.println("Board state");
        System.out.println("-------------------------------------");
        int counter = 1;
        for (TileState[] tileRow : boardTiles) {
            System.out.print(counter + " ");
            for (TileState state : tileRow) {
                System.out.print(state.getDisplayText());
            }
            counter++;
            System.out.println();
        }

        System.out.print(" ");
        IntStream.rangeClosed('a', 'h').mapToObj(c -> (char) c).forEach(c -> System.out.print("  " + c));
        System.out.println();
        System.out.println("-------------------------------------");
        System.out.println();


    }

    public ScoreBoard getScoreBoard() {
        return scoreBoard;
    }

    public void startGame() {
        int moveNumber = 1;

        while (areValidMovesPending(Player.X) || areValidMovesPending(Player.O)) {
            String tileAddress = InputUtils.fetchTileAddressOfNextMove(currentPlayer, moveNumber);
            try {
                TileAddress address = new TileAddress(tileAddress);
                boolean isValidMoveForPlayer = GameBoardLogic.isValidMoveForPlayer(address.getRowIdentifier(), address.getColumnIdentifier(), currentPlayer, this, ActionType.USER_INPUT);
                changePlayer();
                displayGameBoard();
                moveNumber++;
            } catch (IncorrectTileAddressException | InvalidMoveException | AlreadyOccupiedTileException e) {
                System.out.println(e.getMessage());
                System.out.println();
            }
        }
    }

    public boolean areValidMovesPending(Player p) {
        for (int i = 0; i < AppConstants.BOARD_SIZE; i++) {
            for (int j = 0; j < AppConstants.BOARD_SIZE; j++) {
                if (boardTiles[i][j].equals(TileState.Blank)) {
                    try {
                        GameBoardLogic.isValidMoveForPlayer(i, j, p, this, ActionType.MOVES_PENDING_LOGIC);
                        return true;
                    } catch (Exception e) {
                    }
                }
            }
        }
        return false;
    }

}