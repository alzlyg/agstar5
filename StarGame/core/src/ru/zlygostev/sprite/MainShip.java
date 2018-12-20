package ru.zlygostev.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Sprite;
import ru.zlygostev.math.Rect;
import ru.zlygostev.pool.BulletPool;

public class MainShip extends Sprite {

    private Vector2 v0 = new Vector2(0.5f, 0);
    private Vector2 v = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;
    private boolean pressedShoot;

    private BulletPool bulletPool;

    private TextureAtlas atlas;

    private Sound soundShoot;

    private Rect worldBounds;

    private Rect regionButtonLeft = new Rect();
    private Rect regionButtonRight = new Rect();
    private Rect regionButtonShoot = new Rect();

    public MainShip(TextureAtlas atlas, BulletPool bulletPool, Sound soundShoot) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(0.3f);
        this.bulletPool = bulletPool;
        this.atlas = atlas;
        this.soundShoot = soundShoot;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        if (this.getLeft() < worldBounds.getLeft()) {
            this.setLeft(worldBounds.getLeft());
        }
        if (this.getRight() > worldBounds.getRight()) {
            this.setRight(worldBounds.getRight());
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.15f);
        regionButtonLeft.setSize(worldBounds.getWidth()/3, worldBounds.getHeight());
        regionButtonLeft.setLeft(worldBounds.getLeft());
        regionButtonLeft.setBottom(worldBounds.getBottom());
        regionButtonRight.setSize(worldBounds.getWidth()/3, worldBounds.getHeight());
        regionButtonRight.setRight(worldBounds.getRight());
        regionButtonRight.setBottom(worldBounds.getBottom());
        regionButtonShoot.setSize(worldBounds.getWidth()/3, worldBounds.getHeight());
        regionButtonShoot.setLeft(worldBounds.getLeft()+worldBounds.getWidth()/3);
        regionButtonShoot.setBottom(worldBounds.getBottom());
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (regionButtonLeft.isTouched(touch)) {
            pressedLeft = true;
            moveLeft();
        }
        if (regionButtonRight.isTouched(touch)) {
            pressedRight = true;
            moveRight();
        }
        if (regionButtonShoot.isTouched(touch)) {
            pressedShoot = true;
            frame = 1;
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pressedLeft) {
            pressedLeft = false;
            stop();
        }
        if (pressedRight) {
            pressedRight = false;
            stop();
        }
        if (pressedShoot) {
            pressedShoot = false;
            frame = 0;
            shoot();
        }
        return super.touchUp(touch, pointer);
    }

    public boolean keyDown(int keycode) {
        switch(keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                frame = 1;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch(keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;
            case Input.Keys.UP:
                frame = 0;
                shoot();
                break;
        }
        return false;
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void stop() {
        v.setZero();
    }

    public void shoot() {
        Bullet bullet = bulletPool.obtain();
        long id = soundShoot.play(1.0f);
        bullet.set(
                this,
                atlas.findRegion("bulletMainShip"),
                pos,
                new Vector2(0, 0.5f),
                0.01f,
                worldBounds,
                1);
    }

}
