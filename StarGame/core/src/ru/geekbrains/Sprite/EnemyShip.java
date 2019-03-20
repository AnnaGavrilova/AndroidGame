package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;

public class EnemyShip extends Sprite {

    private Rect worldBounds;
    private int classShip; // тип корабля
    private Vector2 v;  // скорость корабля
    private int countLife; //количество жизней
    private int damage; //урон
    private Vector2 vBullet; //скорость пули

    public EnemyShip() {
        this.regions = new TextureRegion[2];
        this.v = new Vector2();
        this.vBullet = new Vector2();
    }

    public void set(
            TextureAtlas atlas,
            int classShip,
            Rect worldBounds,
            Vector2 v0,  // скорость корабля
            float hight, // размер корабля
            int countLife, // количество жизней
            int damage,
            Vector2 vBul
    ) {
        switch (classShip) {
            case 1:
                this.regions[0] = atlas.findRegion("enemy0");
                break;
            case 2:
                this.regions[0] = atlas.findRegion("enemy1");
                break;
            case 3:
                this.regions[0] = atlas.findRegion("enemy2");
                break;
        }
        this.worldBounds = worldBounds;
        this.v.set(v0);
        setHeightProportion(hight);
        this.countLife = countLife;
        this.damage = damage;
        this.vBullet.set(vBul);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setTop(worldBounds.getTop() + 0.05f);
        setLeft(Rnd.nextFloat(-1, 1));
    }
}
