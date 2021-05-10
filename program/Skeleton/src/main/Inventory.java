package main;

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
        this.id = "inv" + String.valueOf(nextId);
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
	 * Getter for the materials.
	 * @return materials: LinkedList<Material>
	 */
	public LinkedList<Material> getMaterials(){
		return materials;
	}
	
	/**
	 * Getter for the materials.
	 * @return materials: LinkedList<Material>
	 */
	public LinkedList<StarGate> getStarGates() {
		return starGates;
	}
	
	/**
	 * Getter for the StarGates count.
	 * @return int: count
	 */
	public int getStarGatesCount() {
		if(starGates!=null)return starGates.size();
		return 0;
	}
	
	/**
	 * Getter for the StarGates count.
	 * @return int: count
	 */
	public int getMaterialsCount() {
		if(materials!=null)return materials.size();
		return 0;
	}
	
	/**
	 * Getter for the StarGates capacity.
	 * @return int: capacity
	 */
	public int getStarGatesCapacity() {
		return capacitySG;
	}
	
	/**
	 * Getter for the Material capacity.
	 * @return int: capacity
	 */
	public int getMaterialsCapacity() {
		return capacitySG;
	}
	
	/**
	 * Setter for the Material capacity.
	 * @param int: value
	 */
	public void setCapacitySG(int value) {
		this.capacitySG=value;
	}
	
	/**
	 * Setter for the StarGate capacity.
	 * @param int: value
	 */
	public void setCapacityM(int value) {
		this.capacityM=value;
	}
	
	/**
	 * True, if the Material inventory is full.
	 * @param boolean
	 */
	public boolean isFullMaterials() {
		return capacityM>materials.size()?false:true;
	}
	
	/**
	 * True, if the StarGates inventory is full.
	 * @param boolean
	 */
	public boolean isFullStarGates() {
		return capacitySG>starGates.size()?false:true;
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
        if(materials.size()==0) {
        	dataMaterial += " material: -\n";
        }else {
        	for(int i =0; i<materials.size(); i++) {
        			dataMaterial += " material: " + materials.get(i).id + "\n";
        	}
        }
        if(starGates.size()==0) {
        	dataStarGate += " starGate: -";
        }else {
        	for(int i =0; i<starGates.size(); i++) {
        		if(i<starGates.size()-1) {
        			dataStarGate += " starGate: " + starGates.get(i).id + "\n";
        		}
        		else {
        			dataStarGate += "	starGate: " + starGates.get(i).id;
        		}
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
                "capacityM: " + this.capacityM + "\n" +
                "capacitySG: " + this.capacitySG + "\n";
    	for(int i =0; i<materials.size(); i++) {
    		returnValue += "MaterialId: " + materials.get(i).id + "\n";
		}

    	for(int i =0; i<starGates.size(); i++) {
    		if(i<starGates.size()-1) {
    			returnValue += "StarGatesId: " + starGates.get(i).id + "\n";
    		}
    		else {
    			returnValue += "StarGatesId: " + starGates.get(i).id;
    		}
		}
        return returnValue;
	}

//Own methods:----------------------------------------------------------------------------
	
	/**
	 * Remove a material to the materials which are in the inventory.
	 * @param m : the material that will be removed from the inventory
	 * @return Material: removed material or null if the inventory not contains the material
	 */
	public Material removeMaterial(Material m) {
		
		for(int i = 0; i<materials.size();i++) {

			   if(materials.get(i).equals(m)) {
				  Material temp = materials.get(i);
				  materials.remove(temp);
			      return temp;
			   }

		}

		return null;
	}
	
	/**
	 * Remove a material from the given list.
	 * @param m : the material that will be removed from the inventory
	 * @param materialList : the target list
	 * @return Material: removed material or null if the inventory not contains the material
	 */
	public Material removeMaterialFromList(LinkedList<Material> materialList,Material m) {
		
		for(int i = 0; i<materialList.size();i++) {

			   if(materialList.get(i).getName().equals(m.getName())) {
				  Material temp = materialList.get(i);
				  materialList.remove(temp);
			      return temp;
			   }

		}

		return null;
	}
	
	/**
	 * Remove a stargate to the stargates which are in the inventory.
	 * @return StarGate: removed stargate or null if the inventory not contains stargate
	 */
	public StarGate removeStarGate(StarGate SG) {
		for(int i = 0; i<starGates.size();i++) {

			   if(starGates.get(i).getId().equals(SG.getId())) {
				  StarGate temp = starGates.get(i);
				  starGates.remove(temp);
			      return temp;
			   }

		}
		return null;
	}
	
	/**
	 * True, if the Inventory contains the given StarGate.
	 * @param SG
	 * @return boolean
	 */
	public boolean isHaveStarGate(StarGate SG) {
		for(int i = 0; i<starGates.size();i++) {

			   if(starGates.get(i).getId().equals(SG.getId())) {
			      return true;
			   }

		}
		return false;
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
	 * returns with the remainder.
	 * @param i: the given inventory
	 * @return LinkedList<Material>: remainder 
	 */
	public LinkedList<Material> subSet(LinkedList<Material> materialList) {
		LinkedList<Material> remainder = new LinkedList<>();
		LinkedList<Material> copyMatList = new LinkedList<Material>();
		for(Material item : materialList) {
			if(item.getName().equals("Iron")) {
				copyMatList.add(new Iron("temp"));
			}
			if(item.getName().equals("Uran")) {
				copyMatList.add(new Uran("temp"));
			}
			if(item.getName().equals("Ice")) {
				copyMatList.add(new Ice("temp"));
			}
			if(item.getName().equals("Coal")) {
				copyMatList.add(new Coal("temp"));
			}
		}
		if(materialList.size()!=0) {
			for(Material item : materials) {
		    	
		    	Material material = removeMaterialFromList(copyMatList,item);
		    	if(material==null) {
		    		remainder.add(item);
		    	}
		    }
		}
		return remainder;
	}
	
	/**
 	 *Compares the given and the local inventory materials list and
	 *removes all contained element and return the remainder.
	 * @param i: the given inventory
	 * @return LinkedList<Material>: differences
	 */
	public LinkedList<Material> subtraction(LinkedList<Material> materialList) {
		LinkedList<Material> remainder = new LinkedList<>();
		if(materialList.size()!=0) {
			for(Material item : materials) {
		    	
		    	Material material = removeMaterialFromList(materialList,item);
		    	if(material==null) {
		    		remainder.add(item);
		    	}
		    }
		}
		return remainder;
	}	
}
		