package com.nyp.gameexample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GDXExampleGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture playerStandingSouth;
	Player player;
	PlayerInput playerInput;
	private int x;
	private int y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		playerStandingSouth = new Texture("me.png");
		player = new Player(0, 0);
		playerInput = new PlayerInput(player);
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		Gdx.input.setInputProcessor(playerInput);
		batch.draw(playerStandingSouth, player.getX(), player.getY());
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		playerStandingSouth.dispose();
	}

}
