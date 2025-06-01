package com.spectrasonic.StripGrimoire.utils;

import com.spectrasonic.Utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class GrimoireItem {

    public static final int GRIMOIRE_CUSTOM_MODEL_DATA = 100;

    private GrimoireItem() {}

    public static ItemStack createStripGrimoire() {
        return ItemBuilder.setMaterial("ENCHANTED_BOOK")
            .setName("<light_purple><b>♢ Strip Grimoire ♢</b></light_purple>")
                .setLore(
                "<b><#dd47ff>「<#ff3d9b>Legendary Item</#ff3d9b>」</#dd47ff></b>",
                "",        
                "<gray>Un Grimorio Mistico que puede</gray><!italic>",
                "<gray>desvestir a quien elijas</gray><!italic>",
                "<dark_gray>-------------------------------</dark_gray>",
                "<yellow>Click derecho en un jugador</yellow><!italic>",
                "<yellow>para desvestir a algun jugador</yellow><!italic>"
            )
            .setCustomModelData(GRIMOIRE_CUSTOM_MODEL_DATA)
            .build();
    }
}
