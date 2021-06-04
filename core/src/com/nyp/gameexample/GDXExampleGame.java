package com.nyp.gameexample;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nyp.gameexample.ENUM.TERRAIN;
import com.nyp.gameexample.model.TileMap;

public class GDXExampleGame extends Game {

    private TextureAtlas textureAtlas;
    private Player player;
    private Texture character;
    private Camera camera;
    private Texture grass1;
    private Texture grass2;
    private TileMap map;
    SpriteBatch batch;
    PlayerInput playerInput;

    @Override
    public void create() {
        camera = new Camera();
        batch = new SpriteBatch();
        character = new Texture("Character/brendan_stand_south.png");
        map = new TileMap(38, 23);
        grass1 = new Texture("Grass/Pokemon_Grass_1.png");
        grass2 = new Texture("Grass/Pokemon_Grass_2.png");
        textureAtlas = new TextureAtlas("tilepack.atlas");

        SoundPlayer soundPlayer = new SoundPlayer();

        AnimationSet animations = new AnimationSet(
                new Animation(0.3f / 2f, textureAtlas.findRegions("brendan_walk_north"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f / 2f, textureAtlas.findRegions("brendan_walk_south"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f / 2f, textureAtlas.findRegions("brendan_walk_east"), Animation.PlayMode.LOOP_PINGPONG),
                new Animation(0.3f / 2f, textureAtlas.findRegions("brendan_walk_west"), Animation.PlayMode.LOOP_PINGPONG),
                textureAtlas.findRegion("brendan_stand_north"),
                textureAtlas.findRegion("brendan_stand_south"),
                textureAtlas.findRegion("brendan_stand_east"),
                textureAtlas.findRegion("brendan_stand_west")
                );

        player = new Player(map, 1, 1, animations);
        playerInput = new PlayerInput(player);

        soundPlayer.getPaletteTownTheme().setLooping(true);
        soundPlayer.getPaletteTownTheme().play();
    }

    @Override
    public void render() {

        player.update(Gdx.graphics.getDeltaTime());
        camera.update(player.getWorldX() + 0.5f, player.getWorldY() + 0.5f);
        ScreenUtils.clear(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float worldStartX = Gdx.graphics.getWidth() / 2f - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2f - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

        batch.begin();

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Texture render;
                if (map.getTile(x, y).getTerrain() == TERRAIN.GRASS_1) {
                    render = grass1;
                } else {
                    render = grass2;
                }
                batch.draw(render,
                        worldStartX + x * Settings.SCALED_TILE_SIZE,
                        worldStartY + y * Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE);
            }
        }

        Gdx.input.setInputProcessor(playerInput);
        batch.draw(player.getSprite(),
                worldStartX + player.getWorldX() * Settings.SCALED_TILE_SIZE,
                worldStartY + player.getWorldY() * Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE,
                Settings.SCALED_TILE_SIZE * 1.5f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        character.dispose();
    }
}
