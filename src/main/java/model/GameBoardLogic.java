package model;

import exception.AlreadyOccupiedTileException;
import exception.InvalidMoveException;
import util.AppConstants;

import java.util.ArrayList;
import java.util.List;

public class GameBoardLogic {

    public static boolean isValidMoveForPlayer(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) throws InvalidMoveException, AlreadyOccupiedTileException {
        TileState tileState = gameBoard.getBoardTiles()[row][column];
        if (!tileState.equals(TileState.Blank)) {
            throw new AlreadyOccupiedTileException(AppConstants.alreadyOccupiedTileExceptionMessage);
        }

        List<Boolean> checkDirections = new ArrayList<>();
        checkDirections.add(checkEast(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkWest(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkNorth(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkSouth(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkSouthEast(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkSouthWest(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkNorthEast(row, column, currentPlayer, gameBoard, actionType));
        checkDirections.add(checkNorthWest(row, column, currentPlayer, gameBoard, actionType));

        boolean calcs = checkDirections.stream().anyMatch(bool -> bool.equals(true));


        if (calcs) {
            if (actionType.equals(ActionType.USER_INPUT)) {
                updateOverlappingCount(gameBoard, currentPlayer.getTileState());
            }
            return calcs;
        } else {
            throw new InvalidMoveException(AppConstants.invalidMoveExceptionMessage);
        }

    }

    private static boolean checkSouthWest(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row + 1, j = column - 1; i < AppConstants.BOARDSIZE && j >= 0; i++, j--) {
            TileState firstTile = boardTiles[i][j];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i + 1 < AppConstants.BOARDSIZE && j - 1 >= 0) {
                conversionCount++;
                i++;
                j--;
                firstTile = boardTiles[i][j];
            }

            if (i < AppConstants.BOARDSIZE && j >= 0) {
                TileState lastTile = boardTiles[i][j];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row, startColumn = column; startRow <= i && startColumn >= j; startRow++, startColumn--) {
                            gameBoard.updateBoardTile(startRow, startColumn, currentPlayerTileState);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkSouthEast(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row + 1, j = column + 1; i < AppConstants.BOARDSIZE && j < AppConstants.BOARDSIZE; i++, j++) {
            TileState firstTile = boardTiles[i][j];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i + 1 < AppConstants.BOARDSIZE && j + 1 < AppConstants.BOARDSIZE) {
                conversionCount++;
                i++;
                j++;
                firstTile = boardTiles[i][j];
            }

            if (i < AppConstants.BOARDSIZE && j < AppConstants.BOARDSIZE) {
                TileState lastTile = boardTiles[i][j];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row, startColumn = column; startRow <= i && startColumn <= j; startRow++, startColumn++) {
                            gameBoard.updateBoardTile(startRow, startColumn, currentPlayerTileState);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkNorthWest(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--, j--) {
            TileState firstTile = boardTiles[i][j];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i - 1 >= 0 && j - 1 >= 0) {
                conversionCount++;
                i--;
                j--;
                firstTile = boardTiles[i][j];
            }

            if (i >= 0 && j >= 0) {
                TileState lastTile = boardTiles[i][j];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row, startColumn = column; startRow >= i && startColumn >= j; startRow--, startColumn--) {
                            gameBoard.updateBoardTile(startRow, startColumn, currentPlayerTileState);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkNorthEast(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();

        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row - 1, j = column + 1; i >= 0 && j < AppConstants.BOARDSIZE; i--, j++) {
            TileState firstTile = boardTiles[i][j];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i - 1 >= 0 && j + 1 < AppConstants.BOARDSIZE) {
                conversionCount++;
                i--;
                j++;
                firstTile = boardTiles[i][j];
            }

            if (i >= 0 && j < AppConstants.BOARDSIZE) {
                TileState lastTile = boardTiles[i][j];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row, startColumn = column; startRow >= i && startColumn <= j; startRow--, startColumn++) {
                            gameBoard.updateBoardTile(startRow, startColumn, currentPlayerTileState);
                        }
                    }
                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkSouth(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row + 1; i < AppConstants.BOARDSIZE; i++) {
            TileState firstTile = boardTiles[i][column];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i + 1 < AppConstants.BOARDSIZE) {
                conversionCount++;
                i++;
                firstTile = boardTiles[i][column];
            }

            if (i < AppConstants.BOARDSIZE) {
                TileState lastTile = boardTiles[i][column];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row; startRow <= i; startRow++) {
                            gameBoard.updateBoardTile(startRow, column, currentPlayerTileState);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    private static boolean checkNorth(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = row - 1; i >= 0; i--) {
            TileState firstTile = boardTiles[i][column];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i - 1 >= 0) {
                conversionCount++;
                i--;
                firstTile = boardTiles[i][column];
            }

            if (i >= 0) {
                TileState lastTile = boardTiles[i][column];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startRow = row; startRow >= i; startRow--) {
                            gameBoard.updateBoardTile(startRow, column, currentPlayerTileState);
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkEast(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = column + 1; i < AppConstants.BOARDSIZE; i++) {
            TileState firstTile = boardTiles[row][i];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i + 1 < AppConstants.BOARDSIZE) {
                conversionCount++;
                i++;
                firstTile = boardTiles[row][i];
            }

            if (i < AppConstants.BOARDSIZE) {
                TileState lastTile = boardTiles[row][i];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startCol = column; startCol <= i; startCol++) {
                            gameBoard.updateBoardTile(row, startCol, currentPlayerTileState);
                        }
                    }

                    return true;
                }
            }
        }

        return false;
    }

    public static boolean checkWest(int row, int column, Player currentPlayer, GameBoard gameBoard, ActionType actionType) {
        int conversionCount = 0;
        TileState[][] boardTiles = gameBoard.getBoardTiles();
        TileState currentPlayerTileState = currentPlayer.getTileState();
        TileState opponentPlayerTileState = currentPlayer.getTileState().equals(TileState.X) ? TileState.O : TileState.X;
        for (int i = column - 1; i >= 0; i--) {
            TileState firstTile = boardTiles[row][i];
            if (firstTile.equals(TileState.Blank)) {
                return false;
            }
            if (firstTile.equals(currentPlayerTileState)) {
                return false;
            }
            while (firstTile.equals(opponentPlayerTileState) && i - 1 >= 0) {
                conversionCount++;
                i--;
                firstTile = boardTiles[row][i];
            }

            if (i >= 0) {
                TileState lastTile = boardTiles[row][i];
                if (lastTile.equals(currentPlayerTileState)) {
                    if (ActionType.USER_INPUT.equals(actionType)) {
                        updateScoreBoard(gameBoard, conversionCount, currentPlayerTileState, opponentPlayerTileState);
                        for (int startCol = column; startCol >= i; startCol--) {
                            gameBoard.updateBoardTile(row, startCol, currentPlayerTileState);
                        }
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private static void updateScoreBoard(GameBoard gameBoard, int conversionCount, TileState currentPlayerTileState, TileState opponentPlayerTileState) {
        ScoreBoard scoreBoard = gameBoard.getScoreBoard();
        scoreBoard.incrementScore(conversionCount, currentPlayerTileState);
        scoreBoard.decrementScore(conversionCount, opponentPlayerTileState);
    }

    private static void updateOverlappingCount(GameBoard gameBoard, TileState currentPlayerTileState) {
        ScoreBoard scoreBoard = gameBoard.getScoreBoard();
        scoreBoard.incrementScore(1, currentPlayerTileState);
        scoreBoard.decrementScore(1, TileState.Blank);
    }


}
