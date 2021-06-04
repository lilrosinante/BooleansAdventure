package com.nyp.gameexample;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.nyp.gameexample.ENUM.DIRECTION;

public class PlayerInput extends InputAdapter {

    private Player player;

    public PlayerInput(Player p) {
        this.player = p;
    }

    public boolean keyDown(int keyCode){

        if (keyCode == Keys.UP){
            player.move(-1, 0, DIRECTION.NORTH);
        }
        if (keyCode == Keys.DOWN){
            player.move(1, 0, DIRECTION.SOUTH);
        }
        if (keyCode == Keys.LEFT){
            player.move(0, -1, DIRECTION.WEST);
        }
        if (keyCode == Keys.RIGHT){
            player.move(0, 1, DIRECTION.EAST);
        }

        return false;
    }

}
