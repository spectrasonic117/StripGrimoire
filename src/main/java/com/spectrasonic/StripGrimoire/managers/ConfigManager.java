package com.spectrasonic.StripGrimoire.managers;

import com.spectrasonic.StripGrimoire.Main;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.configuration.file.FileConfiguration;

@RequiredArgsConstructor
@Getter
public class ConfigManager {
    
    private final Main plugin;
    private final MiniMessage miniMessage = MiniMessage.miniMessage();
    
    public void saveDefaultConfig() {
        plugin.saveDefaultConfig();
    }
    
    public void reloadConfig() {
        plugin.reloadConfig();
    }
    
    public FileConfiguration getConfig() {
        return plugin.getConfig();
    }
    
    public Component getMessage(String key) {
        String message = getConfig().getString("messages." + key, "Message not found: " + key);
        return miniMessage.deserialize(message);
    }
    
    public Component getMessage(String key, String placeholder, String value) {
        String message = getConfig().getString("messages." + key, "Message not found: " + key);
        message = message.replace(placeholder, value);
        return miniMessage.deserialize(message);
    }
}
