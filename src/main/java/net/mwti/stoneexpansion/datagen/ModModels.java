package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.*;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.block.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import static net.minecraft.data.client.BlockStateModelGenerator.*;
import static net.mwti.stoneexpansion.StoneExpansion.MOD_ID;
import static net.mwti.stoneexpansion.block.BlockMaterial.*;
import static net.mwti.stoneexpansion.block.BlockShape.*;
import static net.mwti.stoneexpansion.block.BlockVariant.*;


public class ModModels extends FabricModelProvider {

    public ModModels(FabricDataOutput output) {
        super(output);
    }

    public static final Model TEMPLATE_PILLAR_WALL_POST = block("template_pillar_wall_post", "_post", TextureKey.WALL, TextureKey.TOP, TextureKey.BOTTOM);
    public static final Model TEMPLATE_PILLAR_WALL_SIDE = block("template_pillar_wall_side", "_side", TextureKey.WALL, TextureKey.TOP, TextureKey.BOTTOM);
    public static final Model TEMPLATE_PILLAR_WALL_SIDE_TALL = block("template_pillar_wall_side_tall", "_side_tall", TextureKey.WALL, TextureKey.TOP, TextureKey.BOTTOM);
    public static final Model PILLAR_WALL_INVENTORY = block("pillar_wall_inventory", "_inventory", TextureKey.WALL, TextureKey.TOP, TextureKey.BOTTOM);

