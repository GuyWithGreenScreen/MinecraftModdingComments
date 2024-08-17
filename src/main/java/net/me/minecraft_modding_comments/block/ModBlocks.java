package net.me.minecraft_modding_comments.block;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.block.custom.lamp_block;
import net.me.minecraft_modding_comments.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Minecraft_modding_comments.MODID);


    public static final DeferredBlock<Block> LAMP = registerBlock("lamp", () -> new lamp_block(BlockBehaviour.Properties.of().noOcclusion().destroyTime(4f)));
    public static final DeferredBlock<Block> CHEESE = registerBlock("cheese", () -> new Block(BlockBehaviour.Properties.of().noOcclusion().destroyTime(4f)));

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
