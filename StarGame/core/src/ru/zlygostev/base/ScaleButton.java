package ru.zlygostev.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Sprite;

public abstract class ScaleButton extends Sprite {

    private static final float PRESS_SCALE = 0.9f;

    private int pointer;
    private boolean pressed;

    public ScaleButton(TextureRegion region) {
        super(region);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if(pressed || !isTouched(touch)) {
            return false;
        }
        pressed = true;
        this.pointer = pointer;
        scale = PRESS_SCALE;
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !pressed) {
            return false;
        }
        if (isTouched(touch)) {
            actionPerformed();
        }
        pressed = false;
        scale = 1f;
        return false;
    }

    public abstract void actionPerformed();
}
