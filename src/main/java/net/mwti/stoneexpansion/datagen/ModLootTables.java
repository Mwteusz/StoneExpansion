package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;

import static net.mwti.stoneexpansion.block.ModBlocks.getModdedBlock;

public class ModLootTables extends FabricBlockLootTableProvider {
    public ModLootTables(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {

        // every Stone Expansion block gets its drop
        for (BlockVariant variant : BlockVariant.values()){
            for (BlockMaterial material : BlockMaterial.values()){
                for (BlockShape shape : BlockShape.values()){
                    getModdedBlock(material,variant, shape)
                            .ifPresent(block -> addDrop(block, block));
                }
            }
        }
    }
}
