package net.me.minecraft_modding_comments.tools;

import net.me.minecraft_modding_comments.tools.custom.Start;
import net.me.minecraft_modding_comments.tools.custom.TickObject;
import net.minecraft.world.entity.Entity;

import java.util.ArrayList;
import java.util.HashMap;


public class TickHandler {
    public static HashMap<Entity, Integer> entityTickHandlerHashMap = new HashMap<Entity, Integer>();
    public static HashMap<TickObject, Integer> objectTickHandlerHashMap = new HashMap<TickObject, Integer>();
    public static HashMap<TickObject, Integer> objectTickHandlerHashMapNoDuplicates = new HashMap<TickObject, Integer>();
    public static ArrayList<Entity> MarkedForRemoval = new ArrayList<Entity>();
    public static ArrayList<TickObject> ObjectMarkedForRemoval = new ArrayList<TickObject>();
    public static ArrayList<TickObject> ObjectMarkedForRemovalNoDuplicates = new ArrayList<TickObject>();

    public static void update() {
        if (!entityTickHandlerHashMap.isEmpty()) {
            for (Entity entity : entityTickHandlerHashMap.keySet()) {
                if (entityTickHandlerHashMap.get(entity) > 2100000000) {
                    entityTickHandlerHashMap.replace(entity, 0);
                }
                if (entity.isAlive()) {
                    entityTickHandlerHashMap.replace(entity, entityTickHandlerHashMap.get(entity)+1);
                } else {
                    MarkedForRemoval.add(entity);
                }

            }
            if (!MarkedForRemoval.isEmpty()) {
                for (Entity entity : MarkedForRemoval) {
                    entityTickHandlerHashMap.remove(entity);
                }
                MarkedForRemoval.clear();
            }
        }
        if (!objectTickHandlerHashMap.isEmpty()) {
            for (TickObject tickObject : objectTickHandlerHashMap.keySet()) {
                if (objectTickHandlerHashMap.get(tickObject) > 2100000000) {
                    objectTickHandlerHashMap.replace(tickObject, 0);
                }
                objectTickHandlerHashMap.replace(tickObject, objectTickHandlerHashMap.get(tickObject) + 1);
            }
        }
        for (TickObject tickObject : objectTickHandlerHashMap.keySet()) {
            if (tickObject.value() == objectTickHandlerHashMap.get(tickObject)) {
                tickObject.runnable2().run();
                ObjectMarkedForRemoval.add(tickObject);
            }
        }
        if (!objectTickHandlerHashMapNoDuplicates.isEmpty()) {
            for (TickObject tickObject : objectTickHandlerHashMapNoDuplicates.keySet()) {
                if (objectTickHandlerHashMapNoDuplicates.get(tickObject) > 2100000000) {
                    objectTickHandlerHashMapNoDuplicates.replace(tickObject, 0);
                }
                objectTickHandlerHashMapNoDuplicates.replace(tickObject, objectTickHandlerHashMapNoDuplicates.get(tickObject) + 1);
            }
        }
        for (TickObject tickObject : objectTickHandlerHashMapNoDuplicates.keySet()) {
            if (tickObject.value() == objectTickHandlerHashMapNoDuplicates.get(tickObject)) {
                tickObject.runnable2().run();
                ObjectMarkedForRemoval.add(tickObject);
            }
        }
        if (!ObjectMarkedForRemoval.isEmpty()) {
            for (TickObject tickObject : ObjectMarkedForRemoval) {
                objectTickHandlerHashMap.remove(tickObject);
            }
            ObjectMarkedForRemoval.clear();
        }
    }

    public static void reset(Entity entity) {
        entityTickHandlerHashMap.replace(entity, 0);
    }

    public static void resetEach(Entity entity, int tick) {
        if (entityTickHandlerHashMap.get(entity) == tick) {
            reset(entity);
        }
    }

    public static boolean checkForTick(Entity entity, int tick) {
        return (entityTickHandlerHashMap.get(entity) == tick);
    }

    public static boolean checkForTickAndRemove(Entity entity, int tick) {
        if (entityTickHandlerHashMap.get(entity) == tick) {
            entityTickHandlerHashMap.remove(entity);
            return true;
        }
        return false;
    }

    //RUNS FOREVER!!! JUST CHECKS FOR TICK EACH TIME THEN RESETS
    public static boolean addCheckRemoveTimer(Entity entity, int tick, Start start) {
        if (!entityTickHandlerHashMap.containsKey(entity)) {
            entityTickHandlerHashMap.put(entity, 0);
            if (tools.StartToBoolean(start)) {
                return true;
            }
        }

        return entityTickHandlerHashMap.get(entity) == tick;
    }

    public static void waitThenRun(int delay, Runnable runnable) {
        objectTickHandlerHashMap.put(new TickObject(delay, runnable), 0);
    }

    public static void waitThenRunNoDuplicateRequests(int delay, Runnable runnable) {
        boolean foundDuplicates = false;
        TickObject tickObject2 = new TickObject(delay, runnable);
        for (TickObject tickObject : objectTickHandlerHashMapNoDuplicates.keySet()) {
            if (tickObject.value() == tickObject2.value() && tickObject.runnable2().equals(tickObject2.runnable2())) {
                foundDuplicates = true;
                break;
            }
        }
        if (!foundDuplicates) {
            objectTickHandlerHashMapNoDuplicates.put(tickObject2, 0);
        }
    }

    public static void RemoveEntity(Entity entity) {
        entityTickHandlerHashMap.remove(entity);
    }

    public static void RemoveListOfEntities(ArrayList<Entity> entities) {
        MarkedForRemoval.addAll(entities);
        if (!MarkedForRemoval.isEmpty()) {
            for (Entity entity : MarkedForRemoval) {
                entityTickHandlerHashMap.remove(entity);
            }
            MarkedForRemoval.clear();
        }
    }

    public static void RegisterOnSpawn(Entity entity) {
        entity.addTag("registerOnSpawn");
    }

    public static Integer getTick(Entity entity) {
        if (entityTickHandlerHashMap.containsKey(entity)) {
            return entityTickHandlerHashMap.get(entity);
        }
        return 0;
    }

    public static void RegisterList(ArrayList<Entity> entities) {
        for (Entity entity : entities) {
            RegisterEntity(entity);
        }
    }

    public static void RegisterEntity(Entity entity) {
        if (!entityTickHandlerHashMap.containsKey(entity)) {
            entityTickHandlerHashMap.put(entity, 0);
        }

    }
}
