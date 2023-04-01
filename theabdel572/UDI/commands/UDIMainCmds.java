package theabdel572.UDI.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import theabdel572.UDI.UDI;

public class UDIMainCmds implements CommandExecutor {
  private final UDI plugin;

  public UDIMainCmds(UDI plugin) {
    this.plugin = plugin;
  }

  public boolean onCommand(CommandSender sender, Command cmd, String idk, String[] args) {
    FileConfiguration config = plugin.getConfig();
    if (!(sender instanceof Player)) {
      sender.sendMessage(
          plugin.getUDIName() + ChatColor.RED + "This command only can be executed by players.");
      return true;
    } else {
      Player player = (Player) sender;
      if (args.length > 0) {

        if (player.hasPermission("udi.*")) {

          if (args[0].equalsIgnoreCase("additem")) {
            @SuppressWarnings("deprecation")
            ItemStack item = player.getInventory().getItemInHand();

            if (item != null && item.getType() != Material.AIR) {
              ItemMeta meta = item.getItemMeta();

              if (meta.hasDisplayName()) {
                if (meta.hasLore()) {
                  config.set(
                      "Items." + item.getType().toString() + "." + meta.getDisplayName() + ".lore",
                      meta.getLore());
                  plugin.saveConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item added successfully to UnDroppableItems list!");
                } else {
                  config.set(
                      "Items." + item.getType().toString() + "." + meta.getDisplayName() + ".name",
                      "");
                  plugin.saveConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item added successfully to UnDroppableItems list!");
                }
              } else {
                if (meta.hasLore()) {
                  config.set("Items." + item.getType().toString() + ".lore", meta.getLore());
                  plugin.saveConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item added successfully to UnDroppableItems list!");
                } else {
                  config.set("Items." + item.getType().toString() + ".undroppable", true);
                  plugin.saveConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item added successfully to UnDroppableItems list!");
                }
              }
            } else {
              player.sendMessage(
                  plugin.getUDIName() + ChatColor.RED + "You need to have a item in your hand!");
            }
          } else if (args[0].equalsIgnoreCase("removeitem")) {
            @SuppressWarnings("deprecation")
            ItemStack item = player.getInventory().getItemInHand();

            if (item != null && item.getType() != Material.AIR) {
              ItemMeta meta = item.getItemMeta();

              if (meta.hasDisplayName()) {
                if (meta.hasLore()) {
                  config.set(
                      "Items." + item.getType().toString() + "." + meta.getDisplayName(), null);
                  plugin.saveConfig();
                  plugin.reloadConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item removed successfully from UnDroppableItems list!");
                } else {
                  config.set(
                      "Items." + item.getType().toString() + "." + meta.getDisplayName(), null);
                  plugin.saveConfig();
                  plugin.reloadConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item removed successfully from UnDroppableItems list!");
                }
              } else {
                if (meta.hasLore()) {
                  config.set("Items." + item.getType().toString() + ".lore", null);
                  plugin.saveConfig();
                  plugin.reloadConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item removed successfully from UnDroppableItems list!");
                } else {
                  config.set("Items." + item.getType().toString() + ".undroppable", null);
                  plugin.saveConfig();
                  plugin.reloadConfig();
                  player.sendMessage(
                      plugin.getUDIName()
                          + ChatColor.GREEN
                          + "Item removed successfully from UnDroppableItems list!");
                }
              }
            } else {
              player.sendMessage(
                  plugin.getUDIName() + ChatColor.RED + "You need to have a item in your hand!");
            }
          } else if (args[0].equalsIgnoreCase("version")) {
            player.sendMessage(
                plugin.getUDIName()
                    + ChatColor.GREEN
                    + "The plugin version is: "
                    + plugin.getUDIVersion());
          } else if (args[0].equalsIgnoreCase("reload")) {
            plugin.saveConfig();
            player.sendMessage(
                plugin.getUDIName() + ChatColor.GREEN + "The plugin has been reloded correctly!");
          } else {
            sendUDIHelp(player);
          }
        } else {
          player.sendMessage(
              plugin.getUDIName()
                  + ChatColor.translateAlternateColorCodes('&', config.getString("NoPermission")));
          return false;
        }
      } else {
        sendUDIHelp(player);
      }
    }
    return false;
  }

  public void sendUDIHelp(Player player) {
    player.sendMessage(
        plugin.getUDIName()
            + ChatColor.translateAlternateColorCodes(
                '&', plugin.getUDIName() + "&aby &atheabdel572."));
    player.sendMessage(
        plugin.getUDIName()
            + ChatColor.translateAlternateColorCodes('&', plugin.getUDIName() + "&aHelp page:"));
    player.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&', "&a/udi version &0- &2Use this to see the plugin version."));
    player.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&', "&a/udi reload &0- &2Use this to reload the config file."));
    player.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&',
            "&a/udi additem &0- &2Use this to add the item in your to the undroppable items"
                + " list."));
    player.sendMessage(
        ChatColor.translateAlternateColorCodes(
            '&',
            "&a/udi removeitem &0- &2Use this to remove the item in your  the undroppable items"
                + " list."));
  }
}
