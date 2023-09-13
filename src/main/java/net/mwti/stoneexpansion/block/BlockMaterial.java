package net.mwti.stoneexpansion.block;

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
    BRICKS(true),
    NETHER_BRICKS(true),
    QUARTZ,
    PRISMARINE,
    BASALT;

    private final boolean plural;

    BlockMaterial(boolean plural) {
        this.plural = plural;
    }
    BlockMaterial() {
        this.plural = false;
    }

    public boolean isPlural() {
        return plural;
    }

    public String getSingular() {
        return plural
                ? this.name().substring(0, this.name().length() - 1)
                : this.name();
    }
}
