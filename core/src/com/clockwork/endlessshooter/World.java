package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Chad Collins on 4/25/2016.
 */
class World {
    private static World instance = new World();
    private Array<IWorldObject> worldArray;
    private Array<IWorldObject> worldAddArray;
    private Array<IWorldObject> worldDeleteArray;

    World() {
        worldArray = new Array<IWorldObject>();
        worldAddArray = new Array<IWorldObject>();
        worldDeleteArray = new Array<IWorldObject>();
    }

    void update() {
        // Add and Remove pending Updatables
        for (IWorldObject u : instance.worldAddArray) {
            instance.worldArray.add(u);
        }
        instance.worldAddArray.clear();
        for (IWorldObject u : instance.worldDeleteArray) {
            instance.worldArray.removeValue(u, true);
        }
        instance.worldDeleteArray.clear();

        // Check for hits
        for (int i=0; i<instance.worldArray.size; i++) {
            IWorldObject o = instance.worldArray.get(i);
            for (int j=0; j<instance.worldArray.size; j++) {
                IWorldObject t = instance.worldArray.get(j);
                float dist = o.getPosition().dst(t.getPosition());
                if (!o.equals(t) && dist <= o.getHitRadius() + t.getHitRadius()) {
                    o.hitOccurred(t);
                }
            }
        }

        // Update and Render
        for (IWorldObject o : instance.worldArray) {
            o.update();
            o.render();
        }
    }

    void addWorldObject(IWorldObject updatable) {
        instance.worldAddArray.add(updatable);
    }

    void removeWorldObject(IWorldObject updatable) {
        instance.worldDeleteArray.add(updatable);
    }
}
