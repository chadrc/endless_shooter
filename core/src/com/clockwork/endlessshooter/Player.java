package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Player implements IWorldObject {
    private float speed;
    private SpriteRenderer renderer;

    public Player() {
        renderer = new SpriteRenderer("badlogic.jpg");
        renderer.setSize(new Vector2(50, 50));
        World.AddWorldObject(this);
    }

    public void shoot() {
        Vector2 dir = new Vector2(MathUtils.cosDeg(renderer.getRotation()), MathUtils.sinDeg(renderer.getRotation()));
        new PlayerBullet(renderer.getPosition().x, renderer.getPosition().y, 800, dir);
    }

    @Override
    public void hitOccurred(IWorldObject other) {

    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(renderer.getPosition().x, renderer.getPosition().y);
    }

    @Override
    public float getHitRadius() {
        return renderer.getSize().x/2;
    }

    @Override
    public void update() {
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        EndlessShooter.MainCamera.unproject(touchPos);
        float difX = renderer.getPosition().x - touchPos.x;
        float difY = renderer.getPosition().y - touchPos.y;
        float tan = MathUtils.atan2(difY, difX);
        renderer.setRotation(tan * MathUtils.radiansToDegrees + 180);

        if (Gdx.input.isTouched()) {
            shoot();
        }

        float delta = speed * Gdx.graphics.getDeltaTime();

        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                renderer.getPosition().y + renderer.getSize().y/2 < EndlessShooter.ScreenHeight) {
            renderer.setY(renderer.getY() + delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.S)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                renderer.getPosition().y - renderer.getSize().y/2 > 0) {
            renderer.setY(renderer.getY() - delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.A)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                renderer.getPosition().x - renderer.getSize().x/2 > 0) {
            renderer.setX(renderer.getX() - delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                renderer.getPosition().x + renderer.getSize().x/2 < EndlessShooter.ScreenWidth) {
            renderer.setX(renderer.getX() + delta);
        }
    }

    @Override
    public void render() {
        renderer.render();
    }

    public void setX(float x) {
        renderer.setX(x);
    }

    public void setY(float y) {
        renderer.setY(y);
    }

    public float getX() {
        return renderer.getX();
    }

    public float getY() {
        return renderer.getY();
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
