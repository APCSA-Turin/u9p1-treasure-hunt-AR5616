package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite  {
    private int treasureCount;
    private int numLives;
    private boolean win;

    // player constructor, initializes its coordinates with the superclass, also initalizes treasureCount to 0, and numLives to 2
    public Player(int x, int y) {
        super(x, y);
        treasureCount = 0;
        numLives = 2;
    }

    // returns player's treasureCount
    public int getTreasureCount(){return treasureCount;}

    // returns player's numLives
    public int getLives(){return numLives;}

    // returns if player won or not
    public boolean getWin(){return win;}

    // returns the coordinates of Player by utilizing the method of the Sprite parent class
    @Override
    public String getCoords() { // returns "Player:"+coordinates
        return "Player:" + super.getCoords();
    }

    // overriden method returns the row and column of the Player utilizing a method from the parent class, Sprite
    @Override
    public String getRowCol(int size) { // returns "Player:"+row col
        return "Player:" + super.getRowCol(size);
    }
    
    // sets player's number of lives
    public void setNumLives(int newLives) {
        numLives = newLives;
    }
    
    // overriden method moves the Player in the intended direction
    @Override
    public void move(String direction) { // move the (x,y) coordinates of the player
        // if the direction is upward, the y-coordinate is increased by 1
        if (direction.equals("w")) {
            setY(getY() + 1);
        }
        // if the direction is to the left, the x-coordinate is decreased by 1
        if (direction.equals("a")) {
            setX(getX() - 1);
        }
        // if the direction is downward, the y-coordinate is decreased by 1
        if (direction.equals("s")) {
            setY(getY() - 1);
        }
        // if the direction is to the right, the x-coordinate is increased by 1
        if (direction.equals("d")) {
            setX(getX() + 1);
        }
    }

    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    // numTreasures is the total treasures at the beginning of the game
        if (isValid(size, direction)) {
            // if the object is an Enemy, one life is lost
            if (obj instanceof Enemy) {
                numLives--;
            // if the object is a trophy, we must check if player has the correct amount of treasures before officially winning
            } else if (obj instanceof Trophy) {
                if (treasureCount == numTreasures) {
                    win = true;
                }
            // if the object is a treasure that is not a trophy, we add one to the player's treasure count
            } else if (obj instanceof Treasure) {
                treasureCount++;
            }
        }
    }

    public boolean isValid(int size, String direction) { // check grid boundaries
        // utilizing early returns for each direction
        // w is up, adding 1 to the y-coordinate
        if (direction.equals("w")) {
            if (getY() + 1 < size) {
                return true;
            }
        }
        // a is to the left, subtracting 1 from the x-coordinate
        if (direction.equals("a")) {
            if (getX() - 1 >= 0) {
                return true;
            }
        }
        // s is down, subtracting 1 from the y-coordinate
        if (direction.equals("s")) {
            if (getY() - 1 >= 0) {
                return true;
            }
        }
        // d is to the right, adding 1 to the x-coordinate
        if (direction.equals("d")) {
            if (getX() + 1 < size) {
                return true;
            }
        }
        // if an invalid direction is inputted or the move will be out of bounds, we return false to indicate it is not a valid move
        return false;
    }
}