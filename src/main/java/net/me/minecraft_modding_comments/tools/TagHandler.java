package net.me.minecraft_modding_comments.tools;

import net.minecraft.world.entity.Entity;

import java.util.HashMap;

public class TagHandler {
    private static HashMap<Entity, HashMap<String, Integer>> intTag = new HashMap<Entity, HashMap<String, Integer>>();

    public static void AddIntTag(Entity entity, String tagName, Integer tag) {
        HashMap<String, Integer> placeHolder = new HashMap<String, Integer>();
        placeHolder.put(tagName, tag);
        if (intTag.containsKey(entity)) {
            intTag.replace(entity, placeHolder);
        } else {
            intTag.put(entity, placeHolder);
        }
    }

    public static int getIntTag(Entity entity, String tagName) {
        if (intTag.containsKey(entity)) {
            return intTag.get(entity).get(tagName);
        } else {
            return 0;
        }
    }

    public static void removeIntTag(Entity entity, String tagName) {
        intTag.get(entity).remove(tagName);
    }

    public static void removeEntityTags(Entity entity) {
        intTag.remove(entity);
    }
}
