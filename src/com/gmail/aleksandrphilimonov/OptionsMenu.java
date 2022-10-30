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
                    System.out.println("\nCurrent settings:\n\trows: " + Starter.rows +
                            "\n\tcolumns: " + Starter.columns +
                            "\n\tenemies: " + Starter.amountOfEnemies +
                            "\n\ttransistors: " + Starter.transistorsNeeded +
                            "\n\tmoves: " + Starter.moves +
                            "\n\tflowers: " + Starter.getAmountOfFlowers + '\n');
                    break;
                case 2:
                    String value;
                    System.out.println("Enter a new value for rows, leave blank to keep current value [" + Starter.rows + "]:");

                    scanner.nextLine();

                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.rows = Integer.parseInt(value);
                    }
                    System.out.println("Enter a new value for columns, leave blank to keep current value [" + Starter.columns + "]:");
                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.columns = Integer.parseInt(value);
                    }
                    System.out.println("Enter a new value for enemies, leave blank to keep current value [" + Starter.amountOfEnemies + "]:");
                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.amountOfEnemies = Integer.parseInt(value);
                    }
                    System.out.println("Enter a new value for transistors, leave blank to keep current value [" + Starter.transistorsNeeded + "]:");
                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.transistorsNeeded = Integer.parseInt(value);
                    }
                    System.out.println("Enter a new value for moves, leave blank to keep current value [" + Starter.moves + "]:");
                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.moves = Integer.parseInt(value);
                    }
                    System.out.println("Enter a new value for flowers, leave blank to keep current value [" + Starter.getAmountOfFlowers + "]:");
                    value = scanner.nextLine();
                    if (!value.isBlank()) {
                        Starter.getAmountOfFlowers = Integer.parseInt(value);
                    }
                    if (isValuesNotPayable()) {
                        System.out.println("Values you entered aren't playable, " +
                                "please verify and try again!");
                    }
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Command not recognized, please try again!");
                    break;
            }
        } while (command != 3);
    }

    private static Boolean isValuesNotPayable() {
        int fieldSize = Starter.rows * Starter.columns;
        int allGameObjects = Starter.amountOfEnemies + Starter.getAmountOfFlowers + 1;
        return ((allGameObjects / fieldSize) > 0.75);
    }
}
