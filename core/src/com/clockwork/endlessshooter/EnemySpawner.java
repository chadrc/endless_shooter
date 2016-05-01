package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;

/**
 * Created by Chad on 5/1/2016.
 */
public class EnemySpawner {

    private float lastSpawnTime;
    private float time;
    private WeightCurve[] weights;
    private Runnable[] enemyCreators;

    private Queue<Runnable> createQueue;
    private Timer.Task createTask = new Timer.Task() {
        @Override
        public void run() {
            createQueue.removeFirst().run();
        }
    };

    EnemySpawner() {
        time = 0;
        lastSpawnTime = time;
        createQueue = new Queue<Runnable>();
        weights = new WeightCurve[1];
        enemyCreators = new Runnable[1];

        weights[0] = new WeightCurve();
        enemyCreators[0] = new Runnable() {
            @Override
            public void run() {
                new ShootingEnemy(10, new Vector2(EndlessShooter.RandomScreenX(), EndlessShooter.GetScreenBounds().getHeight() + 100));
            }
        };
    }

    void update() {
        time += Gdx.graphics.getDeltaTime();
        if (time - lastSpawnTime >= 1) {
            float chance = weights[0].getValue(time) * 100;
            float roll = MathUtils.random(100);
            if (chance >= roll) {
                enemyCreators[0].run();
            }
            lastSpawnTime = time;
        }
    }
}
