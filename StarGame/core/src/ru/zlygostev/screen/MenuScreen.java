package ru.zlygostev.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Base2DScreen;
import ru.zlygostev.math.Rect;
import ru.zlygostev.sprite.Background;
import ru.zlygostev.sprite.ButtonExit;
import ru.zlygostev.sprite.ButtonPlay;
import ru.zlygostev.sprite.Star;

public class MenuScreen extends Base2DScreen {
    private Texture bg;
    private TextureAtlas textureAtlas;

    private Background background;

    private Star[] star;
    private static final int STAR_COUNT = 256;

    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;

    private Vector2 pos;
    private Vector2 v;
    private Vector2 direct;
    private Vector2 posDist;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        textureAtlas = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(textureAtlas);
        }

        buttonExit = new ButtonExit(textureAtlas);
        buttonPlay = new ButtonPlay(textureAtlas, game);


        bg = new Texture("textures/background2.jpg");
        background = new Background(new TextureRegion(bg));

        pos = new Vector2(0,0);
        posDist = new Vector2(pos);
        v = new Vector2(0,0);
        direct = new Vector2(0,0);

        System.out.println("posDist touch X = " + posDist.x + " touch Y = " + posDist.y);
        System.out.println("pos touch X = " + pos.x + " touch Y = " + pos.y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw(delta);
    }



    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
    }

    public void draw(float draw) {
        Gdx.gl.glClearColor(0.0f, 0.0f,0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);

        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
    }

    @Override
    public void dispose() {
        textureAtlas.dispose();
        bg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        posDist.set(touch);
        buttonExit.touchDown(touch, pointer);
        buttonPlay.touchDown(touch,pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonPlay.touchUp(touch,pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        return false;
    }
}
