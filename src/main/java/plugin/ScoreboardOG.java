// This is free and unencumbered software released into the public domain.
// Author: NotAlexNoyle.
package plugin;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import me.catcoder.sidebar.ProtocolSidebar;
import me.catcoder.sidebar.Sidebar;
import me.catcoder.sidebar.text.TextIterators;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ScoreboardOG extends JavaPlugin {

    private static ScoreboardOG instance;

    private Sidebar<Component> sidebar;
    private FileConfiguration config;

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        this.config = getConfig();

        // Build the sidebar after the plugin instance exists.
        this.sidebar = ProtocolSidebar.newAdventureSidebar(TextIterators.textFadeHypixel("SIDEBAR"), this);

        initializeScoreboards();

        getServer().getPluginManager().registerEvents(new Listeners(), this);

    }

    public static ScoreboardOG getInstance() {

        return instance;

    }

    public Sidebar<Component> getSidebar() {

        return sidebar;

    }

    public FileConfiguration config() {

        return config;

    }

    private void initializeScoreboards() {

        // let's add some lines
        sidebar.addLine(Component.text("Just a static line").color(NamedTextColor.GREEN));
        // add an empty line
        sidebar.addBlankLine();
        // also you can add updatable lines which applies to all players receiving this
        // sidebar
        sidebar.addUpdatableLine(player -> Component.text("Your Hunger: ")
                .append(Component.text(player.getFoodLevel()).color(NamedTextColor.GREEN)));

        sidebar.addBlankLine();
        sidebar.addUpdatableLine(player -> Component.text("Your Health: ")
                .append(Component.text(player.getHealth()).color(NamedTextColor.GREEN)));
        sidebar.addBlankLine();
        sidebar.addLine(Component.text("https://github.com/CatCoderr/ProtocolSidebar").color(NamedTextColor.YELLOW));

        // update all lines except static ones every 10 ticks
        sidebar.updateLinesPeriodically(0, 10);

        // Show to all currently online players.
        Bukkit.getOnlinePlayers().forEach(sidebar::addViewer);

    }

}