import javax.swing.*;
import java.awt.*;

public class Circles extends JPanel {
    char[][] board = new char[7][6];
    Circles(){
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 6; j++){
                board[i][j] = 'o';
            }
        }
    }

    void setBoard(char[][] boardModel){
        board = boardModel;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xVal;
        int yVal = 10;
        for (int i = 0; i < 6; i++){
            xVal = 15;
            for (int j = 0; j < 7; j++){
                if (board[j][5-i] == 'r') {
                    g.setColor(Color.red);
                }
                else if (board[j][5-i] == 'b') {
                    g.setColor(Color.cyan);
                }
                else {
                    g.setColor(Color.gray);
                }
                g.fillOval(xVal,yVal,70, 70);
                xVal = xVal + 80;
            }
            yVal = yVal + 80;
        }
    }
}
