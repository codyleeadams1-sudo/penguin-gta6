package com.penguinmod;

import com.penguinmod.entity.PenguinEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.biome.Biome;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Penguin Mod (Fabric, Minecraft 1.21.11).
 * Adds a penguin mob that spawns naturally in snowy / icy biomes plus a spawn egg.
 */
public class PenguinMod implements ModInitializer {
    public static final String MOD_ID = "penguinmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final Identifier PENGUIN_ID = Identifier.fromNamespaceAndPath(MOD_ID, "penguin");
    public static final ResourceKey<EntityType<?>> PENGUIN_KEY = ResourceKey.create(Registries.ENTITY_TYPE, PENGUIN_ID);

    /** Biomes listed in data/penguinmod/tags/worldgen/biome/spawns_penguins.json */
    public static final TagKey<Biome> SPAWNS_PENGUINS =
            TagKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(MOD_ID, "spawns_penguins"));

    public static final EntityType<PenguinEntity> PENGUIN = Registry.register(
            BuiltInRegistries.ENTITY_TYPE,
            PENGUIN_KEY,
            EntityType.Builder.<PenguinEntity>of(PenguinEntity::new, MobCategory.CREATURE)
                    .sized(0.6F, 1.0F)
                    .eyeHeight(0.85F)
                    .clientTrackingRange(8)
                    .build(PENGUIN_KEY));

    public static final ResourceKey<Item> PENGUIN_SPAWN_EGG_KEY =
            ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(MOD_ID, "penguin_spawn_egg"));

    public static final Item PENGUIN_SPAWN_EGG = Registry.register(
            BuiltInRegistries.ITEM,
            PENGUIN_SPAWN_EGG_KEY,
            new SpawnEggItem(new Item.Properties().setId(PENGUIN_SPAWN_EGG_KEY).spawnEgg(PENGUIN)));

    // custom penguin sounds (defined in assets/penguinmod/sounds.json)
    public static final SoundEvent PENGUIN_AMBIENT = registerSound("entity.penguin.ambient");
    public static final SoundEvent PENGUIN_AMBIENT_BABY = registerSound("entity.penguin.ambient_baby");
    public static final SoundEvent PENGUIN_HURT = registerSound("entity.penguin.hurt");
    public static final SoundEvent PENGUIN_DEATH = registerSound("entity.penguin.death");
    public static final SoundEvent PENGUIN_STEP = registerSound("entity.penguin.step");

    private static SoundEvent registerSound(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    @Override
    public void onInitialize() {
        // default attributes (health, speed, tempt range)
        FabricDefaultAttributeRegistry.register(PENGUIN, PenguinEntity.createAttributes());

        // natural spawning in snowy / icy biomes
        BiomeModifications.addSpawn(
                BiomeSelectors.tag(SPAWNS_PENGUINS),
                MobCategory.CREATURE,
                PENGUIN,
                10,   // weight
                2,    // min group size
                5);   // max group size

        // spawn egg in the creative "Spawn Eggs" tab
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS)
                .register(entries -> entries.accept(PENGUIN_SPAWN_EGG));

        LOGGER.info("Penguin Mod loaded");
    }
}
