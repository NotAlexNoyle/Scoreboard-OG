// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.catcoder.sidebar.ProtocolSidebar;
import me.catcoder.sidebar.Sidebar;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class ScoreboardOG extends JavaPlugin {

    private static ScoreboardOG instance;

    private Sidebar<String> sidebar;
    private FileConfiguration config;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        this.config = getConfig();

        // Build the sidebar after the plugin instance exists.
        this.sidebar = ProtocolSidebar.newMiniplaceholdersSidebar("<bold><light_green>True<red>OG <yellow>Network",
                this, MiniMessage.miniMessage());

        initializeScoreboards();

        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    public static ScoreboardOG getInstance() {

        return instance;

    }

    public Sidebar<String> getSidebar() {

        return sidebar;

    }

    public FileConfiguration config() {

        return config;

    }

    private void initializeScoreboards() {

        sidebar.addLine("Just a blank line.");
        sidebar.addBlankLine();
        sidebar.addUpdatableLine(player -> "<green>Your Hunger: <yellow>" + player.getFoodLevel());
        sidebar.addBlankLine();
        sidebar.addUpdatableLine(player -> "<gold>Your Health: <red>" + player.getHealth());
        sidebar.addBlankLine();
        sidebar.addLine("<aqua>https://github.com/CatCoderr/ProtocolSidebar");

        // Update all non-static lines every 10 ticks.
        sidebar.updateLinesPeriodically(0, 10);

        // Show to all currently online players.
        Bukkit.getOnlinePlayers().forEach(sidebar::addViewer);

    }

}