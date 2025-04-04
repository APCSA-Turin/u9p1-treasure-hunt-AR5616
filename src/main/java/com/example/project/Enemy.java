package com.example.project;

public class Enemy extends Sprite { // child  of Sprite
    
    // constructs an Enemy object using the super constructor, inheriting from Sprite
    public Enemy(int x, int y) {
        super(x, y);
    }

    // overriden method returns the coordinates of the enemy utilizing a method from the parent class, Sprite
    @Override
    public String getCoords() { // returns "Enemy:"+coordinates
        return "Enemy:" + super.getCoords();
    }

    // overriden method returns the row and column of the enemy utilizing a method from the parent class, Sprite
    @Override
    public String getRowCol(int size) { // returns "Enemy:"+row col
        return "Enemy:" + super.getRowCol(size);
    }
}