package iie;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


public class HelloWorldPlugin extends JavaPlugin {
	
	public static Player iie;
	
	public static Scoreboard board;
	public static Objective hardcoreTimeDead;
	public static Objective hardcoreInvite;
	public static Objective hardcoreOutBound;
	public static AbstractMap<String,String> deathMap = new HashMap<String,String>();
	
	public void onEnable(){
		
		iie = Bukkit.getServer().getOfflinePlayer((UUID) UUID.fromString("633d0de1-4a67-46ff-bd8a-004fa8ce4858")).getPlayer();
		
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
		if (board.getObjective("hardcoreOutBound") != null){								//null check hardcoreOutBound
			hardcoreOutBound = board.getObjective("hardcoreOutBound");
		}else{
			hardcoreOutBound = board.registerNewObjective("hardcoreOutBound", "dummy");
		}
		
		registerCommands();
		getServer().getPluginManager().registerEvents(new JoinListener(this), this);
		getServer().getPluginManager().registerEvents(new DeathListener(this), this);
		getServer().getPluginManager().registerEvents(new BoundaryListener(this), this);

		
	}
	public void registerCommands(){
		getCommand("hardcore").setExecutor(new HelloWorld(this));
	}

}