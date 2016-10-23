package alisolarflare;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;

import iie.HelloWorldPlugin;

public class RandomTPModule{
	private static int conflictX;
	private static int conflictZ;
	private static int conflictRadius = 70;
	private static boolean northUsed;
	private static boolean southUsed;
	private static boolean eastUsed;
	private static boolean westUsed;
	@SuppressWarnings("unused")
	private HelloWorldPlugin helloWorldPlugin;
	public RandomTPModule(HelloWorldPlugin helloWorldPlugin) {
		this.helloWorldPlugin = helloWorldPlugin;
	}

	//every 4 players who use it will be teleported near each other.
	//ex. iie 2000, ali 2010, byz 2005, charles 1995, wind 300, zan 310, etc
	public static void conflictRtp(Player player, World world, Location minLocation, Location maxLocation){
		
		//INIT - xDifference, xAverage
		int xdifference = minLocation.getBlockX() - maxLocation.getBlockX();
		int xAverage = (int) Math.floor(minLocation.getBlockX() + maxLocation.getBlockX() / 2);

		//INIT - zDifference, zAverage
		int zdifference = minLocation.getBlockX() - maxLocation.getBlockY();
		int zAverage = (int) Math.floor(minLocation.getBlockZ() + maxLocation.getBlockZ() / 2);
		
		//CHECK - Reset Cycle
		if (!(northUsed || southUsed || eastUsed || westUsed)){
			
			//Tries 20 times to find a location
			for(int i = 0; i < 20; i ++){

				//INIT - attemptedX, attemptedZ
				int attemptedX = (int) Math.floor((Math.random()-0.5)*xdifference) + xAverage;
				int attemptedZ = (int) Math.floor((Math.random()-0.5)*zdifference) + zAverage;
				
				int cr = conflictRadius;
				
				
				//CHECKS - if ground is safe
				boolean groundIsSafe = world.getHighestBlockAt(attemptedX, attemptedZ).getType() != Material.WATER;
				boolean northIsSafe = world.getHighestBlockAt(attemptedX, attemptedZ-cr).getType() != Material.WATER;
				boolean southIsSafe = world.getHighestBlockAt(attemptedX, attemptedZ+cr).getType() != Material.WATER;
				boolean westIsSafe = world.getHighestBlockAt(attemptedX-cr, attemptedZ).getType() != Material.WATER;
				boolean eastIsSafe = world.getHighestBlockAt(attemptedX+cr, attemptedZ).getType() != Material.WATER;

				
				//TRANSFER - data to class
				if (groundIsSafe && (northIsSafe || southIsSafe || eastIsSafe || westIsSafe)){
					
					northUsed = !northIsSafe;
					eastUsed = !eastIsSafe;
					westUsed = !westIsSafe;
					southUsed = !southIsSafe;
					conflictX = attemptedX;
					conflictZ = attemptedZ;
					
					player.teleport(world.getHighestBlockAt(attemptedX, attemptedZ).getLocation());
					break;
				}
			}
		}
		
		String dir = "north";

		//CHOOSES A RANDOM DIRECTION
		ArrayList<String> directions = new ArrayList<String>();
		if(!northUsed) directions.add("north");
		if(!southUsed) directions.add("south");
		if(!westUsed) directions.add("west");
		if(!eastUsed) directions.add("east");
		dir = directions.get((int) Math.floor(Math.random() * directions.size()));
		
		//TELEPORT - teleports player to the conflict point
		switch(dir){
			case "north":
				player.teleport(world.getHighestBlockAt(conflictX, conflictZ - conflictRadius).getLocation());
				break;
			case "east":
				player.teleport(world.getHighestBlockAt(conflictX + conflictRadius, conflictZ).getLocation());
				break;
			case "south":
				player.teleport(world.getHighestBlockAt(conflictX, conflictZ + conflictRadius).getLocation());
				break;
			case "west":
				player.teleport(world.getHighestBlockAt(conflictX - conflictRadius, conflictZ).getLocation());
				break;
			default:
				player.teleport(world.getHighestBlockAt(conflictX, conflictZ).getLocation());
				break;
		}
	}

	//Randomly teleports a player, into the hardcore world
	public static void rtp(Player player, World world, Location minLocation, Location maxLocation){
		player.sendMessage("TELEPORT INITIATED");
		player.sendMessage("minLocation: " + minLocation.toString());
		player.sendMessage("maxLocation: " + maxLocation.toString());
		player.sendMessage("world      : " + world.toString());
		player.sendMessage("player     : " + player.toString());

		//INIT - xDifference, xAverage
		int xdifference = minLocation.getBlockX() - maxLocation.getBlockX();
		int xAverage = (int) Math.floor(minLocation.getBlockX() + maxLocation.getBlockX() / 2);
		
		//INIT - zDifference, zAverage
		int zdifference = minLocation.getBlockX() - maxLocation.getBlockY();
		int zAverage = (int) Math.floor(minLocation.getBlockZ() + maxLocation.getBlockZ());
		player.sendMessage("Averages   : " + xAverage + "|" + zAverage);
		//TELEPORTS - Tries 20 times to find a location
		for(int i = 0; i < 20; i ++){
			
			//INIT - attemptedX, attemptedZ
			int attemptedX = (int) Math.floor((Math.random()-0.5)*xdifference) + xAverage;
			int attemptedZ = (int) Math.floor((Math.random()-0.5)*zdifference) + zAverage;
			player.sendMessage("TAKE " + i + " : " + attemptedX + ", "+ attemptedZ);
			//CHECKS - if ground is safe
			boolean groundisSafe = world.getHighestBlockAt(attemptedX, attemptedZ).getType() != Material.WATER;
			if (groundisSafe){
				player.sendMessage("SAFE GROUND, TELEPORTING");
				player.teleport(world.getHighestBlockAt(attemptedX, attemptedZ).getLocation());
				return;
			}

			//player.teleport(arg0)
		}
	}
}
