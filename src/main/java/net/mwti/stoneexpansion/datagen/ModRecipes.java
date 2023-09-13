package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.*;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;
import net.mwti.stoneexpansion.util.ModTags;

import static net.mwti.stoneexpansion.block.BlockVariant.*;
import static net.mwti.stoneexpansion.block.BlockMaterial.*;

import java.util.function.Consumer;


public class ModRecipes extends FabricRecipeProvider {

    public ModRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        createStonecutterRecipes(exporter);
        createDarkVariantRecipes(exporter);
    }

    private static void createStonecutterRecipes(Consumer<RecipeJsonProvider> exporter) {

        for(BlockMaterial inputMaterial : BlockMaterial.values()) {
            for(BlockVariant inputVariant : BlockVariant.values()) {

                ModBlocks.getBlock(inputMaterial, inputVariant, BlockShape.BLOCK).ifPresent(inputBlock -> {
                    for (BlockVariant outputVariant : BlockVariant.values()) {
                        if (validateStonecutterRecipe(outputVariant, inputMaterial, inputVariant))
                            createStonecutterShapesRecipes(exporter, inputMaterial, inputVariant, inputBlock, outputVariant);
                    }
                });
            }
        }
    }

    private static void createDarkVariantRecipes(Consumer<RecipeJsonProvider> exporter) {

        for(BlockMaterial material : BlockMaterial.values()) {
            ModBlocks.getBlock(material, DARK, BlockShape.BLOCK).ifPresent(block ->
                    createShapelessRecipe(ModTags.Items.getTag(material), block.asItem(), exporter)
            );
        }
    }

    private static boolean validateStonecutterRecipe(BlockVariant outputVariant, BlockMaterial inputMaterial, BlockVariant inputVariant) {
        if(outputVariant == BASE) //you cannot craft back into a base block
            return false;
        if(inputMaterial == PRISMARINE && inputVariant == BASE) //prismarine bricks are more expensive to craft in vanilla, so base block cannot be used
            return false;
        if(outputVariant == DARK ^ inputVariant == DARK) //dark variant stonecutter recipes are separated from the rest
            return false;
        return true;
    }

    private static void createStonecutterShapesRecipes(Consumer<RecipeJsonProvider> exporter, BlockMaterial inputMaterial, BlockVariant inputVariant, Block inputBlock, BlockVariant outputVariant) {
        for(BlockShape shape : BlockShape.values()) {
            if(outputVariant == inputVariant && shape == BlockShape.BLOCK)
                continue;

            if(outputVariant.hasShape(shape)) {
                ModBlocks.getBlock(inputMaterial, outputVariant, shape).ifPresent(outputBlock -> {
                    if(ModBlocks.isBlockFromTheMod(outputBlock) || ModBlocks.isBlockFromTheMod(inputBlock))
                        createStonecutterRecipe(exporter, outputBlock, inputBlock, shape == BlockShape.SLAB ? 2 : 1);
                });
            }
        }
    }

    private static void createShapelessRecipe(TagKey<Item> inputs, Item output, Consumer<RecipeJsonProvider> exporter) {
        new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .input(Ingredient.fromTag(inputs))
                .input(Items.BLACK_DYE)
                .criterion(nameFromTag(inputs), conditionsFromTag(inputs))
                .offerTo(exporter, createFileName(output, inputs));
    }


    public static void createStonecutterRecipe(Consumer<RecipeJsonProvider> exporter, ItemConvertible output, ItemConvertible input, int count) {
        String name = createFileName(output,input);
        SingleItemRecipeJsonBuilder
                .createStonecutting(Ingredient.ofItems(input), RecipeCategory.BUILDING_BLOCKS, output, count)
                .criterion(hasItem(input), conditionsFromItem(input))
                .offerTo(exporter, name + "_stonecutting");
    }

    private static String nameFromTag(TagKey<Item> tag) {
        return "has_tag_" + tag.id().toUnderscoreSeparatedString();
    }

    private static String createFileName(ItemConvertible output, ItemConvertible input) {
        return Registries.ITEM.getId(output.asItem()).toUnderscoreSeparatedString()
                + "_from_"
                + Registries.ITEM.getId(input.asItem()).toUnderscoreSeparatedString();
    }
    private static String createFileName(ItemConvertible output, TagKey<Item> input) {
        return Registries.ITEM.getId(output.asItem()).toUnderscoreSeparatedString()
                + "_from_"
                + nameFromTag(input);
    }

}
