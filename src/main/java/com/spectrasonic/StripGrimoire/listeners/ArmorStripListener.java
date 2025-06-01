package com.spectrasonic.StripGrimoire.listeners;

import com.spectrasonic.StripGrimoire.Main;
import com.spectrasonic.StripGrimoire.utils.GrimoireItem;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class ArmorStripListener implements Listener {
    
    private final Main plugin;
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        Player clicker = event.getPlayer();
        
        if (!(event.getRightClicked() instanceof Player target)) {
            return;
        }
        
        ItemStack itemInHand = clicker.getInventory().getItemInMainHand();
        if (itemInHand == null || itemInHand.getType() != Material.ENCHANTED_BOOK) {
            return;
        }

        ItemMeta meta = itemInHand.getItemMeta();
        if (meta == null || !meta.hasCustomModelData() || meta.getCustomModelData() != GrimoireItem.GRIMOIRE_CUSTOM_MODEL_DATA) {
            return;
        }

        PlayerInventory targetInventory = target.getInventory();
        List<ItemStack> armorPieces = Arrays.asList(
            targetInventory.getHelmet(),
            targetInventory.getChestplate(),
            targetInventory.getLeggings(),
            targetInventory.getBoots()
        );
        
        boolean hasArmor = armorPieces.stream()
            .anyMatch(Objects::nonNull);
        
        if (!hasArmor) {
            clicker.sendMessage(plugin.getConfigManager()
                .getMessage("no-armor", "{player}", target.getName()));
            return;
        }
        
        stripArmorSafely(target, armorPieces);
        
        clicker.sendMessage(plugin.getConfigManager()
            .getMessage("armor-stripped", "{player}", target.getName()));
        target.sendMessage(plugin.getConfigManager()
            .getMessage("armor-removed", "{player}", clicker.getName()));
        
        target.playSound(target.getLocation(), Sound.BLOCK_RESPAWN_ANCHOR_DEPLETE, 1.0f, 0.5f);

        event.setCancelled(true);
    }
    
    private void stripArmorSafely(Player target, List<ItemStack> armorPieces) {
        PlayerInventory inventory = target.getInventory();
        
        inventory.setHelmet(null);
        inventory.setChestplate(null);
        inventory.setLeggings(null);
        inventory.setBoots(null);
        
        armorPieces.stream()
            .filter(Objects::nonNull)
            .forEach(armor -> {
                var leftOver = inventory.addItem(armor);
                
                leftOver.values().forEach(item -> 
                    target.getWorld().dropItem(target.getLocation(), item));
            });
    }
}
