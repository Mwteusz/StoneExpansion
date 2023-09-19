package net.mwti.stoneexpansion.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.WallBlock;
import java.util.function.Function;

public enum BlockShape {
    FULL_BLOCK(fullBlock -> fullBlock),
    STAIRS(fullBlock -> new StairsBlock(fullBlock.getDefaultState(), FabricBlockSettings.copyOf(fullBlock))),
    SLAB(fullBlock -> new SlabBlock(FabricBlockSettings.copyOf(fullBlock))),
    WALL(fullBlock -> new WallBlock(FabricBlockSettings.copyOf(fullBlock)));
    private final Function<Block, Block> createNewShapedBlockMethod;

    BlockShape(Function<Block, Block> function) {
        this.createNewShapedBlockMethod = function;
    }

    public Block newShapedBlock(Block fullBlock) {
        return createNewShapedBlockMethod.apply(fullBlock);
    }
}
