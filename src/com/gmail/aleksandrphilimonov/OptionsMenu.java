package com.gmail.aleksandrphilimonov;

import java.util.Scanner;

public class OptionsMenu {
    static Scanner scanner = new Scanner(System.in);
    static int command;

    public static void showOptionsMenu() {
        do {
            System.out.println("Make your choice and press Enter\n1: Show current settings\n2: Change settings\n3: Exit\n");
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    System.out.println("\nCurrent settings:\n\trows: " + Starter.sizeX +
                            "\n\tcolumns: " + Starter.sizeY +
                            "\n\tenemies: " + Starter.amountOfEnemies +
                            "\n\ttransistors: " + Starter.transistorsNeeded +
                            "\n\tmoves: " + Starter.moves + '\n');
                    break;
                case 2:
                    System.out.println("Enter a new value for rows: ");
                    Starter.sizeX = scanner.nextInt();
                    System.out.println("Enter a new value for columns: ");
                    Starter.sizeY = scanner.nextInt();
                    System.out.println("Enter a new value for enemies: ");
                    Starter.amountOfEnemies = scanner.nextInt();
                    System.out.println("Enter a new value for transistors: ");
                    Starter.transistorsNeeded = scanner.nextInt();
                    System.out.println("Enter a new value for moves: ");
                    Starter.moves = scanner.nextInt();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Command not recognized, please try again!");
                    break;
            }
        } while (command != 3);
    }
}
