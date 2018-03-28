package util;

import model.Player;

import java.util.Scanner;

public class InputUtils {

    public static String fetchTileAddressOfNextMove(Player currentPlayer, int moveNumber) {
        System.out.print("Move " + moveNumber + " by player" + currentPlayer.getTileState().getDisplayText() + ":");
        Scanner scanner = new Scanner(System.in);
        String tileAddress = scanner.next();
        return tileAddress;
    }
}
