package com.nyp.gameexample;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.nyp.gameexample.ENUM.ACTOR_STATE;
import com.nyp.gameexample.ENUM.DIRECTION;
import com.nyp.gameexample.model.TileMap;

public class Player {

    private float worldX, worldY;
    private int srcX, srcY;
    private int destX, destY;
    private float animationTimer;
    private float ANIMATION_TIME = 0.2f;
    private ACTOR_STATE state;
    private TileMap map;
    private int x;
    private int y;
    private AnimationSet animations;
    private DIRECTION facing;
    private boolean moveRequestThisFrame;
    private float walktimer;
    private Sound sound;

    public Player(TileMap map, int x, int y, AnimationSet animations) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.animations = animations;
        this.state = ACTOR_STATE.STANDING;
        map.getTile(x, y).setPlayer(this);
        facing = DIRECTION.SOUTH;
        this.sound = sound;
    }

  public boolean move(int newX, int newY, DIRECTION direction) {

        if(state == ACTOR_STATE.WALKING){
            if(facing == direction){
                moveRequestThisFrame = true;
            }
            return false;
        }
        
        if (x + newX >= map.getWidth() || x + newX < 0 || y + newY >= map.getHeight() || y + newY < 0) {
            return false;
        }

        if (map.getTile(x + newX, y + newY).getPlayer() != null) {
            return false;
        }

        initializeMove(x, y, newX, newY, direction);

        map.getTile(x, y).setPlayer(null);
        x += newX;
        y += newY;
        map.getTile(x, y).setPlayer(this);
        return true;
    }

    public void update(float delta){
        if(state == ACTOR_STATE.WALKING){
            animationTimer += delta;
            walktimer += delta;
            worldX = Interpolation.linear.apply(srcX, destX, animationTimer/ANIMATION_TIME);
            worldY = Interpolation.linear.apply(srcY, destY, animationTimer/ANIMATION_TIME);
            if (animationTimer > ANIMATION_TIME){
                float leftOverTime = animationTimer-ANIMATION_TIME;
                walktimer -= leftOverTime;
                finishMove();
                if (moveRequestThisFrame){
                    if (facing == DIRECTION.NORTH){
                        move(-1, 0, DIRECTION.NORTH);
                    } else if (facing == DIRECTION.SOUTH){
                        move(1, 0, DIRECTION.SOUTH);
                    } else if (facing == DIRECTION.WEST){
                        move(0, -1, DIRECTION.WEST);
                    } else if (facing == DIRECTION.EAST){
                        move(0, 1, DIRECTION.EAST);
                    }
                } else {
                    walktimer = 0f;
                }
            }
        }
        moveRequestThisFrame = false;
    }

    private void initializeMove(int oldX, int oldY, int newX, int newY, DIRECTION facing){
        this.facing = facing;
        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = oldX+newX;
        this.destY = oldY+newY;
        this.worldX = oldX;
        this.worldY = oldY;
        animationTimer = 0f;
        state = ACTOR_STATE.WALKING;
    }
  
    public void render () {
    }

    private void finishMove(){
        state = ACTOR_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    public TextureRegion getSprite(){
        if(state == ACTOR_STATE.WALKING){
            return (TextureRegion) animations.getWalking(facing).getKeyFrame(walktimer);
        } else if(state == ACTOR_STATE.STANDING){
            return animations.getStanding(facing);
        }
        return animations.getStanding(DIRECTION.SOUTH);
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
