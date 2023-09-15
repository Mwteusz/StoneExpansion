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
            throw new RuntimeException("Block "+ getName(material,variant,shape) +" is already added");
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

        addVanillaBlockWithShapes(BASE, ANDESITE, Blocks.ANDESITE, Blocks.ANDESITE_STAIRS, Blocks.ANDESITE_SLAB, Blocks.ANDESITE_WALL);
        registerBlockWithShapes(COBBLED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.ANDESITE)));
        registerBlockWithShapes(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        addVanillaBlockWithShapes(POLISHED, ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE_SLAB,null);
        registerBlockWithShapes(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockWithShapes(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        addVanillaBlockWithShapes(BASE, GRANITE, Blocks.GRANITE, Blocks.GRANITE_STAIRS, Blocks.GRANITE_SLAB, Blocks.GRANITE_WALL);
        registerBlockWithShapes(COBBLED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.GRANITE)));
        registerBlockWithShapes(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        addVanillaBlockWithShapes(POLISHED, GRANITE, Blocks.POLISHED_GRANITE, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE_SLAB, null);
        registerBlockWithShapes(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockWithShapes(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        addVanillaBlockWithShapes(BASE, DIORITE, Blocks.DIORITE, Blocks.DIORITE_STAIRS, Blocks.DIORITE_SLAB, Blocks.DIORITE_WALL);
        registerBlockWithShapes(COBBLED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.DIORITE)));
        registerBlockWithShapes(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        addVanillaBlockWithShapes(POLISHED, DIORITE, Blocks.POLISHED_DIORITE, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE_SLAB, null);
        registerBlockWithShapes(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockWithShapes(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        addVanillaBlockWithShapes(BASE, STONE, Blocks.STONE, Blocks.STONE_STAIRS, Blocks.STONE_SLAB, null);
        addVanillaBlockWithShapes(COBBLED, STONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_WALL);
        registerBlockWithShapes(SMOOTH, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlock(CHISELED, STONE, FULL_BLOCK, Blocks.CHISELED_STONE_BRICKS);
        registerBlockWithShapes(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlockWithShapes(STONE, BlockVariant.BRICKS, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL);
        addVanillaBlock(CRACKED_BRICKS, STONE, FULL_BLOCK, Blocks.CRACKED_STONE_BRICKS);
        registerBlockWithShapes(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        registerBlockWithShapes(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        // Mossy stone has no base
        addVanillaBlockWithShapes(COBBLED, MOSSY_STONE, Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_WALL);
        registerBlockWithShapes(SMOOTH, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockWithShapes(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlockWithShapes(MOSSY_STONE, BlockVariant.BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL);
        registerBlockWithShapes(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlockWithShapes(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlockWithShapes(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        addVanillaBlockWithShapes(BASE, END_STONE, Blocks.END_STONE, null, null, null);
        registerBlockWithShapes(COBBLED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockWithShapes(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockWithShapes(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockWithShapes(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockWithShapes(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockWithShapes(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        addVanillaBlockWithShapes(END_STONE, BlockVariant.BRICKS, Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_WALL);
        registerBlockWithShapes(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockWithShapes(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockWithShapes(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        addVanillaBlockWithShapes(BASE, PURPUR, Blocks.PURPUR_BLOCK, Blocks.PURPUR_STAIRS, Blocks.PURPUR_SLAB, null);
        registerBlockWithShapes(COBBLED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(SMOOTH, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(CUT, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(POLISHED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(CHISELED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        addVanillaBlock(PURPUR, PILLAR, FULL_BLOCK, Blocks.PURPUR_PILLAR);
        registerBlockWithShapes(PURPUR, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(CRACKED_BRICKS, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(DARK, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockWithShapes(PURPUR, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

        addVanillaBlockWithShapes(BASE, SANDSTONE, Blocks.SANDSTONE, Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_WALL);
        registerBlockWithShapes(COBBLED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        addVanillaBlockWithShapes(SMOOTH, SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_SLAB, null);
        addVanillaBlockWithShapes(CUT, SANDSTONE, Blocks.CUT_SANDSTONE, null, Blocks.CUT_SANDSTONE_SLAB, null);
        registerBlockWithShapes(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        addVanillaBlock(CHISELED, SANDSTONE, FULL_BLOCK, Blocks.CHISELED_SANDSTONE);
        registerBlockWithShapes(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockWithShapes(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockWithShapes(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockWithShapes(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockWithShapes(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        addVanillaBlockWithShapes(BASE, RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE_WALL);
        registerBlockWithShapes(COBBLED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        addVanillaBlockWithShapes(SMOOTH, RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_SLAB, null);
        addVanillaBlockWithShapes(CUT, RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, null, Blocks.CUT_RED_SANDSTONE_SLAB, null);
        registerBlockWithShapes(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        addVanillaBlock(CHISELED, RED_SANDSTONE, FULL_BLOCK, Blocks.CHISELED_RED_SANDSTONE);
        registerBlockWithShapes(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockWithShapes(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockWithShapes(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockWithShapes(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockWithShapes(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        addVanillaBlock(BASE, DEEPSLATE, FULL_BLOCK, Blocks.DEEPSLATE);
        addVanillaBlockWithShapes(COBBLED, DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_WALL);
        registerBlockWithShapes(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        registerBlockWithShapes(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockWithShapes(POLISHED, DEEPSLATE, Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE_WALL);
        addVanillaBlock(CHISELED, DEEPSLATE, FULL_BLOCK, Blocks.CHISELED_DEEPSLATE);
        registerBlockWithShapes(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockWithShapes(DEEPSLATE, BlockVariant.BRICKS, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_WALL);
        addVanillaBlock(CRACKED_BRICKS, DEEPSLATE, FULL_BLOCK, Blocks.CRACKED_DEEPSLATE_BRICKS);
        registerBlockWithShapes(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockWithShapes(DEEPSLATE, TILES, Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_WALL);

        addVanillaBlockWithShapes(BASE, BLACKSTONE, Blocks.BLACKSTONE, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE_WALL);
        registerBlockWithShapes(COBBLED, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)));
        registerBlockWithShapes(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlockWithShapes(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addVanillaBlockWithShapes(POLISHED, BLACKSTONE, Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_WALL);
        addVanillaBlock(CHISELED, BLACKSTONE, FULL_BLOCK, Blocks.CHISELED_POLISHED_BLACKSTONE);
        registerBlockWithShapes(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addVanillaBlockWithShapes(BLACKSTONE, BlockVariant.BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        addVanillaBlock(CRACKED_BRICKS, BLACKSTONE, FULL_BLOCK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerBlockWithShapes(DARK, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlockWithShapes(BLACKSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        addVanillaBlock(BASE, MUD, FULL_BLOCK, Blocks.PACKED_MUD); // Packed Mud has no other shapes!
        registerBlockWithShapes(COBBLED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.PACKED_MUD)));
        registerBlockWithShapes(SMOOTH, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(CUT, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(POLISHED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(CHISELED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(MUD, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        addVanillaBlockWithShapes(MUD, BlockVariant.BRICKS, Blocks.MUD_BRICKS, Blocks.MUD_BRICK_STAIRS, Blocks.MUD_BRICK_SLAB, Blocks.MUD_BRICK_WALL);
        registerBlockWithShapes(CRACKED_BRICKS, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(DARK, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockWithShapes(MUD, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));

        // Base variant is the same as Brick variant
        registerBlockWithShapes(SMOOTH, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(COBBLED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(CUT, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(POLISHED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(CHISELED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        addVanillaBlockWithShapes(BRICKS, BlockVariant.BRICKS, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL);
        registerBlockWithShapes(CRACKED_BRICKS, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(DARK, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockWithShapes(BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));

        // Base variant is the same as Brick variant
        registerBlockWithShapes(COBBLED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockWithShapes(SMOOTH, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockWithShapes(CUT, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockWithShapes(POLISHED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addVanillaBlock(CHISELED, NETHER_BRICKS, FULL_BLOCK, Blocks.CHISELED_NETHER_BRICKS);
        registerBlockWithShapes(NETHER_BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addVanillaBlockWithShapes(NETHER_BRICKS, BlockVariant.BRICKS, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_WALL);
        addVanillaBlock(CRACKED_BRICKS, NETHER_BRICKS, FULL_BLOCK, Blocks.CRACKED_NETHER_BRICKS);
        registerBlockWithShapes(DARK, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockWithShapes(NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));

        // Base variant is the same as Polished variant
        registerBlockWithShapes(COBBLED, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addVanillaBlockWithShapes(SMOOTH, QUARTZ, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ_SLAB, null);
        registerBlockWithShapes(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addVanillaBlockWithShapes(POLISHED, QUARTZ, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_SLAB, null);
        addVanillaBlock(CHISELED, QUARTZ, FULL_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK);
        addVanillaBlock(QUARTZ, PILLAR, FULL_BLOCK, Blocks.QUARTZ_PILLAR);
        addVanillaBlockWithShapes(QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS, null, null, null);
        registerBlockWithShapes(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlockWithShapes(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlockWithShapes(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        addVanillaBlockWithShapes(BASE, PRISMARINE, Blocks.PRISMARINE, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_WALL);
        // Cobbled Prismarine would not really make sense as Prismarine is already cobble-like
        registerBlockWithShapes(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockWithShapes(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockWithShapes(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockWithShapes(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockWithShapes(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addVanillaBlockWithShapes(PRISMARINE, BlockVariant.BRICKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICK_SLAB, null);
        registerBlockWithShapes(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addVanillaBlockWithShapes(DARK, PRISMARINE, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE_SLAB, null);
        registerBlockWithShapes(PRISMARINE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));

        addVanillaBlockWithShapes(BASE, BASALT, Blocks.BASALT, null, null, null);
        addVanillaBlockWithShapes(SMOOTH, BASALT, Blocks.SMOOTH_BASALT, null, null, null);
        registerBlockWithShapes(COBBLED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.BASALT)));
        registerBlockWithShapes(CUT, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockWithShapes(POLISHED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockWithShapes(CHISELED, BASALT, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        addVanillaBlock(BASALT, PILLAR, FULL_BLOCK, Blocks.POLISHED_BASALT);
        registerBlockWithShapes(BASALT, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockWithShapes(CRACKED_BRICKS, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockWithShapes(DARK, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockWithShapes(BASALT, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    }

    private static void registerBlockWithShapes(BlockVariant variant, BlockMaterial material, Block block) {
        registerBlockWithShapes(material,variant,block);
    }
    private static void registerBlockWithShapes(BlockMaterial material, BlockVariant variant, Block block) {
        for(BlockShape shape : BlockShape.values()) {
            if(variant.hasShape(shape))
                registerBlock(block, material, variant, shape);
        }
    }

    private static void addVanillaBlockWithShapes(BlockMaterial material, BlockVariant variant, Block block, Block stairBlock, Block slabBlock, Block wallBlock) {
        addVanillaBlockWithShapes(variant, material, block, stairBlock, slabBlock, wallBlock);
    }
    private static void addVanillaBlockWithShapes(BlockVariant variant, BlockMaterial material, Block block, Block stairBlock, Block slabBlock, Block wallBlock) {
        addVanillaBlock(variant, material, FULL_BLOCK, block);
        addVanillaBlock(variant, material, STAIRS, stairBlock);
        addVanillaBlock(variant, material, SLAB, slabBlock);
        addVanillaBlock(variant, material, WALL, wallBlock);
    }

    private static void addVanillaBlock(BlockMaterial material, BlockVariant variant, BlockShape shape, Block block) {
        addVanillaBlock(variant, material, shape, block);
    }
    private static void addVanillaBlock(BlockVariant variant, BlockMaterial material, BlockShape shape, Block block){

        if(block == null && variant.hasShape(shape)) {
            getBlock(material, variant, FULL_BLOCK)
                    .ifPresent(fullBlock -> registerBlock(fullBlock, material, variant, shape));
        }
        else {
            setBlock(material, variant, shape, block);
        }
    }

    private static void registerBlock(Block block, BlockMaterial material, BlockVariant variant, BlockShape shape) {
        if(ModModels.isModelBlacklisted(material,variant,shape))
            return;
        Block shapedBlock = switch (shape){
            case STAIRS -> new StairsBlock(block.getDefaultState(), FabricBlockSettings.copyOf(block));
            case SLAB -> new SlabBlock(FabricBlockSettings.copyOf(block));
            case WALL -> new WallBlock(FabricBlockSettings.copyOf(block));
            default -> block;
        };
        String name = getName(material, variant, shape);
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

    public static String getName(BlockMaterial material, BlockVariant variant, BlockShape shape) {

        StringBuilder stringBuilder = new StringBuilder();

        if(variant.toString().contains(material.name())) {
            stringBuilder.append(variant.name());
        }
        else {
            switch (variant) {
                case BASE ->
                        stringBuilder.append(material);
                case PILLAR, BRICKS, TILES ->
                        stringBuilder.append(material.getSingular()).append("_").append(variant);
                case CRACKED_BRICKS ->
                        stringBuilder.append(variant.name().replace("_","_" + material + "_"));
                default ->
                        stringBuilder.append(variant).append("_").append(material);
            }
        }
        if(shape != FULL_BLOCK) {
            if(stringBuilder.toString().endsWith("S"))
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("_").append(shape);
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static boolean isBlockFromTheMod(Block block) {
        return Registries.BLOCK.getId(block).getNamespace().equals(StoneExpansion.MOD_ID);
    }
}
