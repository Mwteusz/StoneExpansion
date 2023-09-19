package net.mwti.stoneexpansion.block;

import java.util.List;
import java.util.function.Function;

import static net.mwti.stoneexpansion.block.BlockShape.*;

public enum BlockVariant {
    BASE(false, List.of(BlockShape.values()),
            material -> material.toString()),

    COBBLED(false, List.of(BlockShape.values())),

    SMOOTH(false, List.of(FULL_BLOCK, STAIRS, SLAB)),

    POLISHED(false, List.of(BlockShape.values())),

    CHISELED(true, List.of(FULL_BLOCK)),

    CUT(true, List.of(FULL_BLOCK, SLAB)),

    BRICKS(false, List.of(BlockShape.values()),
            material -> material.getSingular() + "_BRICKS"),

    CRACKED_BRICKS(false, List.of(FULL_BLOCK, SLAB),
            material ->  "CRACKED_" + material.getSingular() + "_BRICKS"),

    PILLAR(true, List.of(FULL_BLOCK),
            material -> material.getSingular() + "_PILLAR"),

    TILES(false, List.of(BlockShape.values()),
            material -> material.getSingular() + "_TILES"),

    DARK(false, List.of(BlockShape.values()));


    private final boolean isColumn;
    private final boolean[] allowedShapes = new boolean[BlockShape.values().length];
    private Function<BlockMaterial,String> namingPattern = material -> this.name() + "_" + material;

    BlockVariant(boolean isColumn, List<BlockShape> allowedShapes) {
        this.isColumn = isColumn;
        for (BlockShape shape : allowedShapes) {
            this.allowedShapes[shape.ordinal()] = true;
        }
    }

    BlockVariant(boolean isColumn, List<BlockShape> allowedShapes, Function<BlockMaterial,String> namingPattern) {
        this(isColumn, allowedShapes);
        this.namingPattern = namingPattern;
    }


    public boolean hasShape(BlockShape shape) {
        return allowedShapes[shape.ordinal()];
    }

    public boolean isColumn() {
        return isColumn;
    }

    public String createName(BlockMaterial material, BlockShape shape) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(namingPattern.apply(material));

        if(shape != FULL_BLOCK) {
            if(stringBuilder.toString().endsWith("S"))
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("_").append(shape);
        }
        return stringBuilder.toString().toLowerCase()
                .replace("brick_brick","brick");
    }
}
