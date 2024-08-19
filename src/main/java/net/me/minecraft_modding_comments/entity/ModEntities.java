package net.me.minecraft_modding_comments.entity;

import net.me.minecraft_modding_comments.Minecraft_modding_comments;
import net.me.minecraft_modding_comments.entity.custom.CanonEntity;
import net.me.minecraft_modding_comments.entity.custom.Racoon;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Minecraft_modding_comments.MODID);

    public static final Supplier<EntityType<CanonEntity>> CANON =
            ENTITY_TYPES.register("canon", () -> EntityType.Builder.of(CanonEntity::new, MobCategory.MISC)
                    .sized(0.75f, 0.95f).build("canon"));

    public static final Supplier<EntityType<Racoon>> RACOON =
            ENTITY_TYPES.register("racoon", () -> EntityType.Builder.of(Racoon::new, MobCategory.MISC)
                    .sized(0.75f, 0.95f).build("racoon"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
