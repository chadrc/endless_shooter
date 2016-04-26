package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Player implements IWorldObject {
    private Texture img;
    private Rectangle rectangle;
    private float speed;
    private float rotation;

    public Player() {
        img = new Texture("badlogic.jpg");
        rectangle = new Rectangle();
        rectangle.width = 50;
        rectangle.height = 50;
    }

    public void shoot() {
        Vector2 dir = new Vector2(MathUtils.cosDeg(rotation), MathUtils.sinDeg(rotation));
        new Bullet(rectangle.x, rectangle.y, 10, dir);
    }

    @Override
    public void hitOccurred(IWorldObject other) {

    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(rectangle.x, rectangle.y);
    }

    @Override
    public float getHitRadius() {
        return rectangle.width/2;
    }

    @Override
    public void update() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        EndlessShooter.MainCamera.unproject(touchPos);
        float difX = rectangle.x - touchPos.x;
        float difY = rectangle.y - touchPos.y;
        float tan = MathUtils.atan2(difY, difX);
        rotation = tan * MathUtils.radiansToDegrees + 180;

        if (Gdx.input.isTouched()) {
            shoot();
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                rectangle.y + rectangle.height/2 < EndlessShooter.ScreenHeight) {
            rectangle.y += speed;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.S)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                rectangle.y - rectangle.height/2 > 0) {
            rectangle.y -= speed;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.A)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                rectangle.x - rectangle.width/2 > 0) {
            rectangle.x -= speed;
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                rectangle.x + rectangle.width/2 < EndlessShooter.ScreenWidth) {
            rectangle.x += speed;
        }
    }

    @Override
    public void render() {
        float cx = rectangle.x - rectangle.width/2;
        float cy = rectangle.y - rectangle.height/2;
        EndlessShooter.Batch.begin();
        EndlessShooter.Batch.draw(img, cx, cy,
                rectangle.width/2, rectangle.height/2,
                rectangle.width, rectangle.height,
                1, 1,
                rotation,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
        EndlessShooter.Batch.end();
    }

    public void setX(float x) {
        rectangle.x = x;
    }

    public void setY(float y) {
        rectangle.y = y;
    }

    public float getX() {
        return rectangle.x;
    }

    public float getY() {
        return rectangle.y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
