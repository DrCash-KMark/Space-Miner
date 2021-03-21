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
	private LinkedList<Material> materials;
	private LinkedList<StarGate> starGate;
	private int capacity;
	
	/**
	 * Constructor for Inventory without parameters.
	 */
	Inventory(){
		this.materials = new LinkedList<>();
		this.starGate = new LinkedList<>();
		this.capacity = 10;
	}
	
	/**
	 * Constructor for the Inventory which sets all parameters at once.
	 * @param materials
	 * @param starGate
	 */
	Inventory(LinkedList<Material> materials, LinkedList<StarGate> starGate, int capacity){
		this.materials = materials;
		this.starGate = starGate;
		this.capacity = capacity;
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
	 * Compares the given and the local inventory material list and 
	 * returns with the not remainder.
	 * @param i the given inventory
	 * @return remainder
	 */
	public Inventory subSet(Inventory i) {
		
		Inventory remainder = new Inventory();
		
		Iterator<Material> iRecipe = materials.iterator();
	    while (iRecipe.hasNext()) {
	    	
	    	Material material = i.removeInventory(iRecipe.next());
	    	if(material==null) {
	    		remainder.addInventory(material);
	    	}
	    	else {
	    		 i.addInventory(material);
	    	}
	    }
		return remainder;
	}
	
	/**
 	 *Compares the given and the local inventory materials list and
	 *removes all contained element and return the remainder.
	 * @param i the given inventory
	 * @return differences
	 */
	public Inventory subtraction(Inventory i) {
		
		Inventory remainder = new Inventory();
		
		Iterator<Material> iRecipe = materials.iterator();
	    while (iRecipe.hasNext()) {
	    	
	    	Material material = i.removeInventory(iRecipe.next());
	    	if(material==null) {
	    		remainder.addInventory(material);
	    	}
	    }
		return remainder;
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
			materials.add(m);
	}
}
