package com.clockwork.endlessshooter;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class World {
    private static World instance = new World();
    private Array<IWorldObject> worldArray;
    private Array<IWorldObject> worldAddArray;
    private Array<IWorldObject> worldDeleteArray;

    private World() {
        worldArray = new Array<IWorldObject>();
        worldAddArray = new Array<IWorldObject>();
        worldDeleteArray = new Array<IWorldObject>();
    }

    public static void Update() {
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

    public static void AddWorldObject(IWorldObject updatable) {
        instance.worldAddArray.add(updatable);
    }

    public static void RemoveWorldObject(IWorldObject updatable) {
        instance.worldDeleteArray.add(updatable);
    }
}
