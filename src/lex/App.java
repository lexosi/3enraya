package lex;

import java.util.List;

import javax.swing.*;

import lex.game.GameBoard;
import lex.game.GamePlayer;

import java.awt.*;

public class App {

    public static JFrame frame;
    public static JPanel panel;
    public static JButton[] buttons = new JButton[9];

    public static GameBoard gameBoard;

    public static void main(String[] args) throws Exception {
        frame = new JFrame("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        gameBoard = new GameBoard(
                3,
                List.of(
                        new GamePlayer("Player 1", 'x'),
                        new GamePlayer("Player 2", 'o')));

        panel = new JPanel();
        panel.setLayout(new GridLayout(gameBoard.getSize(), gameBoard.getSize()));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 0; i < Math.pow(gameBoard.getSize(), 2); i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 40));
            int index = i;
            buttons[i].addActionListener(listener -> {
                gameBoard.playTurn(index);
            });
            panel.add(buttons[i]);
        }

        ImageIcon ImageIcon = new ImageIcon("icon.ico");
        Image Image = ImageIcon.getImage();
        frame.setIconImage(Image); 

        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(400, 400);
        frame.setVisible(true);

    }

}
