package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/26/2016.
 */
class Enemy implements IWorldObject {
    private int health;
    private float speed;
    protected Sprite sprite;

    Enemy(int health, Vector2 position) {
        this.health = health;
        sprite = new Sprite(Assets.GetBasicEnemyTexture());
        sprite.setSize(40, 40);
        sprite.setX(position.x + sprite.getWidth()/2);
        sprite.setY(position.y + sprite.getHeight()/2);
        sprite.setColor(1, 0, 1, 1);
        speed = 80;
        EndlessShooter.RegisterWorldObject(this);
    }

    @Override
    public void render() {
        sprite.draw(EndlessShooter.GetMainBatch());
    }

    @Override
    public void update() {
        sprite.translateY(-speed * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (other instanceof PlayerBullet) {
            health--;
            if (health <= 0) {
                EndlessShooter.UnregisterWorldObject(this);
            }
        }
    }

    @Override
    public float getHitRadius() {
        return sprite.getWidth()/2;
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(sprite.getX()+sprite.getWidth()/2, sprite.getY()+sprite.getHeight()/2);
    }

    Vector2 getSize() {
        return new Vector2(sprite.getWidth(), sprite.getHeight());
    }

    int getHealth() {
        return health;
    }
}
