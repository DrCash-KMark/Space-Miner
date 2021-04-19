package main;

import java.util.Random;

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
public class Robot extends NonPlayer implements Controllable, Drilling {
	/**
	 * Default constructor without parameters.
	 */
	public Robot() {
		super();
		this.setId("rob"+ String.valueOf(nextId));
		nextId++;
	}
	/**
	 * Constructor with parameters.
	 * @param id: String: The id of the Robot
	 * @param owner: The game which this Settler belongs to.
	 * @param asteroid: Asteroid: The Asteroid which this Robot is standing on.
	 * @param random: boolean: Sets the deterministic behaviour.
	 */
	public Robot(String id,  Game owner, Asteroid asteroid, boolean random) {
		super(id, owner, asteroid, random);
	}
	/**
	 * The robot decides what to do.
	 * In every 4th  step it moves to a neighbour asteroid, else he drills.
	 * If it's asteroid hasn't got a neighbour it dies.
	 */
	public void onTurn() {
		if(isRandom == true) {
			if(counter % 5 < 4) {
				this.drill();
			}
			else {
				Random rand = new Random();
				int index = rand.nextInt(this.asteroid.getNeighbours().size());
				this.move(this.asteroid.getNeighbours().get(index));
			}
			counter++;
		}
	}
	/**
	 * The robot's asteroid explodes. If the asteroid has a neighbour, it chooses a random destination to move to.
	 * If the asteroid hasn't got a neighbour, the robot dies.
	 */
	public void asteroidExploded() {
		//main.log(false, name, this.getClass().getName(), "asteroidExploded()");
		if(asteroid.getNeighbours().size() == 0) {
			this.die();
		}
		else {
			int neighbour_index = (int)Math.random() * asteroid.getNeighbours().size();
			this.move(asteroid.getNeighbours().get(neighbour_index));
		}
		//main.log(true, "void", "void", "");
	}	
	public String genUIString() {
		String resstring = "Robot id: "+this.id+"\n isRandom: "+this.getIsRandom()+"\n asteroid: "+this.asteroid.getId();
		return resstring;
	}
	
	/**
	 * Robot moves to an asteroid.
	 * @param destination: Asteroid
	 */
	public void move(Asteroid destination) {
		super.move(destination);
		owner.addTurnEvent("robot move "+this.getId()+" "+destination.getId());
	}
	
	/**
	 * Robot moves to an asteroid through a stargate.
	 * @param destination: StarGate
	 */
	public void move(StarGate destination) {
		super.move(destination);
		owner.addTurnEvent("robot move "+this.getId()+" "+destination.getAsteroid().getId());
	}
	
	/**
	 * Robot dies.
	 */
	public void die() {
		super.die();
		owner.addTurnEvent("robot died "+this.getId());
	}
	

	/**
	 * The robot drills it's asteroid.
	 */
	public void drill() {
		this.asteroid.drilling();
		owner.addTurnEvent("asteroid drilled "+this.asteroid.getId());
	}
	public String genSaveString() {
		String resstring = "id: "+this.id+"\nasteroid: "+this.asteroid.getId()+"\nisRandom: "+this.getIsRandom();	
		return resstring;
	}
	
}
