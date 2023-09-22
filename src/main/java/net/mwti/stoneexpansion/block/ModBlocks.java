package net.mwti.stoneexpansion.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.StoneExpansion;

import java.util.Optional;
import java.util.function.Consumer;

import static net.mwti.stoneexpansion.block.BlockMaterial.BRICKS;
import static net.mwti.stoneexpansion.block.BlockShape.*;
import static net.mwti.stoneexpansion.block.BlockVariant.*;
import static net.mwti.stoneexpansion.block.BlockMaterial.*;

public class ModBlocks {

    private static final Block[][][] blocks = new Block[BlockMaterial.values().length][BlockVariant.values().length][BlockShape.values().length];

    private static void setBlock(BlockMaterial material, BlockVariant variant, BlockShape shape, Block block) {
        if(blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] != null)
            throw new UnsupportedOperationException("Block "+ getBlockName(material,variant,shape) +" is already added!");
        blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] = block;
    }

    public static Optional<Block> getBlock(BlockMaterial material, BlockVariant variant, BlockShape shape) {
        return Optional.ofNullable(blocks[material.ordinal()][variant.ordinal()][shape.ordinal()]);
    }

    public static Optional<Block> getModdedBlock(BlockMaterial material, BlockVariant variant, BlockShape shape) {

        Optional<Block> blockOptional = getBlock(material, variant, shape);
        if(blockOptional.isPresent()) {
            if(!isBlockFromTheMod(blockOptional.get())) {
                return Optional.empty();
            }
        }
        return blockOptional;
    }

    public static void registerModBlocks(){

        add(BASE, ANDESITE, new BlockFamily(Blocks.ANDESITE).stairs(Blocks.ANDESITE_STAIRS).slab(Blocks.ANDESITE_SLAB).wall(Blocks.ANDESITE_WALL));
        register(COBBLED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.ANDESITE)));
        register(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        add(POLISHED, ANDESITE, new BlockFamily(Blocks.POLISHED_ANDESITE).stairs(Blocks.POLISHED_ANDESITE_STAIRS).slab(Blocks.POLISHED_ANDESITE_SLAB));
        register(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        add(BASE, GRANITE, new BlockFamily(Blocks.GRANITE).stairs(Blocks.GRANITE_STAIRS).slab(Blocks.GRANITE_SLAB).wall(Blocks.GRANITE_WALL));
        register(COBBLED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.GRANITE)));
        register(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        add(POLISHED, GRANITE, new BlockFamily(Blocks.POLISHED_GRANITE).stairs(Blocks.POLISHED_GRANITE_STAIRS).slab(Blocks.POLISHED_GRANITE_SLAB));
        register(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        add(BASE, DIORITE, new BlockFamily(Blocks.DIORITE).stairs(Blocks.DIORITE_STAIRS).slab(Blocks.DIORITE_SLAB).wall(Blocks.DIORITE_WALL));
        register(COBBLED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.DIORITE)));
        register(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        add(POLISHED, DIORITE, new BlockFamily(Blocks.POLISHED_DIORITE).stairs(Blocks.POLISHED_DIORITE_STAIRS).slab(Blocks.POLISHED_DIORITE_SLAB));
        register(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        add(BASE, STONE, new BlockFamily(Blocks.STONE).stairs(Blocks.STONE_STAIRS).slab(Blocks.STONE_SLAB));
        add(COBBLED, STONE, new BlockFamily(Blocks.COBBLESTONE).stairs(Blocks.COBBLESTONE_STAIRS).slab(Blocks.COBBLESTONE_SLAB).wall(Blocks.COBBLESTONE_WALL));
        // Smoothstone is separated into its own material! Also, this "smooth stone" would look not much different from stone, so there's no point
        register(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        add(CHISELED, STONE, Blocks.CHISELED_STONE_BRICKS);
        register(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        add(STONE, BlockVariant.BRICKS, new BlockFamily( Blocks.STONE_BRICKS).stairs(Blocks.STONE_BRICK_STAIRS).slab(Blocks.STONE_BRICK_SLAB).wall(Blocks.STONE_BRICK_WALL));
        add(CRACKED_BRICKS, STONE, Blocks.CRACKED_STONE_BRICKS);
        register(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        register(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        register(BASE, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        add(COBBLED, MOSSY_STONE, new BlockFamily(Blocks.MOSSY_COBBLESTONE).stairs(Blocks.MOSSY_COBBLESTONE_STAIRS).slab(Blocks.MOSSY_COBBLESTONE_SLAB).wall(Blocks.MOSSY_COBBLESTONE_WALL));
        // "smooth mossy stone" would look not much different from mossy stone, so there's no point
        register(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        add(MOSSY_STONE, BlockVariant.BRICKS, new BlockFamily(Blocks.MOSSY_STONE_BRICKS).stairs(Blocks.MOSSY_STONE_BRICK_STAIRS).slab(Blocks.MOSSY_STONE_BRICK_SLAB).wall(Blocks.MOSSY_STONE_BRICK_WALL));
        register(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        add(BASE, SMOOTHSTONE, new BlockFamily(Blocks.SMOOTH_STONE).slab(Blocks.SMOOTH_STONE_SLAB));
        register(COBBLED, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(SMOOTH, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CUT, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(POLISHED, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CHISELED, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(SMOOTHSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(SMOOTHSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CRACKED_BRICKS, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(DARK, SMOOTHSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(SMOOTHSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));

        add(BASE, END_STONE, Blocks.END_STONE);
        register(COBBLED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        register(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        add(END_STONE, BlockVariant.BRICKS, new BlockFamily(Blocks.END_STONE_BRICKS).stairs(Blocks.END_STONE_BRICK_STAIRS).slab(Blocks.END_STONE_BRICK_SLAB).wall(Blocks.END_STONE_BRICK_WALL));
        register(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        add(BASE, PURPUR, new BlockFamily(Blocks.PURPUR_BLOCK).stairs(Blocks.PURPUR_STAIRS).slab(Blocks.PURPUR_SLAB));
        register(COBBLED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(SMOOTH, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(CUT, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(POLISHED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(CHISELED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        add(PURPUR, PILLAR, Blocks.PURPUR_PILLAR);
        register(PURPUR, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(CRACKED_BRICKS, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(DARK, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        register(PURPUR, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

        add(BASE, SANDSTONE, new BlockFamily(Blocks.SANDSTONE).stairs(Blocks.SANDSTONE_STAIRS).slab(Blocks.SANDSTONE_SLAB).wall(Blocks.SANDSTONE_WALL));
        register(COBBLED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        add(SMOOTH, SANDSTONE, new BlockFamily( Blocks.SMOOTH_SANDSTONE).stairs(Blocks.SMOOTH_SANDSTONE_STAIRS).slab(Blocks.SMOOTH_SANDSTONE_SLAB));
        add(CUT, SANDSTONE, new BlockFamily(Blocks.CUT_SANDSTONE).slab(Blocks.CUT_SANDSTONE_SLAB));
        register(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        add(CHISELED, SANDSTONE, Blocks.CHISELED_SANDSTONE);
        register(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        add(BASE, RED_SANDSTONE, new BlockFamily(Blocks.RED_SANDSTONE).stairs(Blocks.RED_SANDSTONE_STAIRS).slab(Blocks.RED_SANDSTONE_SLAB).wall(Blocks.RED_SANDSTONE_WALL));
        register(COBBLED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        add(SMOOTH, RED_SANDSTONE, new BlockFamily(Blocks.SMOOTH_RED_SANDSTONE).stairs(Blocks.SMOOTH_RED_SANDSTONE_STAIRS).slab(Blocks.SMOOTH_RED_SANDSTONE_SLAB));
        add(CUT, RED_SANDSTONE, new BlockFamily(Blocks.CUT_RED_SANDSTONE).slab(Blocks.CUT_RED_SANDSTONE_SLAB));
        register(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        add(CHISELED, RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE);
        register(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        add(BASE, DEEPSLATE, Blocks.DEEPSLATE);
        add(COBBLED, DEEPSLATE, new BlockFamily(Blocks.COBBLED_DEEPSLATE).slab(Blocks.COBBLED_DEEPSLATE_SLAB).stairs(Blocks.COBBLED_DEEPSLATE_STAIRS).wall(Blocks.COBBLED_DEEPSLATE_WALL));
        register(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        register(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(POLISHED, DEEPSLATE, new BlockFamily(Blocks.POLISHED_DEEPSLATE).stairs(Blocks.POLISHED_DEEPSLATE_STAIRS).slab(Blocks.POLISHED_DEEPSLATE_SLAB).wall(Blocks.POLISHED_DEEPSLATE_WALL));
        add(CHISELED, DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
        register(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(DEEPSLATE, BlockVariant.BRICKS, new BlockFamily(Blocks.DEEPSLATE_BRICKS).stairs(Blocks.DEEPSLATE_BRICK_STAIRS).slab(Blocks.DEEPSLATE_BRICK_SLAB).wall(Blocks.DEEPSLATE_BRICK_WALL));
        add(CRACKED_BRICKS, DEEPSLATE, Blocks.CRACKED_DEEPSLATE_BRICKS);
        register(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(DEEPSLATE, TILES, new BlockFamily(Blocks.DEEPSLATE_TILES).stairs(Blocks.DEEPSLATE_TILE_STAIRS).slab(Blocks.DEEPSLATE_TILE_SLAB).wall(Blocks.DEEPSLATE_TILE_WALL));

        add(BASE, BLACKSTONE, new BlockFamily(Blocks.BLACKSTONE).stairs(Blocks.BLACKSTONE_STAIRS).slab(Blocks.BLACKSTONE_SLAB).wall(Blocks.BLACKSTONE_WALL));
        register(COBBLED, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)));
        register(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        register(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        add(POLISHED, BLACKSTONE, new BlockFamily(Blocks.POLISHED_BLACKSTONE).stairs(Blocks.POLISHED_BLACKSTONE_STAIRS).slab(Blocks.POLISHED_BLACKSTONE_SLAB).wall(Blocks.POLISHED_BLACKSTONE_WALL));
        add(CHISELED, BLACKSTONE, Blocks.CHISELED_POLISHED_BLACKSTONE);
        register(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        add(BLACKSTONE, BlockVariant.BRICKS, new BlockFamily(Blocks.POLISHED_BLACKSTONE_BRICKS).stairs(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS).slab(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB).wall(Blocks.POLISHED_BLACKSTONE_BRICK_WALL));
        add(CRACKED_BRICKS, BLACKSTONE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        register(DARK, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        register(BLACKSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        add(BASE, MUD, Blocks.PACKED_MUD);
        register(COBBLED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.PACKED_MUD)));
        register(SMOOTH, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(CUT, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(POLISHED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(CHISELED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(MUD, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        add(MUD, BlockVariant.BRICKS, new BlockFamily(Blocks.MUD_BRICKS).stairs(Blocks.MUD_BRICK_STAIRS).slab(Blocks.MUD_BRICK_SLAB).wall(Blocks.MUD_BRICK_WALL));
        register(CRACKED_BRICKS, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(DARK, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        register(MUD, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));

        // Base variant is the same as Brick variant
        register(SMOOTH, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(COBBLED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(CUT, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(POLISHED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(CHISELED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        add(BRICKS, BlockVariant.BRICKS, new BlockFamily(Blocks.BRICKS).stairs(Blocks.BRICK_STAIRS).slab(Blocks.BRICK_SLAB).wall(Blocks.BRICK_WALL));
        register(CRACKED_BRICKS, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(DARK, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        register(BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));

        // Base variant is the same as Brick variant
        register(COBBLED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        register(SMOOTH, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        register(CUT, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        register(POLISHED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        add(CHISELED, NETHER_BRICKS, Blocks.CHISELED_NETHER_BRICKS);
        register(NETHER_BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        add(NETHER_BRICKS, BlockVariant.BRICKS, new BlockFamily(Blocks.NETHER_BRICKS).stairs(Blocks.NETHER_BRICK_STAIRS).slab(Blocks.NETHER_BRICK_SLAB).wall(Blocks.NETHER_BRICK_WALL));
        add(CRACKED_BRICKS, NETHER_BRICKS, Blocks.CRACKED_NETHER_BRICKS);
        register(DARK, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        register(NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));

        // Base variant is the same as Brick variant
        register(COBBLED, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(SMOOTH, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(CUT, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(POLISHED, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(CHISELED, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(RED_NETHER_BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        add(RED_NETHER_BRICKS, BlockVariant.BRICKS, new BlockFamily(Blocks.RED_NETHER_BRICKS).slab(Blocks.RED_NETHER_BRICK_SLAB).stairs(Blocks.RED_NETHER_BRICK_STAIRS).wall(Blocks.RED_NETHER_BRICK_WALL));
        register(CRACKED_BRICKS, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(DARK, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(RED_NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));

        // Base variant is the same as Polished variant
        register(COBBLED, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        add(SMOOTH, QUARTZ, new BlockFamily(Blocks.SMOOTH_QUARTZ).stairs(Blocks.SMOOTH_QUARTZ_STAIRS).slab(Blocks.SMOOTH_QUARTZ_SLAB));
        register(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        add(POLISHED, QUARTZ, new BlockFamily(Blocks.QUARTZ_BLOCK).stairs(Blocks.QUARTZ_STAIRS).slab(Blocks.QUARTZ_SLAB));
        add(CHISELED, QUARTZ, Blocks.CHISELED_QUARTZ_BLOCK);
        add(QUARTZ, PILLAR, Blocks.QUARTZ_PILLAR);
        add(QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS);
        register(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        register(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        register(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        add(BASE, PRISMARINE, new BlockFamily(Blocks.PRISMARINE).stairs(Blocks.PRISMARINE_STAIRS).slab(Blocks.PRISMARINE_SLAB).wall(Blocks.PRISMARINE_WALL));
        // Cobbled Prismarine would not really make sense as Prismarine is already cobble-like
        register(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        add(PRISMARINE, BlockVariant.BRICKS, new BlockFamily(Blocks.PRISMARINE_BRICKS).stairs(Blocks.PRISMARINE_BRICK_STAIRS).slab(Blocks.PRISMARINE_BRICK_SLAB));
        register(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        add(DARK, PRISMARINE, new BlockFamily(Blocks.DARK_PRISMARINE).stairs(Blocks.DARK_PRISMARINE_STAIRS).slab(Blocks.DARK_PRISMARINE_SLAB));
        register(PRISMARINE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));

        add(BASE, BASALT, Blocks.BASALT);
        add(SMOOTH, BASALT, Blocks.SMOOTH_BASALT);
        register(COBBLED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.BASALT)));
        register(CUT, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        register(POLISHED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        register(CHISELED, BASALT, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        add(BASALT, PILLAR, Blocks.POLISHED_BASALT);
        register(BASALT, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        register(CRACKED_BRICKS, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        register(DARK, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        register(BASALT, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));

    }

    private static void register(BlockVariant variant, BlockMaterial material, Block block) {
        register(material,variant,block);
    }
    private static void register(BlockMaterial material, BlockVariant variant, Block block) {
        for(BlockShape shape : BlockShape.values()) {
            if(variant.hasShape(shape))
                registerBlock(block, material, variant, shape);
        }
    }

    private static void add(BlockVariant variant, BlockMaterial material, Block block) {
        add(material, variant, block);
    }
    private static void add(BlockMaterial material, BlockVariant variant, Block block) {
        add(variant, material, new BlockFamily(block));
    }

    private static void add(BlockMaterial material, BlockVariant variant, BlockFamily blockFamily) {
        add(variant, material, blockFamily);
    }
    private static void add(BlockVariant variant, BlockMaterial material, BlockFamily blockFamily) {

        for(BlockShape shape : BlockShape.values()) {
            addOrRegister(variant, material, shape, blockFamily.get(shape));
        }
    }

    /** registers shaped block if it's not provided */
    private static void addOrRegister(BlockVariant variant, BlockMaterial material, BlockShape shape, Block shapedBlock){

        if(shapedBlock == null && variant.hasShape(shape)) {
            getBlock(material, variant, FULL_BLOCK)
                    .ifPresent(fullBlock -> registerBlock(fullBlock, material, variant, shape));
        }
        else {
            setBlock(material, variant, shape, shapedBlock);
        }
    }

    private static void registerBlock(Block block, BlockMaterial material, BlockVariant variant, BlockShape shape) {

        Block shapedBlock = shape.newShapedBlock(block);
        String blockName = getBlockName(material, variant, shape);
        setBlock(material, variant, shape, shapedBlock);
        registerBlockItem(blockName, shapedBlock);
        Registry.register(Registries.BLOCK, new Identifier(StoneExpansion.MOD_ID, blockName), shapedBlock);
    }

    static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(StoneExpansion.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void forEachBlock(Consumer<Block> action) {
        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                for(BlockShape shape : BlockShape.values()) {
                    getBlock(material,variant,shape).ifPresent(action);
                }
            }
        }
    }

    public static String getBlockName(BlockMaterial material, BlockVariant variant, BlockShape shape) {

        return variant.createName(material, shape);
    }

    public static boolean isBlockFromTheMod(Block block) {
        return Registries.BLOCK.getId(block).getNamespace().equals(StoneExpansion.MOD_ID);
    }
}
