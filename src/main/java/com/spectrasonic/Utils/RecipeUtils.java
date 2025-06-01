package com.spectrasonic.Utils;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public final class RecipeUtils {

    private RecipeUtils() {
        // Private constructor to prevent instantiation
    }

    public static ShapelessRecipe createShapelessRecipe(JavaPlugin plugin, String recipeKey, ItemStack result) {
        NamespacedKey key = new NamespacedKey(plugin, recipeKey.toLowerCase());
        return new ShapelessRecipe(key, result);
    }

    public static ShapelessRecipe createGrimoireRecipe(JavaPlugin plugin) {
        ItemStack grimoire = new ItemStack(Material.ENCHANTED_BOOK); // El resultado real se establece en el CraftingListener
        ShapelessRecipe recipe = createShapelessRecipe(plugin, "strip_grimoire_recipe", grimoire);
        recipe.addIngredient(Material.ENCHANTED_BOOK);
        recipe.addIngredient(Material.GOLDEN_HELMET);
        return recipe;
    }

    public static void registerGrimoireRecipe(JavaPlugin plugin) {
        plugin.getServer().addRecipe(createGrimoireRecipe(plugin));
    }
}
