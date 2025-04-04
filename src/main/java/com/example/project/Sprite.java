package com.example.project;

public class Sprite {
    private int x, y;

    // constructs a Sprite with its x and y coordinates
    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // returns the x coordinate of the Sprite
    public int getX(){return x;}

    // returns the y coordinate of the Sprite
    public int getY(){return y;}

    // sets the x coordinate to newX
    public void setX(int newX){x = newX;}

    // sets the y coordinate to newY
    public void setY(int newY){y = newY;}

    // returns the coordinates of the sprite ->"(x,y)"
    public String getCoords() {
        return ("(" + x + "," + y + ")");
    }

    // returns the row and column of the sprite -> "[row][col]"
    public String getRowCol(int size) {
        return "[" + (size - y - 1) + "]" + "[" + x + "]";
    }
    
    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }
}