package com.example.crossycar;

/**
 * used as a interface for all interactions the player encounters
 * ex: Boats, Cows, Deer, etc...
 */
public class PlayerInteractions {
    private int x;
    private int y;
    //private int width, height;

    public PlayerInteractions() {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void setX(int x) { x = x; }

    public void setY(int y) { y = x; }
}
