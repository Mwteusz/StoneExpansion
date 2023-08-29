package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.mwti.stoneexpansion.block.ModBlocks;

import static net.mwti.stoneexpansion.block.ModBlocks.isBlockFromTheMod;

public class ModLootTables extends FabricBlockLootTableProvider {
    public ModLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        // every Stone Expansion block gets its drop
        ModBlocks.forEachBlock(block->{
            if(isBlockFromTheMod(block))
                addDrop(block,block);
        });
    }
}
