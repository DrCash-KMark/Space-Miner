package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Building.java
//  @ Date : 17/03/2021
//  @ Author : 
//
//



/**
 * Building abstract base class.
 */
public abstract class Building {
	protected Asteroid asteroid;
	protected Game owner;
	protected Main main;
	protected String name;
	
	public Asteroid getAsteroid() { return asteroid; }
	public void setAsteroid(Asteroid a) { asteroid = a; }
	
	public Game getGame() { return owner; }
	public void setGame(Game g) { owner = g; }
	
	public void setMain(Main m) { main = m; }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * constructor for Building without parameters
	 */
	public Building() {
	}
	
	/**
	 * abstract function for placing the building on an asteroid
	 * @param a: Asteroid: asteroid on which the building is placed
	 */
	public abstract void onPlace(); 
	
	/**
	 * abstract function for destroying the building
	 */
	public abstract void destroy(); 
}