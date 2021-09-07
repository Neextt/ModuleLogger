package io.github.biielkts.logger.logger.config;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

import io.github.biielkts.logger.logger.BukkitPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigUtils {

    private BukkitPlugin plugin;
    private File file;
    private YamlConfiguration config;

    private ConfigUtils(BukkitPlugin plugin, String path, String name) {
        this.plugin = plugin;
        this.file = new File(path + "/" + name + ".yml");
        if (!file.exists()) {
            this.file.getParentFile().mkdirs();
            InputStream in = plugin.getResource(name + ".yml");
            if (in != null) {
                plugin.getFileUtils().copyFile(in, file);
            } else {
                try {
                    this.file.createNewFile();
                } catch (IOException ex) {
                    plugin.getLogger().log(Level.SEVERE, "Error for loading this file \"" + file.getName() + "\": ", ex);
                }
            }
        }

        try {
            this.config = YamlConfiguration.loadConfiguration(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        } catch (IOException ex) {
            plugin.getLogger().log(Level.SEVERE, "Error for loading this file \"" + file.getName() + "\": ", ex);
        }
    }

    private static Map<String, ConfigUtils> cache = new HashMap<>();

    public static ConfigUtils getConfig(BukkitPlugin plugin, String path, String name) {
        if (!cache.containsKey(path + "/" + name)) {
            cache.put(path + "/" + name, new ConfigUtils(plugin, path, name));
        }

        return cache.get(path + "/" + name);
    }
}

