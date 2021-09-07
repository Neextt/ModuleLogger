package io.github.biielkts.logger.logger.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginLogger;
import java.util.logging.LogRecord;

public class LoggerUtils extends PluginLogger {

    private Plugin plugin;
    private String prefix;
    private CommandSender sender;

    public LoggerUtils(Plugin plugin) {
        super(plugin);
        this.plugin = plugin;
        this.prefix = "[" + plugin.getName() + "] ";
        this.sender = Bukkit.getConsoleSender();
    }

    public LoggerUtils(LoggerUtils parent, String prefix) {
        super(parent.plugin);
        this.plugin = parent.plugin;
        this.prefix = parent.prefix + prefix;
        this.sender = Bukkit.getConsoleSender();
    }

    @Override
    public void log(LogRecord logRecord) {
        Logger level = Logger.thisName(logRecord.getLevel().getName());
        if (level == null) {
            return;
        }

        String message = logRecord.getMessage();
        if (message.equals("Default system encoding may have misread config.yml from plugin jar")) {
            return;
        }
        StringBuilder result = new StringBuilder(this.prefix + message);
        if (logRecord.getThrown() != null) {
            result.append("\n").append(logRecord.getThrown().getLocalizedMessage());
            for (StackTraceElement ste : logRecord.getThrown().getStackTrace()) {
                result.append("\n").append(ste.toString());
            }
        }

        this.sender.sendMessage(level.format(result.toString()));
    }

    private enum Logger {
        INFO("§b"),
        WARNING("§6"),
        SEVERE("§c");

        private String color;

        Logger(String color) {
            this.color = color;
        }

        public String format(String message) {
            return this.color + message;
        }

        public static Logger thisName(String name) {
            for (Logger level : values()) {
                if (level.name().equalsIgnoreCase(name)) {
                    return level;
                }
            }

            return null;
        }
    }
}
