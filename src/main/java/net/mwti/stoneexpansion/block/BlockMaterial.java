package net.mwti.stoneexpansion.block;

public enum BlockMaterial {
    STONE(false, false),
    SMOOTHSTONE(false, true),
    MOSSY_STONE(false, true),
    GRANITE(false, false),
    DIORITE(false, false),
    ANDESITE(false, false),
    DEEPSLATE(false, false),
    BRICKS(true, false),
    MUD(false, false),
    SANDSTONE(false, false),
    RED_SANDSTONE(false, false),
    PRISMARINE(false, false),
    NETHER_BRICKS(true, false),
    RED_NETHER_BRICKS(true, false),
    BASALT(false, false),
    BLACKSTONE(false, false),
    END_STONE(false, false),
    PURPUR(false, true),
    QUARTZ(false, false);

    private final boolean plural;
    private final boolean craftableBase;

    BlockMaterial(boolean plural, boolean craftableBase) {
        this.plural = plural;
        this.craftableBase = craftableBase;
    }

    public boolean isPlural() { return plural; }
    public boolean isBaseCraftable() { return craftableBase; }

    public String getSingular() {
        return isPlural()
                ? this.name().substring(0, this.name().length() - 1)
                : this.name();
    }
}
