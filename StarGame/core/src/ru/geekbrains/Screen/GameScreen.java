package ru.geekbrains.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Base2DScreen;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Sprite.Background;
import ru.geekbrains.Sprite.Spaceship;
import ru.geekbrains.Sprite.Star;

public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 60;
    private Background background;
    private Texture backgroundTexture;
    private TextureAtlas atlas;
    private TextureAtlas atlasStar;
    private Star starList[];
    private Spaceship myShip;

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("starSky.jpg");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        atlasStar = new TextureAtlas("textures/menuAtlas.tpack");
        starList = new Star[STAR_COUNT];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlasStar);
        }
        myShip = new Spaceship(atlas);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        myShip.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        for (Star star : starList) {
            star.update(delta);
        }
    }

    private void draw() {
        Gdx.gl.glClearColor(0.51f, 0.34f, 0.64f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (Star star : starList) {
            star.draw(batch);
        }
        myShip.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        atlas.dispose();
        atlasStar.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        myShip.touchDown(touch, pointer);
        return false;
    }
}
