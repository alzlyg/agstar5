package ru.zlygostev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    Texture imgbg;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
        imgbg = new Texture("background.jpg");
		region = new TextureRegion(img, 20, 20, 100, 100);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.1f, 0.3f,0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
        batch.draw(imgbg, 0, 0);
//		batch.draw(img, 100, 100);
//		batch.setColor(0.8f, 0.44f, 0.66f, 0.23f);
        batch.draw(region, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
