package com.bluearchivesitem.block;

import com.bluearchivesitem.BlueArchivesItem;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    // 方块：青辉石块
    public static final Block QHS_BLOCK = new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK));

    public static void registerBlocks() {
        BlueArchivesItem.LOGGER.info("Registering Mod Blocks for " + BlueArchivesItem.MOD_ID);

        // Use Item.Settings and create a BlockItem inside registerBlock.
        // Avoid referencing ItemGroup constants (mapping names may differ); register without a group.
        registerBlock("qhs_block", QHS_BLOCK, new Item.Settings());
    }

    private static Block registerBlock(String name, Block block, Item.Settings settings) {
        Block registered = Registry.register(Registries.BLOCK, new Identifier("blue-archives-item", name), block);
        // Register the BlockItem using the provided settings
        Registry.register(Registries.ITEM, new Identifier("blue-archives-item", name), new BlockItem(block, settings));
        return registered;
    }
}
