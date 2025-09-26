package plugin;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.catcoder.sidebar.Sidebar;
import net.kyori.adventure.text.Component;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerJoin(PlayerJoinEvent event) {

        final Sidebar<Component> sidebar = ScoreboardOG.getInstance().getSidebar();

        sidebar.addViewer(event.getPlayer());

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerQuit(PlayerQuitEvent event) {

        final Sidebar<Component> sidebar = ScoreboardOG.getInstance().getSidebar();

        sidebar.removeViewer(event.getPlayer());

    }

}