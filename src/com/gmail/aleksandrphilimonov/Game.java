package com.gmail.aleksandrphilimonov;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private int rows;
    private int columns;
    private int amountOfEnemies;
    private int transistorsNeeded;
    private int turnsLeft;
    private int transistorsGathered;
    private Field field;
    private boolean isGameFinished = false;
    private int amountOfFlowers;
    private ArrayList<Flower> flowerArrayList = new ArrayList<>();
    private ArrayList<Enemy> enemyArrayList = new ArrayList<>();
    private Random randomNumber = new Random();
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private Boolean isIncorrectCommand = true;

    public Game(int rows, int columns, int amountOfEnemies,
                int transistorsNeeded, int turnsLeft, int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemies = amountOfEnemies;
        this.amountOfFlowers = amountOfFlowers;
        this.transistorsNeeded = transistorsNeeded;
        this.turnsLeft = turnsLeft;
        field = new Field(rows, columns);

    }

    public Field getField() {
        return this.field;
    }

    public ArrayList<Flower> getFlowerArrayList() {
        return this.flowerArrayList;
    }

    public void setTransistorsGathered(int transistorsToAdd) {
        this.transistorsGathered += transistorsToAdd;
    }

    public void fillFieldWithEmptyObjects() {

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                field.setFieldable(i, j, new Empty());
            }
        }
    }

    public void startGame() {

        possessPlayer();
        possessEnemies();
        possessFlowers();

        while (!isGameFinished) {

            showField();
            playerTurn();
            if (isIncorrectCommand) {
                incorrectCommand();
                continue;
            }
            computerTurn();
            checkIfGameNotFinished();

        }

    }

    private void incorrectCommand() {
        System.out.println("You have entered an incorrect command, please " +
                "verify and try again!");
    }

    private void possessPlayer() {
        int playerRowPosition = randomNumber.nextInt(rows);
        int playerColumnPosition = randomNumber.nextInt(columns);

        player = new Player(playerRowPosition, playerColumnPosition, this);
    }

    private void possessEnemies() {
        generateEnemies();
    }

    private void generateEnemies() {
        for (int i = amountOfEnemies - enemyArrayList.size(); i > 0; ) {

            int enemyRowPosition = randomNumber.nextInt(rows);
            int enemyColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(enemyRowPosition, enemyColumnPosition)
                    instanceof Empty) {
                Enemy enemy = new Enemy(enemyRowPosition, enemyColumnPosition);

                field.setFieldable(enemyRowPosition, enemyColumnPosition, enemy);
                enemyArrayList.add(enemy);
                i--;
            }
        }
    }

    private void possessFlowers() {
        generateFlowers();
    }

    private void showField() {
        System.out.println("\n\nTurns left: " + turnsLeft
                + ", transistors gathered: " + transistorsGathered
                + "/" + transistorsNeeded);
        field.showField();
    }

    private void playerTurn() {
        System.out.println("Please enter your command and press Enter:");
        String command = scanner.nextLine();
        isIncorrectCommand = player.makeMove(command);
    }

    private void computerTurn() {
        enemyMove();
        generateFlowers();
        turnsLeft--;
    }

    private void enemyMove() {

        int rowIndex;
        int columnIndex;
        int newRowIndex;
        int newColumnIndex;
        int regenerateIndex = 0;
        boolean isNeededToRegenerate;

        for (Enemy enemy : enemyArrayList) {

            rowIndex = enemy.getRowIndex();
            columnIndex = enemy.getColumnIndex();

            do {
                int deltaRow = randomNumber.nextInt(3) - 1;
                int deltaColumn = randomNumber.nextInt(3) - 1;

                newRowIndex = rowIndex + deltaRow;
                newColumnIndex = columnIndex + deltaColumn;

                if ((newRowIndex < 0) || (newColumnIndex < 0) || (newRowIndex >= field.getRows()) ||
                        (newColumnIndex >= field.getColumns()) || field.getFieldable(newRowIndex, newColumnIndex) instanceof Player ||
                        field.getFieldable(newRowIndex, newColumnIndex) instanceof Enemy) {
                    regenerateIndex++;
                    isNeededToRegenerate = true;
                } else {
                    if (field.getFieldable(newRowIndex, newColumnIndex) instanceof Flower) {
                        Flower flower = (Flower) field.getFieldable(newRowIndex, newColumnIndex);
                        flowerArrayList.remove(flower);

                        field.setFieldable(newRowIndex, newColumnIndex, enemy);
                        field.setFieldable(rowIndex, columnIndex, new Empty());
                        enemy.setRowIndex(newRowIndex);
                        enemy.setColumnIndex(newColumnIndex);
                        isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                    } else {
                        field.setFieldable(newRowIndex, newColumnIndex, enemy);
                        field.setFieldable(rowIndex, columnIndex, new Empty());
                        enemy.setRowIndex(newRowIndex);
                        enemy.setColumnIndex(newColumnIndex);
                        isNeededToRegenerate = swapEnemy(rowIndex, columnIndex, newRowIndex, newColumnIndex, enemy);
                    }
                }

            } while (isNeededToRegenerate && regenerateIndex <= 10);

        }
    }

    private boolean swapEnemy(int rowIndex, int columnIndex, int newRowIndex, int newColumnIndex, Enemy enemy) {
        field.setFieldable(newRowIndex, newColumnIndex, enemy);
        field.setFieldable(rowIndex, columnIndex, new Empty());
        enemy.setRowIndex(newRowIndex);
        enemy.setColumnIndex(newColumnIndex);
        return false;
    }

    private void generateFlowers() {

        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {

            int flowerAmountOfTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Player) {
                transistorsGathered = transistorsGathered + flowerAmountOfTransistors;
                i--;
            } else if (field.getFieldable(flowerRowPosition, flowerColumnPosition)
                    instanceof Empty) {
                Flower flower = new Flower(flowerAmountOfTransistors, flowerRowPosition, flowerColumnPosition);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;
            }
        }
    }

    private void checkIfGameNotFinished() {

        if (turnsLeft == 0) {
            System.out.println("No more turns left, you lost!");
            isGameFinished = true;
        } else if (transistorsGathered >= 100) {
            System.out.println("You have gathered the required " +
                    "number of transistors, you won!!!");
            isGameFinished = true;

        }
    }
}
