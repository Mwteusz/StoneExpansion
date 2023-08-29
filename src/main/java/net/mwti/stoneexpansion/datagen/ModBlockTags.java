package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;
import net.mwti.stoneexpansion.StoneExpansion;

import java.util.concurrent.CompletableFuture;

import static net.mwti.stoneexpansion.block.ModBlocks.forEachBlock;

public class ModBlockTags extends FabricTagProvider.BlockTagProvider {

    public ModBlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        StoneExpansion.LOGGER.info("Generating tags for " + StoneExpansion.MOD_ID);

        forEachBlock(block -> getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block));
    }
}