    private static Model block(String parent, String variant, TextureKey... requiredTextureKeys) {
        return new Model(Optional.of(new Identifier(MOD_ID, "block/" + parent)), Optional.of(variant), requiredTextureKeys);
    }


    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                for (BlockShape shape : BlockShape.values()) {
                    generateBlockShape(material, variant, shape, blockStateModelGenerator);
                }
            }
        }
    }

    private static void generateBlock(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator blockStateModelGenerator) {
        switch (variant) {
            case PILLAR -> generatePillarBlock(material, blockStateModelGenerator);
            case CHISELED -> generateChiseledBlock(material, blockStateModelGenerator);
            case CUT -> generateCutBlock(material, blockStateModelGenerator);
            default -> generateCube(material, variant, blockStateModelGenerator);
        }
    }

    private void generateBlockShape(BlockMaterial material, BlockVariant variant, BlockShape shape, BlockStateModelGenerator blockStateModelGenerator) {
        if(!variant.hasShape(shape))
            return;
        switch (shape) {
            case FULL_BLOCK -> generateBlock(material,variant,blockStateModelGenerator);
            case SLAB -> generateSlab(material, variant, blockStateModelGenerator);
            case STAIRS -> generateStairs(material, variant, blockStateModelGenerator);
            case WALL -> generateWall(material, variant, blockStateModelGenerator);
        }
    }

    private static void generateSlab(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, SLAB).ifPresent(slabBlock ->
                ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                    TextureMap textureMap = getTextureMap(block, material, variant);
                    Identifier fullBlockModel = ModelIds.getBlockModelId(block);
                    Identifier bottomModel = Models.SLAB.upload(slabBlock, textureMap, modelGenerator.modelCollector);
                    Identifier topModel = Models.SLAB_TOP.upload(slabBlock, textureMap, modelGenerator.modelCollector);
                    modelGenerator.blockStateCollector.accept(createSlabBlockState(slabBlock, bottomModel, topModel, fullBlockModel));
                })
        );
    }

    private static void generateWall(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, WALL).ifPresent(wallBlock ->
                ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                    TextureMap textureMap = getTextureMap(block, material, variant);
                    Identifier wallPost = TEMPLATE_PILLAR_WALL_POST.upload(wallBlock, textureMap, modelGenerator.modelCollector);
                    Identifier wallSide = TEMPLATE_PILLAR_WALL_SIDE.upload(wallBlock, textureMap, modelGenerator.modelCollector);
                    Identifier wallSideTall = TEMPLATE_PILLAR_WALL_SIDE_TALL.upload(wallBlock, textureMap, modelGenerator.modelCollector);
                    Identifier wallInventory = PILLAR_WALL_INVENTORY.upload(wallBlock, textureMap, modelGenerator.modelCollector);
                    modelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createWallBlockState(wallBlock, wallPost, wallSide, wallSideTall));
                    modelGenerator.registerParentedItemModel(wallBlock, wallInventory);
                })
        );
    }

    private static void generateStairs(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, variant, BlockShape.STAIRS).ifPresent(stairsBlock ->
            ModBlocks.getBlock(material, variant, BlockShape.FULL_BLOCK).ifPresent(block -> {

                TextureMap textureMap = getTextureMap(block, material, variant);

                Identifier stairs = Models.STAIRS.upload(stairsBlock, textureMap, modelGenerator.modelCollector);
                Identifier innerStairs = Models.INNER_STAIRS.upload(stairsBlock, textureMap, modelGenerator.modelCollector);
                Identifier outerStairs = Models.OUTER_STAIRS.upload(stairsBlock, textureMap, modelGenerator.modelCollector);
                modelGenerator.blockStateCollector.accept(BlockStateModelGenerator.createStairsBlockState(stairsBlock, innerStairs, stairs, outerStairs));
                modelGenerator.registerParentedItemModel(stairsBlock, stairs);
            })
        );
    }

    private static TextureMap getTextureMap(Block block, BlockMaterial material, BlockVariant variant) {
        Identifier topTexture = getTopTexture(block, material, variant);
        Identifier sideTexture = getSideTexture(block, material, variant);
        return new TextureMap()
                .put(TextureKey.TOP, topTexture)
                .put(TextureKey.SIDE, sideTexture)
                .put(TextureKey.WALL, sideTexture)
                .put(TextureKey.BOTTOM, topTexture);
    }

    private static void generateCutBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CUT, FULL_BLOCK).ifPresent(cutBlock -> {

            TextureMap textureMap = getTextureMap(cutBlock, material, CUT);
            modelGenerator.blockStateCollector.accept(createSingletonBlockState(cutBlock, Models.CUBE_BOTTOM_TOP.upload(cutBlock, textureMap, modelGenerator.modelCollector)));
        });
    }

    private static void generatePillarBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {
        ModBlocks.getModdedBlock(material, BlockVariant.PILLAR, FULL_BLOCK).ifPresent(block -> generateColumn(modelGenerator, block));
    }

    private static void generateColumn(BlockStateModelGenerator modelGenerator, Block block) {
        modelGenerator.registerAxisRotated(block,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN,
                TexturedModel.END_FOR_TOP_CUBE_COLUMN_HORIZONTAL);
    }

    private static void generateChiseledBlock(BlockMaterial material, BlockStateModelGenerator modelGenerator) {

        ModBlocks.getModdedBlock(material, BlockVariant.CHISELED, FULL_BLOCK).ifPresent(block -> {
            if (material == BASALT)
                generateColumn(modelGenerator, block);
            else
                modelGenerator.registerSimpleCubeAll(block);
        });
    }

    private static void generateCube(BlockMaterial material, BlockVariant variant, BlockStateModelGenerator modelGenerator) {
        ModBlocks.getModdedBlock(material, variant, FULL_BLOCK).ifPresent(modelGenerator::registerCubeAllModelTexturePool);
    }

    private static Identifier getSideTexture(Block block, BlockMaterial material, BlockVariant variant) {
        if(material == BASALT && variant == BASE
        || material == QUARTZ && variant == POLISHED)
            return TextureMap.getSubId(block,"_side");
        else
            return TextureMap.getId(block);
    }

    private static Identifier getTopTexture(Block block, BlockMaterial material, BlockVariant variant) {
        return switch (variant) {
            case CUT -> getSmoothTexture(material);
            case PILLAR -> TextureMap.getSubId(block,"_top");
            case CHISELED -> {
                if(material == BASALT)
                    yield TextureMap.getSubId(block,"_top");
                yield TextureMap.getId(block);
            }
            case BASE -> {
                if(material == BASALT || material == DEEPSLATE || material == BLACKSTONE)
                    yield TextureMap.getSubId(block,"_top");
                yield TextureMap.getId(block);
            }
            case POLISHED -> {
                if(material == QUARTZ)
                    yield TextureMap.getSubId(block,"_top");
                yield TextureMap.getId(block);
            }
            default -> TextureMap.getId(block);
        };
    }

    private static Identifier getSmoothTexture(BlockMaterial material) {
        if (material == QUARTZ)
            return TextureMap.getSubId(Blocks.QUARTZ_BLOCK, "_bottom");

        return TextureMap.getId(ModBlocks.getBlock(material, SMOOTH, FULL_BLOCK)
                .or(() -> ModBlocks.getBlock(material, BASE, FULL_BLOCK))
                .orElseThrow(() -> new NoSuchElementException("Can't find any smooth textures")));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

}
