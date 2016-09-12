package alisolarflare.listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class DiamondArmorBlocker implements Listener{
	public static List<Material> blockedItems = Arrays.asList(Material.DIAMOND_BOOTS, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_HELMET);
	@EventHandler
	public boolean onArmorSmith(CraftItemEvent event){
		//SANITATION - hardcore
		if(event.getWhoClicked().getWorld().getName() != "hardcore"){
			return false;
		}
		
		//INIT - inventory, targetItem
		ItemStack targetItem = event.getRecipe().getResult();
		
		//REPLACE - Diamond Chestplate > Chainmail Chestplate
		if (blockedItems.contains(targetItem.getType())){
			event.setCancelled(true);			
			//GIVE - chainmail chestplate
			event.getWhoClicked().getInventory().addItem(failArmor(targetItem.getType()));
			//PLAY - cave sound
			event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.AMBIENT_CAVE,0,0);
			event.getWhoClicked().getWorld().playSound(event.getWhoClicked().getLocation(), Sound.ENTITY_ITEM_BREAK,0,0);
		}
		return false;
		
		
	}
	public ItemStack failArmor(Material material){
		ItemStack failArmor = new ItemStack(material);
		failArmor.addEnchantment(Enchantment.THORNS, 1);
		
		//INIT - Chainmail's lore
		List<String> loreString = new ArrayList<String>();
		loreString.add("This world is forever dangerous.");
		loreString.add("There is no protection here.");
		failArmor.getItemMeta().setLore(loreString);
		return failArmor;
	}
}
