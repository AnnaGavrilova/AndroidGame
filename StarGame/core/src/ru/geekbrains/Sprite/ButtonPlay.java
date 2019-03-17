package ru.geekbrains.Sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.Base.ScaledButton;
import ru.geekbrains.Math.Rect;
import ru.geekbrains.Screen.GameScreen;

public class ButtonPlay extends ScaledButton {

    private Game game;

    public ButtonPlay(TextureAtlas atlas, Game game) {
        super(atlas.findRegion("btPlay"));
        this.game = game;
        setHeightProportion(0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom() + 0.02f);
        setRight(worldBounds.getRight() - 0.02f);
    }

    @Override
    protected void action() {
        game.setScreen(new GameScreen());
    }
}
