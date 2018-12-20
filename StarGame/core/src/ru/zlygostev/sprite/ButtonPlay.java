package ru.zlygostev.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.zlygostev.base.ScaleButton;
import ru.zlygostev.math.Rect;
import ru.zlygostev.screen.GameScreen;

public class ButtonPlay extends ScaleButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        setHeightProportion(0.5f);
        this.game = game;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        pos.set(0, 0);
    }

    @Override
    public void actionPerformed() {
        game.setScreen(new GameScreen(game));
    }
}
