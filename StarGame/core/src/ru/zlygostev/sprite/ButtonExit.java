package ru.zlygostev.sprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.zlygostev.base.ScaleButton;
import ru.zlygostev.math.Rect;

public class ButtonExit extends ScaleButton {
    public ButtonExit(TextureAtlas atlas) {
        super(atlas.findRegion("btExit"));
        setHeightProportion(0.3f);
    }

    @Override
    public void actionPerformed() {
        Gdx.app.exit();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom()+0.05f);
        setRight(worldBounds.getRight()-0.05f);
    }
}
