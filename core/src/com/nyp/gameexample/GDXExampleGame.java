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
	SpriteBatch batch;
	Texture playerStandingSouth;
	PlayerInput playerInput;
	private int x;
	private int y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		character = new Texture("Character/brendan_stand_south.png");
		player = new Player(1, 1);
		playerInput = new PlayerInput(player);
	}

	@Override
	public void render () {
		ScreenUtils.clear(1f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
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
