package ru.zlygostev.pool;

import ru.zlygostev.base.SpritesPool;
import ru.zlygostev.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}
