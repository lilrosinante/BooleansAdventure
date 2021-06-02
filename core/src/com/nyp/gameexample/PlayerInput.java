package com.nyp.gameexample;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class PlayerInput extends InputAdapter {

    private Player player;

    public PlayerInput(Player p) {
        this.player = p;
    }

    public boolean keyDown(int keyCode){

        if (keyCode == Keys.UP){
            player.move(0, 1);
        }
        if (keyCode == Keys.DOWN){
            player.move(0, -1);
        }
        if (keyCode == Keys.LEFT){
            player.move(-1, 0);
        }
        if (keyCode == Keys.RIGHT){
            player.move(1, 0);
        }

        return false;
    }

}
