package ru.zlygostev;

import com.badlogic.gdx.Game;

import ru.zlygostev.screen.MenuScreen;

public class Star2DGame extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));

    }
}
