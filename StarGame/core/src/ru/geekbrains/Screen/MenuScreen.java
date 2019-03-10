package ru.geekbrains.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture img;
    private Texture background;
    private Vector2 touch;
    private Vector2 pos;
    private Vector2 vector; //вектор движения
    private Vector2 v1; //скорость перемещения картинки
    private int step = 5; //шаг - количество пикселей
    private float count; // необходимое количество шагов


    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        background = new Texture("starSky.jpg");
        touch = new Vector2();
        pos = new Vector2(0, 0);
        v1 = new Vector2();
        vector = new Vector2();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        vector = touch.sub(pos);
        count = vector.len()/step;
        v1.set(vector.x/count, vector.y/count);
        pos.add(v1);
        if(pos.x == touch.x || pos.y == touch.y){
            v1.setZero();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        background.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        return false;
    }
}
