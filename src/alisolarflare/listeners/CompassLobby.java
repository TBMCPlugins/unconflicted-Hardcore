package alisolarflare.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class CompassLobby implements Listener{
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		openGUI(event.getPlayer());
	}
	public void openGUI(Player p){
	    //format: null, size of inventory (must be divisible by 9), "GUI name"
	    Inventory inv = Bukkit.createInventory(null, 9, "GUI Name");
	    inv.setItem(0, new ItemStack(Material.GRASS));
	    inv.setItem(1, new ItemStack(Material.IRON_SWORD));
	    inv.setItem(8, new ItemStack(Material.BARRIER));
	    p.openInventory(inv);
	}
}
