package io.github.biielkts.logger;

import io.github.biielkts.logger.logger.BukkitPlugin;

public class BukkitLogger extends BukkitPlugin {

    // Logger for Core
    // Make by github.com/biielkts
    // Source-Code: github.com/biielkts/Logger/

    @Override
    public void starting() {
    }

    @Override
    public void loading() {
    //  this.getLogger().info("Connecting to API for hades api.redehades.com...");
    // Connect to player for API
        this.getLogger().info("ModuleLogger by biielkts");

    }

    @Override
    public void onStart() {
        this.getLogger().info("ModuleLogger is been enabled!");

    }

    @Override
    public void onDisabled() {
        this.getLogger().info("ModuleLogger is been disabled!");
    }
}
