package lex.game;

import java.util.List;
import java.util.function.Supplier;

public class GamePlayer {
    private String name;
    private char symbol;

    public GamePlayer(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean checkWin(char[][] matrix) {
        List<char[]> rows = List.of(matrix);

        // check rows
        if (rows.stream().anyMatch(row -> {
            for (char c : row) {
                if (c != this.symbol) {
                    return false;
                }
            }
            return true;
        })) {
            return true;
        }

        // check columns
        for (int i = 0; i < matrix.length; i++) {
            int index = i;
            if (rows.stream().map(row -> row[index]).allMatch(c -> {
                if (c != this.symbol) {
                    return false;
                }
                return true;
            })) {
                return true;
            }
        }

        // check diagonals

        Supplier<Boolean> checkFirstDiagonal = () -> {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][i] != this.symbol) {
                    return false;
                }
            }
            return true;
        };

        Supplier<Boolean> checkSecondDiagonal = () -> {
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][matrix.length - i - 1] != this.symbol) {
                    return false;
                }
            }
            return true;
        };

        if (checkFirstDiagonal.get() || checkSecondDiagonal.get()) {
            return true;
        }
        return false;
    }

}
