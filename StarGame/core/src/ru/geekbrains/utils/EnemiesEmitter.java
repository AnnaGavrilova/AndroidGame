package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyPool;
import ru.geekbrains.sprite.EnemyShip;

public class EnemiesEmitter {

    private static final float ENEMY_SMALL_HEIGHT = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT = 0.01f;
    private static final float ENEMY_SMALL_BULLET_VY = -0.3f;
    private static final int ENEMY_SMALL_DAMAGE = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_SMALL_HP = 1;

    private static final float ENEMY_AVERAGE_HEIGHT = 0.2f;
    private static final float ENEMY_AVERAGE_BULLET_HEIGHT = 0.02f;
    private static final float ENEMY_AVERAGE_BULLET_VY = -0.4f;
    private static final int ENEMY_AVERAGE_DAMAGE = 2;
    private static final float ENEMY_AVERAGE_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_AVERAGE_HP = 2;

    private static final float ENEMY_BIG_HEIGHT = 0.3f;
    private static final float ENEMY_BIG_BULLET_HEIGHT = 0.03f;
    private static final float ENEMY_BIG_BULLET_VY = -0.3f;
    private static final int ENEMY_BIG_DAMAGE = 3;
    private static final float ENEMY_BIG_RELOAD_INTERVAL = 3f;
    private static final int ENEMY_BIG_HP = 3;


    private Rect worldBounds;

    private float generateInterval = 4f;
    private float generateTimer;

    private TextureRegion[] enemySmallRegion;
    private TextureRegion[] enemyAverageRegion;
    private TextureRegion[] enemyBigRegion;

    private Vector2 enemySmallV = new Vector2(0f, -0.2f);
    private Vector2 enemyAverageV = new Vector2(0f, -0.3f);
    private Vector2 enemyBigV = new Vector2(0f, -0.4f);

    private TextureRegion bulletRegion;

    private EnemyPool enemyPool;

    private int rndClassEnemyShip;

    public EnemiesEmitter(TextureAtlas atlas, Rect worldBounds, EnemyPool enemyPool) {
        this.worldBounds = worldBounds;
        this.enemyPool = enemyPool;
        rndClassEnemyShip = (int) (Math.random() * 3);
        switch (rndClassEnemyShip) {
            case 1:
                TextureRegion textureRegion0 = atlas.findRegion("enemy0");
                this.enemySmallRegion = Regions.split(textureRegion0, 1, 2, 2);
                break;
            case 2:
                TextureRegion textureRegion1 = atlas.findRegion("enemy1");
                this.enemyAverageRegion = Regions.split(textureRegion1, 1, 2, 2);
                break;
            case 3:
                TextureRegion textureRegion2 = atlas.findRegion("enemy2");
                this.enemyBigRegion = Regions.split(textureRegion2, 1, 2, 2);
                break;
        }
        this.bulletRegion = atlas.findRegion("buleltEnemy");
    }

    public void generate(float delta) {
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            EnemyShip enemyShip = enemyPool.obtain();
            switch (rndClassEnemyShip) {
                case 1:
                    enemyShip.set(
                            enemySmallRegion,
                            enemySmallV,
                            bulletRegion,
                            ENEMY_SMALL_BULLET_HEIGHT,
                            ENEMY_SMALL_BULLET_VY,
                            ENEMY_SMALL_DAMAGE,
                            ENEMY_SMALL_RELOAD_INTERVAL,
                            ENEMY_SMALL_HEIGHT,
                            ENEMY_SMALL_HP);
                    break;
                case 2:
                    enemyShip.set(
                            enemyAverageRegion,
                            enemyAverageV,
                            bulletRegion,
                            ENEMY_AVERAGE_BULLET_HEIGHT,
                            ENEMY_AVERAGE_BULLET_VY,
                            ENEMY_AVERAGE_DAMAGE,
                            ENEMY_AVERAGE_RELOAD_INTERVAL,
                            ENEMY_AVERAGE_HEIGHT,
                            ENEMY_AVERAGE_HP);
                    break;
                case 3:
                    enemyShip.set(
                            enemyBigRegion,
                            enemyBigV,
                            bulletRegion,
                            ENEMY_BIG_BULLET_HEIGHT,
                            ENEMY_BIG_BULLET_VY,
                            ENEMY_BIG_DAMAGE,
                            ENEMY_BIG_RELOAD_INTERVAL,
                            ENEMY_BIG_HEIGHT,
                            ENEMY_BIG_HP);
                    break;
            }
            enemyShip.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemyShip.getHalfWidth(), worldBounds.getRight() - enemyShip.getHalfWidth());
            enemyShip.setBottom(worldBounds.getTop());
        }
    }
}
