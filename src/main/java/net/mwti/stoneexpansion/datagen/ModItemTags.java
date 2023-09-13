package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;
import net.mwti.stoneexpansion.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTags extends FabricTagProvider.ItemTagProvider {

    public ModItemTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    public void configure(RegistryWrapper.WrapperLookup arg) {

        addItemsToTags();
    }

    private void addItemsToTags() {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                if(variant == BlockVariant.DARK)
                    continue;
                ModBlocks.getBlock(material, variant, BlockShape.BLOCK).ifPresent(block ->
                        getOrCreateTagBuilder(ModTags.Items.getTag(material)).add(block.asItem())
                );
            }
        }
    }
}
