package com.nyp.gameexample;

public class Player {

    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int newX, int newY){
        x += newX;
        y += newY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
