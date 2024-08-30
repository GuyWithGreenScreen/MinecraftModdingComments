package net.me.minecraft_modding_comments.item;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Minecraft_modding_comments.MODID);

    public static final Supplier<CreativeModeTab> MOD_ITEMS_TAB =
            CREATIVE_MODE_TABS.register("mod_items", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModBlocks.TNT_ROULETTE))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.HOT_POTATO);
                        output.accept(ModItems.BIG_MAC);
                        output.accept(ModItems.CANON_SPAWNER);
                        output.accept(ModItems.CHIP);
                        output.accept(ModItems.DEODORANT);
                        output.accept(ModItems.GEIGERCOUNTER);
                        output.accept(ModItems.PURINACATCHOW);
                        output.accept(ModItems.RADIUM224);
                        output.accept(ModItems.TACO);
                        output.accept(ModItems.WAND);
                        output.accept(ModBlocks.TNT_ROULETTE);
                        output.accept(ModBlocks.LAMP);
                            }
                    )
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
