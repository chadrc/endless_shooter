package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Player implements IWorldObject {
    private float speed;
    private float reloadTimer;
    private float reloadTime;
    private Sprite sprite;

    public Player() {
        sprite = new Sprite(new Texture("Player-Ship.png"));
        sprite.setColor(0, 1, 0, 1);
        sprite.setSize(50, 50);
        sprite.setOrigin(sprite.getWidth()/2, sprite.getHeight()/2);
        World.AddWorldObject(this);
        reloadTime = 1;
        reloadTimer = 0;
    }

    private void shoot() {
        if (reloadTimer >= reloadTime)
        {
            Vector2 dir = new Vector2(MathUtils.cosDeg(sprite.getRotation()+90), MathUtils.sinDeg(sprite.getRotation()+90));
            Vector2 pos = new Vector2(dir.x, dir.y);
            pos.scl(sprite.getWidth()/2);
            pos.add(getPosition());
            PlayerBullet b = new PlayerBullet(pos.x, pos.y, 800, dir);
            b.setColor(0, 1, 0, 1);
            reloadTimer = 0;
        }
    }

    @Override
    public void hitOccurred(IWorldObject other) {

    }

    @Override
    public void update() {
        reloadTimer += Gdx.graphics.getDeltaTime();
        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        EndlessShooter.MainCamera.unproject(touchPos);
        float difX = sprite.getX() - touchPos.x;
        float difY = sprite.getY() - touchPos.y;
        float tan = MathUtils.atan2(difY, difX);
        sprite.setRotation(tan * MathUtils.radiansToDegrees + 90);

        if (Gdx.input.isTouched()) {
            shoot();
        }

        float delta = speed * Gdx.graphics.getDeltaTime();

        if ((Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                sprite.getY() + sprite.getHeight()/2 < EndlessShooter.ScreenHeight) {
            sprite.translateY(delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.S)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                sprite.getY() - sprite.getHeight()/2 > 0) {
            sprite.translateY(-delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.A)  || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                sprite.getX() - sprite.getWidth()/2 > 0) {
            sprite.translateX(-delta);
        }

        if ((Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyPressed(Input.Keys.UP)) &&
                sprite.getX() + sprite.getWidth()/2 < EndlessShooter.ScreenWidth) {
            sprite.translateX(delta);
        }
    }

    @Override
    public void render() {
        sprite.draw(EndlessShooter.GetMainBatch());
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX()+sprite.getWidth()/2, sprite.getY()+sprite.getHeight()/2);
    }

    @Override
    public float getHitRadius() {
        return sprite.getWidth()/2;
    }

    public void setX(float x) {
        sprite.setX(x - sprite.getWidth()/2);
    }

    public void setY(float y) {
        sprite.setY(y - sprite.getHeight()/2);
    }

    public float getX() {
        return sprite.getX() + sprite.getWidth()/2;
    }

    public float getY() {
        return sprite.getY() + sprite.getHeight()/2;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
