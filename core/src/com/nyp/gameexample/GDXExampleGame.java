package com.nyp.gameexample;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.nyp.gameexample.ENUM.TERRAIN;
import com.nyp.gameexample.model.TileMap;

public class GDXExampleGame extends Game {

	private Player player;
	private Texture character;
	private Texture grass1;
	private Texture grass2;
	private TileMap map;
	SpriteBatch batch;
	PlayerInput playerInput;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		character = new Texture("Character/brendan_stand_south.png");
		map = new TileMap(38, 23);
		player = new Player(map,1, 1);
		grass1 = new Texture ("Grass/Pokemon_Grass_1.png");
		grass2 = new Texture ("Grass/Pokemon_Grass_2.png");
		playerInput = new PlayerInput(player);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		for ( int x = 0; x < map.getWidth(); x++) {
			for (int y = 0; y < map.getHeight(); y++) {
				Texture render;
				if (map.getTile(x, y).getTerrain() == TERRAIN.GRASS_1) {
					render = grass1;
				} else {
					render = grass2;
				}
				batch.draw(render, x*Settings.SCALED_TILE_SIZE, y*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE);
			}
		}

		Gdx.input.setInputProcessor(playerInput);
		batch.draw(character, player.getX()*Settings.SCALED_TILE_SIZE, player.getY()*Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE*1.5f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		character.dispose();
	}

}
