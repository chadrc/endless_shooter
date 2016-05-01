package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.Vector2;

interface IWorldObject {
    void render();
    void update();
    void hitOccurred(IWorldObject other);
    float getHitRadius();
    Vector2 getPosition();
}

