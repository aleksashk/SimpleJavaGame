package com.gmail.aleksandrphilimonov;

import java.util.ArrayList;
import java.util.Random;

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
    private Random randomNumber = new Random();

    public Game(int rows, int columns, int amountOfEnemies, int transistorsNeeded, int turnsLeft, int amountOfFlowers) {
        this.rows = rows;
        this.columns = columns;
        this.amountOfEnemies = amountOfEnemies;
        this.amountOfFlowers = amountOfFlowers;
        this.transistorsNeeded = transistorsNeeded;
        this.turnsLeft = turnsLeft;
        field = new Field(rows, columns);
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
            computerTurn();
            checkIfGameNotFinished();

        }
    }

    private void possessFlowers() {
        generateFlowers();
    }

    private void possessEnemies() {
    }

    private void possessPlayer() {

    }

    private void checkIfGameNotFinished() {
        if (turnsLeft == 0) {
            System.out.println("No more turns left, you lost!");
            isGameFinished = true;
        } else if (transistorsGathered >= 100) {
            System.out.println("You have gathered the required number of transistors, you won!!!");
            isGameFinished = true;
        }
    }

    private void computerTurn() {
    }

    private void generateFlowers() {
        for (int i = amountOfFlowers - flowerArrayList.size(); i > 0; ) {

            int flowerAmountOfTransistors = randomNumber.nextInt(9) + 1;
            int flowerRowPosition = randomNumber.nextInt(rows);
            int flowerColumnPosition = randomNumber.nextInt(columns);

            if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Player) {
                transistorsGathered = transistorsGathered + flowerAmountOfTransistors;
                i--;
            } else if (field.getFieldable(flowerRowPosition, flowerColumnPosition) instanceof Empty) {
                Flower flower = new Flower(flowerAmountOfTransistors);
                field.setFieldable(flowerRowPosition, flowerColumnPosition, flower);
                flowerArrayList.add(flower);
                i--;
            }
        }
    }

    private void playerTurn() {
    }

    private void showField() {
        System.out.println("\n\nTurns left: " + turnsLeft
                + ",\nTransistors gathered: " + transistorsGathered
                + "/" + transistorsNeeded);
        field.showField();
    }
}
