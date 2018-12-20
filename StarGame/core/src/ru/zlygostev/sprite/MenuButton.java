package ru.zlygostev.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.zlygostev.base.Sprite;
import ru.zlygostev.math.Rect;
import ru.zlygostev.math.Rnd;

public class MenuButton extends Sprite {

    private static final float DECREASE_SIZE = 0.9f;
    boolean buttonTouched;

    public MenuButton(TextureAtlas atlas, String atlasName, float posX, float posY, float height) {
        super(atlas.findRegion(atlasName));
        setHeightProportion(height);
        pos.set(posX, posY);
        buttonTouched = false;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void isTouchedDown(Vector2 touch) {
        if (isTouched(touch)) {
            setScale(getScale()*DECREASE_SIZE);
            buttonTouched = true;

            System.out.println("buttonTouched = true");
        }
    }

    public boolean isTouchedUp(Vector2 touch) {
        if (buttonTouched) {
            setScale(getScale()/DECREASE_SIZE);
            buttonTouched = false;
            if (isTouched(touch)) {
                return true;
            }
        }
        return false;
    }

}
