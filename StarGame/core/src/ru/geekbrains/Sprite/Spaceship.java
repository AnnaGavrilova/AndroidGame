package ru.geekbrains.Sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.Base.Sprite;
import ru.geekbrains.Math.Rect;

public class Spaceship extends Sprite {
    private Rect worldBounds;
    private static float V_LEN = 0.006f;

    private Vector2 pos;
    private Vector2 touch;
    private Vector2 v;
    private Vector2 buf;

    public Spaceship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(0.3f);
        this.pos = new Vector2();
        touch = new Vector2();
        v = new Vector2();
        buf = new Vector2();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        buf.set(touch);
        if (buf.sub(pos).len() <= V_LEN) {
            pos.set(touch);
        } else {
            pos.add(v);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
        float x = getHalfWidth();
        setLeft(x);
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        this.touch = touch;
        v = touch.cpy().sub(pos).setLength(V_LEN);
        return false;
    }
}
