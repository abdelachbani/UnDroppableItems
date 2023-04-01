package theabdel572.UDI;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import theabdel572.UDI.commands.UDIMainCmds;
import theabdel572.UDI.listeners.SendUpdates;
import theabdel572.UDI.listeners.PlayerDeath;
import theabdel572.UDI.listeners.PlayerDropItem;

public class UDI extends JavaPlugin {
	private final PluginDescriptionFile pdffile = getDescription();
	private final String version = ChatColor.RED + pdffile.getVersion();
	private final String name = ChatColor.translateAlternateColorCodes('&', "&6[&bUnDroppableItems&6] ");
	protected String configPath;
	private String latestversion;

	public void onEnable() {
		registerConfig();
		registerCommands();
		registerEvents();
		checkUpdates();
	}

	public void registerConfig() {
		File config = new File(this.getDataFolder(), ("config.yml"));
		configPath = config.getPath();
		if (!config.exists()) {
			this.getConfig().options().copyDefaults(true);
			saveConfig();
		}
	}

	public void registerCommands() {
		this.getCommand("udi").setExecutor(new UDIMainCmds(this));
	}

	public void registerEvents() {
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new PlayerDropItem(this), this);
		pm.registerEvents(new PlayerDeath(this), this);
		pm.registerEvents(new SendUpdates(this), this);
	}

	public String getUDIVersion() {
		return this.version;
	}
	
	public String getLatestVersion() {
		return this.latestversion;
	}

	public String getUDIName() {
		return this.name;
	}
	
	public void checkUpdates(){		  
		  try {
			  HttpURLConnection con = (HttpURLConnection) new URL(
	                  "https://api.spigotmc.org/legacy/update.php?resource=92280").openConnection();
	          int timed_out = 1250;
	          con.setConnectTimeout(timed_out);
	          con.setReadTimeout(timed_out);
	          latestversion = new BufferedReader(new InputStreamReader(con.getInputStream())).readLine();
	          if (latestversion.length() <= 7) {
	        	  if(!version.equals(latestversion)){
	        		  Bukkit.getConsoleSender().sendMessage(ChatColor.RED +"There is a new version available. "+ChatColor.YELLOW+
	        				  "("+ChatColor.GRAY+latestversion+ChatColor.YELLOW+")");
						Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You can download it at: "
								+ ChatColor.WHITE
								+ "https://www.spigotmc.org/resources/undroppableitems-make-the-items-you-want-undroppable.92280/");  
	        	  }      	  
	          }
	      } catch (Exception ex) {
	    	  Bukkit.getConsoleSender().sendMessage(name + ChatColor.RED +"Error while checking update.");
	      }
	  }
}