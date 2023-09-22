package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.block.*;

import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.mwti.stoneexpansion.block.BlockMaterial.*;
import static net.mwti.stoneexpansion.block.BlockShape.*;
import static net.mwti.stoneexpansion.block.BlockVariant.BASE;
import static net.mwti.stoneexpansion.block.BlockVariant.POLISHED;


public class ModModels extends FabricModelProvider {
    public ModModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {

                switch (variant) {
                    case PILLAR -> generatePillarBlock(material, blockStateModelGenerator);
                    case CHISELED -> generateChiseledBlock(material, blockStateModelGenerator);
                    case CUT -> generateCutBlock(material, blockStateModelGenerator);
                    default -> generateCube(material, variant, blockStateModelGenerator);
                }
                if(variant.hasShape(STAIRS)) {
                    generateStairs(material, variant, blockStateModelGenerator);
                }
                if(variant.hasShape(SLAB)) {
                    generateSlab(material, variant, blockStateModelGenerator);
                }
                if(variant.hasShape(WALL)) {
                    generateWall(material, variant, blockStateModelGenerator);
                }
            }
        }
    }

    private static void generateSlab(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, SLAB).ifPresent(slabBlock ->
                ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                    TexturedModel texturedModel = variant.usesColumnModel() ?
                            TexturedModel.CUBE_COLUMN.get(block) :
                            TexturedModel.CUBE_ALL.get(block);
                    Identifier fullBlockModel = ModelIds.getBlockModelId(block);
                    Identifier bottomModel = Models.SLAB.upload(slabBlock, texturedModel.getTextures(), modelGenerator.modelCollector);
                    Identifier topModel = Models.SLAB_TOP.upload(slabBlock, texturedModel.getTextures(), modelGenerator.modelCollector);
                    modelGenerator.blockStateCollector.accept(createSlabBlockState(slabBlock, bottomModel, topModel, fullBlockModel));
                })
        );
    }

    private static void generateStairs(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, BlockShape.STAIRS).ifPresent(stairsBlock ->
            ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
                modelGenerator.new BlockTexturePool(texturedModel.getTextures())
                        .stairs(stairsBlock);
            })
        );
    }

    private static void generateWall(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, WALL).ifPresent(wallBlock ->
                ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                    TexturedModel texturedModel = TexturedModel.CUBE_ALL.get(block);
                    modelGenerator.new BlockTexturePool(texturedModel.getTextures())
                            .wall(wallBlock);
                })
        );
    }

    private static void generateCube(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {
        ModBlocks.getModdedBlock(material, variant, FULL_BLOCK).ifPresent(modelGenerator::registerCubeAllModelTexturePool);
    }

    private static void generateCutBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CUT, FULL_BLOCK).ifPresent(block ->
            modelGenerator.registerSingleton(block, TexturedModel.CUBE_COLUMN)
        );
    }

    private static void generatePillarBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.PILLAR, FULL_BLOCK).ifPresent(block ->
            modelGenerator.registerAxisRotated(block,
                    TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                    TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL)
        );
    }

    private static void generateChiseledBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CHISELED, FULL_BLOCK).ifPresent(block -> {

            if (material == BASALT) {
                modelGenerator.registerAxisRotated(block,
                        TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                        TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            } else {
                modelGenerator.registerSimpleCubeAll(block);
            }
        });
    }

    /* temporary fix */
    public static boolean isModelBlacklisted(BlockMaterial material, BlockVariant variant, BlockShape shape) {
        return      (variant == POLISHED    && material == QUARTZ       && shape == WALL)
                ||  (variant == BASE        && material == BASALT       && shape != FULL_BLOCK)
                ||  (variant == BASE        && material == DEEPSLATE    && shape != FULL_BLOCK);
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
