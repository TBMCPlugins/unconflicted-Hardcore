package iie;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
	
	HelloWorldPlugin plugin;
	public DeathListener(HelloWorldPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onHardcoreDeath(PlayerDeathEvent deathEvent){
				
		int currentTime = (int) ((System.currentTimeMillis())/1000);   //divided by 1000 to fit within Integer range
		Player player = deathEvent.getEntity();
		String playername = (String) player.getName();
		Location location = player.getLocation();
		String worldString = (String) location.getWorld().getName();
		
		//player.sendMessage("you died");
		//player.sendMessage("currentTime = " + String.valueOf(currentTime));
		//player.sendMessage("worldString = " + String.valueOf(worldString));
		
		if (Objects.equals(worldString, "hardcore")){
						
				HelloWorldPlugin.hardcoreTimeDead.getScore(playername).setScore(currentTime);
				HelloWorldPlugin.hardcoreInvite.getScore(playername).setScore(0);
				
				//player.sendMessage("death detected");
				//player.sendMessage("hardcoreTimeDead score = " + String.valueOf(HelloWorldPlugin.hardcoreTimeDead.getScore(playername).getScore()));
				//player.sendMessage("currentTime            = " + String.valueOf(currentTime));
				
		}
	}
}
