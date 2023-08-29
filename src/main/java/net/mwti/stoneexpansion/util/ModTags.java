package net.mwti.stoneexpansion.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.mwti.stoneexpansion.StoneExpansion;
import net.mwti.stoneexpansion.block.BlockMaterial;

import java.util.LinkedHashMap;

public class ModTags {
    public static class Items {

        private static final LinkedHashMap<BlockMaterial,TagKey<Item>> materialTags = new LinkedHashMap<>();


        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(StoneExpansion.MOD_ID, name));
        }

        public static TagKey<Item> getTag(BlockMaterial material){
            return materialTags.get(material);
        }

    }
    public static void registerModTags() {

        for (BlockMaterial material : BlockMaterial.values()) {
            Items.materialTags.put(material, Items.createTag(material.toString().toLowerCase()+"_blocks"));
        }

    }
}
