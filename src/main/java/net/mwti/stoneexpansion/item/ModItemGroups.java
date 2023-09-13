package net.mwti.stoneexpansion.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.StoneExpansion;
import net.mwti.stoneexpansion.block.BlockMaterial;
import net.mwti.stoneexpansion.block.BlockShape;
import net.mwti.stoneexpansion.block.BlockVariant;
import net.mwti.stoneexpansion.block.ModBlocks;

import java.util.NoSuchElementException;

public class ModItemGroups {

    public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(StoneExpansion.MOD_ID,"stoneexpansion"), FabricItemGroup.builder()
                    .displayName(Text.translatable("itemGroup."+StoneExpansion.MOD_ID+".blocks"))
                    .icon(ModItemGroups::getIcon)
                    .entries((((displayContext, entries) -> ModBlocks.getCreativeMenuBlocks(entries::add))))
                    .build());

    public static void registerItemGroups() {
        StoneExpansion.LOGGER.info("Registering Item Groups for " + StoneExpansion.MOD_ID);
    }
    private static ItemStack getIcon(){
        return new ItemStack(ModBlocks.getBlock(BlockMaterial.BRICKS, BlockVariant.TILES, BlockShape.BLOCK)
                .orElseThrow(() -> new NoSuchElementException("this is bad")));
    }
}
