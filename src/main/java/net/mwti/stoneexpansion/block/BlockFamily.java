package net.mwti.stoneexpansion.block;

import net.minecraft.block.Block;

/** container for declarations of vanilla blocks */
public class BlockFamily {
    private final Block[] shapes = new Block[BlockShape.values().length];

    public BlockFamily(Block block) {
        add(BlockShape.FULL_BLOCK, block);
    }
    public BlockFamily(){}

    public BlockFamily add(BlockShape shape, Block block) {
        if(shapes[shape.ordinal()] != null)
            throw new UnsupportedOperationException("Cannot overwrite element with " + block + ". " + shape + " is already defined as "+shapes[shape.ordinal()]+"! Check your code.");
        shapes[shape.ordinal()] = block;
        return this;
    }
    public BlockFamily slab(Block slabBlock){
        return add(BlockShape.SLAB, slabBlock);
    }
    public BlockFamily stairs(Block stairBlock){
        return add(BlockShape.STAIRS, stairBlock);
    }
    public BlockFamily wall(Block wallBlock){
        return add(BlockShape.WALL, wallBlock);
    }

    public Block get(BlockShape shape) {
        return shapes[shape.ordinal()];
    }
}
