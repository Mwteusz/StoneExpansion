package net.mwti.stoneexpansion.block;

public enum BlockMaterial {
    STONE,
    MOSSY_STONE,
    GRANITE,
    DIORITE,
    ANDESITE,
    DEEPSLATE,
    BRICKS(true),
    MUD,
    SANDSTONE,
    RED_SANDSTONE,
    PRISMARINE,
    NETHER_BRICKS(true),
    BASALT,
    BLACKSTONE,
    END_STONE,
    PURPUR,
    QUARTZ;

    private final boolean plural;

    BlockMaterial() {
        this(false);
    }

    BlockMaterial(boolean plural) {
        this.plural = plural;
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
