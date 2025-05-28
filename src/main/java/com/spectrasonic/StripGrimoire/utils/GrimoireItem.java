package com.spectrasonic.StripGrimoire.utils;

import com.spectrasonic.Utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public class GrimoireItem {

    public static final int GRIMOIRE_CUSTOM_MODEL_DATA = 100;

    private GrimoireItem() {
        // Private constructor to prevent instantiation
    }

    public static ItemStack createStripGrimoire() {
        return ItemBuilder.setMaterial("COAL")
            .setName("<dark_purple>Strip Grimoire</dark_purple><!italic>")
            .setLore(
                "<gray>Un tomo Mistico que puede</gray><!italic>",
                "<gray>quitar la armadura a quien elijas</gray><!italic>",
                "<dark_gray>-------------------------------</dark_gray>",
                "<yellow>Click derecho en un jugador </yellow><!italic>",
                "<yellow>para quitarle la armadura</yellow><!italic>"
            )
            .setCustomModelData(GRIMOIRE_CUSTOM_MODEL_DATA)
            .build();
    }
}
