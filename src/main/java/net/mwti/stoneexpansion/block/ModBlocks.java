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

        addVanillaBlockShapes(BASE, ANDESITE, Blocks.ANDESITE, Blocks.ANDESITE_STAIRS, Blocks.ANDESITE_SLAB, Blocks.ANDESITE_WALL);
        registerBlockShapes(COBBLED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.ANDESITE)));
        registerBlockShapes(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        addVanillaBlockShapes(POLISHED, ANDESITE, Blocks.POLISHED_ANDESITE, Blocks.POLISHED_ANDESITE_STAIRS, Blocks.POLISHED_ANDESITE_SLAB,null);
        registerBlockShapes(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlockShapes(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        addVanillaBlockShapes(BASE, GRANITE, Blocks.GRANITE, Blocks.GRANITE_STAIRS, Blocks.GRANITE_SLAB, Blocks.GRANITE_WALL);
        registerBlockShapes(COBBLED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.GRANITE)));
        registerBlockShapes(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        addVanillaBlockShapes(POLISHED, GRANITE, Blocks.POLISHED_GRANITE, Blocks.POLISHED_GRANITE_STAIRS, Blocks.POLISHED_GRANITE_SLAB, null);
        registerBlockShapes(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlockShapes(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        addVanillaBlockShapes(BASE, DIORITE, Blocks.DIORITE, Blocks.DIORITE_STAIRS, Blocks.DIORITE_SLAB, Blocks.DIORITE_WALL);
        registerBlockShapes(COBBLED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.DIORITE)));
        registerBlockShapes(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        addVanillaBlockShapes(POLISHED, DIORITE, Blocks.POLISHED_DIORITE, Blocks.POLISHED_DIORITE_STAIRS, Blocks.POLISHED_DIORITE_SLAB, null);
        registerBlockShapes(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlockShapes(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        addVanillaBlockShapes(BASE, STONE, Blocks.STONE, Blocks.STONE_STAIRS, Blocks.STONE_SLAB, null);
        addVanillaBlockShapes(COBBLED, STONE, Blocks.COBBLESTONE, Blocks.COBBLESTONE_STAIRS, Blocks.COBBLESTONE_SLAB, Blocks.COBBLESTONE_WALL);
        registerBlockShapes(SMOOTH, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlockShapes(CHISELED, STONE, FULL_BLOCK, Blocks.CHISELED_STONE_BRICKS);
        registerBlockShapes(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlockShapes(STONE, BlockVariant.BRICKS, Blocks.STONE_BRICKS, Blocks.STONE_BRICK_STAIRS, Blocks.STONE_BRICK_SLAB, Blocks.STONE_BRICK_WALL);
        addVanillaBlockShapes(CRACKED_BRICKS, STONE, FULL_BLOCK, Blocks.CRACKED_STONE_BRICKS);
        registerBlockShapes(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        registerBlockShapes(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        // Mossy stone has no base
        addVanillaBlockShapes(COBBLED, MOSSY_STONE, Blocks.MOSSY_COBBLESTONE, Blocks.MOSSY_COBBLESTONE_STAIRS, Blocks.MOSSY_COBBLESTONE_SLAB, Blocks.MOSSY_COBBLESTONE_WALL);
        registerBlockShapes(SMOOTH, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlockShapes(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addVanillaBlockShapes(MOSSY_STONE, BlockVariant.BRICKS, Blocks.MOSSY_STONE_BRICKS, Blocks.MOSSY_STONE_BRICK_STAIRS, Blocks.MOSSY_STONE_BRICK_SLAB, Blocks.MOSSY_STONE_BRICK_WALL);
        registerBlockShapes(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlockShapes(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlockShapes(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        addVanillaBlockShapes(BASE, END_STONE, Blocks.END_STONE, null, null, null);
        registerBlockShapes(COBBLED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockShapes(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockShapes(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockShapes(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlockShapes(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockShapes(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        addVanillaBlockShapes(END_STONE, BlockVariant.BRICKS, Blocks.END_STONE_BRICKS, Blocks.END_STONE_BRICK_STAIRS, Blocks.END_STONE_BRICK_SLAB, Blocks.END_STONE_BRICK_WALL);
        registerBlockShapes(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockShapes(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlockShapes(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        addVanillaBlockShapes(BASE, PURPUR, Blocks.PURPUR_BLOCK, Blocks.PURPUR_STAIRS, Blocks.PURPUR_SLAB, null);
        registerBlockShapes(COBBLED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(SMOOTH, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(CUT, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(POLISHED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(CHISELED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        addVanillaBlockShapes(PURPUR, PILLAR, FULL_BLOCK, Blocks.PURPUR_PILLAR);
        registerBlockShapes(PURPUR, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(CRACKED_BRICKS, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(DARK, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlockShapes(PURPUR, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

        addVanillaBlockShapes(BASE, SANDSTONE, Blocks.SANDSTONE, Blocks.SANDSTONE_STAIRS, Blocks.SANDSTONE_SLAB, Blocks.SANDSTONE_WALL);
        registerBlockShapes(COBBLED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        addVanillaBlockShapes(SMOOTH, SANDSTONE, Blocks.SMOOTH_SANDSTONE, Blocks.SMOOTH_SANDSTONE_STAIRS, Blocks.SMOOTH_SANDSTONE_SLAB, null);
        addVanillaBlockShapes(CUT, SANDSTONE, Blocks.CUT_SANDSTONE, null, Blocks.CUT_SANDSTONE_SLAB, null);
        registerBlockShapes(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        addVanillaBlockShapes(CHISELED, SANDSTONE, FULL_BLOCK, Blocks.CHISELED_SANDSTONE);
        registerBlockShapes(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockShapes(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockShapes(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockShapes(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlockShapes(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        addVanillaBlockShapes(BASE, RED_SANDSTONE, Blocks.RED_SANDSTONE, Blocks.RED_SANDSTONE_STAIRS, Blocks.RED_SANDSTONE_SLAB, Blocks.RED_SANDSTONE_WALL);
        registerBlockShapes(COBBLED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        addVanillaBlockShapes(SMOOTH, RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE_STAIRS, Blocks.SMOOTH_RED_SANDSTONE_SLAB, null);
        addVanillaBlockShapes(CUT, RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE, null, Blocks.CUT_RED_SANDSTONE_SLAB, null);
        registerBlockShapes(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        addVanillaBlockShapes(CHISELED, RED_SANDSTONE, FULL_BLOCK, Blocks.CHISELED_RED_SANDSTONE);
        registerBlockShapes(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockShapes(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockShapes(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockShapes(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlockShapes(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        addVanillaBlockShapes(BASE, DEEPSLATE, FULL_BLOCK, Blocks.DEEPSLATE);
        addVanillaBlockShapes(COBBLED, DEEPSLATE, Blocks.COBBLED_DEEPSLATE, Blocks.COBBLED_DEEPSLATE_SLAB, Blocks.COBBLED_DEEPSLATE_STAIRS, Blocks.COBBLED_DEEPSLATE_WALL);
        registerBlockShapes(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        registerBlockShapes(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockShapes(POLISHED, DEEPSLATE, Blocks.POLISHED_DEEPSLATE, Blocks.POLISHED_DEEPSLATE_STAIRS, Blocks.POLISHED_DEEPSLATE_SLAB, Blocks.POLISHED_DEEPSLATE_WALL);
        addVanillaBlockShapes(CHISELED, DEEPSLATE, FULL_BLOCK, Blocks.CHISELED_DEEPSLATE);
        registerBlockShapes(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockShapes(DEEPSLATE, BlockVariant.BRICKS, Blocks.DEEPSLATE_BRICKS, Blocks.DEEPSLATE_BRICK_STAIRS, Blocks.DEEPSLATE_BRICK_SLAB, Blocks.DEEPSLATE_BRICK_WALL);
        addVanillaBlockShapes(CRACKED_BRICKS, DEEPSLATE, FULL_BLOCK, Blocks.CRACKED_DEEPSLATE_BRICKS);
        registerBlockShapes(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addVanillaBlockShapes(DEEPSLATE, TILES, Blocks.DEEPSLATE_TILES, Blocks.DEEPSLATE_TILE_STAIRS, Blocks.DEEPSLATE_TILE_SLAB, Blocks.DEEPSLATE_TILE_WALL);

        addVanillaBlockShapes(BASE, BLACKSTONE, Blocks.BLACKSTONE, Blocks.BLACKSTONE_STAIRS, Blocks.BLACKSTONE_SLAB, Blocks.BLACKSTONE_WALL);
        registerBlockShapes(COBBLED, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.BLACKSTONE)));
        registerBlockShapes(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlockShapes(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addVanillaBlockShapes(POLISHED, BLACKSTONE, Blocks.POLISHED_BLACKSTONE, Blocks.POLISHED_BLACKSTONE_STAIRS, Blocks.POLISHED_BLACKSTONE_SLAB, Blocks.POLISHED_BLACKSTONE_WALL);
        addVanillaBlockShapes(CHISELED, BLACKSTONE, FULL_BLOCK, Blocks.CHISELED_POLISHED_BLACKSTONE);
        registerBlockShapes(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addVanillaBlockShapes(BLACKSTONE, BlockVariant.BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        addVanillaBlockShapes(CRACKED_BRICKS, BLACKSTONE, FULL_BLOCK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerBlockShapes(DARK, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlockShapes(BLACKSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        addVanillaBlockShapes(BASE, MUD, FULL_BLOCK, Blocks.PACKED_MUD); // Packed Mud has no other shapes!
        registerBlockShapes(COBBLED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.PACKED_MUD)));
        registerBlockShapes(SMOOTH, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(CUT, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(POLISHED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(CHISELED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(MUD, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        addVanillaBlockShapes(MUD, BlockVariant.BRICKS, Blocks.MUD_BRICKS, Blocks.MUD_BRICK_STAIRS, Blocks.MUD_BRICK_SLAB, Blocks.MUD_BRICK_WALL);
        registerBlockShapes(CRACKED_BRICKS, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(DARK, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlockShapes(MUD, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));

        // Base variant is the same as Brick variant
        registerBlockShapes(SMOOTH, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(COBBLED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(CUT, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(POLISHED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(CHISELED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        addVanillaBlockShapes(BRICKS, BlockVariant.BRICKS, Blocks.BRICKS, Blocks.BRICK_STAIRS, Blocks.BRICK_SLAB, Blocks.BRICK_WALL);
        registerBlockShapes(CRACKED_BRICKS, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(DARK, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlockShapes(BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));

        // Base variant is the same as Brick variant
        registerBlockShapes(COBBLED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockShapes(SMOOTH, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockShapes(CUT, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockShapes(POLISHED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addVanillaBlockShapes(CHISELED, NETHER_BRICKS, FULL_BLOCK, Blocks.CHISELED_NETHER_BRICKS);
        registerBlockShapes(NETHER_BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addVanillaBlockShapes(NETHER_BRICKS, BlockVariant.BRICKS, Blocks.NETHER_BRICKS, Blocks.NETHER_BRICK_STAIRS, Blocks.NETHER_BRICK_SLAB, Blocks.NETHER_BRICK_WALL);
        addVanillaBlockShapes(CRACKED_BRICKS, NETHER_BRICKS, FULL_BLOCK, Blocks.CRACKED_NETHER_BRICKS);
        registerBlockShapes(DARK, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlockShapes(NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));

        // Base variant is the same as Polished variant
        registerBlockShapes(COBBLED, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addVanillaBlockShapes(SMOOTH, QUARTZ, Blocks.SMOOTH_QUARTZ, Blocks.SMOOTH_QUARTZ_STAIRS, Blocks.SMOOTH_QUARTZ_SLAB, null);
        registerBlockShapes(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addVanillaBlockShapes(POLISHED, QUARTZ, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_STAIRS, Blocks.QUARTZ_SLAB, null);
        addVanillaBlockShapes(CHISELED, QUARTZ, FULL_BLOCK, Blocks.CHISELED_QUARTZ_BLOCK);
        addVanillaBlockShapes(QUARTZ, PILLAR, FULL_BLOCK, Blocks.QUARTZ_PILLAR);
        addVanillaBlockShapes(QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS, null, null, null);
        registerBlockShapes(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlockShapes(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlockShapes(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        addVanillaBlockShapes(BASE, PRISMARINE, Blocks.PRISMARINE, Blocks.PRISMARINE_STAIRS, Blocks.PRISMARINE_SLAB, Blocks.PRISMARINE_WALL);
        // Cobbled Prismarine would not really make sense as Prismarine is already cobble-like
        registerBlockShapes(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockShapes(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockShapes(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockShapes(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlockShapes(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addVanillaBlockShapes(PRISMARINE, BlockVariant.BRICKS, Blocks.PRISMARINE_BRICKS, Blocks.PRISMARINE_BRICK_STAIRS, Blocks.PRISMARINE_BRICK_SLAB, null);
        registerBlockShapes(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addVanillaBlockShapes(DARK, PRISMARINE, Blocks.DARK_PRISMARINE, Blocks.DARK_PRISMARINE_STAIRS, Blocks.DARK_PRISMARINE_SLAB, null);
        registerBlockShapes(PRISMARINE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));

        addVanillaBlockShapes(BASE, BASALT, Blocks.BASALT, null, null, null);
        addVanillaBlockShapes(SMOOTH, BASALT, Blocks.SMOOTH_BASALT, null, null, null);
        registerBlockShapes(COBBLED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.BASALT)));
        registerBlockShapes(CUT, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockShapes(POLISHED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockShapes(CHISELED, BASALT, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        addVanillaBlockShapes(BASALT, PILLAR, FULL_BLOCK, Blocks.POLISHED_BASALT);
        registerBlockShapes(BASALT, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockShapes(CRACKED_BRICKS, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockShapes(DARK, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlockShapes(BASALT, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    }

    public static boolean isBlockFromTheMod(Block block) {
        return Registries.BLOCK.getId(block).getNamespace().equals(StoneExpansion.MOD_ID);
    }

    private static void addVanillaBlockShapes(BlockVariant variant, BlockMaterial material, Block block, Block stairBlock, Block slabBlock, Block wallBlock) {
        addVanillaBlockShapes(variant, material, FULL_BLOCK, block);
        addVanillaBlockShapes(variant, material, STAIRS, stairBlock);
        addVanillaBlockShapes(variant, material, SLAB, slabBlock);
        addVanillaBlockShapes(variant, material, WALL, wallBlock);
    }

    private static void addVanillaBlockShapes(BlockMaterial material, BlockVariant variant, Block block, Block stairBlock, Block slabBlock, Block wallBlock) {
        addVanillaBlockShapes(variant, material, block, stairBlock, slabBlock, wallBlock);
    }

    private static void addVanillaBlockShapes(BlockMaterial material, BlockVariant variant, BlockShape shape, Block block) {
        if(blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] != null)
            throw new RuntimeException("Block "+ getName(material,variant,shape) +" is already added");
        blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] = block;
    }

    private static void addVanillaBlockShapes(BlockVariant variant, BlockMaterial material, BlockShape shape, Block block){

        if(block == null && variant.hasShape(shape)) {
            getBlock(material, variant, FULL_BLOCK).ifPresent(fullBlock ->
                registerShapedBlock(fullBlock, material, variant, shape)
            );
        }
        else {
            addVanillaBlockShapes(material, variant, shape, block);
        }
    }

    private static void registerBlockShapes(BlockMaterial material, BlockVariant variant, Block block){
        for(BlockShape shape : BlockShape.values()) {
            if(variant.hasShape(shape))
                registerShapedBlock(block,material,variant,shape);
        }
    }

    private static void registerShapedBlock(Block block, BlockMaterial material, BlockVariant variant, BlockShape shape) {
        if(ModModels.isModelBlacklisted(material,variant,shape))
            return;
        Block shapedBlock = switch (shape){
            case STAIRS -> new StairsBlock(block.getDefaultState(), FabricBlockSettings.copyOf(block));
            case SLAB -> new SlabBlock(FabricBlockSettings.copyOf(block));
            case WALL -> new WallBlock(FabricBlockSettings.copyOf(block));
            default -> block;
        };
        String name = getName(material, variant, shape);
        registerBlockItem(name, shapedBlock);
        addVanillaBlockShapes(material, variant, shape, shapedBlock);
        Registry.register(Registries.BLOCK, new Identifier(StoneExpansion.MOD_ID, name), shapedBlock);
    }

    private static void registerBlockShapes(BlockVariant variant, BlockMaterial material, Block block){
        registerBlockShapes(material,variant,block);
    }

    static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(StoneExpansion.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
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

    public static void forEachBlock(Consumer<Block> action) {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                for(BlockShape shape : BlockShape.values()) {

                    getBlock(material, variant, shape).ifPresent(action);
                }
            }
        }
    }
    public static void forEachBlock(BlockShape shape, Consumer<Block> action) {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                getBlock(material, variant, shape).ifPresent(action);
            }
        }
    }


    public static void getCreativeMenuBlocks(Consumer<Block> action) {
        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                for(BlockShape shape : BlockShape.values()) {
                    getBlock(material,variant,shape).ifPresent(action);
                }
            }
        }
    }
}
