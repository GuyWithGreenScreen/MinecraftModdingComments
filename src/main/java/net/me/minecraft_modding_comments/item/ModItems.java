package net.me.minecraft_modding_comments.item;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.item.custom.*;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Minecraft_modding_comments.MODID);

    public static DeferredItem<Item> CANON_SPAWNER = ITEMS.registerItem("canon_spawner", Canon_spawner::new);
    public static DeferredItem<Item> WAND = ITEMS.registerItem("wand", Wand::new);
    public static DeferredItem<Item> HOT_POTATO = ITEMS.registerItem("hot_potato", Hot_Potato::new);
    public static DeferredItem<Item> DEODORANT = ITEMS.registerItem("deodorant", Deodorant::new);
    public static DeferredItem<Item> TACO = ITEMS.registerItem("taco", Item::new);
    public static DeferredItem<Item> CHIP = ITEMS.registerItem("chip", Chip::new);
    public static DeferredItem<Item> BIG_MAC = ITEMS.registerItem("big_mac", Item::new);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
