package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Grid {
    private Sprite[][] grid;
    private int size;

    // initializes and creates a grid with all DOT objects
    public Grid(int size) {
        this.size = size;
        this.grid = new Sprite[size][size];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                Dot LeDot = new Dot(i, j);
                grid[i][j] = LeDot;
            }
        }
    }

    // returns the grid 2D array
    public Sprite[][] getGrid(){return grid;}

    // returns the size of the grid
    public int getSize() {
        return size;
    }

    // places sprite in new spot
    public void placeSprite(Sprite s) {
        // the rows are reversed going from coordinates to row col
        int row = size - 1 - s.getY();
        int col = s.getX();
        // making sure none of row or col is out of bounds
        if (row < 0 || col < 0 || row >= size || col >= size) {
            return;
        }
        grid[row][col] = s;
    }

    // places sprite in a new spot based on direction given
    public void placeSprite(Sprite s, String direction) {
        placeSprite(s);
        // a Dot object replace is created to fill in the spot left open by the Sprite we are moving
        Dot replace = new Dot(s.getX(), s.getY());
        // if the direction is up, we move replace down to accomodate the space left by Player
        if (direction.equals("w")) {
            replace.setY(replace.getY() - 1);
        // if the direction is left, we move replace right to accomodate the space left by Player
        } else if (direction.equals("a")) {
            replace.setX(replace.getX() + 1);
        // if the direction is down, we move replace up to accomodate the space left by Player
        } else if (direction.equals("s")) {
            replace.setY(replace.getY() + 1);
        // if the direction is right, we move replace left to accomodate the space left by Player
        } else if (direction.equals("d")) {
            replace.setX(replace.getX() - 1);
        // if the direction does not equal any direction-oriented letters, nothing is done
        } else {
            return;
        }
        // replace is placed in the previous spot of Player
        placeSprite(replace);
    }

    // prints out the current grid to the screen 
    public void display() {
        // a nested-for loop is used to print the grid, which is stored as a 2D array
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // if the object in the grid is a Player object, 😎 is printed
                if (grid[i][j] instanceof Player) {
                    System.out.print("😎");
                } else if (grid[i][j] instanceof Dot) {
                // if the object in the grid is a Dot object, 🟦 is printed
                    System.out.print("🟦");
                } else if (grid[i][j] instanceof Enemy) {
                // if the object in the grid is an Enemy object, 👺 is printed
                    System.out.print("👺");
                } else if (grid[i][j] instanceof Treasure && !(grid[i][j] instanceof Trophy)) {
                // if the object in the grid is a Treasure object and NOT a Trophy, 🏀 is printed
                    System.out.print("🏀");
                } else {
                // if the object in the grid is a Trophy object, 🥇 is printed
                    System.out.print("🥇");
                }
            }
            // making sure each row has the same amount of columns
            System.out.println();
        }
    }
    
    // what is displayed when a loss occurs
    public void gameover() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("👺");
            }
            System.out.println();
        }
        System.out.println("---LOST! GAME OVER!---");
    }

    // what is displayed when a win occurs
    public void win() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print("🏀");
            }
            System.out.println();
        }
        System.out.println("---LET'S GOOOOO! WWWWW!---");
    }
    }