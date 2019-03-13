package ru.geekbrains.Sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.Base.Sprite;

public class Background extends Sprite {

    private TextureRegion region;

    public Background(TextureRegion region) {
        super(region);
        this.region = region;
    }

    @Override
    public void draw(SpriteBatch batch) {
        batch.draw(region, -50f, -50f );
    }
}
