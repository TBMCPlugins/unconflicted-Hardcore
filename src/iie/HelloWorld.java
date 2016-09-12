package iie;

import java.util.Objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HelloWorld implements CommandExecutor {
	
	HelloWorldPlugin plugin;
	public HelloWorld(HelloWorldPlugin plugin){
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command label, String command, String[] args) {
		
		
		
		
		if (sender instanceof Player){
			
			Player player = (Player) sender;
			String playername = sender.getName();
			
			if (!Objects.equals(player.getScoreboard(), Bukkit.getScoreboardManager().getMainScoreboard())){
				player.sendMessage("not in your current circumstances");
				return false;
			}
			
			World hardcoreWorld = player.getServer().getWorld("hardcore");
			Location location = new Location(hardcoreWorld, 1280, 71, -179);

			int currentTime = (int) ((System.currentTimeMillis())/1000);
			int deathTime = 0;
						
			if (HelloWorldPlugin.hardcoreTimeDead.getScore(playername) != null)					//null check - if score exists
				deathTime = HelloWorldPlugin.hardcoreTimeDead.getScore(playername).getScore();  //set deathTime to that score
			
			
			
							
			if (currentTime - deathTime >= 86400 && deathTime != 0){				
				sender.sendMessage("You died " + ((currentTime - deathTime) /3600) + " hours ago. Good luck, " + playername + ".");				
				player.teleport(location);
				player.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,1F,1F);
			}else if(deathTime == 0){		
				sender.sendMessage("You have never died, good luck");				
				player.teleport(location);
				player.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,1F,1F);
			}else{
				sender.sendMessage("you are dead for the next " + ((86400 - (currentTime - deathTime)) /3600) + " hours");
				player.getWorld().playSound(player.getLocation(), Sound.AMBIENT_CAVE,1F,1F);
				// replace sound with some other sound
			}
			
			
			
		}else{			
			sender.sendMessage("You must be a player to use this command!");			
		}
		
		
		
		return false;
	}
	
}
