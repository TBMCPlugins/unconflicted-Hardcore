package iie;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
	
	HelloWorldPlugin plugin;
	public JoinListener(HelloWorldPlugin plugin){
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent JoinEvent){
		
		
		Player player = JoinEvent.getPlayer();
		String playername = (String) player.getName();
				
		
		if (HelloWorldPlugin.hardcoreInvite.getScore(playername) == null){			//null check
			HelloWorldPlugin.hardcoreInvite.getScore(playername).setScore(0);		//convert null to 0
		}
		if (HelloWorldPlugin.hardcoreTimeDead.getScore(playername) == null){		//null check
			HelloWorldPlugin.hardcoreTimeDead.getScore(playername).setScore(0);		//convert null to 0
		}
		
		int invite = HelloWorldPlugin.hardcoreInvite.getScore(playername).getScore();
		int deathTime = HelloWorldPlugin.hardcoreTimeDead.getScore(playername).getScore();
		int currentTime = (int) ((System.currentTimeMillis())/1000);
				

		if (currentTime - deathTime >= 86400 && deathTime != 0 && invite == 0){				
			player.sendMessage(playername + ", your death has lifted in Hardcore world. (You died " + String.valueOf((currentTime - deathTime) /3600) + " hours ago)");
			player.sendMessage("Are you ready to give life another shot?");
			HelloWorldPlugin.hardcoreInvite.getScore(playername).setScore(1);
		}else if (currentTime - deathTime <= 86400){
			player.sendMessage(String.valueOf((86400 - (currentTime - deathTime)) /3600) + " hours of death remaining in hardcore");
		}else if (deathTime == 0){
			return;
		}
		
		
		//HelloWorldPlugin.deathMap.put(playername, String.valueOf(HelloWorldPlugin.hardcoreTimeDead.getScore(playername).getScore()));
		
		
	}
	
}
