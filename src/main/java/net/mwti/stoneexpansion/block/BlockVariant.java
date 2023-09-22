package net.mwti.stoneexpansion.block;

import java.util.Arrays;

import static net.mwti.stoneexpansion.block.BlockShape.*;

public enum BlockVariant {
    BASE(false, "[materialPlural]", STAIRS, SLAB, WALL),
    COBBLED(false, "[variant]_[materialPlural]", STAIRS, SLAB, WALL),
    SMOOTH(false, "[variant]_[materialPlural]", STAIRS, SLAB),
    POLISHED(false, "[variant]_[materialPlural]", STAIRS, SLAB, WALL),
    CUT(true, "[variant]_[materialPlural]", SLAB),
    CHISELED(true, "[variant]_[materialPlural]"),
    BRICKS(false, "[materialSingular]_[variant]", STAIRS, SLAB, WALL),
    CRACKED_BRICKS(false, "CRACKED_[materialSingular]_BRICKS", SLAB),
    PILLAR(true, "[materialSingular]_[variant]"),
    TILES(false, "[materialSingular]_[variant]", STAIRS, SLAB, WALL),
    DARK(false, "[variant]_[materialPlural]", STAIRS, SLAB, WALL);

    private final boolean[] allowedShapes = new boolean[BlockShape.values().length - 1]; // less 1 because FULL_BLOCK is always true
    private final boolean useColumnModel;
    private final String namingPattern;

    BlockVariant(boolean useColumnModel, String namingPattern, BlockShape... allowedShapes) {
        this.useColumnModel = useColumnModel;
        this.namingPattern = namingPattern;
        Arrays.stream(allowedShapes).forEach(this::allowShape);
    }

    private void allowShape(BlockShape shape) {
        this.allowedShapes[shape.ordinal() - 1] = true;
    }

    public boolean hasShape(BlockShape shape) {
        if(shape == FULL_BLOCK)
            return true;
        return allowedShapes[shape.ordinal() - 1];
    }

    public boolean usesColumnModel() {
        return useColumnModel;
    }

    public String createName(BlockMaterial material, BlockShape shape) {

        String nameFromPattern = this.applyNamingPattern(material);
        StringBuilder stringBuilder = new StringBuilder(nameFromPattern);

        if(shape != FULL_BLOCK) {
            if(nameFromPattern.endsWith("S"))
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("_").append(shape);
        }
        return stringBuilder.toString().toLowerCase()
                .replace("brick_brick","brick");
    }

    private String applyNamingPattern(BlockMaterial material) {

        return namingPattern
                .replace("[materialPlural]", material.name())
                .replace("[materialSingular]", material.getSingular())
                .replace("[variant]", this.name());
    }
}
