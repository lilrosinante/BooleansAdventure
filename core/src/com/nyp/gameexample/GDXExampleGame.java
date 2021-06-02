package com.nyp.gameexample;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GDXExampleGame extends ApplicationAdapter {

	SpriteBatch batch;
	Texture img;
	private int x;
	private int y;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();
		batch.draw(img, x, y);
		move();
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void move(){
		x++;
		y++;
	}
}
