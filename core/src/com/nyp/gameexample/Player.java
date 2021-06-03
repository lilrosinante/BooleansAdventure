package com.nyp.gameexample;

import com.badlogic.gdx.math.Interpolation;

public class Player {

    private float worldX, worldY;

    private int srcX, srcY;
    private int destX, destY;

    private float animationTimer;
    private float ANIMATION_TIME = 0.5f;

    private ACTOR_STATE state;

    private int x;
    private int y;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.worldX = x;
        this.worldY = y;
        this.state = ACTOR_STATE.STANDING;
    }

    public boolean move(int newX, int newY){
        if(state != ACTOR_STATE.STANDING){
            return false;
        }
        initializeMove(x, y, newX, newY);
        x += newX;
        y += newY;

        return true;
    }

    public void update(float delta){
        if(state == ACTOR_STATE.WALKING){
            animationTimer += delta;
            worldX = Interpolation.pow2.apply(srcX, destX, animationTimer/ANIMATION_TIME);
            worldY = Interpolation.pow2.apply(srcY, destY, animationTimer/ANIMATION_TIME);
            if (animationTimer > ANIMATION_TIME){
                finishMove();
            }
        }
    }

    private void initializeMove(int oldX, int oldY, int newX, int newY){
        this.srcX = oldX;
        this.srcY = oldY;
        this.destX = newX;
        this.destY = newY;
        this.worldX = oldX;
        this.worldY = oldY;
        animationTimer = 0f;
        state = ACTOR_STATE.WALKING;
    }

    private void finishMove(){
        state = ACTOR_STATE.STANDING;
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
