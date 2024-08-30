package net.me.minecraft_modding_comments.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;

public class Taco extends Item {
    public Taco(Properties properties) {
        super(properties);
    }

    @Override
    public Component getDescription() {
        return Component.literal("BREAKS YOUR WORLD");
    }
}
