import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class View extends JPanel {
    JButton dropButton1;
    JButton dropButton2;
    JButton dropButton3;
    JButton dropButton4;
    JButton dropButton5;
    JButton dropButton6;
    JButton dropButton7;
    JTextField playerOneLabel;
    JTextField playerTwoLabel;
    JLabel gameDisplayLabel;
    JLabel pOneWins;
    JLabel pTwoWins;
    JLabel drawCount;
    JFrame frame;
    Circles circle;
    int num = -1;

    View(){
        circle = new Circles();
        playerOneLabel = new JTextField("Player One");
        playerOneLabel.setForeground(Color.red);
        playerTwoLabel = new JTextField("Player Two");
        playerTwoLabel.setForeground(Color.blue);
        gameDisplayLabel = new JLabel("Please wait for another player");
        gameDisplayLabel.setForeground(Color.white);

        pOneWins = new JLabel("Player One wins: 0");
        pOneWins.setForeground(Color.white);
        pTwoWins = new JLabel( "Player Two wins: 0");
        pTwoWins.setForeground(Color.white);
        drawCount = new JLabel("Draws: 0");
        drawCount.setForeground(Color.white);

        dropButton1 = new JButton("Drop 1");
        dropButton2 = new JButton("Drop 2");
        dropButton3 = new JButton("Drop 3");
        dropButton4 = new JButton("Drop 4");
        dropButton5 = new JButton("Drop 5");
        dropButton6 = new JButton("Drop 6");
        dropButton7 = new JButton("Drop 7");

        frame = new JFrame("Connect Four");

        JPanel panel = new JPanel();
        JPanel panelThree = new JPanel(new GridLayout(2,3,5,5));

        dropButton1.setBackground(Color.pink);
        panel.add(dropButton1);
        dropButton2.setBackground(Color.pink);
        panel.add(dropButton2);
        dropButton3.setBackground(Color.pink);
        panel.add(dropButton3);
        dropButton4.setBackground(Color.pink);
        panel.add(dropButton4);
        dropButton5.setBackground(Color.pink);
        panel.add(dropButton5);
        dropButton6.setBackground(Color.pink);
        panel.add(dropButton6);
        dropButton7.setBackground(Color.pink);
        panel.add(dropButton7);

        panelThree.add(playerOneLabel);
        panelThree.add(gameDisplayLabel);
        panelThree.add(playerTwoLabel);
        panelThree.add(pOneWins);
        panelThree.add(drawCount);
        panelThree.add(pTwoWins);

        panel.setBackground(Color.black);
        circle.setBackground(Color.black);
        panelThree.setBackground(Color.black);

        frame.getContentPane().add(BorderLayout.NORTH, panel);
        frame.getContentPane().add(BorderLayout.CENTER, circle);
        frame.getContentPane().add(BorderLayout.SOUTH, panelThree);

        frame.setSize(600,600);

        frame.setVisible(true);
    }

    void setActionListener(ActionListener aL) {
        dropButton1.addActionListener(aL);
        dropButton2.addActionListener(aL);
        dropButton3.addActionListener(aL);
        dropButton4.addActionListener(aL);
        dropButton5.addActionListener(aL);
        dropButton6.addActionListener(aL);
        dropButton7.addActionListener(aL);
    }
}
