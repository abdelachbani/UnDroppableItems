package theabdel572.UDI.listeners;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import theabdel572.UDI.UDI;

public class onPlayerDeath implements Listener {
	private final UDI plugin;

	public onPlayerDeath(UDI plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDeath(PlayerDeathEvent e) {
		Player player = e.getEntity();
		if (player.hasPermission("udi.exclude")) {
			return;
		} else {
			List<ItemStack> drops = e.getDrops();
			FileConfiguration config = plugin.getConfig();
			for (int i = 0; i < drops.size(); i++) {
				ItemStack item = drops.get(i);
				String material = item.getType().toString();
				String name = item.getItemMeta().getDisplayName();
				List<String> lore = item.getItemMeta().getLore();

				if (config.getStringList("Items." + material + "." + name + ".lore").equals(lore)) {
					drops.remove(item);
				} else if (config.contains("Items." + material + "." + name + ".name")) {
					drops.remove(item);
				} else if (config.getStringList("Items." + material + ".lore").equals(lore)) {
					drops.remove(item);
				} else if (config.getBoolean("Items." + material + ".undroppable") == true) {
					drops.remove(item);
				} else {
					return;
				}
			}
		}
	}
}
