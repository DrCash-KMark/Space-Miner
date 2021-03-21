package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Entity.java
//  @ Date : 17/03/2021
//  @ Author : TAdam
//
//


/**
 * 
 * @author TAdam
 * Entity abstract base class.
 */

public abstract class Entity {
	protected Asteroid asteroid;
	protected Game owner;
	protected Main main;
	/**
	 * Constructor with parameters.
	 * @param a: Asteroid: The Asteroid which this Entity is standing on.
	 * @param o: Game: The game which this Entity belongs to.
	 */
	public Entity(Asteroid a, Game o) {
		asteroid = a;
		owner = o;
	}
	/**
	 * Default constructor, without parameters.
	 */
	public Entity() {
		
	}
	/**
	 * The getter of the Entity's asteroid.
	 * @return asteroid: Asteroid
	 */
	public Asteroid getAsteroid() {
		return asteroid;
	}
	/**
	 * The setter of the Entity's asteroid.
	 * @param asteroid: Asteroid
	 */
	public void setAsteroid(Asteroid asteroid) {
		this.asteroid = asteroid;
	}
	/**
	 * The getter of the Entity's owner.
	 * @return owner: Game
	 */
	public Game getOwner() {
		return owner;
	}
	/**
	 * The setter of the Entity's owner.
	 * @param owner: Game
	 */
	public void setOwner(Game owner) {
		this.owner = owner;
	}
	/**
	 * The setter of the Main logger.
	 * Only for testing.
	 * @param m: Main
	 */
	public void setMain(Main m) {
		main = m;
	}
	/**
	 * Moves this Entity to another asteroid.
	 * @param destination: Asteroid
	 */
	public void move(Asteroid destination) {
		destination.addEntity(this);
		asteroid.removeEntity(this);
		this.setAsteroid(destination);
	}
	/**
	 * Entity drills his current asteroid.
	 */
	public void drill() {
		asteroid.drilling();
	}
	/**
	 * Entity dies.
	 * Removes the entity from the current asteroid.
	 */
	public void die() {
		asteroid.removeEntity(this);
	}
	//Ez eddig nem volt abstract
	public abstract void asteroidExploded();
	//Ezt nem haszn�ltuk napviharn�l, csak sim�n meghalnak.
	public void getNotifiedAboutSunflare() {
	}
}
