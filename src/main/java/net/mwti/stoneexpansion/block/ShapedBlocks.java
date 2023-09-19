package net.mwti.stoneexpansion.block;

import net.minecraft.block.Block;

/** container for declarations of vanilla blocks */
public class ShapedBlocks {
    private final Block[] shapes = new Block[BlockShape.values().length];

    public ShapedBlocks(Block block) {
        add(BlockShape.FULL_BLOCK, block);
    }
    public ShapedBlocks(){}

    public ShapedBlocks add(BlockShape shape, Block block) {
        if(shapes[shape.ordinal()] != null)
            throw new UnsupportedOperationException("Cannot overwrite element with " + block + ". " + shape + " is already defined as "+shapes[shape.ordinal()]+"! Check your code.");
        shapes[shape.ordinal()] = block;
        return this;
    }

    public Block get(BlockShape shape) {
        return shapes[shape.ordinal()];
    }
}
