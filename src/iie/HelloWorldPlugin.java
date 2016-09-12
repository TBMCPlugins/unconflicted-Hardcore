package iie;

import java.util.AbstractMap;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class HelloWorldPlugin extends JavaPlugin {
	
	public static Scoreboard board;
	public static Objective hardcoreTimeDead;
	public static Objective hardcoreInvite;
	public static AbstractMap<String,String> deathMap = new HashMap<String,String>();
	
	public void onEnable(){
		
		board = Bukkit.getServer().getScoreboardManager().getMainScoreboard();			
		if (board.getObjective("hardcoreTimeDead") != null){							//null check hardcoreTimeDead
			hardcoreTimeDead = board.getObjective("hardcoreTimeDead");
		}else{
			hardcoreTimeDead = board.registerNewObjective("hardcoreTimeDead", "dummy");
		}
		if (board.getObjective("hardcoreInvite") != null){								//null check hardcoreInvite
			hardcoreInvite = board.getObjective("hardcoreInvite");
		}else{
			hardcoreInvite = board.registerNewObjective("hardcoreInvite", "dummy");
		}
		
		registerCommands();
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(this), this);

		
	}
	public void registerCommands(){
		getCommand("hardcore").setExecutor(new HelloWorld(this));
	}

}