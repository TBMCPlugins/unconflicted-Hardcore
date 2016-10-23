package iie;

import java.util.AbstractMap;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class BoundaryListener implements Listener {
	
	ButtonHardcorePlugin plugin;
	public BoundaryListener(ButtonHardcorePlugin plugin){
		this.plugin = plugin;
	}
	
	int minX = 666;				// MAP BORDER COORDS HERE
	int maxX = 2366;
	int minZ = -1520;
	int maxZ = 280;
	
	public boolean OutOfBounds (Player player, int x, int z){
		boolean isOutOfBounds = false;
		x = player.getLocation().getBlockX();
		z = player.getLocation().getBlockZ();
		if (x < minX || x > maxX || z < minZ || z > maxZ) isOutOfBounds = true;
		return isOutOfBounds;
	}
	
	public boolean FurtherOut (Player player, int x, int z){
		boolean isFurtherOut = false;
		x = player.getLocation().getBlockX();
		z = player.getLocation().getBlockZ();
		if (x < minX - 50 || x > maxX + 50 || z < minZ - 50 || z > maxZ + 50) isFurtherOut = true;
		return isFurtherOut;
	}
	
	public static AbstractMap<String, Integer> moveDelay = new HashMap<String, Integer>();
	public static AbstractMap<String, Boolean> boundaryWarning = new HashMap<String, Boolean>();
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerMove(PlayerMoveEvent event){
		
		Player player = (Player) event.getPlayer();
		String playername = player.getName();
		World world = player.getWorld();
		String worldname = world.getName();
		if (playername == "iie") world.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1F,1F);

		new Thread(() -> {
		

			
			if (worldname != "hardcore") return;
									
			int currentTime = (int) (System.currentTimeMillis()/1000);
			int playerTime = moveDelay.get(playername);
			boolean playerWarning = boundaryWarning.get(playername);
			int x = player.getLocation().getBlockX();
			int z = player.getLocation().getBlockZ();
			boolean OutOfBounds = OutOfBounds(player, x, z);
			
			
			if (playerWarning && currentTime > playerTime + 1 && OutOfBounds){
				world.playSound(player.getLocation(), Sound.AMBIENT_CAVE,1F,1F);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ("tellraw " + playername + " {\"text\":\"...out of bounds...\",\"color\":\"dark_red\"}"));
				boundaryWarning.put(playername, false);
			} else if (playerWarning && currentTime > playerTime && FurtherOut(player, x, z)){
				world.playSound(player.getLocation(), Sound.AMBIENT_CAVE,1F,1F);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ("tellraw " + playername + " {\"text\":\"...out of bounds...\",\"color\":\"dark_red\"}"));
				boundaryWarning.put(playername, false);
				if (playername == "iie") ;
			} else if (!playerWarning && !OutOfBounds) boundaryWarning.put(playername, true);

			
			moveDelay.put(playername, currentTime);
		
		}).start();
		
	}

}
