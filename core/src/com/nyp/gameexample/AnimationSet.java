package com.nyp.gameexample;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.nyp.gameexample.ENUM.DIRECTION;

import java.util.HashMap;
import java.util.Map;

public class AnimationSet {



    private Map<DIRECTION, Animation> walking;
    private Map<DIRECTION, Animation> running;
    private Map<DIRECTION, TextureRegion> standing;
    private Map<DIRECTION, Animation> biking;

    public AnimationSet(Animation walkNorth,
                        Animation walkSouth,
                        Animation walkEast,
                        Animation walkWest,
                        TextureRegion standNorth,
                        TextureRegion standSouth,
                        TextureRegion standEast,
                        TextureRegion standWest) {
        walking = new HashMap<DIRECTION, Animation>();
        walking.put(DIRECTION.NORTH, walkWest);
        walking.put(DIRECTION.SOUTH, walkEast);
        walking.put(DIRECTION.EAST, walkNorth);
        walking.put(DIRECTION.WEST, walkSouth);
        standing = new HashMap<DIRECTION, TextureRegion>();
        standing.put(DIRECTION.NORTH, standWest);
        standing.put(DIRECTION.SOUTH, standEast);
        standing.put(DIRECTION.EAST, standNorth);
        standing.put(DIRECTION.WEST, standSouth);
    }

    public Animation getWalking(DIRECTION dir) {
        return walking.get(dir);
    }

    public TextureRegion getStanding(DIRECTION dir) {
        return standing.get(dir);
    }

}
