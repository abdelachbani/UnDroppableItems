package theabdel572.UDI.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import theabdel572.UDI.UDI;

public class onPlayerDropItem implements Listener {
	private final UDI plugin;

	public onPlayerDropItem(UDI plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		Player player = e.getPlayer();
		if (player.hasPermission("udi.exclude")) {
			return;
		} else {
			FileConfiguration config = plugin.getConfig();
			ItemStack item = e.getItemDrop().getItemStack();
			ItemMeta meta = item.getItemMeta();
			if (config.getStringList("Items." + item.getType().toString() + "." + meta.getDisplayName() + ".lore")
					.equals(meta.getLore())) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Cannot-Drop")));
			} else if (config.contains("Items." + item.getType().toString() + "." + meta.getDisplayName() + ".name")) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Cannot-Drop")));
			} else if (config.getStringList("Items." + item.getType().toString() + ".lore").equals(meta.getLore())) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Cannot-Drop")));
			} else if (config.getBoolean("Items." + item.getType().toString() + ".undroppable") == true) {
				e.setCancelled(true);
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', config.getString("Cannot-Drop")));
			} else {
				return;
			}
		}
	}
}
