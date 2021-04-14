package theabdel572.UDI;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import theabdel572.UDI.commands.UDIMainCmds;

public class UDI extends JavaPlugin{
	private final PluginDescriptionFile pdffile = getDescription();
	private final String version = ChatColor.RED+pdffile.getVersion();
	private final String name = ChatColor.translateAlternateColorCodes('&', "&6[&bUnDroppableItems&6] ");
	protected String configPath;
	public void onEnable() {
		registerConfig();
		registerCommands();
	}
	public void registerConfig() {
		File config = new File (this.getDataFolder(),("config.yml"));
		configPath = config.getPath();
		if(!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveConfig();
	}
	}
	public void registerCommands() {
		this.getCommand("udi").setExecutor(new UDIMainCmds(this));
	}
	public String getUDIVersion() {
		return this.version;
	}
	public String getUDIName() {
		return this.name;
	}
}