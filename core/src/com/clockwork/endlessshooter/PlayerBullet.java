package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/26/2016.
 */
public class PlayerBullet extends Bullet {

    public PlayerBullet(float x, float y, float speed, Vector2 direction) {
        super(x, y, speed, direction);
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (!(other instanceof Bullet) && !(other instanceof Player)) {
            World.RemoveWorldObject(this);
        }
    }
}
