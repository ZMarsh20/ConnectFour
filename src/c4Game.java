class c4Game {

    private static int count(int chipColumn, int chipRow, char[][] board, int horizontalMod, int verticalMod){
        int counter = 0;
        int chipColumnClone = chipColumn;
        int chipRowClone = chipRow;
        char chipPosition = board[chipColumn][chipRow];
        char positionClone = chipPosition;
        while (positionClone == chipPosition){
            counter++;
            if((chipColumn + horizontalMod) > -1 && (chipColumn + horizontalMod) < 7){
                if (((chipRow + verticalMod) > -1) && ((chipRow + verticalMod) < 6)) {
                    chipColumn = chipColumn + horizontalMod;
                    chipRow = chipRow + verticalMod;
                    positionClone = board[chipColumn][chipRow];
                } else break;
            } else break;
        }

        counter--;
        horizontalMod = horizontalMod*(-1);
        verticalMod = verticalMod*(-1);
        positionClone = chipPosition;

        while (positionClone == chipPosition){
            counter++;
            if((chipColumnClone + horizontalMod) > -1 && (chipColumnClone + horizontalMod) < 7){
                if (((chipRowClone + verticalMod) > -1) && ((chipRowClone + verticalMod) < 6)) {
                    chipColumnClone = chipColumnClone + horizontalMod;
                    chipRowClone = chipRowClone + verticalMod;
                    positionClone = board[chipColumnClone][chipRowClone];
                } else break;
            } else break;
        }

        return counter;
    }

    static boolean endGame(int chipColumn, int chipRow, char[][] board){
        int consecVertical = count(chipColumn, chipRow, board, 0, 1);
        int consecHorizontal = count(chipColumn, chipRow, board, 1, 0);
        int consecLeftToUp = count(chipColumn, chipRow, board, 1, 1);
        int consecLeftToDown = count(chipColumn, chipRow, board, 1, -1);

        if (consecVertical > 3 || consecHorizontal > 3 || consecLeftToUp > 3 || consecLeftToDown > 3){
            return true;
        }
        else {
            return false;
        }
    }
}
