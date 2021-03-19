package main;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Inventory.java
//  @ Date : 17/03/2021
//  @ Author : Barkanyi
//
//


/**
 * List are only available in java7 or older.
 * @author Barkanyi
 *
 */
public class Inventory {
	private List<Material> materials;
	private List<StarGate> starGate;
	
	/**
	 * Constructor for Inventory without parameters.
	 */
	Inventory(){
		materials = new LinkedList<>();
	}
	
	/**
	 * Constructor for the Inventory which sets all parameters at once.
	 * @param materials
	 * @param starGate
	 */
	Inventory(List<Material> materials, List<StarGate> starGate){
		this.materials = materials;
		this.starGate = starGate;
	}
	
	/**
	 * Remove a material to the materials which are in the inventory.
	 * @param m the material that will be removed from the inventory
	 * @return removed material or null if the inventory not contains the material
	 */
	public Material removeInventory(Material m) {
		if(materials.remove(m)) {
			return m;
		}
		return null;
	}
	
	/**
	 * Remove a stargate to the stargates which are in the inventory.
	 * @return removed stargate or null if the inventory not contains stargate
	 */
	public StarGate removeStarGate() {
		if(starGate.size()!=0) {
			return starGate.get(0);
		}
		return null;
	}
	
	/**
	 * Compares the given and the local inventory materials list and 
	 * if contains one the other then remove all specific material from local.
	 * @param i the given inventory
	 * @return true if the local contains the given or false
	 */
	public boolean subSet(Inventory i) {
		
		LinkedList<Material> components = new LinkedList<>();
		
		Iterator<Material> iComponents;
		
		Iterator<Material> iInventory = materials.iterator();
	    while (iInventory.hasNext()) {
	    	
	    	Material component = i.removeInventory(iInventory.next());
	    	if(component!=null) {
	    		components.add(component);
	    	}
	    	else {
	    		 iComponents = components.iterator();
	    		 while (iComponents.hasNext()) {
	    			 i.addInventory(iComponents.next());
	    			 iComponents.remove();
	    		 }
	    		 return false;
	    	}
	    }
	    iComponents = components.iterator();
	    while (iComponents.hasNext()) {
	    	materials.add(iComponents.next());
	    }
	    
		return true;
	}
	
	
	/**
	 * Adds the given stargate to the stargate list.
	 * @param sg the stargate the will be added to the stargate list
	 */
	public void addStarGate(StarGate sg) {
		if(starGate.size()<2) {
			starGate.add(sg);
		}
	}
	
	/**
	 * Adds the given material to the materials list.
	 * @param m the material the will be added to the materials list
	 */
	public void addInventory(Material m) {
		if(materials.size()<10) {
			materials.add(m);
		}
	}
}
