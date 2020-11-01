import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.Timer;
import static java.lang.System.exit;

public class Controller implements ActionListener {
    Model model;
    View view;
    Socket sock;
    PrintWriter write;
    BufferedReader read;
    boolean buttonSwitchBoolean = false;

    Controller() {
        model = new Model();
        view = new View();
        buttonsSwitch();
        int delayTime = 100;
        Timer myTimer = new Timer(delayTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InputStreamReader reader = new InputStreamReader(sock.getInputStream());
                    read = new BufferedReader(reader);
                    model.pturn = Integer.parseInt(read.readLine());
                } catch (NullPointerException ex) {
                    System.out.println("Hmmm. I can't find connect four at that IP. Sorry.");
                    exit(0);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                if (model.pturn == -1) {
                    view.gameDisplayLabel.setText("You are Player Two. You go 2nd!");
                    Runnable task = new Task();
                    Thread t = new Thread(task);
                    t.start();
                }
                else {
                    buttonsSwitch();
                    view.gameDisplayLabel.setText("You are Player one. You go 1st!");
                }
          }
        });
        myTimer.setRepeats(false);
        myTimer.start();

        view.setActionListener(this);
        view.frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent windowEvent) {

            }
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                try {
                    if (write != null && read != null && sock!= null) {
                        write.close();
                        read.close();
                        sock.close();
                    }
                    exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void windowClosed(WindowEvent windowEvent) {

            }
            @Override
            public void windowIconified(WindowEvent windowEvent) {

            }
            @Override
            public void windowDeiconified(WindowEvent windowEvent) {

            }
            @Override
            public void windowActivated(WindowEvent windowEvent) {

            }
            @Override
            public void windowDeactivated(WindowEvent windowEvent) {

            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (model.pturn == model.turn) {
            boolean filled = false;
            if (e.getSource() == view.dropButton1) filled = pieceHandler(0);
            else if (e.getSource() == view.dropButton2) filled = pieceHandler(1);
            else if (e.getSource() == view.dropButton3) filled = pieceHandler(2);
            else if (e.getSource() == view.dropButton4) filled = pieceHandler(3);
            else if (e.getSource() == view.dropButton5) filled = pieceHandler(4);
            else if (e.getSource() == view.dropButton6) filled = pieceHandler(5);
            else if (e.getSource() == view.dropButton7) filled = pieceHandler(6);

            if (filled) {
                buttonsSwitch();
                try {
                    write = new PrintWriter(sock.getOutputStream());
                    write.println(view.num);
                    write.flush();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                view.circle.setBoard(model.board);
                view.frame.repaint();
                if (stopGame()) {
                    view.gameDisplayLabel.setText("It's their turn!");
                    Runnable task = new Task();
                    Thread t = new Thread(task);
                    t.start();
                }
            }
        }
    }

    boolean pieceHandler(int col){
        if (model.board[col][5] == 'o') {
            model.setPiece(col);
            view.num = col;
            return true;
        }
        else {
            view.gameDisplayLabel.setText("Sorry. This column is full");
            return false;
        }
    }

    boolean stopGame(){
        if (model.winner) {
            didWin();
            return false;
        } else if (model.ifDraw()) {
            didDraw();
            return false;
        }
        else {
            return true;
        }
    }

    void didWin(){
        model.turn = model.turn*(-1);
        model.tallyWinner();
        if (model.turn == model.pturn) {
            view.gameDisplayLabel.setText("You win!!");
        }
        else {
            view.gameDisplayLabel.setText("Dang. They win :(");
        }
        view.pOneWins.setText("Player One wins: " + model.p1Wins);
        view.pTwoWins.setText("Player Two wins: " + model.p2Wins);

        int delayTime = 2000;
        Timer myTimer = new Timer(delayTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.reSetBoard();
                view.circle.setBoard(model.board);
                view.frame.repaint();
                if (model.turn == model.pturn) {
                    view.gameDisplayLabel.setText("It's your turn!");
                }
                else {
                    view.gameDisplayLabel.setText("It's their turn!");
                }
                if (model.pturn != model.turn) {
                    startOver();
                }
                else {
                    buttonsSwitch();
                }
            }
        });
        myTimer.setRepeats(false);
        myTimer.start();
    }

    void didDraw(){
        view.gameDisplayLabel.setText("It's a Draw");
        model.draw++;
        view.drawCount.setText("Draws: " + model.draw);

        int delayTime = 2000;
        Timer myTimer = new Timer(delayTime, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                model.reSetBoard();
                view.circle.setBoard(model.board);
                view.frame.repaint();
                if (model.turn == model.pturn) {
                    view.gameDisplayLabel.setText("It's your turn!");
                }
                else {
                    view.gameDisplayLabel.setText("It's their turn!");
                }
                if (model.pturn != model.turn) {
                    startOver();
                }
                else {
                    buttonsSwitch();
                }
            }
        });
        myTimer.setRepeats(false);
        myTimer.start();
    }

    void startOver(){
        view.gameDisplayLabel.setText("It's their turn!");
        Runnable task = new Task();
        Thread t = new Thread(task);
        t.start();
    }

    void buttonsSwitch(){
        view.dropButton1.setEnabled(buttonSwitchBoolean);
        view.dropButton2.setEnabled(buttonSwitchBoolean);
        view.dropButton3.setEnabled(buttonSwitchBoolean);
        view.dropButton4.setEnabled(buttonSwitchBoolean);
        view.dropButton5.setEnabled(buttonSwitchBoolean);
        view.dropButton5.setEnabled(buttonSwitchBoolean);
        view.dropButton6.setEnabled(buttonSwitchBoolean);
        view.dropButton7.setEnabled(buttonSwitchBoolean);
        buttonSwitchBoolean = !buttonSwitchBoolean;
    }

    class Task implements Runnable{
        @Override
        public void run() {
            try {
                InputStreamReader reader = new InputStreamReader(sock.getInputStream());
                read = new BufferedReader(reader);
                int nextPiece = Integer.parseInt(read.readLine());
                if (nextPiece == -1){
                    forfeitProtocol();
                }
                else {
                    model.setPiece(nextPiece);
                    view.circle.setBoard(model.board);
                    view.frame.repaint();
                    view.gameDisplayLabel.setText("It's your turn!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            int delayTime = 10;
            Timer myTimer = new Timer(delayTime, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (stopGame()) {
                        buttonsSwitch();
                    }
                }
            });
            myTimer.setRepeats(false);
            myTimer.start();
        }
    }

    void forfeitProtocol(){
        view.gameDisplayLabel.setText("Other player has left the game");
        view.drawCount.setText("To keep playing, restart server");
        int delayTime = 4000;
        Timer myTimer = new Timer(delayTime, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });
        myTimer.setRepeats(false);
        myTimer.start();
    }
}
