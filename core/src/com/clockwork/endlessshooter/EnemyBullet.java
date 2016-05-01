package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/26/2016.
 */
class EnemyBullet extends Bullet {

    EnemyBullet(float x, float y, float speed, Vector2 direction) {
        super(x, y, speed, direction);
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (!(other instanceof Bullet) && !(other instanceof Enemy)) {
            EndlessShooter.UnregisterWorldObject(this);
        }
    }

}
