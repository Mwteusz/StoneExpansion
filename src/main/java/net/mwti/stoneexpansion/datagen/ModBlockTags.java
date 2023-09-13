package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mwti.stoneexpansion.StoneExpansion;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;

import java.util.concurrent.CompletableFuture;

import static net.mwti.stoneexpansion.block.ModBlocks.getModdedBlock;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {

    public ModBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        StoneExpansion.LOGGER.info("Generating tags for " + StoneExpansion.MOD_ID);

        for (BlockVariant variant : BlockVariant.values()) {
            for (BlockMaterial material : BlockMaterial.values()) {
                for (BlockShape shape : BlockShape.values()) {
                    getModdedBlock(material,variant, shape).ifPresent(block -> {

                        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block);

                        switch(shape) {
                            case STAIRS -> getOrCreateTagBuilder(BlockTags.STAIRS).add(block);
                            case SLAB -> getOrCreateTagBuilder(BlockTags.SLABS).add(block);
                            case WALL -> getOrCreateTagBuilder(BlockTags.WALLS).add(block);
                        }
                    });
                }
            }
        }
    }
}
