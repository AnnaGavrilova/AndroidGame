package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Spaceship extends Sprite {

    private static  final int INVALID_POINTER = -1;
    private TextureAtlas atlas;

    private Rect worldBounds;

    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0.5f, 0);

    private boolean isPressedLeft;
    private boolean isPressedRight;

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;


    public Spaceship(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2 );
        this.atlas = atlas;
        setHeightProportion(0.15f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if(getRight() > worldBounds.getRight()){
            setRight(worldBounds.getRight());
            stop();
        }
        if(getLeft() < worldBounds.getLeft()){
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    public void keyDown(int keyCode){
        switch (keyCode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                isPressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                isPressedRight = true;
                moveRight();
                break;
        }
    }

    public void keyUp(int keyCode){
        switch (keyCode){
            case Input.Keys.LEFT:
            case Input.Keys.A:
                isPressedLeft = false;
                if(isPressedRight){
                    moveRight();
                }else{
                    stop();
                }
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                isPressedRight = false;
                if(isPressedLeft){
                    moveLeft();
                }else{
                    stop();
                }
                break;
        }
    }

    private void moveRight(){
        v.set(v0);
    }

    private  void moveLeft(){
        v.set(v0).rotate(180);
    }

    private void stop(){
        v.setZero();
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if(pointer == leftPointer){
            leftPointer = INVALID_POINTER;
            if(rightPointer != INVALID_POINTER){
                moveRight();
            }else{
                stop();
            }
        }else if(pointer == rightPointer){
            rightPointer = INVALID_POINTER;
            if(leftPointer != INVALID_POINTER){
                moveLeft();
            }else{
                stop();
            }
        }
        return false;
    }

    public boolean touchDown(Vector2 touch, int pointer) {
        if(touch.x < worldBounds.pos.x){
            if(leftPointer != INVALID_POINTER){
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        }else{
            if(rightPointer != INVALID_POINTER){
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    public void shoot(){

    }
}
