package com.spectrasonic.StripGrimoire.managers;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import com.spectrasonic.StripGrimoire.Main;
import lombok.Getter;

@Getter
public class CommandManager {
    
    private final Main plugin;
    private final PaperCommandManager commandManager;
    
    public CommandManager(Main plugin) {
        this.plugin = plugin;
        this.commandManager = new PaperCommandManager(plugin);
        
        setupCommandManager();
    }
    
    private void setupCommandManager() {
        commandManager.enableUnstableAPI("help");
        commandManager.enableUnstableAPI("brigadier");
    }
    
    public void registerCommand(BaseCommand command) {
        commandManager.registerCommand(command);
    }
    
    public void shutdown() {
        if (commandManager != null) {
            commandManager.unregisterCommands();
        }
    }
}