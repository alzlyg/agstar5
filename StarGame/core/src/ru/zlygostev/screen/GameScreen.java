package ru.zlygostev.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Base2DScreen;
import ru.zlygostev.math.Rect;
import ru.zlygostev.pool.BulletPool;
import ru.zlygostev.sprite.Background;
import ru.zlygostev.sprite.MainShip;
import ru.zlygostev.sprite.Star;

public class GameScreen extends Base2DScreen {

    private Texture bg;
    private TextureAtlas textureAtlas;
    private TextureAtlas textureAtlasStar;
    private Background background;

    private Star[] star;
    private static final int STAR_COUNT = 64;

    private MainShip mainShip;

    private BulletPool bulletPool;

    private Sound soundShoot;
    private Music music;

    public GameScreen(Game game) { super(game); }

    @Override
    public void show() {
        super.show();
        textureAtlasStar = new TextureAtlas("textures/menuAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(textureAtlasStar);
        }

        textureAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        bg = new Texture("textures/background2.jpg");
        background = new Background(new TextureRegion(bg));

        bulletPool = new BulletPool();
        soundShoot = Gdx.audio.newSound(Gdx.files.internal("sounds/shoot.wav"));
        mainShip = new MainShip(textureAtlas, bulletPool, soundShoot);


        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/europe.mp3"));
        music.setVolume(0.25f);
        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestoyed();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveSprites(delta);
    }

    public void checkCollisions() {

    }

    public void deleteAllDestoyed() {
        bulletPool.freeAllDestoyedActiveSprites();
    }

    public void draw() {
        Gdx.gl.glClearColor(0.0f, 0.0f,0.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveSprites(batch);
        batch.end();
    }




    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        textureAtlas.dispose();
        textureAtlasStar.dispose();
        bulletPool.dispose();
        soundShoot.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }
}
