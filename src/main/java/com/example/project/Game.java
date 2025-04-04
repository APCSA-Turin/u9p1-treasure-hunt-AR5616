package com.example.project;
import java.util.Scanner;

public class Game {
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size) { // the constructor should call initialize() and play()
        this.size = size;
        initialize();
        play();
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() { // write your game logic here
        Scanner scanner = new Scanner(System.in);
        boolean continueGame = true;

        // running the while loop until the boolean continueGame becomes false
        while(continueGame) {
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            clearScreen(); // Clear the screen at the beginning of the while loop

            // firstly displaying the grid and printing out all the necessary information
            grid.display();

            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(grid.getSize()));
            System.out.println("Treasure Collected: " + player.getTreasureCount());
            System.out.println("Lives Remaining: " + player.getLives());
           
            // asking for the direction player is intended to move in
            System.out.print("Enter a direction (w, a, s, d) or 'q' to exit: ");
            String LeString = scanner.nextLine();

            // if the direction is q, the game will stop
            if (LeString.equals("q")) {
                continueGame = false;
                break;
            }

            // making sure that the move the player is trying to make is in bounds
            if (player.isValid(grid.getSize(), LeString)) {
                // creating a new x and y variable to get the position of the object we are trying to interact with (where player will be moving)
                int xPosition = player.getX();
                int yPosition = player.getY();
                // if we intend to move player up, the y will have 1 added to it
                if (LeString.equals("w")) {
                    yPosition += 1;
                }
                // if we intend to move player to the left, the x will have 1 subtracted from it
                if (LeString.equals("a")) {
                    xPosition -= 1;
                }
                // if we intend to move player down, the y will have 1 subtracted from it
                if (LeString.equals("s")) {
                    yPosition -= 1;
                }
                // if we intend to move player to the right, the x will have 1 added to it
                if (LeString.equals("d")) {
                    xPosition += 1;
                }
                // the coordinates of xPosition and yPosition now match the coords of the object we will be interacting when player moves there
                // the y-coordinates are reversed from the coordinate plane to the 2D array so the rows will be size - 1 - the y-coordinate
                Sprite obj = grid.getGrid()[size - 1 - yPosition][xPosition];
                // we first interact with the object and update what is necessary
                player.interact(grid.getSize(), LeString, treasures.length, obj);
                // player then has its x/y coordinates updated properly
                player.move(LeString);
                // player is officially placed at the intended location on the grid
                grid.placeSprite(player, LeString);
            }
            // if the player wins or loses all of their lives, we stop the while loop
            if (player.getWin() || player.getLives() == 0) {
                continueGame = false;
            }
        }
        // the screen is cleared to display the 'end of game' message
        clearScreen();
        // if the player loses all their lives, a loss message is displayed
        if (player.getLives() == 0) {
            grid.gameover();
        }
        // if a player wins with all the treasures, a win message is displayed
        if (player.getWin()) {
            grid.win();
        }
    }

    // initializing the grid with all needed Sprites, placing the Sprites in the correct row and column
    public void initialize() {
        grid = new Grid(10);
        player = new Player(0, 0);
        Enemy enemy = new Enemy(5, 5);
        Enemy enemy2 = new Enemy(7,8);
        Treasure treasure = new Treasure(2, 2);
        Treasure treasure2 = new Treasure(1,7);
        trophy = new Trophy(9, 9);
        this.treasures = new Treasure[] {treasure, treasure2};
        this.enemies = new Enemy[] {enemy, enemy2};
        grid.placeSprite(player);
        grid.placeSprite(enemy);
        grid.placeSprite(enemy2);
        grid.placeSprite(treasure);
        grid.placeSprite(treasure2);
        grid.placeSprite(trophy);
    }

    public static void main(String[] args) {
        Game LeBron = new Game(10);
    }
}