package fr.istic.vv.simpleGame.cli;

import java.util.Scanner;

import fr.istic.vv.simpleGame.core.Direction;
import fr.istic.vv.simpleGame.core.Game;
import fr.istic.vv.simpleGame.exception.OutOfBoardException;

public class CLIMain {
	
	private static Game game;

    public static void main(String[] args) {

        game = new Game();
        Scanner scanner = new Scanner(System.in);
        while(!game.isGameOver()) {
            System.out.println(game.toString());
            int maxValues = Direction.values().length;
            int choice = -10;
            do {
                int counter = 0;
                for(Direction d : Direction.values()) {
                    System.out.println(counter + ": "+d.name());
                    counter++;
                }
                System.out.println("Please chose a direction: ");
                choice = scanner.nextInt();
            } while (choice >= maxValues || choice < 0);
            try {
                System.out.println(game.moveNextPawn(
                                       Direction.values()[choice]));
            } catch (OutOfBoardException e) {
                System.out.println("You can't go that way!");
            }
        }
        scanner.close();

    }

}
