package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Material.java
//  @ Date : 17/03/2021
//  @ Author : Barkanyi
//
//




public abstract class Material implements Cloneable{
	protected boolean isRadio;
	protected String name;
	protected boolean canEvaporate;
	protected Main main;
	
	/**
	 * constructor for the Material which sets all parameters at once	
	 * @param isRadio
	 * @param name
	 * @param canEvaporate
	 */
	Material(boolean isRadio, String name, boolean canEvaporate){
		this.isRadio = isRadio;
		this.name = name;
		this.canEvaporate = canEvaporate;
	}
	
	Material(Material m)
	{
		isRadio = m.isRadio();
		name = m.getName();
		canEvaporate = m.isCanEvaporate();
		main = m.getMain();
	}
	
	public boolean isRadio() {
		return isRadio;
	}

	public void setRadio(boolean isRadio) {
		this.isRadio = isRadio;
	}

	public boolean isCanEvaporate() {
		return canEvaporate;
	}

	public void setCanEvaporate(boolean canEvaporate) {
		this.canEvaporate = canEvaporate;
	}

	public Main getMain() {
		return main;
	}
	
	public Material clone()throws CloneNotSupportedException{  
		return (Material)super.clone();
	  }

	/**
	 * The setter of the Main logger.
	 * Only for testing.
	 * @param m: Main
	 */
	public void setMain(Main m) {
		main = m;
	}
	
	public void exposedAndCloseToSun(Asteroid a) {
		this.main.log(false, this.name, this.getClass().getName(), "exposedAndCloseToSun(" + a.getName() + ":" + (a.getClass().getName()));
		this.main.log(true, "void", "void", "");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {		
		this.name = name;
	}
	
}
