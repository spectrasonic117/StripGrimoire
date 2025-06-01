package com.spectrasonic.StripGrimoire.listeners;

import com.spectrasonic.StripGrimoire.Main;
import com.spectrasonic.StripGrimoire.utils.GrimoireItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapelessRecipe;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class CraftingListener implements Listener {
    
    private final Main plugin;
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onCraftPrepare(PrepareItemCraftEvent event) {
        Recipe recipe = event.getRecipe();
        
        if (!(recipe instanceof ShapelessRecipe)) {
            return;
        }
        
        List<ItemStack> validItems = Arrays.stream(event.getInventory().getMatrix())
            .filter(item -> item != null && item.getType() != Material.AIR)
            .collect(Collectors.toList());
        
        if (isValidGrimoireRecipe(validItems)) {
            event.getInventory().setResult(GrimoireItem.createStripGrimoire());
        }
    }
    
    private boolean isValidGrimoireRecipe(List<ItemStack> ingredients) {
        if (ingredients.size() != 2) {
            return false;
        }

        boolean hasEnchantedBook = false;
        boolean hasGoldenHelmet = false;

        for (ItemStack item : ingredients) {
            if (item.getType() == Material.ENCHANTED_BOOK) {
                hasEnchantedBook = true;
            } else if (item.getType() == Material.GOLDEN_HELMET) {
                hasGoldenHelmet = true;
            }
        }

        return hasEnchantedBook && hasGoldenHelmet;
    }
}
