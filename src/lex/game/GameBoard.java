package lex.game;

import java.util.List;

import javax.swing.JOptionPane;

import lex.App;

public class GameBoard {
    private char[][] board;
    private int size;
    private List<GamePlayer> players;
    private int currentTurn = 0;

    public GameBoard(int size, List<GamePlayer> players) {
        this.size = size;
        this.players = players;
        this.board = new char[size][size];
        this.resetBoard();
    }

    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<GamePlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<GamePlayer> players) {
        this.players = players;
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public void nextTurn() {
        currentTurn = (currentTurn + 1) % players.size();
    }

    public boolean checkWiner() {
        return players.get(currentTurn).checkWin(board);
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void resetBoard() {
        this.currentTurn = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
                int index = i * size + j;
                if (App.buttons[index] != null) {

                    App.buttons[index].setText(" ");
                    App.buttons[index].setEnabled(true);
                }
            }
        }
    }

    public boolean playTurn(int index) {

        int row = index / size;
        int column = index % size;

        this.board[row][column] = players.get(currentTurn).getSymbol();
        if (App.buttons[index] != null) {
            App.buttons[index].setText(players.get(currentTurn).getSymbol() + "");
            App.buttons[index].setEnabled(false);
        }
        if (checkWiner()) {
            JOptionPane.showMessageDialog(App.frame, "Player " + players.get(currentTurn).getName() + " won!");
            return false;
        }
        if (isFull()) {
            JOptionPane.showMessageDialog(App.frame, "Draw!");
            resetBoard();
        } else {
            nextTurn();
        }
        return true;
    }

    public GamePlayer getCurrentPlayer() {
        return players.get(currentTurn);
    }

}
