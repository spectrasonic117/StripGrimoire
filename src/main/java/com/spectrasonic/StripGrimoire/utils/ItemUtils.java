package com.spectrasonic.StripGrimoire.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public final class ItemUtils {
    
    private static final int GRIMOIRE_CUSTOM_MODEL_DATA = 100;
    
    private ItemUtils() {
    }
    
    public static ItemStack createStripGrimoire() {
        ItemStack grimoire = new ItemStack(Material.COAL);
        ItemMeta meta = grimoire.getItemMeta();
        
        if (meta != null) {
            meta.displayName(Component.text("Strip Grimoire")
                .color(NamedTextColor.DARK_PURPLE)
                .decoration(TextDecoration.ITALIC, false));
            
            meta.lore(List.of(
                Component.text("A mystical tome that can")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false),
                Component.text("strip armor from enemies.")
                    .color(NamedTextColor.GRAY)
                    .decoration(TextDecoration.ITALIC, false),
                Component.empty(),
                Component.text("Right-click on a player")
                    .color(NamedTextColor.YELLOW)
                    .decoration(TextDecoration.ITALIC, false),
                Component.text("to remove their armor!")
                    .color(NamedTextColor.YELLOW)
                    .decoration(TextDecoration.ITALIC, false)
            ));
            
            meta.setCustomModelData(GRIMOIRE_CUSTOM_MODEL_DATA);
            
            grimoire.setItemMeta(meta);
        }
        
        return grimoire;
    }
    
    public static boolean isStripGrimoire(ItemStack item) {
        if (item == null || item.getType() != Material.COAL) {
            return false;
        }
        
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return false;
        }
        
        return meta.hasCustomModelData() && 
               meta.getCustomModelData() == GRIMOIRE_CUSTOM_MODEL_DATA;
    }
}