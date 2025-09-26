// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.catcoder.sidebar.ProtocolSidebar;
import me.catcoder.sidebar.Sidebar;

public class ScoreboardOG extends JavaPlugin {

    private static ScoreboardOG plugin;
    private static FileConfiguration config;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();
        config = getConfig();

        initializeScoreboards();

    }

    public static ScoreboardOG getPlugin() {

        return plugin;

    }

    static FileConfiguration config() {

        return config;

    }

    private static void initializeScoreboards() {

        // Create sidebar which uses MiniMessage API.
        final Sidebar<String> sidebar = ProtocolSidebar
                .newMiniMessageSidebar("<bold><light_green>True<red>OG <yellow>Network", getPlugin());

        // Add a line with example text.
        sidebar.addLine("<light_green>Just a static line");

        // Add an empty line
        sidebar.addBlankLine();

        // Add a dynamic line for hunger.
        sidebar.addUpdatableLine(player -> ("<green>Your Hunger: <yellow>" + (player.getFoodLevel())));

        // Add an empty line
        sidebar.addBlankLine();

        // Add a dynamic line for health.
        sidebar.addUpdatableLine(player -> "<gold>Your Health: <red>" + player.getHealth());

        // Add an empty line
        sidebar.addBlankLine();

        // Add a line with a URL.
        sidebar.addLine("<aqua>https://github.com/CatCoderr/ProtocolSidebar");

        // Update all lines except static ones every 10 ticks
        sidebar.updateLinesPeriodically(0, 10);

        // Show the sidebar to all online players.
        Bukkit.getOnlinePlayers().forEach(sidebar::addViewer);

    }

}