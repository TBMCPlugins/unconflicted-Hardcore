package iie;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	ButtonHardcorePlugin plugin;
	public JoinListener(ButtonHardcorePlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent JoinEvent){
		
		
		Player player = JoinEvent.getPlayer();
		String playername = (String) player.getName();
		
		
		if (ButtonHardcorePlugin.hardcoreInvite.getScore(playername) == null){			//null check
			ButtonHardcorePlugin.hardcoreInvite.getScore(playername).setScore(0);		//convert null to 0
		}
		if (ButtonHardcorePlugin.hardcoreTimeDead.getScore(playername) == null){		//null check
			ButtonHardcorePlugin.hardcoreTimeDead.getScore(playername).setScore(0);		//convert null to 0
		}
		
		int invite = ButtonHardcorePlugin.hardcoreInvite.getScore(playername).getScore();
		int deathTime = ButtonHardcorePlugin.hardcoreTimeDead.getScore(playername).getScore();
		int currentTime = (int) ((System.currentTimeMillis())/1000);
				

		if (currentTime - deathTime >= 86400 && deathTime != 0 && invite == 0){				
			player.sendMessage(playername + ", your death has lifted in Hardcore world. (You died " + String.valueOf((currentTime - deathTime) /3600) + " hours ago)");
			player.sendMessage("Are you ready to give life another shot?");
			ButtonHardcorePlugin.hardcoreInvite.getScore(playername).setScore(1);
		}else if (currentTime - deathTime <= 86400){
			player.sendMessage(String.valueOf((86400 - (currentTime - deathTime)) /3600) + " hours of death remaining in hardcore");
		}else if (deathTime == 0){
			return;
		}
		
		BoundaryListener.moveDelay.put(playername, currentTime);
		BoundaryListener.boundaryWarning.put(playername, true);
		
		
		//HelloWorldPlugin.deathMap.put(playername, String.valueOf(HelloWorldPlugin.hardcoreTimeDead.getScore(playername).getScore()));
		
		
	}
	
}
