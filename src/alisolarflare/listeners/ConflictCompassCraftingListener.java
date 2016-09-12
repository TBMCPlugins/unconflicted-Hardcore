package alisolarflare.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class ConflictCompassCraftingListener implements Listener{
	public static void main(String[] args){
		String nulltest = null;
		if(nulltest == null){
			System.out.println("NUUUUULL");
		}
	}

	@EventHandler
	public boolean onConflictCompassCraft(CraftItemEvent event){
		//SANITATION - HARDCORE
		if(event.getWhoClicked().getWorld().getName() != "hardcore")
			return false;
		
		
		//INIT - targetItem
		ItemStack targetItem = event.getRecipe().getResult();
		
		//SANITATION - NOT COMPASS
		if(targetItem.getType() != Material.COMPASS)
			return false;
		
		
		event.setCancelled(true);			
		//GIVE - chainmail chestplate
		event.getWhoClicked().getInventory().addItem(generateConflictCompass(event.getWhoClicked()));
		//PLAY - cave sound
		event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.AMBIENT_CAVE,0,0);
		return false;
	}

	private ItemStack generateConflictCompass(HumanEntity crafter) {
		ItemStack conflictCompass = new ItemStack(Material.COMPASS);
		conflictCompass.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		List<String> loreString = new ArrayList<String>();
		loreString.add("The needle is tipped with the scent of");
		
		loreString.add(nearestPlayerName(crafter));
		conflictCompass.getItemMeta().setLore(loreString);
		return null;
	}

	private String nearestPlayerName(HumanEntity crafter) {
		Player nearestPlayer = null;
		for(Player player: crafter.getWorld().getPlayers()){
			if (player.getUniqueId() == crafter.getUniqueId()){
				//SKIP CODE
			}if (nearestPlayer == null){
				nearestPlayer = player;				
			}else if (nearestPlayer.getLocation().distance(crafter.getLocation()) > player.getLocation().distance(crafter.getLocation())){
				nearestPlayer = player;
			}
		}
		if(nearestPlayer == null)
			return "METAL";
		
		return nearestPlayer.toString();
	}
}
