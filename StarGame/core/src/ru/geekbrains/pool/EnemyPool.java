package ru.geekbrains.pool;

import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyPool extends SpritesPool<EnemyShip> {

    private BulletPool bulletPool;
    private Sound shootSound;
    private Rect worldBounds;


    public EnemyPool(BulletPool bulletPool, Rect worldBounds, Sound shootSound){
        this.bulletPool = bulletPool;
        this.worldBounds = worldBounds;
        this.shootSound = shootSound;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, shootSound, worldBounds);
    }
}
