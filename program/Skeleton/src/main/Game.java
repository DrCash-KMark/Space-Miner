package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Game.java
//  @ Date : 17/03/2021
//  @ Author : Simon Zolt�n
//
//



/**
 * Represents an instance of the running game. Hold reference to all the present Settlers, Robots and Planets in the game.
 * @author Simon
 *
 */
public class Game {
	private Main main;	//Logger
	private String name;
	
	//Lists of objects present in game:
	private List<Settler> settlers = new ArrayList<Settler>();
	private List<Robot> robots = new ArrayList<Robot>();
	private List<Planet> planets = new ArrayList<Planet>();

	//Lists of objects scheduled to destroy:
	private List<Settler> settlersToDestroy = new ArrayList<Settler>();;
	private List<Robot> robotsToDestroy = new ArrayList<Robot>();;
	private List<Planet> planetsToDestroy = new ArrayList<Planet>();;
	
	
	//Event related:-----------------------------------------------------------------
	
	/**
	 * Removes reference to objects, if they called destroyMe or killMe.
	 */
	public void cleanup () {
		for (Settler settler : settlersToDestroy) {
			settlers.remove(settler);
		}
		for (Robot robot : robotsToDestroy) {
			planets.remove(robot);
		}
		for (Planet planet : planetsToDestroy) {
			planets.remove(planet);
		}
		
	}
	
	/**
	 * Notifies all Planets referenced in this Game, about occurrence of a sun flare. 
	 */
	public void notifyAllAboutSunFlare() {
		for (Planet planet : planets) {
			main.log(false, "", "", "getNotifiedAboutSunflare()");
			planet.getNotifiedAboutSunflare();
			main.log(true, "", "", "getNotifiedAboutSunflare()");
		}
	}

	/**
	 * Called, when conditions of victory are met.
	 * Finishes the game.
	 */
	public void gameWon() {
		System.out.println("Game won!");
	}

	//Adders:-----------------------------------------------------------------
	
	/**
	 * Adds Settler to Game.
	 * @param settler Settler to be added.
	 */
	public void addSettler(Settler settler) {
		settlers.add(settler);
	}
	
	/**
	 * Adds Robot to Game.
	 * @param robot Robot to be added.
	 */
	public void addRobot(Robot robot) {
		robots.add(robot);
	}	

	/**
	 * Adds Planet to Game.
	 * @param planet Planet to be added.
	 */
	public void addPlanet(Planet planet) {
		planets.add(planet);
	}	
	
	//Destroyers:---------------------------------------------------------------
	
	/**
	 * Logs request of caller, to kill Settler in parameter.
	 * The Settler will be destroyed right after current game turn. 
	 * @param toKill Settler to kill
	 */
	public void killMe(Settler toKill) {
		settlersToDestroy.add(toKill);
	}
	
	/**
	 * Logs request of caller, to destroy the Robot in parameter.
	 * The Robot will be destroyed right after current game turn. 
	 * @param toDestroy Robot to destroy
	 */
	public void destroyMe(Robot toDestroy) {
		robotsToDestroy.add(toDestroy);		
	}
		
	/**
	 * Logs request of caller, to destroy the Planet in parameter.
	 * The Planet will be destroyed right after current game turn. 
	 * @param toDestroy Planet to destroy
	 */
	public void destroyMe(Planet toDestroy) {
		planetsToDestroy.add(toDestroy);
	}

	//========================================================================
	//Only for testing:------------------------------------------------------
	
	public void setMain(Main m) {
		main = m;
	}
	
	/**
	 * For testing!!!
	 * @return
	 */
	public List<Robot> getRobots() {
		return robots;
	}

	/**
	 * For testing!!!
	 * @return
	 */
	public List<Settler> getSettlers() {
		return settlers;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
