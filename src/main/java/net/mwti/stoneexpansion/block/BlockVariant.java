package net.mwti.stoneexpansion.block;

public enum BlockVariant {
    BASE(true,false, true, true),
    COBBLED(true,true,true,true),
    SMOOTH(true,true, true, false),
    POLISHED(true,true, true, true),
    CHISELED(false,false, false, false),
    CUT(false,false, true, false),
    BRICKS(true,true, true, true),
    CRACKED_BRICKS(true,false, true, false),
    PILLAR(false,false, false, false),
    TILES(true,true, true, true),
    DARK(true,true, true, true);

    private final boolean isCube;
    private final boolean[] shapes;

    BlockVariant(boolean isCube, boolean hasStairs, boolean hasSlabs, boolean hasWalls) {
        this.isCube = isCube;

        boolean hasBlock = true;
        this.shapes = new boolean[]{hasBlock, hasStairs, hasSlabs, hasWalls};
    }

    public boolean hasShape(BlockShape shape) {
        return shapes[shape.ordinal()];
    }

    /* false means pillar, at least for now */
    public boolean isCube() {
        return isCube;
    }
}
