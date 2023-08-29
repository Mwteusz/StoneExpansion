package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.*;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;

import java.util.NoSuchElementException;

import static net.mwti.stoneexpansion.block.BlockMaterial.BASALT;
import static net.mwti.stoneexpansion.block.BlockVariant.CHISELED;
import static net.mwti.stoneexpansion.block.ModBlocks.getBlock;


public class ModModels extends FabricModelProvider {
    public ModModels(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {


        for(BlockMaterial material : BlockMaterial.values()) {

            //simple cubes :)
            generateCube(material, BlockVariant.SMOOTH, blockStateModelGenerator);
            generateCube(material, BlockVariant.POLISHED, blockStateModelGenerator);
            generateCube(material, BlockVariant.BRICKS, blockStateModelGenerator);
            generateCube(material, BlockVariant.CRACKED_BRICKS, blockStateModelGenerator);
            generateCube(material, BlockVariant.DARK, blockStateModelGenerator);
            generateCube(material, BlockVariant.TILES, blockStateModelGenerator);

            //weird shapes that took me 3 hours to understand syntax of :(
            generateCutBlock(material, blockStateModelGenerator);
            generateChiseledBlock(material, blockStateModelGenerator);
            generatePillarBlock(material, blockStateModelGenerator);
        }
    }

    private static void generateCube(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator blockStateModelGenerator) {
        ModBlocks.getModdedBlock(material, variant).ifPresent(blockStateModelGenerator::registerSimpleCubeAll);
    }

    private static void generateCutBlock(BlockMaterial material, BlockStateModelGenerator blockStateModelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CUT).ifPresent(block -> {
            blockStateModelGenerator.registerSingleton(block, TexturedModel.CUBE_COLUMN);
        });
    }

    private static void generatePillarBlock(BlockMaterial material, BlockStateModelGenerator blockStateModelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.PILLAR).ifPresent(block -> {
            blockStateModelGenerator.registerAxisRotated(block,
                    TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                    TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
        });
    }

    private static void generateChiseledBlock(BlockMaterial material, BlockStateModelGenerator blockStateModelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CHISELED).ifPresent(block -> {

            if (block == getBlock(BASALT, CHISELED).orElseThrow(() -> new NoSuchElementException("this is bad"))) {
                blockStateModelGenerator.registerAxisRotated(block,
                        TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                        TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
            } else {
                blockStateModelGenerator.registerSimpleCubeAll(block);
            }
        });
    }


    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }
}
