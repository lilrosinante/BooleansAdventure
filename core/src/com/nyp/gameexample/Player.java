package com.nyp.gameexample;

import com.nyp.gameexample.model.TileMap;

public class Player {

    private TileMap map;
    private int x;
    private int y;

    public Player(TileMap map, int x, int y) {
        this.map = map;
        this.x = x;
        this.y = y;
        map.getTile(x, y).setPlayer(this);
    }

    public boolean move(int newX, int newY) {
        if (x + newX >= map.getWidth() || x + newX < 0 || y + newY >= map.getHeight() || y + newY < 0) {
            return false;
        }

        if (map.getTile(x + newX, y + newY).getPlayer() != null) {
            return false;
        }
        map.getTile(x, y).setPlayer(null);
        x += newX;
        y += newY;
        map.getTile(x, y).setPlayer(this);
        return true;
    }

    public void render () {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
