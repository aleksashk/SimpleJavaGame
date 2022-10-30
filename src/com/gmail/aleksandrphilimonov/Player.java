package com.gmail.aleksandrphilimonov;

public class Player implements Fieldable {
    private static final String MOVE_LEFT = "a";
    private static final String MOVE_RIGHT = "d";
    private static final String MOVE_UP = "w";
    private static final String MOVE_DOWN = "z";
    private static final String NO_MOVE = "s";

    private int rowIndex;
    private int columnIndex;
    private Field field;

    @Override
    public String getSymbol() {
        return " @ ";
    }

    public Player(int rowIndex, int columnIndex, Field field) {
        this.rowIndex = rowIndex;
        this.columnIndex = columnIndex;
        this.field = field;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    public void makeMove(String command) {

        switch (command) {
            case MOVE_LEFT:
                movePlayer(0, -1);
                break;

            case MOVE_RIGHT:
                movePlayer(0, 1);
                break;

            case MOVE_UP:
                movePlayer(1, 0);
                break;

            case MOVE_DOWN:
                movePlayer(-1, 0);
                break;

            case NO_MOVE:
                break;

            default:
                showError(command);
                break;

        }
    }

    private void showError(String command) {
        System.out.println("Sorry, there is no " + command +
                " command, please verify and try again. ");
    }

    public void movePlayer(int deltaRowIndex, int deltaColumnIndex){}
}
