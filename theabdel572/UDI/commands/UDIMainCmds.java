package theabdel572.UDI.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import theabdel572.UDI.UDI;

public class UDIMainCmds implements CommandExecutor{
	private final UDI plugin;
	public UDIMainCmds(UDI plugin) {
		this.plugin = plugin;
	}
	public boolean onCommand(CommandSender sender, Command cmd, String paq, String[] args) {
		FileConfiguration config = plugin.getConfig();
		if(!(sender instanceof Player)) {
			sender.sendMessage(plugin.getUDIName()+ChatColor.RED+"This command only can be executed by players.");
			return false;
		}else {
			Player player = (Player) sender;
			if(args.length > 0) {
				if(player.hasPermission("udi.*")) {
					if(args[0].equalsIgnoreCase("additem")) {
						if(player.getInventory().getItemInMainHand() != null) {
						ItemStack item = player.getInventory().getItemInMainHand();
						ItemMeta meta = item.getItemMeta();
						config.set("Items."+item.getType().name()+"."+meta.getDisplayName()+".lore", meta.getLore());
						plugin.saveConfig();
						player.sendMessage(plugin.getUDIName()+ChatColor.GREEN+"Item set correctly!");
						return true;
						}else {
							player.sendMessage(plugin.getUDIName()+ChatColor.RED+"You need to have a item in your hand!");
						}
					}else if(args[0].equalsIgnoreCase("version")){
						player.sendMessage(plugin.getUDIName()+ChatColor.GREEN+"The plugin version is: "+plugin.getUDIVersion());
					}else if(args[0].equalsIgnoreCase("reload")) {
						plugin.saveConfig();
						player.sendMessage(plugin.getUDIName()+ChatColor.GREEN+"The plugin has been reloded correctly!");
					}else {
						player.sendMessage(plugin.getUDIName()+ChatColor.translateAlternateColorCodes('&', "&aHelp page:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi version &0- &2Use this to see the plugin version."));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi reload &0- &2Use this to reload the config file."));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi additem &0- &2Use this to add an item to the undroppable items list."));
					}
				}else {
					player.sendMessage(plugin.getUDIName()+ChatColor.translateAlternateColorCodes('&', config.getString("NoPermission")));
					return false;
				}
			}else {
				player.sendMessage(plugin.getUDIName()+ChatColor.translateAlternateColorCodes('&', "&aHelp page:"));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi version &0- &2Use this to see the plugin version."));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi reload &0- &2Use this to reload the config file."));
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/udi additem &0- &2Use this to add an item to the undroppable items list."));
			}
			}
			return false;
		}
}