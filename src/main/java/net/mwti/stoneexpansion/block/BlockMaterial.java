package net.mwti.stoneexpansion.block;

import static net.mwti.stoneexpansion.block.BlockVariant.SMOOTH;

public enum BlockMaterial {
    ANDESITE,
    GRANITE,
    DIORITE,
    STONE,
    MOSSY_STONE,
    END_STONE,
    PURPUR,
    SANDSTONE,
    RED_SANDSTONE,
    DEEPSLATE,
    BLACKSTONE,
    MUD,
    BRICKS,
    NETHERRACK,
    QUARTZ,
    PRISMARINE,
    BASALT;


    public static BlockMaterial get(int i) {
        return values()[i];
    }
}
