package com.spectrasonic.StripGrimoire.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import com.spectrasonic.StripGrimoire.Main;
import com.spectrasonic.StripGrimoire.utils.ItemUtils;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;

@CommandAlias("stripgrimoire|sg")
@RequiredArgsConstructor
public class StripGrimoireCommand extends BaseCommand {
    
    private final Main plugin;
    
    @Subcommand("get")
    @CommandPermission("stripgrimoire.admin")
    @Description("Get a Strip Grimoire item")
    public void onGet(Player player) {
        var grimoire = ItemUtils.createStripGrimoire();
        
        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItem(player.getLocation(), grimoire);
            player.sendMessage(plugin.getConfigManager().getMessage("grimoire-dropped"));
        } else {
            player.getInventory().addItem(grimoire);
            player.sendMessage(plugin.getConfigManager().getMessage("grimoire-received"));
        }
    }
    
    @Subcommand("reload")
    @CommandPermission("stripgrimoire.admin")
    @Description("Reload the plugin configuration")
    public void onReload(Player player) {
        plugin.getConfigManager().reloadConfig();
        player.sendMessage(plugin.getConfigManager().getMessage("config-reloaded"));
    }
}

