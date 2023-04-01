package theabdel572.UDI.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import theabdel572.UDI.UDI;

public class SendUpdates implements Listener {
  private UDI plugin;

  public SendUpdates(UDI plugin) {
    this.plugin = plugin;
  }

  @EventHandler
  public void onJoin(PlayerJoinEvent e) {
    Player player = e.getPlayer();

    if (player.hasPermission("udi.*")
        && !(plugin.getUDIVersion().equals(plugin.getLatestVersion()))) {
      player.sendMessage(
          plugin.getUDIName()
              + ChatColor.RED
              + " There is a new version available. "
              + ChatColor.YELLOW
              + "("
              + ChatColor.GRAY
              + plugin.getLatestVersion()
              + ChatColor.YELLOW
              + ")");
      player.sendMessage(
          ChatColor.RED
              + "You can download it at: "
              + ChatColor.WHITE
              + "https://www.spigotmc.org/resources/undroppableitems-make-the-items-you-want-undroppable.92280/");
    }
  }
}
