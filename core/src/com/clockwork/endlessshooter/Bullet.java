package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Bullet implements IWorldObject {
    protected float speed;
    protected Vector2 direction;
    protected Sprite sprite;

    public Bullet(float x, float y, float speed, Vector2 direction) {
        direction = direction.nor();
        sprite = new Sprite(new Texture("BasicBullet.png"));
        sprite.setSize(10, 10);
        sprite.setX(x-sprite.getWidth()/2);
        sprite.setY(y-sprite.getHeight()/2);
        this.speed = speed;
        this.direction = direction;
        World.AddWorldObject(this);
    }

    @Override
    public void update() {
        sprite.translateX(direction.x * speed * Gdx.graphics.getDeltaTime());
        sprite.translateY(direction.y * speed * Gdx.graphics.getDeltaTime());

        if (sprite.getX() < -100 || sprite.getX() > EndlessShooter.ScreenWidth + 100 ||
                sprite.getY() < -100 || sprite.getY() > EndlessShooter.ScreenHeight + 100) {
            World.RemoveWorldObject(this);
        }
    }

    public void setColor(float r, float g, float b, float a)
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
            World.RemoveWorldObject(this);
        }
    }

    @Override
    public void render() {
        sprite.draw(EndlessShooter.GetMainBatch());
    }
}
