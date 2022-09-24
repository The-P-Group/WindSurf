package me.lucasdang.grasscutter;

import emu.grasscutter.plugin.Plugin;

public class WindSurf extends Plugin {
    private static WindSurf instance;

    public static WindSurf getInstance() {
        return instance;
    }

    @Override
    public void onLoad() {
        // Set the plugin instance.
        instance = this;
    }

    @Override
    public void onEnable() {

        // Register commands.
        this.getHandle().registerCommand(new me.lucasdang.grasscutter.commands.windyCommand());

        // Log a plugin status message.
        this.getLogger().info("WindSurf By Lucas");
        this.getLogger().info("Death is like the wind always by my side");
    }

    @Override
    public void onDisable() {
        // Log a plugin status message.
        this.getLogger().info("How could you do this to me... No more Windy");
    }
}
