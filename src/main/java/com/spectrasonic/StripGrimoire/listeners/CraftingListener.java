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
import java.util.Optional;

@RequiredArgsConstructor
public class CraftingListener implements Listener {
    
    private final Main plugin;
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onCraftPrepare(PrepareItemCraftEvent event) {
        Recipe recipe = event.getRecipe();
        
        if (!(recipe instanceof ShapelessRecipe)) {
            return;
        }
        
        List<ItemStack> matrix = Arrays.asList(event.getInventory().getMatrix());
        
        if (isValidGrimoireRecipe(matrix)) {
            event.getInventory().setResult(GrimoireItem.createStripGrimoire());
        }
    }
    
    private boolean isValidGrimoireRecipe(List<ItemStack> matrix) {
        List<ItemStack> items = matrix.stream()
            .filter(item -> item != null && item.getType() != Material.AIR)
            .toList();
        
        if (items.size() != 2) {
            return false;
        }
        
        Optional<ItemStack> enchantedBook = items.stream()
            .filter(item -> item.getType() == Material.ENCHANTED_BOOK)
            .findFirst();
        
        Optional<ItemStack> goldHelmet = items.stream()
            .filter(item -> item.getType() == Material.GOLDEN_HELMET)
            .findFirst();
        
        return enchantedBook.isPresent() && goldHelmet.isPresent();
    }
}
