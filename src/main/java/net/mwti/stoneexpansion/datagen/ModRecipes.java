package net.mwti.stoneexpansion.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.*;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;
import net.mwti.stoneexpansion.util.ModTags;
import static net.mwti.stoneexpansion.block.BlockVariant.*;

import java.util.function.Consumer;


public class ModRecipes extends FabricRecipeProvider {

    public ModRecipes(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {

        createStonecutterRecipes(exporter);
        createCraftingRecipes(exporter);
    }

    private static void createStonecutterRecipes(Consumer<RecipeJsonProvider> exporter) {

        for(BlockMaterial material : BlockMaterial.values()) {
            for(BlockVariant variant : BlockVariant.values()) {
                if(variant == DARK)
                    continue;
                ModBlocks.getBlock(material,variant).ifPresent(block ->
                    createStonecutterRecipe(ModTags.Items.getTag(material), block.asItem(), exporter)
                );
            }
        }
    }

    private static void createCraftingRecipes(Consumer<RecipeJsonProvider> exporter) {

        for(BlockMaterial material : BlockMaterial.values()) {
            ModBlocks.getBlock(material, DARK).ifPresent(block ->
                createShapelessRecipe(ModTags.Items.getTag(material), block.asItem(), exporter)
            );
        }
    }

    private static void createStonecutterRecipe(TagKey<Item> inputs, Item output, Consumer<RecipeJsonProvider> exporter) {
        new SingleItemRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, RecipeSerializer.STONECUTTING, Ingredient.fromTag(inputs), output, 1)
                .criterion(nameFromTag(inputs), conditionsFromTag(inputs))
                .offerTo(exporter, createFileName(Block.getBlockFromItem(output),inputs.id().toUnderscoreSeparatedString()) + "_stonecutting");
    }
    private static void createShapelessRecipe(TagKey<Item> inputs, Item output, Consumer<RecipeJsonProvider> exporter) {
        new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, output, 1)
                .input(Ingredient.fromTag(inputs))
                .input(Items.BLACK_DYE)
                .criterion(nameFromTag(inputs), conditionsFromTag(inputs))
                .offerTo(exporter, createFileName(Block.getBlockFromItem(output),inputs.id().toUnderscoreSeparatedString()));
    }

    private static String nameFromTag(TagKey<Item> tag) {
        return "has_tag_"
                + tag.id().toUnderscoreSeparatedString();
    }

    private static String createFileName(Block output, String input) {
        return input
                + "_from_"
                + Registries.BLOCK.getId(output).toUnderscoreSeparatedString();
    }
}
