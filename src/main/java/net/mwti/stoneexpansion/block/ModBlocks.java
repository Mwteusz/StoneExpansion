package net.mwti.stoneexpansion.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.StoneExpansion;
import net.mwti.stoneexpansion.datagen.ModModels;

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

        add(BASE, ANDESITE, new ShapedBlocks(Blocks.ANDESITE).add(STAIRS, Blocks.ANDESITE_STAIRS).add(SLAB, Blocks.ANDESITE_SLAB).add(WALL, Blocks.ANDESITE_WALL));
        register(COBBLED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.ANDESITE)));
        register(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        add(POLISHED, ANDESITE, new ShapedBlocks(Blocks.POLISHED_ANDESITE).add(STAIRS, Blocks.POLISHED_ANDESITE_STAIRS).add(SLAB, Blocks.POLISHED_ANDESITE_SLAB));
        register(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        register(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        add(BASE, GRANITE, new ShapedBlocks(Blocks.GRANITE).add(STAIRS, Blocks.GRANITE_STAIRS).add(SLAB, Blocks.GRANITE_SLAB).add(WALL, Blocks.GRANITE_WALL));
        register(COBBLED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.GRANITE)));
        register(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        add(POLISHED, GRANITE, new ShapedBlocks(Blocks.POLISHED_GRANITE).add(STAIRS, Blocks.POLISHED_GRANITE_STAIRS).add(SLAB, Blocks.POLISHED_GRANITE_SLAB));
        register(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        register(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        add(BASE, DIORITE, new ShapedBlocks(Blocks.DIORITE).add(STAIRS, Blocks.DIORITE_STAIRS).add(SLAB, Blocks.DIORITE_SLAB).add(WALL, Blocks.DIORITE_WALL));
        register(COBBLED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.DIORITE)));
        register(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        add(POLISHED, DIORITE, new ShapedBlocks(Blocks.POLISHED_DIORITE).add(STAIRS, Blocks.POLISHED_DIORITE_STAIRS).add(SLAB, Blocks.POLISHED_DIORITE_SLAB));
        register(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        register(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        add(BASE, STONE, new ShapedBlocks(Blocks.STONE).add(STAIRS, Blocks.STONE_STAIRS).add(SLAB, Blocks.STONE_SLAB));
        add(COBBLED, STONE, new ShapedBlocks(Blocks.COBBLESTONE).add(STAIRS, Blocks.COBBLESTONE_STAIRS).add(SLAB, Blocks.COBBLESTONE_SLAB).add(WALL, Blocks.COBBLESTONE_WALL));
        register(SMOOTH, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        add(CHISELED, STONE, Blocks.CHISELED_STONE_BRICKS);
        register(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        add(STONE, BlockVariant.BRICKS, new ShapedBlocks( Blocks.STONE_BRICKS).add(STAIRS, Blocks.STONE_BRICK_STAIRS).add(SLAB, Blocks.STONE_BRICK_SLAB).add(WALL, Blocks.STONE_BRICK_WALL));
        add(CRACKED_BRICKS, STONE, Blocks.CRACKED_STONE_BRICKS);
        register(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        register(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        // Mossy stone has no base
        add(COBBLED, MOSSY_STONE, new ShapedBlocks(Blocks.MOSSY_COBBLESTONE).add(STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS).add(SLAB, Blocks.MOSSY_COBBLESTONE_SLAB).add(WALL, Blocks.MOSSY_COBBLESTONE_WALL));
        register(SMOOTH, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        register(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        add(MOSSY_STONE, BlockVariant.BRICKS, new ShapedBlocks(Blocks.MOSSY_STONE_BRICKS).add(STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS).add(SLAB, Blocks.MOSSY_STONE_BRICK_SLAB).add(WALL, Blocks.MOSSY_STONE_BRICK_WALL));
        register(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        register(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        add(BASE, END_STONE,Blocks.END_STONE);
        register(COBBLED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        register(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        register(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        register(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        register(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        add(END_STONE, BlockVariant.BRICKS, new ShapedBlocks(Blocks.END_STONE_BRICKS).add(STAIRS, Blocks.END_STONE_BRICK_STAIRS).add(SLAB, Blocks.END_STONE_BRICK_SLAB).add(WALL, Blocks.END_STONE_BRICK_WALL));
        register(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        register(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        add(BASE, PURPUR, new ShapedBlocks(Blocks.PURPUR_BLOCK).add(STAIRS, Blocks.PURPUR_STAIRS).add(SLAB, Blocks.PURPUR_SLAB));
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

        add(BASE, SANDSTONE, new ShapedBlocks(Blocks.SANDSTONE).add(STAIRS, Blocks.SANDSTONE_STAIRS).add(SLAB, Blocks.SANDSTONE_SLAB).add(WALL, Blocks.SANDSTONE_WALL));
        register(COBBLED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        add(SMOOTH, SANDSTONE, new ShapedBlocks( Blocks.SMOOTH_SANDSTONE).add(STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS).add(SLAB, Blocks.SMOOTH_SANDSTONE_SLAB));
        add(CUT, SANDSTONE, new ShapedBlocks(Blocks.CUT_SANDSTONE).add(SLAB, Blocks.CUT_SANDSTONE_SLAB));
        register(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        add(CHISELED, SANDSTONE, Blocks.CHISELED_SANDSTONE);
        register(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        register(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        add(BASE, RED_SANDSTONE, new ShapedBlocks(Blocks.RED_SANDSTONE).add(STAIRS, Blocks.RED_SANDSTONE_STAIRS).add(SLAB, Blocks.RED_SANDSTONE_SLAB).add(WALL, Blocks.RED_SANDSTONE_WALL));
        register(COBBLED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        add(SMOOTH, RED_SANDSTONE, new ShapedBlocks(Blocks.SMOOTH_RED_SANDSTONE).add(STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS).add(SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB));
        add(CUT, RED_SANDSTONE, new ShapedBlocks(Blocks.CUT_RED_SANDSTONE).add(SLAB, Blocks.CUT_RED_SANDSTONE_SLAB));
        register(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        add(CHISELED, RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE);
        register(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        register(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        add(BASE, DEEPSLATE, Blocks.DEEPSLATE);
        add(COBBLED, DEEPSLATE, new ShapedBlocks(Blocks.COBBLED_DEEPSLATE).add(SLAB, Blocks.COBBLED_DEEPSLATE_SLAB).add(STAIRS, Blocks.COBBLED_DEEPSLATE_STAIRS).add(WALL, Blocks.COBBLED_DEEPSLATE_WALL));
        register(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        register(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(POLISHED, DEEPSLATE, new ShapedBlocks(Blocks.POLISHED_DEEPSLATE).add(STAIRS, Blocks.POLISHED_DEEPSLATE_STAIRS).add(SLAB, Blocks.POLISHED_DEEPSLATE_SLAB).add(WALL, Blocks.POLISHED_DEEPSLATE_WALL));
        add(CHISELED, DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
        register(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(DEEPSLATE, BlockVariant.BRICKS, new ShapedBlocks(Blocks.DEEPSLATE_BRICKS).add(STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS).add(SLAB, Blocks.DEEPSLATE_BRICK_SLAB).add(WALL, Blocks.DEEPSLATE_BRICK_WALL));
        add(CRACKED_BRICKS, DEEPSLATE, Blocks.CRACKED_DEEPSLATE_BRICKS);
        register(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        add(DEEPSLATE, TILES, new ShapedBlocks(Blocks.DEEPSLATE_TILES).add(STAIRS, Blocks.DEEPSLATE_TILE_STAIRS).add(SLAB, Blocks.DEEPSLATE_TILE_SLAB).add(WALL, Blocks.DEEPSLATE_TILE_WALL));

        add(BASE, BLACKSTONE, new ShapedBlocks(Blocks.BLACKSTONE).add(STAIRS, Blocks.BLACKSTONE_STAIRS).add(SLAB, Blocks.BLACKSTONE_SLAB).add(WALL, Blocks.BLACKSTONE_WALL));
        register(COBBLED, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)));
        register(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        register(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        add(POLISHED, BLACKSTONE, new ShapedBlocks(Blocks.POLISHED_BLACKSTONE).add(STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS).add(SLAB, Blocks.POLISHED_BLACKSTONE_SLAB).add(WALL, Blocks.POLISHED_BLACKSTONE_WALL));
        add(CHISELED, BLACKSTONE, Blocks.CHISELED_POLISHED_BLACKSTONE);
        register(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        add(BLACKSTONE, BlockVariant.BRICKS, new ShapedBlocks(Blocks.POLISHED_BLACKSTONE_BRICKS).add(STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS).add(SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB).add(WALL, Blocks.POLISHED_BLACKSTONE_BRICK_WALL));
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
        add(MUD, BlockVariant.BRICKS, new ShapedBlocks(Blocks.MUD_BRICKS).add(STAIRS, Blocks.MUD_BRICK_STAIRS).add(SLAB, Blocks.MUD_BRICK_SLAB).add(WALL, Blocks.MUD_BRICK_WALL));
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
        add(BRICKS, BlockVariant.BRICKS, new ShapedBlocks(Blocks.BRICKS).add(STAIRS, Blocks.BRICK_STAIRS).add(SLAB, Blocks.BRICK_SLAB).add(WALL, Blocks.BRICK_WALL));
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
        add(NETHER_BRICKS, BlockVariant.BRICKS, new ShapedBlocks(Blocks.NETHER_BRICKS).add(STAIRS, Blocks.NETHER_BRICK_STAIRS).add(SLAB, Blocks.NETHER_BRICK_SLAB).add(WALL, Blocks.NETHER_BRICK_WALL));
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
        add(RED_NETHER_BRICKS, BlockVariant.BRICKS, new ShapedBlocks(Blocks.RED_NETHER_BRICKS).add(SLAB, Blocks.RED_NETHER_BRICK_SLAB).add(STAIRS, Blocks.RED_NETHER_BRICK_STAIRS).add(WALL, Blocks.RED_NETHER_BRICK_WALL));
        register(CRACKED_BRICKS, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(DARK, RED_NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));
        register(RED_NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_NETHER_BRICKS)));

        // Base variant is the same as Polished variant
        register(COBBLED, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        add(SMOOTH, QUARTZ, new ShapedBlocks(Blocks.SMOOTH_QUARTZ).add(STAIRS, Blocks.SMOOTH_QUARTZ_STAIRS).add(SLAB, Blocks.SMOOTH_QUARTZ_SLAB));
        register(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        add(POLISHED, QUARTZ, new ShapedBlocks(Blocks.QUARTZ_BLOCK).add(STAIRS, Blocks.QUARTZ_STAIRS).add(SLAB, Blocks.QUARTZ_SLAB));
        add(CHISELED, QUARTZ, Blocks.CHISELED_QUARTZ_BLOCK);
        add(QUARTZ, PILLAR, Blocks.QUARTZ_PILLAR);
        add(QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS);
        register(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        register(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        register(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        add(BASE, PRISMARINE, new ShapedBlocks(Blocks.PRISMARINE).add(STAIRS, Blocks.PRISMARINE_STAIRS).add(SLAB, Blocks.PRISMARINE_SLAB).add(WALL, Blocks.PRISMARINE_WALL));
        // Cobbled Prismarine would not really make sense as Prismarine is already cobble-like
        register(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        register(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        add(PRISMARINE, BlockVariant.BRICKS, new ShapedBlocks(Blocks.PRISMARINE_BRICKS).add(STAIRS, Blocks.PRISMARINE_BRICK_STAIRS).add(SLAB, Blocks.PRISMARINE_BRICK_SLAB));
        register(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        add(DARK, PRISMARINE, new ShapedBlocks(Blocks.DARK_PRISMARINE).add(STAIRS, Blocks.DARK_PRISMARINE_STAIRS).add(SLAB, Blocks.DARK_PRISMARINE_SLAB));
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
        add(variant, material, new ShapedBlocks(block));
    }

    private static void add(BlockMaterial material, BlockVariant variant, ShapedBlocks shapedBlocks) {
        add(variant, material, shapedBlocks);
    }
    private static void add(BlockVariant variant, BlockMaterial material, ShapedBlocks shapedBlocks) {

        for(BlockShape shape : BlockShape.values()) {
            addOrRegister(variant, material, shape, shapedBlocks.get(shape));
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
        if(ModModels.isModelBlacklisted(material,variant,shape))
            return;
        Block shapedBlock = shape.newShapedBlock(block);
        String name = getBlockName(material, variant, shape);
        setBlock(material, variant, shape, shapedBlock);
        registerBlockItem(name, shapedBlock);
        Registry.register(Registries.BLOCK, new Identifier(StoneExpansion.MOD_ID, name), shapedBlock);
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
