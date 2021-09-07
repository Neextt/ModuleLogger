package io.github.biielkts.logger.logger;

import io.github.biielkts.logger.logger.config.ConfigUtils;
import io.github.biielkts.logger.logger.utils.FileUtils;
import io.github.biielkts.logger.logger.utils.LoggerUtils;
import io.github.biielkts.logger.logger.writers.WriterUtils;
import io.github.biielkts.logger.utils.Accessors;
import io.github.biielkts.logger.utils.acessors.FieldAccessor;
import org.bukkit.plugin.PluginLogger;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public abstract class BukkitPlugin extends JavaPlugin {

    private static final FieldAccessor<PluginLogger> LOGGER_ACCESSOR = Accessors.getField(JavaPlugin.class, "logger", PluginLogger.class);
    private final FileUtils fileUtils;

    public BukkitPlugin() {
        this.fileUtils = new FileUtils(this);
        LOGGER_ACCESSOR.set(this, new LoggerUtils(this));
        this.starting();
    }

    public abstract void starting();

    public abstract void loading();

    public abstract void onStart();

    public abstract void onDisabled();

    @Override
    public void onLoad() {
        this.loading();
    }

    @Override
    public void onEnable() {
        this.onStart();
    }

    @Override
    public void onDisable() {
        this.onDisabled();
    }

    public ConfigUtils getConfig(String name) {
        return this.getConfig("", name);
    }

    public ConfigUtils getConfig(String path, String name) {
        return ConfigUtils.getConfig(this, "plugins/" + this.getName() + "/" + path, name);
    }

    public FileUtils getFileUtils() {
        return this.fileUtils;
    }
}
