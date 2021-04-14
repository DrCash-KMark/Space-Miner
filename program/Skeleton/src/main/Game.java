package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
	private UI ui;
	
	//Lists of objects present in game:
	private List<Settler> settlers = new ArrayList<Settler>();
	//private List<Controllable> controllables = new ArrayList<Controllable>();

	// maybe?
	private List<Sun> suns = new ArrayList<Sun>();
	private List<Asteroid> asteroids = new ArrayList<Asteroid>();
	private List<NonPlayer> nonPlayers = new ArrayList<NonPlayer>();
	
	//Lists of objects scheduled to destroy:
	private List<Settler> settlersToRemove = new ArrayList<Settler>();
	//private List<Controllable> controllablesToRemove = new ArrayList<Controllable>();
	
	private List<Sun> sunsToRemove = new ArrayList<Sun>();
	private List<Asteroid> asteroidsToRemove = new ArrayList<Asteroid>();
	private List<NonPlayer> nonPlayersToRemove = new ArrayList<NonPlayer>();
	
	//Event related:-----------------------------------------------------------------
	
	/**
	 * For testing only!
	 * Removes reference to objects, if they called destroyMe or killMe.
	 */
	public void cleanup () {
		for (Settler settler : settlersToRemove) {
			settlers.remove(settler);
		}
		
		/*for (Controllable controllable : controllablesToRemove) {
			controllables.remove(controllable);
		}*/
		
		for (Sun sun : sunsToRemove) {
			suns.remove(sun);
		}
		
		for (Asteroid asteroid : asteroidsToRemove) {
			asteroids.remove(asteroid);
		}
		
		for (NonPlayer nonPlayer : nonPlayersToRemove) {
			nonPlayers.remove(nonPlayer);
		}
	}

	/**
	 * Adds Settler to Game.
	 * @param settler Settler to be added.
	 */
	public void addSettler(Settler settler) {
		settlers.add(settler);
	}
	
	/*public void addControllable(Controllable controllable) {
		controllables.add(controllable);
	}*/
	
	public void addSun(Sun sun) {
		suns.add(sun);
	}
	
	public void addAsteroid(Asteroid asteroid) {
		asteroids.add(asteroid);
	}
	
	public void addNonPlayer(NonPlayer nonPlayer) {
		nonPlayers.add(nonPlayer);
	}
	
	public void removeSettler(Settler settler) {
		settlersToRemove.add(settler);
	}
	
	/*public void removeControllable(Controllable controllable) {
		controllablesToRemove.add(controllable);
	}*/
	
	public void removeSun(Sun sun) {
		sunsToRemove.add(sun);
	}
	
	public void removeAsteroid(Asteroid asteroid) {
		asteroidsToRemove.add(asteroid);
	}
	
	public void removeNonPlayer(NonPlayer nonPlayer) {
		nonPlayersToRemove.add(nonPlayer);
	}
	
	/**
	 * Called, when conditions of victory are met.
	 * Finishes the game.
	 */
	public void gameWon() {
		ui.displayMessage("The game is won!");
	}
	
	public Boolean isGameLost() {
		cleanup();
		
		if (settlers.size() == 0)
			return true;
		
		return true;
	}
	
	public void gameLost() {
		for (Settler settler : settlers)
			settlersToRemove.add(settler);
		
		/*for (Controllable controllable : controllables)
			controllablesToRemove.add(controllable);*/
		
		for (Sun sun : suns)
			sunsToRemove.add(sun);
		
		for (Asteroid asteroid : asteroids)
			asteroidsToRemove.add(asteroid);
		
		for (NonPlayer nonPlayer : nonPlayers)
			nonPlayersToRemove.add(nonPlayer);
		
		cleanup();
		
		ui.displayMessage("The game is lost!");
	}
	
	public void startTurn() {
		cleanup();
		
		for (Sun sun : suns)
			sun.onTurn();
		
		for (Asteroid asteroid : asteroids)
			asteroid.onTurn();
		
		for (NonPlayer nonPlayer : nonPlayers)
			nonPlayer.onTurn();
	}
	
	public void listAllSettlers() {
		String ret = "";
		
		for (Settler settler : settlers)
			ret += settler.genUIString();
		
		ui.displayMessage(ret);
	}
	
	public void initGame(Boolean isManual) {
		if (isManual)
			return;
		
		Random rnd = new Random();
		
		int amountOfSuns = rnd.nextInt(10) + 10;
		int amountOfAsteroidsPerSun = rnd.nextInt(30) + 10;
		
		int amountOfSettlers = 10;
		int amountOfAliens = 30;
		
		for (int i = 0; i < amountOfSuns; i++) {
			Sun s = new Sun();
			
			addSun(s);
			
			for (int j = 0; j < amountOfAsteroidsPerSun; j++) {
				Asteroid a = new Asteroid();
				a.initialize();
				
				s.addAsteroid(a);
				
				addAsteroid(a);
			}
		}
		
		int numOfAsteroids = amountOfSuns * amountOfAsteroidsPerSun;
		int asteroidIndex = rnd.nextInt(numOfAsteroids);
		Asteroid startAsteroid = asteroids.get(asteroidIndex);
		
		for (int i = 0; i < amountOfSettlers; i++)
		{
			Settler s = new Settler();
			
			startAsteroid.addSettler(s);
		}
		
		for (int i = 0; i < amountOfAliens; i++)
		{
			Alien a = new Alien();
			
			asteroids.get(rnd.nextInt(numOfAsteroids)).addNonPlayer(a);
		}
	}
	
	// do we need this?
	public void executeCommand(String arguments) {
		
	}
	
	public void startGame() {
		startTurn();
	}
	
	public void loadGame(String fileName) {
		String loadString;
		
		try {
			File loadFile = new File(fileName);
			Scanner loadScanner = new Scanner(loadFile);
			while (loadScanner.hasNextLine())
				loadString += loadScanner.nextLine();
		
			loadScanner.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("Can't load game.");
		}

		//TODO
	}
	
	public void saveGame(String fileName) {
		String saveString = "";
		
		for (Sun sun : suns)
			saveString += sun.genUIString();
		
		for (Asteroid asteroid : asteroids)
			saveString += asteroid.genUIString();
		
		for (Settler settler : settlers)
			saveString += settler.genUIString();
		
		for (NonPlayer nonPlayer : nonPlayers)
			saveString += nonPlayer.genUIString();
		
		try {
			FileWriter saveWriter = new FileWriter(fileName);
			saveWriter.write(saveString);
			saveWriter.close();
		} 
		catch (IOException e) {
		    System.out.println("Can't save game.");
		}
	}
	
	public void list(String id) {
		Settler s;
		
		for (Settler settler : settlers) {
			if (settler.getId() == id)
				s = settler;
		}
		
		ui.displayMessage(s.genUIString());
	}
}
