package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Chad on 5/1/2016.
 */
final class Assets {
    private static Assets instance;

    private Texture playerShip;
    private Texture basicBullet;
    private Texture basicEnemyShip;

    private Assets() {
        playerShip = new Texture("Player-Ship.png");
        basicBullet = new Texture("BasicBullet.png");
        basicEnemyShip = new Texture("BasicEnemyShip.png");
    }

    static void LoadAssets() {
        instance = new Assets();
    }

    static Texture GetPlayerShipTexture() {
        return instance.playerShip;
    }

    static Texture GetBasicBulletTexture() {
        return instance.basicBullet;
    }

    static Texture GetBasicEnemyTexture() {
        return instance.basicEnemyShip;
    }
}
