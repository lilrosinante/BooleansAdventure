package com.nyp.gameexample.model;

import com.nyp.gameexample.ENUM.TERRAIN;
import com.nyp.gameexample.Player;

public class Tile {

    private TERRAIN terrain;
    private Player player;

    public Tile (TERRAIN terrain){
        this.terrain = terrain;
    }

    public TERRAIN getTerrain() {
        return terrain;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}
