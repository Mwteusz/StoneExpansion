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

    public static boolean isBlockFromTheMod(Block block) {
        return Registries.BLOCK.getId(block).getNamespace().equals(StoneExpansion.MOD_ID);
    }
    public static boolean isBlockFromTheMod(BlockMaterial material, BlockVariant variant, BlockShape shape) {
        Optional<Block> block = getBlock(material, variant, shape);
        return block.filter(ModBlocks::isBlockFromTheMod).isPresent();
    }

    private static void addBlockToTable(BlockMaterial material, BlockVariant variant, BlockShape shape, Block block) {
        if(blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] != null)
            throw new RuntimeException("Block "+ getName(material,variant,shape) +" is already added");
        blocks[material.ordinal()][variant.ordinal()][shape.ordinal()] = block;
    }

    private static void addBlockToTable(BlockVariant variant, BlockMaterial material, BlockShape shape, Block block){
        addBlockToTable(material, variant, shape, block);
    }

    private static void registerBlock(BlockMaterial material, BlockVariant variant, Block block){

        for(BlockShape shape : BlockShape.values()) {
            if(variant.hasShape(shape))
                registerShapedBlock(block,material,variant,shape);
        }
    }

    private static void registerShapedBlock(Block block, BlockMaterial material, BlockVariant variant, BlockShape shape) {
        String name = getName(material, variant, shape);
        Block shapedBlock = switch (shape){
            case STAIRS -> new StairsBlock(block.getDefaultState(), FabricBlockSettings.copyOf(block));
            case SLAB -> new SlabBlock(FabricBlockSettings.copyOf(block));
            case WALL -> new WallBlock(FabricBlockSettings.copyOf(block));
            default -> block;
        };
        registerBlockItem(name, shapedBlock);
        addBlockToTable(material, variant, shape, shapedBlock);
        Registry.register(Registries.BLOCK, new Identifier(StoneExpansion.MOD_ID, name), shapedBlock);
    }

    private static void registerBlock(BlockVariant variant, BlockMaterial material, Block block){
        registerBlock(material,variant,block);
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
        if(shape != BLOCK) {
            if(stringBuilder.toString().endsWith("S"))
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("_").append(shape);
        }
        return stringBuilder.toString().toLowerCase();
    }

    public static void registerModBlocks(){

        addBlockToTable(BASE, ANDESITE, BLOCK, Blocks.ANDESITE);
        addBlockToTable(BASE, ANDESITE, STAIRS, Blocks.ANDESITE_STAIRS);
        addBlockToTable(BASE, ANDESITE, SLAB, Blocks.ANDESITE_SLAB);
        addBlockToTable(BASE, ANDESITE, WALL, Blocks.ANDESITE_WALL);
        registerBlock(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        addBlockToTable(POLISHED, ANDESITE, BLOCK, Blocks.POLISHED_ANDESITE);
        addBlockToTable(POLISHED, ANDESITE, STAIRS, Blocks.POLISHED_ANDESITE_STAIRS);
        addBlockToTable(POLISHED, ANDESITE, SLAB, Blocks.POLISHED_ANDESITE_SLAB);
        //addBlockToTable(POLISHED, ANDESITE, BlockShape.WALL, Blocks.POLISHED_ANDESITE_WALL);
        registerBlock(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        addBlockToTable(BASE,GRANITE, BLOCK, Blocks.GRANITE);
        addBlockToTable(BASE,GRANITE, STAIRS, Blocks.GRANITE_STAIRS);
        addBlockToTable(BASE,GRANITE, SLAB, Blocks.GRANITE_SLAB);
        addBlockToTable(BASE,GRANITE, WALL, Blocks.GRANITE_WALL);
        registerBlock(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        addBlockToTable(POLISHED,GRANITE, BLOCK, Blocks.POLISHED_GRANITE);
        addBlockToTable(POLISHED,GRANITE, STAIRS, Blocks.POLISHED_GRANITE_STAIRS);
        addBlockToTable(POLISHED,GRANITE, SLAB, Blocks.POLISHED_GRANITE_SLAB);
        registerBlock(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        addBlockToTable(BASE,DIORITE, BLOCK, Blocks.DIORITE);
        addBlockToTable(BASE,DIORITE, STAIRS, Blocks.DIORITE_STAIRS);
        addBlockToTable(BASE,DIORITE, SLAB, Blocks.DIORITE_SLAB);
        addBlockToTable(BASE,DIORITE, WALL, Blocks.DIORITE_WALL);
        registerBlock(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        addBlockToTable(POLISHED,DIORITE, BLOCK, Blocks.POLISHED_DIORITE);
        addBlockToTable(POLISHED,DIORITE, STAIRS, Blocks.POLISHED_DIORITE_STAIRS);
        addBlockToTable(POLISHED,DIORITE, SLAB, Blocks.POLISHED_DIORITE_SLAB);
        registerBlock(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        addBlockToTable(BASE, STONE, BLOCK, Blocks.STONE);
        addBlockToTable(BASE, STONE, STAIRS, Blocks.STONE_STAIRS);
        addBlockToTable(BASE, STONE, SLAB, Blocks.STONE_SLAB);
        registerBlock(SMOOTH, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(CHISELED, STONE, BLOCK, Blocks.CHISELED_STONE_BRICKS);
        registerBlock(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(STONE, BlockVariant.BRICKS, BLOCK, Blocks.STONE_BRICKS);
        addBlockToTable(STONE, BlockVariant.BRICKS, STAIRS, Blocks.STONE_BRICK_STAIRS);
        addBlockToTable(STONE, BlockVariant.BRICKS, SLAB, Blocks.STONE_BRICK_SLAB);
        addBlockToTable(STONE, BlockVariant.BRICKS, WALL, Blocks.STONE_BRICK_WALL);
        addBlockToTable(CRACKED_BRICKS, STONE, BLOCK, Blocks.CRACKED_STONE_BRICKS);
        registerBlock(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        registerBlock(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        addBlockToTable(BASE, MOSSY_STONE, BLOCK, Blocks.MOSSY_COBBLESTONE);
        addBlockToTable(BASE, MOSSY_STONE, STAIRS, Blocks.MOSSY_COBBLESTONE_STAIRS);
        addBlockToTable(BASE, MOSSY_STONE, SLAB, Blocks.MOSSY_COBBLESTONE_SLAB);
        addBlockToTable(BASE, MOSSY_STONE, WALL, Blocks.MOSSY_COBBLESTONE_WALL);
        registerBlock(SMOOTH, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(MOSSY_STONE, BlockVariant.BRICKS, BLOCK, Blocks.MOSSY_STONE_BRICKS);
        addBlockToTable(MOSSY_STONE, BlockVariant.BRICKS, STAIRS, Blocks.MOSSY_STONE_BRICK_STAIRS);
        addBlockToTable(MOSSY_STONE, BlockVariant.BRICKS, SLAB, Blocks.MOSSY_STONE_BRICK_SLAB);
        addBlockToTable(MOSSY_STONE, BlockVariant.BRICKS, WALL, Blocks.MOSSY_STONE_BRICK_WALL);
        registerBlock(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlock(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlock(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        addBlockToTable(BASE, END_STONE, BLOCK, Blocks.END_STONE);
        //addBlockToTable(BASE,END_STONE, BlockShape.STAIRS, Blocks.END_STONE_STAIRS);
        //addBlockToTable(BASE,END_STONE, BlockShape.SLAB, Blocks.END_STONE_SLAB);
        registerBlock(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        addBlockToTable(END_STONE, BlockVariant.BRICKS, BLOCK, Blocks.END_STONE_BRICKS);
        addBlockToTable(END_STONE, BlockVariant.BRICKS, STAIRS, Blocks.END_STONE_BRICK_STAIRS);
        addBlockToTable(END_STONE, BlockVariant.BRICKS, SLAB, Blocks.END_STONE_BRICK_SLAB);
        addBlockToTable(END_STONE, BlockVariant.BRICKS, WALL, Blocks.END_STONE_BRICK_WALL);
        registerBlock(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        addBlockToTable(BASE, PURPUR, BLOCK, Blocks.PURPUR_BLOCK);
        addBlockToTable(BASE, PURPUR, STAIRS, Blocks.PURPUR_STAIRS);
        addBlockToTable(BASE, PURPUR, SLAB, Blocks.PURPUR_SLAB);
        registerBlock(SMOOTH, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CUT, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(POLISHED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CHISELED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        addBlockToTable(PURPUR, PILLAR, BLOCK, Blocks.PURPUR_PILLAR);
        registerBlock(PURPUR, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CRACKED_BRICKS, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(DARK, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(PURPUR, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

        addBlockToTable(BASE, SANDSTONE, BLOCK, Blocks.SANDSTONE);
        addBlockToTable(BASE, SANDSTONE, STAIRS, Blocks.SANDSTONE_STAIRS);
        addBlockToTable(BASE, SANDSTONE, SLAB, Blocks.SANDSTONE_SLAB);
        addBlockToTable(BASE, SANDSTONE, WALL, Blocks.SANDSTONE_WALL);
        addBlockToTable(SMOOTH, SANDSTONE, BLOCK, Blocks.SMOOTH_SANDSTONE);
        addBlockToTable(SMOOTH, SANDSTONE, STAIRS, Blocks.SMOOTH_SANDSTONE_STAIRS);
        addBlockToTable(SMOOTH, SANDSTONE, SLAB, Blocks.SMOOTH_SANDSTONE_SLAB);
        addBlockToTable(CUT, SANDSTONE, BLOCK, Blocks.CUT_SANDSTONE);
        addBlockToTable(CUT, SANDSTONE, SLAB, Blocks.CUT_SANDSTONE_SLAB);
        registerBlock(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        addBlockToTable(CHISELED, SANDSTONE, BLOCK, Blocks.CHISELED_SANDSTONE);
        registerBlock(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        addBlockToTable(BASE, RED_SANDSTONE, BLOCK, Blocks.RED_SANDSTONE);
        addBlockToTable(BASE, RED_SANDSTONE, STAIRS, Blocks.RED_SANDSTONE_STAIRS);
        addBlockToTable(BASE, RED_SANDSTONE, SLAB, Blocks.RED_SANDSTONE_SLAB);
        addBlockToTable(BASE, RED_SANDSTONE, WALL, Blocks.RED_SANDSTONE_WALL);
        addBlockToTable(SMOOTH, RED_SANDSTONE, BLOCK, Blocks.SMOOTH_RED_SANDSTONE);
        addBlockToTable(SMOOTH, RED_SANDSTONE, STAIRS, Blocks.SMOOTH_RED_SANDSTONE_STAIRS);
        addBlockToTable(SMOOTH, RED_SANDSTONE, SLAB, Blocks.SMOOTH_RED_SANDSTONE_SLAB);
        addBlockToTable(CUT, RED_SANDSTONE, BLOCK, Blocks.CUT_RED_SANDSTONE);
        addBlockToTable(CUT, RED_SANDSTONE, SLAB, Blocks.CUT_RED_SANDSTONE_SLAB);
        registerBlock(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        addBlockToTable(CHISELED, RED_SANDSTONE, BLOCK, Blocks.CHISELED_RED_SANDSTONE);
        registerBlock(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        addBlockToTable(BASE, DEEPSLATE, BLOCK, Blocks.COBBLED_DEEPSLATE);
        addBlockToTable(BASE, DEEPSLATE, STAIRS, Blocks.COBBLED_DEEPSLATE_STAIRS);
        addBlockToTable(BASE, DEEPSLATE, SLAB, Blocks.COBBLED_DEEPSLATE_SLAB);
        registerBlock(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        registerBlock(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(POLISHED, DEEPSLATE, BLOCK, Blocks.POLISHED_DEEPSLATE);
        addBlockToTable(POLISHED, DEEPSLATE, STAIRS, Blocks.POLISHED_DEEPSLATE_STAIRS);
        addBlockToTable(POLISHED, DEEPSLATE, SLAB, Blocks.POLISHED_DEEPSLATE_SLAB);
        addBlockToTable(POLISHED, DEEPSLATE, WALL, Blocks.POLISHED_DEEPSLATE_WALL);
        addBlockToTable(CHISELED, DEEPSLATE, BLOCK, Blocks.CHISELED_DEEPSLATE);
        registerBlock(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(DEEPSLATE, BlockVariant.BRICKS, BLOCK, Blocks.DEEPSLATE_BRICKS);
        addBlockToTable(DEEPSLATE, BlockVariant.BRICKS, STAIRS, Blocks.DEEPSLATE_BRICK_STAIRS);
        addBlockToTable(DEEPSLATE, BlockVariant.BRICKS, SLAB, Blocks.DEEPSLATE_BRICK_SLAB);
        addBlockToTable(DEEPSLATE, BlockVariant.BRICKS, WALL, Blocks.DEEPSLATE_BRICK_WALL);
        addBlockToTable(CRACKED_BRICKS, DEEPSLATE, BLOCK, Blocks.CRACKED_DEEPSLATE_BRICKS);
        registerBlock(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(DEEPSLATE, TILES, BLOCK, Blocks.DEEPSLATE_TILES);
        addBlockToTable(DEEPSLATE, TILES, STAIRS, Blocks.DEEPSLATE_TILE_STAIRS);
        addBlockToTable(DEEPSLATE, TILES, SLAB, Blocks.DEEPSLATE_TILE_SLAB);
        addBlockToTable(DEEPSLATE, TILES, WALL, Blocks.DEEPSLATE_TILE_WALL);

        addBlockToTable(BASE,BLACKSTONE, BLOCK, Blocks.BLACKSTONE);
        addBlockToTable(BASE,BLACKSTONE, STAIRS, Blocks.BLACKSTONE_STAIRS);
        addBlockToTable(BASE,BLACKSTONE, SLAB, Blocks.BLACKSTONE_SLAB);
        addBlockToTable(BASE,BLACKSTONE, WALL, Blocks.BLACKSTONE_WALL);
        registerBlock(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlock(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addBlockToTable(POLISHED, BLACKSTONE, BLOCK, Blocks.POLISHED_BLACKSTONE);
        addBlockToTable(POLISHED, BLACKSTONE, STAIRS, Blocks.POLISHED_BLACKSTONE_STAIRS);
        addBlockToTable(POLISHED, BLACKSTONE, SLAB, Blocks.POLISHED_BLACKSTONE_SLAB);
        addBlockToTable(POLISHED, BLACKSTONE, WALL, Blocks.POLISHED_BLACKSTONE_WALL);
        addBlockToTable(CHISELED, BLACKSTONE, BLOCK, Blocks.CHISELED_POLISHED_BLACKSTONE);
        registerBlock(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addBlockToTable(BLACKSTONE, BlockVariant.BRICKS, BLOCK, Blocks.POLISHED_BLACKSTONE_BRICKS);
        addBlockToTable(BLACKSTONE, BlockVariant.BRICKS, STAIRS, Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS);
        addBlockToTable(BLACKSTONE, BlockVariant.BRICKS, SLAB, Blocks.POLISHED_BLACKSTONE_BRICK_SLAB);
        addBlockToTable(BLACKSTONE, BlockVariant.BRICKS, WALL, Blocks.POLISHED_BLACKSTONE_BRICK_WALL);
        addBlockToTable(CRACKED_BRICKS, BLACKSTONE, BLOCK, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerBlock(DARK, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlock(BLACKSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        addBlockToTable(BASE,MUD, BLOCK, Blocks.PACKED_MUD);
        registerBlock(SMOOTH, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(CUT, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(POLISHED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(CHISELED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(MUD, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        addBlockToTable(MUD, BlockVariant.BRICKS, BLOCK, Blocks.MUD_BRICKS);
        addBlockToTable(MUD, BlockVariant.BRICKS, STAIRS, Blocks.MUD_BRICK_STAIRS);
        addBlockToTable(MUD, BlockVariant.BRICKS, SLAB, Blocks.MUD_BRICK_SLAB);
        addBlockToTable(MUD, BlockVariant.BRICKS, WALL, Blocks.MUD_BRICK_WALL);
        registerBlock(CRACKED_BRICKS, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(DARK, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(MUD, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));

        //addBlockToTable(BASE,BRICKS, Blocks.BRICKS); /// already exists as bricks
        registerBlock(SMOOTH, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(CUT, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(POLISHED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(CHISELED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        addBlockToTable(BRICKS, BlockVariant.BRICKS, BLOCK, Blocks.BRICKS);
        addBlockToTable(BRICKS, BlockVariant.BRICKS, STAIRS, Blocks.BRICK_STAIRS);
        addBlockToTable(BRICKS, BlockVariant.BRICKS, SLAB, Blocks.BRICK_SLAB);
        addBlockToTable(BRICKS, BlockVariant.BRICKS, WALL, Blocks.BRICK_WALL);
        registerBlock(CRACKED_BRICKS, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(DARK, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));

        //addBlockToTable(BASE,NETHERRACK, Blocks.NETHER_BRICKS); /// already exists as bricks
        registerBlock(SMOOTH, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(CUT, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(POLISHED, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addBlockToTable(CHISELED, NETHER_BRICKS, BLOCK, Blocks.CHISELED_NETHER_BRICKS);
        registerBlock(NETHER_BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addBlockToTable(NETHER_BRICKS, BlockVariant.BRICKS, BLOCK, Blocks.NETHER_BRICKS);
        addBlockToTable(NETHER_BRICKS, BlockVariant.BRICKS, STAIRS, Blocks.NETHER_BRICK_STAIRS);
        addBlockToTable(NETHER_BRICKS, BlockVariant.BRICKS, SLAB, Blocks.NETHER_BRICK_SLAB);
        addBlockToTable(NETHER_BRICKS, BlockVariant.BRICKS, WALL, Blocks.NETHER_BRICK_WALL);
        addBlockToTable(CRACKED_BRICKS, NETHER_BRICKS, BLOCK, Blocks.CRACKED_NETHER_BRICKS);
        registerBlock(DARK, NETHER_BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(NETHER_BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));

        //addBlockToTable(BASE,QUARTZ, Blocks.QUARTZ_BLOCK); /// already exists as polished variant
        addBlockToTable(SMOOTH, QUARTZ, BLOCK, Blocks.SMOOTH_QUARTZ);
        addBlockToTable(SMOOTH, QUARTZ, STAIRS, Blocks.SMOOTH_QUARTZ_STAIRS);
        addBlockToTable(SMOOTH, QUARTZ, SLAB, Blocks.SMOOTH_QUARTZ_SLAB);
        registerBlock(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addBlockToTable(POLISHED, QUARTZ, BLOCK, Blocks.QUARTZ_BLOCK);
        addBlockToTable(POLISHED, QUARTZ, STAIRS, Blocks.QUARTZ_STAIRS);
        addBlockToTable(POLISHED, QUARTZ, SLAB, Blocks.QUARTZ_SLAB);
        addBlockToTable(CHISELED, QUARTZ, BLOCK, Blocks.CHISELED_QUARTZ_BLOCK);
        addBlockToTable(QUARTZ, PILLAR, BLOCK, Blocks.QUARTZ_PILLAR);
        addBlockToTable(QUARTZ, BlockVariant.BRICKS, BlockShape.STAIRS, Blocks.QUARTZ_BRICKS);
        //addBlockToTable(QUARTZ, BlockVariant.BRICKS, BlockShape.STAIRS, Blocks.QUARTZ_BRICK_STAIRS);
        //addBlockToTable(QUARTZ, BlockVariant.BRICKS, BlockShape.SLAB, Blocks.QUARTZ_BRICK_SLAB);
        registerBlock(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlock(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlock(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        addBlockToTable(BASE, PRISMARINE, BLOCK, Blocks.PRISMARINE);
        addBlockToTable(BASE, PRISMARINE, STAIRS, Blocks.PRISMARINE_STAIRS);
        addBlockToTable(BASE, PRISMARINE, SLAB, Blocks.PRISMARINE_SLAB);
        addBlockToTable(BASE, PRISMARINE, WALL, Blocks.PRISMARINE_WALL);
        registerBlock(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addBlockToTable(PRISMARINE, BlockVariant.BRICKS, BLOCK, Blocks.PRISMARINE_BRICKS);
        addBlockToTable(PRISMARINE, BlockVariant.BRICKS, STAIRS, Blocks.PRISMARINE_BRICK_STAIRS);
        addBlockToTable(PRISMARINE, BlockVariant.BRICKS, SLAB, Blocks.PRISMARINE_BRICK_SLAB);
        registerBlock(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addBlockToTable(DARK, PRISMARINE, BLOCK, Blocks.DARK_PRISMARINE);
        addBlockToTable(DARK, PRISMARINE, STAIRS, Blocks.DARK_PRISMARINE_STAIRS);
        addBlockToTable(DARK, PRISMARINE, SLAB, Blocks.DARK_PRISMARINE_SLAB);
        registerBlock(PRISMARINE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));

        addBlockToTable(BASE,BASALT, BLOCK, Blocks.BASALT);
        addBlockToTable(SMOOTH, BASALT, BLOCK, Blocks.SMOOTH_BASALT);
        //addBlockToTable(SMOOTH, BASALT, BlockShape.STAIRS, Blocks.SMOOTH_BASALT_STAIRS);
        //addBlockToTable(SMOOTH, BASALT, BlockShape.SLAB, Blocks.SMOOTH_BASALT_SLAB);
        registerBlock(CUT, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(POLISHED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(CHISELED, BASALT, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        addBlockToTable(BASALT, PILLAR, BLOCK, Blocks.POLISHED_BASALT);
        //addBlockToTable(BASALT, PILLAR, BlockShape.STAIRS, Blocks.POLISHED_BASALT_STAIRS);
        //addBlockToTable(BASALT, PILLAR, BlockShape.SLAB, Blocks.POLISHED_BASALT_SLAB);
        registerBlock(BASALT, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(CRACKED_BRICKS, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(DARK, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(BASALT, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
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
