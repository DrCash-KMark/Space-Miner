package main;

import java.util.Iterator;
import java.util.LinkedList;

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
public class Inventory extends Printable{
	private LinkedList<Material> materials;
	private LinkedList<StarGate> starGates;
	private int capacityM;
	private int capacitySG;

//Constructors:----------------------------------------------------------------
	
	/**
	 * Constructor for Inventory without parameters.
	 */
	Inventory(){
        this.id = "I" + String.valueOf(nextId);
        nextId++;
		
		this.materials = new LinkedList<>();
		this.starGates = new LinkedList<>();
		this.capacityM = 10;
		this.capacitySG = 3;
	}
	
	/**
	 * Constructor for the Inventory which sets all parameters at once.
	 * @param materials
	 * @param starGate
	 */
	Inventory(String id, LinkedList<Material> materials, LinkedList<StarGate> starGates, int capacityM, int capacitySG){
		this.id = id;
		this.materials = materials;
		this.starGates = starGates;
		this.capacityM = capacityM;
		this.capacitySG = capacitySG;
	}

//Get/Set-----------------------------------------------------------------

	/**
	 * Getter of the materials.
	 * @return materials: LinkedList<Material>
	 */
	public LinkedList<Material> getMaterials(){
		return materials;
	}
	
	/**
	 * Getter of the materials.
	 * @return materials: LinkedList<Material>
	 */
	public LinkedList<StarGate> getStarGates() {
		return starGates;
	}
	
	public int getStarGatesCount() {
		if(starGates!=null)return starGates.size();
		return 0;
	}
	
	public int getMaterialsCount() {
		if(materials!=null)return materials.size();
		return 0;
	}
	
	public int getStarGatesCapacity() {
		return capacitySG;
	}
	
	public int getMaterialsCapacity() {
		return capacitySG;
	}

//Inherited:-----------------------------------------------------------------------------

	//Printable
		
	/**
	* Creates a string with this class' all important data.
	*
	* @return string: containing all the important information for the user
	*/
	@Override
	public String genUIString() {
		String dataMaterial = "materials:\n";
		String dataStarGate = "starGates:\n";
		for(StarGate starGate : starGates) {
			dataStarGate += "	starGate: " + starGate.getId() + "\n";
		}	
        if(materials.size()==0) {
        	dataMaterial = "materials: -\n";
        }else {
        	for(Material material : materials) {
        		dataMaterial += "material: " + material.getId() + "\n";
			}
        }
        if(starGates.size()==0) {
        	dataStarGate = "starGate: -\n";
        }else {
        	for(StarGate starGate : starGates) {
        		dataStarGate += "	starGate: " + starGate.getId() + "\n";
    		}
        }
		return "Inventory id: " + id + "\n"
				+ dataMaterial + dataStarGate;
	}

    /**
     * Creates a string storing the most important datas of this class.
     *
     * @return string: containing all the important information.
     */
	@Override
	public String genSaveString() {
        String returnValue = "id: " + this.id + "\n" +
                "capacityM:" + this.capacityM + "\n" +
                "capacitySG: " + this.capacitySG + "\n";
        if(materials.size()==0) {
        	returnValue += "MaterialId: -\n";
        }else {
        	for(Material material : materials) {
				returnValue += "MaterialId:" + material.getId() + "\n";
			}
        }
        if(starGates.size()==0) {
        	returnValue += "StarGatesId: -\n";
        }else {
        	for(StarGate starGate : starGates) {
    			returnValue += "StarGatesId: " + starGate.getId() + "\n";
    		}
        }
        return returnValue;
	}

//Own methods:----------------------------------------------------------------------------

	public boolean isFullMaterials() {
		return capacityM>materials.size()?true:false;
	}
	
	public boolean isFullStarGates() {
		return capacitySG>starGates.size()?true:false;
	}
	
	/**
	 * Remove a material to the materials which are in the inventory.
	 * @param m : the material that will be removed from the inventory
	 * @return Material: removed material or null if the inventory not contains the material
	 */
	public Material removeMaterial(Material m) {
	
		if(materials!= null && materials.remove(m)) {
			return m;
		}
		return null;
	}
	
	/**
	 * Remove a stargate to the stargates which are in the inventory.
	 * @return StarGate: removed stargate or null if the inventory not contains stargate
	 */
	public StarGate removeStarGate() {
		
		if(starGates!= null && starGates.size()!=0) {
			return starGates.get(0);
		}
		return null;
	}
	
	/**
	 * Adds the given stargate to the stargate list.
	 * If the inventory capacity value is -1 then it's not have limit.
	 * @param sg: the stargate the will be added to the stargate list
	 */
	public void addStarGate(StarGate sg) {

		if(sg != null && (starGates.size()<capacitySG || capacitySG == -1)) {
			starGates.add(sg);
		}
	}
	
	/**
	 * Adds the given material to the materials list.
	 * If the inventory capacity value is -1 then it's not have limit.
	 * @param m the material the will be added to the materials list
	 */
	public void addMaterial(Material m) {
		if (m != null && (materials.size()<capacityM || capacityM == -1))
		{
			materials.add(m);
		}
	}
	
	/**
	 * Compares the given and the local inventory material list and 
	 * returns with the not remainder.
	 * @param i: the given inventory
	 * @return Inventory: remainder 
	 */
	public Inventory subSet(Inventory i) {
		if(i==null)return null;
		Inventory remainder = new Inventory();
		
		Iterator<Material> iRecipe = materials.iterator();
	    while (iRecipe.hasNext()) {
	    	
	    	Material material = i.removeMaterial(iRecipe.next());
	    	if(material==null) {
	    		remainder.addMaterial(material);
	    	}
	    	else {
	    		 i.addMaterial(material);
	    	}
	    }

		return remainder;
	}
	
	/**
 	 *Compares the given and the local inventory materials list and
	 *removes all contained element and return the remainder.
	 * @param i: the given inventory
	 * @return Inventory: differences
	 */
	public Inventory subtraction(Inventory i) {
		if(i==null)return null;
		Inventory remainder = new Inventory();
		
		Iterator<Material> iRecipe = materials.iterator();
	    while (iRecipe.hasNext()) {
	    	
	    	Material material = i.removeMaterial(iRecipe.next());
	    	if(material==null) {
	    		remainder.addMaterial(material);
	    	}
	    }
		return remainder;
	}	
}
		