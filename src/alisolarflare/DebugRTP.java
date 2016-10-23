package alisolarflare;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DebugRTP implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)){
			sender.sendMessage("You must be a Player to use this command!");
			sender.sendMessage(sender.toString());
			return false;
		}
		Player player = (Player) sender;
		if(player.getWorld().getName() != "hardcore"){
			sender.sendMessage("You must be in the hardcore world to use this command!");
			sender.sendMessage("Current World: " + player.getWorld().getName());
			return false;
		}
		RandomTPModule.rtp(player, player.getWorld(), new Location(player.getWorld(), 644, 65, -944), new Location(player.getWorld(), 1700, 65, 464));
		return false;
	}
}
