package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Chad Collins on 4/26/2016.
 */
class ShootingEnemy extends Enemy {
    private Timer.Task shootTask = new Timer.Task() {
        @Override
        public void run() {
            shoot();
        }
    };

    ShootingEnemy(int health, Vector2 position) {
        super(health, position);
        Timer.schedule(shootTask, MathUtils.random(0, 3f), 3);
    }

    private void shoot() {
        if (getPosition().y - getSize().y > EndlessShooter.GetScreenBounds().getHeight()) {
            return;
        }
        
        if (getHealth() <= 0)
        {
            shootTask.cancel();
        }
        else
        {
            EnemyBullet b = new EnemyBullet(getPosition().x, getPosition().y, 500, new Vector2(0, -1));
            b.setColor(sprite.getColor().r, sprite.getColor().g, sprite.getColor().b, sprite.getColor().a);
        }
    }

    @Override
    public void update() {
        super.update();
    }
}
