package com.nyp.gameexample;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GDXExampleGame extends Game {

	private Player player;
	private Texture character;
	private Camera camera;
	SpriteBatch batch;
	PlayerInput playerInput;
	
	@Override
	public void create () {
		camera = new Camera();
		batch = new SpriteBatch();
		character = new Texture("Character/brendan_stand_south.png");
		player = new Player(1, 1);
		playerInput = new PlayerInput(player);
	}

	@Override
	public void render () {
		player.update(Gdx.graphics.getDeltaTime());
		ScreenUtils.clear(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update(player.getWorldX(), player.getWorldY());

		float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
		float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;

		batch.begin();
		Gdx.input.setInputProcessor(playerInput);
		batch.draw(character,
				worldStartX + player.getWorldX()*Settings.SCALED_TILE_SIZE,
				worldStartY + player.getWorldY()*Settings.SCALED_TILE_SIZE,
				Settings.SCALED_TILE_SIZE,
				Settings.SCALED_TILE_SIZE*1.5f);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		character.dispose();
	}

}
