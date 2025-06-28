package com.spectrasonic.StripGrimoire;

import com.spectrasonic.StripGrimoire.commands.StripGrimoireCommand;
import com.spectrasonic.StripGrimoire.listeners.ArmorStripListener;
import com.spectrasonic.StripGrimoire.listeners.CraftingListener;
import com.spectrasonic.Utils.RecipeUtils;
import com.spectrasonic.StripGrimoire.managers.CommandManager;
import com.spectrasonic.StripGrimoire.managers.ConfigManager;
import com.spectrasonic.StripGrimoire.managers.EventManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import com.spectrasonic.Utils.CommandUtils;
import com.spectrasonic.Utils.MessageUtils;

@Getter
public final class Main extends JavaPlugin {

    private CommandManager commandManager;
    private EventManager eventManager;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        initializeManagers();
        registerComponents();

        RecipeUtils.registerGrimoireRecipe(this);

        CommandUtils.setPlugin(this);
        MessageUtils.sendStartupMessage(this);

    }

    @Override
    public void onDisable() {
        if (commandManager != null) {
            commandManager.shutdown();
        }
        MessageUtils.sendShutdownMessage(this);
    }

    private void initializeManagers() {
        this.configManager = new ConfigManager(this);
        this.commandManager = new CommandManager(this);
        this.eventManager = new EventManager(this);
    }

    private void registerComponents() {
        commandManager.registerCommand(new StripGrimoireCommand(this));
        eventManager.registerListener(new ArmorStripListener(this));
        eventManager.registerListener(new CraftingListener(this));
    }
}
