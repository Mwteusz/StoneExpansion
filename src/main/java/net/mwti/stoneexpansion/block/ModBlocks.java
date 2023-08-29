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
import static net.mwti.stoneexpansion.block.BlockVariant.*;
import static net.mwti.stoneexpansion.block.BlockMaterial.*;

public class ModBlocks {

    private static final Block[][] blocks = new Block[17][10];

    public static Optional<Block> getBlock(BlockMaterial material, BlockVariant variant) {
        return Optional.ofNullable(blocks[material.ordinal()][variant.ordinal()]);
    }

    public static Optional<Block> getModdedBlock(BlockMaterial material, BlockVariant variant) {

        Optional<Block> blockOptional = getBlock(material, variant);

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

    private static void addBlockToTable(BlockMaterial material, BlockVariant variant, Block block){
        blocks[material.ordinal()][variant.ordinal()] = block;
    }

    private static void addBlockToTable(BlockVariant variant, BlockMaterial material, Block block){
        addBlockToTable(material, variant, block);
    }

    private static void registerBlock(BlockMaterial material, BlockVariant variant, Block block){

        String name = getName(material, variant);
        registerBlockItem(name, block);
        addBlockToTable(material, variant, block);
        Registry.register(Registries.BLOCK, new Identifier(StoneExpansion.MOD_ID, name), block);
    }

    private static void registerBlock(BlockVariant variant, BlockMaterial material, Block block){
        registerBlock(material,variant,block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, new Identifier(StoneExpansion.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static String getName(BlockMaterial material, BlockVariant variant) {

        if(isBricksBricksFunnyName(material, variant))
            return variant.toString().toLowerCase();

        String idName = switch(variant){
            case BASE ->
                material.toString();
            case SMOOTH, CUT, DARK, CHISELED, POLISHED ->
                variant + "_" + material;
            case PILLAR, BRICKS, TILES ->
                material + "_" + variant;
            case CRACKED_BRICKS ->
                variant.toString().replace("_","_" + material.toString() + "_");
        };
        return idName.toLowerCase();
    }

    private static boolean isBricksBricksFunnyName(BlockMaterial material, BlockVariant variant) {
        return material == BlockMaterial.BRICKS && (variant == BlockVariant.BRICKS || variant == BlockVariant.CRACKED_BRICKS);
    }

    public static void registerModBlocks(){

        addBlockToTable(BASE,ANDESITE, Blocks.ANDESITE);
        registerBlock(SMOOTH, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(CUT, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        addBlockToTable(POLISHED,ANDESITE, Blocks.POLISHED_ANDESITE);
        registerBlock(CHISELED, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(CRACKED_BRICKS, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(DARK, ANDESITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));
        registerBlock(ANDESITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

        addBlockToTable(BASE,GRANITE, Blocks.GRANITE);
        registerBlock(SMOOTH, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(CUT, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        addBlockToTable(POLISHED,GRANITE, Blocks.POLISHED_GRANITE);
        registerBlock(CHISELED, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(CRACKED_BRICKS, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(DARK, GRANITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));
        registerBlock(GRANITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_GRANITE)));

        addBlockToTable(BASE,DIORITE, Blocks.DIORITE);
        registerBlock(SMOOTH, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(CUT, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        addBlockToTable(POLISHED,DIORITE, Blocks.POLISHED_DIORITE);
        registerBlock(CHISELED, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(CRACKED_BRICKS, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DARK, DIORITE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));
        registerBlock(DIORITE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_DIORITE)));

        addBlockToTable(BASE,STONE, Blocks.STONE);
        registerBlock(SMOOTH, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CUT, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(POLISHED, STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(CHISELED, STONE, Blocks.CHISELED_STONE_BRICKS);
        registerBlock(STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(STONE, BlockVariant.BRICKS, Blocks.STONE_BRICKS);
        addBlockToTable(CRACKED_BRICKS, STONE, Blocks.CRACKED_STONE_BRICKS);
        registerBlock(DARK, STONE, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
        registerBlock(STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));

        addBlockToTable(BASE,MOSSY_STONE, Blocks.MOSSY_COBBLESTONE);
        registerBlock(SMOOTH, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CUT, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(POLISHED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(CHISELED, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        registerBlock(MOSSY_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SMOOTH_STONE)));
        addBlockToTable(MOSSY_STONE, BlockVariant.BRICKS, Blocks.MOSSY_STONE_BRICKS);
        registerBlock(CRACKED_BRICKS, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlock(DARK, MOSSY_STONE, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));
        registerBlock(MOSSY_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MOSSY_STONE_BRICKS)));

        addBlockToTable(BASE,END_STONE, Blocks.END_STONE);
        registerBlock(SMOOTH, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(CUT, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(POLISHED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));
        registerBlock(CHISELED, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(END_STONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        addBlockToTable(END_STONE, BlockVariant.BRICKS, Blocks.END_STONE_BRICKS);
        registerBlock(CRACKED_BRICKS, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(DARK, END_STONE, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
        registerBlock(END_STONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));

        addBlockToTable(BASE,PURPUR, Blocks.PURPUR_BLOCK);
        registerBlock(SMOOTH, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CUT, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(POLISHED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CHISELED, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        addBlockToTable(PURPUR, PILLAR, Blocks.PURPUR_PILLAR);
        registerBlock(PURPUR, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(CRACKED_BRICKS, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(DARK, PURPUR, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));
        registerBlock(PURPUR, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PURPUR_BLOCK)));

        addBlockToTable(BASE, SANDSTONE, Blocks.SANDSTONE);
        addBlockToTable(SMOOTH, SANDSTONE, Blocks.SMOOTH_SANDSTONE);
        addBlockToTable(CUT, SANDSTONE, Blocks.CUT_SANDSTONE);
        registerBlock(POLISHED, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_SANDSTONE)));
        addBlockToTable(CHISELED, SANDSTONE, Blocks.CHISELED_SANDSTONE);
        registerBlock(SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(CRACKED_BRICKS, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(DARK, SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));
        registerBlock(SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.SANDSTONE)));

        addBlockToTable(BASE,RED_SANDSTONE, Blocks.RED_SANDSTONE);
        addBlockToTable(SMOOTH, RED_SANDSTONE, Blocks.SMOOTH_RED_SANDSTONE);
        addBlockToTable(CUT, RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE);
        registerBlock(POLISHED, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.SMOOTH_RED_SANDSTONE)));
        addBlockToTable(CHISELED, RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE);
        registerBlock(RED_SANDSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(RED_SANDSTONE, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(CRACKED_BRICKS, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(DARK, RED_SANDSTONE, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));
        registerBlock(RED_SANDSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.RED_SANDSTONE)));

        addBlockToTable(BASE,DEEPSLATE, Blocks.COBBLED_DEEPSLATE);
        registerBlock(SMOOTH, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        registerBlock(CUT, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(POLISHED, DEEPSLATE, Blocks.POLISHED_DEEPSLATE);
        addBlockToTable(CHISELED, DEEPSLATE, Blocks.CHISELED_DEEPSLATE);
        registerBlock(DEEPSLATE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(DEEPSLATE, BlockVariant.BRICKS, Blocks.DEEPSLATE_BRICKS);
        addBlockToTable(CRACKED_BRICKS, DEEPSLATE, Blocks.CRACKED_DEEPSLATE_BRICKS);
        registerBlock(DARK, DEEPSLATE, new Block(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_BRICKS)));
        addBlockToTable(DEEPSLATE, TILES, Blocks.DEEPSLATE_TILES);

        addBlockToTable(BASE,BLACKSTONE, Blocks.BLACKSTONE);
        registerBlock(SMOOTH, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlock(CUT, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addBlockToTable(POLISHED, BLACKSTONE, Blocks.POLISHED_BLACKSTONE);
        addBlockToTable(CHISELED, BLACKSTONE, Blocks.CHISELED_POLISHED_BLACKSTONE);
        registerBlock(BLACKSTONE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        addBlockToTable(BLACKSTONE, BlockVariant.BRICKS, Blocks.POLISHED_BLACKSTONE_BRICKS);
        addBlockToTable(CRACKED_BRICKS, BLACKSTONE, Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS);
        registerBlock(DARK, BLACKSTONE, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));
        registerBlock(BLACKSTONE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BLACKSTONE_BRICKS)));

        addBlockToTable(BASE,MUD, Blocks.PACKED_MUD);
        registerBlock(SMOOTH, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(CUT, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(POLISHED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(CHISELED, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(MUD, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        addBlockToTable(MUD, BlockVariant.BRICKS, Blocks.MUD_BRICKS);
        registerBlock(CRACKED_BRICKS, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(DARK, MUD, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));
        registerBlock(MUD, TILES, new Block(FabricBlockSettings.copyOf(Blocks.MUD_BRICKS)));

        //addBlockToTable(BASE,BRICKS, Blocks.BRICKS); /// already exists as bricks
        registerBlock(SMOOTH, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(CUT, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(POLISHED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(CHISELED, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(BRICKS, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        addBlockToTable(BRICKS, BlockVariant.BRICKS, Blocks.BRICKS);
        registerBlock(CRACKED_BRICKS, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(DARK, BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));
        registerBlock(BRICKS, TILES, new Block(FabricBlockSettings.copyOf(Blocks.BRICKS)));

        //addBlockToTable(BASE,NETHERRACK, Blocks.NETHER_BRICKS); /// already exists as bricks
        registerBlock(SMOOTH, NETHERRACK, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(CUT, NETHERRACK, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(POLISHED, NETHERRACK, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addBlockToTable(CHISELED, NETHERRACK, Blocks.CHISELED_NETHER_BRICKS);
        registerBlock(NETHERRACK, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        addBlockToTable(NETHERRACK, BlockVariant.BRICKS, Blocks.NETHER_BRICKS);
        addBlockToTable(CRACKED_BRICKS, NETHERRACK, Blocks.CRACKED_NETHER_BRICKS);
        registerBlock(DARK, NETHERRACK, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));
        registerBlock(NETHERRACK, TILES, new Block(FabricBlockSettings.copyOf(Blocks.NETHER_BRICKS)));

        //addBlockToTable(BASE,QUARTZ, Blocks.QUARTZ_BLOCK); /// already exists as polished variant
        addBlockToTable(SMOOTH, QUARTZ, Blocks.SMOOTH_QUARTZ);
        registerBlock(CUT, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BLOCK)));
        addBlockToTable(POLISHED, QUARTZ, Blocks.QUARTZ_BLOCK); //to chyba tez
        addBlockToTable(CHISELED, QUARTZ, Blocks.CHISELED_QUARTZ_BLOCK);
        addBlockToTable(QUARTZ, PILLAR, Blocks.QUARTZ_PILLAR);
        addBlockToTable(QUARTZ, BlockVariant.BRICKS, Blocks.QUARTZ_BRICKS);
        registerBlock(CRACKED_BRICKS, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlock(DARK, QUARTZ, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));
        registerBlock(QUARTZ, TILES, new Block(FabricBlockSettings.copyOf(Blocks.QUARTZ_BRICKS)));

        //addBlockToTable(BASE,PRISMARINE, Blocks.PRISMARINE); /// destroys vanilla balance
        registerBlock(SMOOTH, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(CUT, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(POLISHED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(CHISELED, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        registerBlock(PRISMARINE, PILLAR, new PillarBlock(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addBlockToTable(PRISMARINE, BlockVariant.BRICKS, Blocks.PRISMARINE_BRICKS);
        registerBlock(CRACKED_BRICKS, PRISMARINE, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));
        addBlockToTable(DARK, PRISMARINE, Blocks.DARK_PRISMARINE);
        registerBlock(PRISMARINE, TILES, new Block(FabricBlockSettings.copyOf(Blocks.PRISMARINE_BRICKS)));

        addBlockToTable(BASE,BASALT, Blocks.BASALT);
        addBlockToTable(SMOOTH, BASALT, Blocks.SMOOTH_BASALT);
        registerBlock(CUT, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(POLISHED, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(CHISELED, BASALT, new PillarBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        addBlockToTable(BASALT, PILLAR, Blocks.POLISHED_BASALT);
        registerBlock(BASALT, BlockVariant.BRICKS, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(CRACKED_BRICKS, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(DARK, BASALT, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
        registerBlock(BASALT, TILES, new Block(FabricBlockSettings.copyOf(Blocks.POLISHED_BASALT)));
    }

    public static void forEachBlock(Consumer<Block> action) {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {

                getBlock(material,variant).ifPresent(action);
            }
        }
    }

    public static void getCreativeMenuBlocks(Consumer<Block> action) {
        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {

                if(variant != BASE)
                    getBlock(material,variant).ifPresent(action);
            }
        }
    }
}
