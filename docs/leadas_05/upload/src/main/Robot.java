package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Robot.java
//  @ Date : 17/03/2021
//  @ Author : Tadam
//
//



/**
 * Robot class, extends Entity
 * @author Tadam
 *
 */
public class Robot extends Entity implements Controllable {
	/**
	 * Default constructor without parameters.
	 */
	public Robot() {
		super();
	}
	/**
	 * Constructor with parameters.
	 * @param asteroid: Asteroid: The Asteroid which this Robot is standing on.
	 * @param owner: The game which this Settler belongs to.
	 */
	public Robot(Asteroid asteroid, Game owner) {
		super(asteroid, owner);
	}
	/**
	 * The robot decides what to do.
	 */
	@Override
	public void onTurn() {	
	}
	/**
	 * Robot dies.
	 */
	public void die() {
		main.log(false, name, this.getClass().getName(), "die()");
		asteroid.removeRobot(this);
		owner.destroyMe(this);
		main.log(true, "void", "void", "");
	}
	/**
	 * The robot's asteroid explodes. If the asteroid has a neighbour, it chooses a random destination to move to.
	 * If the asteroid hasn't got a neighbour, the robot dies.
	 */
	public void asteroidExploded() {
		main.log(false, name, this.getClass().getName(), "asteroidExploded()");
		if(asteroid.getNeighbours().size() == 0) {
			this.die();
		}
		else {
			int neighbour_index = (int)Math.random() * asteroid.getNeighbours().size();
			this.move(asteroid.getNeighbours().get(neighbour_index));
		}
		main.log(true, "void", "void", "");
	}
	
	@Override
	public void move(Asteroid destination) {
		main.log(false, name, this.getClass().getName(), "move("+destination.getName()+":"+destination.getClass().getName()+")");
		destination.addRobot(this);
		asteroid.removeRobot(this);
		this.setAsteroid(destination);
		main.log(true, "void", "void", "");
	}
	
}