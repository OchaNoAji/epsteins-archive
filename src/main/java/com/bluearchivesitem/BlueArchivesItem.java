package com.bluearchivesitem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bluearchivesitem.item.ModItemGroups;
import com.bluearchivesitem.item.ModItems;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents; // 导入物品组事件 API
import net.minecraft.item.ItemGroups; // 导入原版物品组枚举
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class BlueArchivesItem implements ModInitializer {
    public static final String MOD_ID = "blue_archives_item";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    public static final RegistryKey<PlacedFeature> CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, "ore_custom"));
    public static final RegistryKey<PlacedFeature> END_CUSTOM_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, "ore_end_custom"));

    @Override
    public void onInitialize() {
        ModItems.registerItems(); // 注册物品
        com.bluearchivesitem.block.ModBlocks.registerBlocks(); // 注册方块
        ModItemGroups.registerItemGroups(); // 注册自定义物品栏

        // 2. 将物品添加到原版物品组
        addItemsToCreativeTabs();

        // 3. 世界生成注册
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, CUSTOM_ORE_PLACED_KEY);
        BiomeModifications.addFeature(BiomeSelectors.foundInTheEnd(), GenerationStep.Feature.RAW_GENERATION, END_CUSTOM_ORE_PLACED_KEY);

        LOGGER.info("Blue Archive's Item Mod loaded!");
    }

    // 将物品添加到指定的创造模式物品栏
    private void addItemsToCreativeTabs() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            content.add(ModItems.QHS_SHOVEL);   // 锹
            content.add(ModItems.QHS_PICKAXE);  // 镐
            content.add(ModItems.QHS_AXE);      // 斧 
            content.add(ModItems.QHS_HOE);      // 锄
        });

        // 添加到战斗组 (Combat)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            content.add(ModItems.QHS_SWORD);    // 剑
            content.add(ModItems.QHS_AXE);      // 斧 
        });

		// 3. 添加到自然方块组 (Natural Blocks)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.add(com.bluearchivesitem.block.ModBlocks.QHS_ORE_BLOCK); 
        });

        // 4. 添加到建筑方块组 (Building Blocks)
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.add(com.bluearchivesitem.block.ModBlocks.QHS_BLOCK);
        });
    }
}