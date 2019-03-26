package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;

public class Ship extends Sprite {

    protected TextureRegion bulletRegion;
    protected Vector2 v = new Vector2();
    protected Vector2 bulletV = new Vector2();
    protected float bulletHeight;
    protected int damage;

   protected Sound shootSound;

    protected Rect worldBounds;
    protected BulletPool bulletPool;

    protected float reloadInterval;
    protected float reloadTimer;

    protected int hp;


    public Ship() {
    }

    public Ship(TextureRegion region, int rows, int cols, int frames) {
        super(region, rows, cols, frames);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }


    @Override
    public void update(float delta) {

        pos.mulAdd(v, delta);
    }

    public void shoot(){
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, damage);
        shootSound.play();
    }
}
