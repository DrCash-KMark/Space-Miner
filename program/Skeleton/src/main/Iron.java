package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Iron.java
//  @ Date : 17/03/2021
//  @ Author : Barkanyi
//
//

public class Iron extends Material {

//Constructors:----------------------------------------------------------------

	/**
	 * Constructor for Iron.
	 */
	Iron(String id){
		super(false, "Iron", false, 0);
		this.id = id;
	}
	
	/**
	 * Constructor for Iron without parameters.
	 */
	Iron(){
		super(false, "Iron", true , 0);
        this.id = "Iron" + String.valueOf(nextId);
        nextId++;
	}

//Inherited:-----------------------------------------------------------------------------

	//Material
	
    /**
     * creates a string storing the most important datas of this class
     *
     * @return
     */
	@Override
	public String genSaveString() {
		 String returnValue = "Iron\n" +
	                "id: " + this.id + "\n" +
	                "isRadio:" + Tools.bool(this.isRadio) + "\n" +
	                "name: " + this.name + "\n" +
	                "canEvaporate: " + Tools.bool(this.canEvaporate) + "\n" +
	                "exposedCounter: " + this.exposedCounter + "\n";
		return returnValue;
	}

}
