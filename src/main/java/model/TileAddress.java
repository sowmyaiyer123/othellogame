package model;

import exception.IncorrectTileAddressException;

import java.util.stream.IntStream;

import static util.AppConstants.*;

public class TileAddress {

    String userTileAddress;
    private int rowIdentifier;
    private int columnIdentifier;

    public TileAddress(String userTileAddress) throws IncorrectTileAddressException {
        this.userTileAddress = userTileAddress;
        constructIdentifiers(userTileAddress);
    }

    private void constructIdentifiers(String userTileAddress) throws IncorrectTileAddressException {
        validateInputLength(userTileAddress);


        boolean isCharacter = IntStream.rangeClosed('a', 'h').mapToObj(c -> (char) c).anyMatch(character -> character.equals(userTileAddress.charAt(0)));
        if (isCharacterAdmissableInteger(userTileAddress) && isCharacter) {
            rowIdentifier = Integer.parseInt(String.valueOf(userTileAddress.charAt(1))) - 1;
            columnIdentifier = userTileAddress.charAt(0) - ASCIIOFSMALLA;
        } else {
            throw new IncorrectTileAddressException(tileAddressExceptionMessage);
        }
    }

    private boolean isCharacterAdmissableInteger(String userTileAddress) throws IncorrectTileAddressException {
        try {
            Integer.parseInt(String.valueOf(userTileAddress.charAt(1)));
        } catch (Exception e) {
            throw new IncorrectTileAddressException(tileAddressExceptionMessage);
        }
        return IntStream.rangeClosed(1, 8).anyMatch(x -> x == Integer.parseInt(String.valueOf(userTileAddress.charAt(1))));
    }

    private void validateInputLength(String userTileAddress) throws IncorrectTileAddressException {
        if (userTileAddress.length() != INPUTLENGTH) {
            throw new IncorrectTileAddressException(tileAddressExceptionMessage);
        }
    }

    public String getUserTileAddress() {
        return userTileAddress;
    }

    public void setUserTileAddress(String userTileAddress) {
        this.userTileAddress = userTileAddress;
    }

    public int getRowIdentifier() {
        return rowIdentifier;
    }

    public void setRowIdentifier(int rowIdentifier) {
        this.rowIdentifier = rowIdentifier;
    }

    public int getColumnIdentifier() {
        return columnIdentifier;
    }

    public void setColumnIdentifier(int columnIdentifier) {
        this.columnIdentifier = columnIdentifier;
    }

}
