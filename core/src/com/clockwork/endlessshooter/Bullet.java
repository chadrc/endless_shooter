package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/25/2016.
 */
class Bullet implements IWorldObject {
    private float speed;
    private Vector2 direction;
    private Sprite sprite;

    Bullet(float x, float y, float speed, Vector2 direction) {
        direction = direction.nor();
        sprite = new Sprite(Assets.GetBasicBulletTexture());
        sprite.setSize(10, 10);
        sprite.setX(x-sprite.getWidth()/2);
        sprite.setY(y-sprite.getHeight()/2);
        this.speed = speed;
        this.direction = direction;
        EndlessShooter.RegisterWorldObject(this);
    }

    @Override
    public void update() {
        sprite.translateX(direction.x * speed * Gdx.graphics.getDeltaTime());
        sprite.translateY(direction.y * speed * Gdx.graphics.getDeltaTime());

        Rectangle bounds = EndlessShooter.GetScreenBounds();
        if (sprite.getX() < bounds.getX() - 100 || sprite.getX() > bounds.getWidth() + bounds.getX() + 100 ||
                sprite.getY() < bounds.getY() - 100 || sprite.getY() > bounds.getHeight() + bounds.getY() + 100) {
            EndlessShooter.UnregisterWorldObject(this);
        }
    }

    void setColor(float r, float g, float b, float a)
    {
        sprite.setColor(r, g, b, a);
    }

    @Override
    public Vector2 getPosition()
    {
        return new Vector2(sprite.getX()+sprite.getWidth()/2, sprite.getY()+sprite.getHeight()/2);
    }

    @Override
    public float getHitRadius()
    {
        return sprite.getWidth();
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (!(other instanceof Bullet)) {
            EndlessShooter.UnregisterWorldObject(this);
        }
    }

    @Override
    public void render() {
        sprite.draw(EndlessShooter.GetMainBatch());
    }
}
