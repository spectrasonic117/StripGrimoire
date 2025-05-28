package com.spectrasonic.StripGrimoire.managers;

import com.spectrasonic.StripGrimoire.Main;
import lombok.RequiredArgsConstructor;
import org.bukkit.event.Listener;

@RequiredArgsConstructor
public class EventManager {
    
    private final Main plugin;
    
    public void registerListener(Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
