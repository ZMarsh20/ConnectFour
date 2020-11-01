public class Model {
    char[][] board = new char[7][6];
    boolean winner = false;
    int p1Wins = 0;
    int p2Wins = 0;
    int draw = 0;
    int turn = 1;
    int pturn;
    int beginner = 1;

    public Model(){
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 'o';
            }
        }
    }

    void setPiece(int column) {
        int height = 0;

        while (board[column][height] != 'o'){
            height++;
        }

        if (turn == 1){
            board[column][height] = 'r';
        }
        else {
            board[column][height] = 'b';
        }
        turn = turn*(-1);
        winner = c4Game.endGame(column,height,board);
    }

    void reSetBoard(){
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 'o';
            }
        }
        winner = false;
        beginner = beginner * (-1);
        turn = beginner;
    }

    void tallyWinner(){
        if (turn == 1){
            p1Wins++;
        }
        else {
            p2Wins++;
        }
    }

    boolean ifDraw(){
        for (int i = 0; i < 7; i++){
            if (board[i][5] == 'o'){
                return false;
            }
        }
        return true;
    }

}
