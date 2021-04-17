package main;

import java.util.ArrayList;
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
	
	private List<String> turnEvents = new ArrayList<String>();
	
	public void setUI(UI u) {
		ui = u;
	}
	
	//Event related:-----------------------------------------------------------------
	
	public void addTurnEvent(String s) {
		turnEvents.add(s);
	}
	
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
	
	public void listTurnEvents() {
		String displayString = "";
		
		for (String s : turnEvents) {
			displayString += s + "\n";
		}
		
		ui.displayMessage(displayString);
	}
	
	public void listAll() {
		for (Sun sun : suns) {
			ui.displayMessage(sun.genUIString());
			ui.displayMessage("--------------------");
		}
		
		for (Asteroid asteroid : asteroids) {
			ui.displayMessage(asteroid.genUIString());
			ui.displayMessage("--------------------");
		}
		
		for (Settler settler : settlers) {
			ui.displayMessage(settler.genUIString());
			ui.displayMessage("--------------------");
		}
		
		for (NonPlayer nonPlayer : nonPlayers) {
			ui.displayMessage(nonPlayer.genUIString());
			ui.displayMessage("--------------------");
		}
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
		
		startGame();
	}
	
	public void startGame() {
		startTurn();
	}
	
	public void loadGame(String fileName) {
		List<StarGate> starGateLoadList = new ArrayList<StarGate>();
		List<Base> baseLoadList = new ArrayList<Base>();
		List<Material> materialLoadList = new ArrayList<Material>();
		List<Alien> alienLoadList = new ArrayList<Alien>();
		List<Robot> robotLoadList = new ArrayList<Robot>();
		List<Sun> sunLoadList = new ArrayList<Sun>();
		List<Asteroid> asteroidLoadList = new ArrayList<Asteroid>();
		List<Settler> settlerLoadList = new ArrayList<Settler>();
		List<Inventory> inventoryLoadList = new ArrayList<Inventory>();
		
		String loadString;
		
		try {
			File loadFile = new File(fileName);
			Scanner loadScanner = new Scanner(loadFile);
			
			while (loadScanner.hasNextLine())
			{
				loadString = loadScanner.nextLine();
				
				switch (loadString) {
					case "Sun":
						loadSun(loadScanner, sunLoadList, asteroidLoadList);
						
						break;
					case "Asteroid":
						loadAsteroid(loadScanner, asteroidLoadList, settlerLoadList, materialLoadList, robotLoadList, alienLoadList, baseLoadList, starGateLoadList);
						
						break;
					case "Material":
						loadString = loadScanner.nextLine();
						
						switch (loadString.split(" ")[1].charAt(0)) {
							case 'i':
								if (loadString.split(" ")[1].charAt(1) == 'c') {
									Ice ice = new Ice();
									//ice.setId(loadString.split(" ")[1]);
									
									loadMaterial(ice, loadScanner, materialLoadList);
								}
								else {
									Iron iron = new Iron();
									//iron.setId(loadString.split(" ")[1]);
									
									loadMaterial(iron, loadScanner, materialLoadList);
								}
								
								break;
							case 'u':
								Uran uran = new Uran();
								//uran.setId(loadString.split(" ")[1]);
								
								loadMaterial(uran, loadScanner, materialLoadList);
								
								break;
							case 'c':
								Coal coal = new Coal();
								//coal.setId(loadString.split(" ")[1]);
								
								loadMaterial(coal, loadScanner, materialLoadList);
								
								break;
						}
						
						break;
					case "Building":
						loadString = loadScanner.nextLine();
						
						if (loadString.split(" ")[1].charAt(0) == 's')
							loadStarGate(loadScanner, loadString, starGateLoadList, asteroidLoadList);
						else
							loadBase(loadScanner, loadString, baseLoadList, asteroidLoadList);
						
						break;
					case "Settler":
						loadSettler(loadScanner, settlerLoadList, asteroidLoadList, inventoryLoadList);
						
						break;
					case "Inventory":
						loadInventory(loadScanner, inventoryLoadList, materialLoadList, starGateLoadList);
						
						break;
					case "NonPlayer":
						loadString = loadScanner.nextLine();
						
						if (loadString.split(" ")[1].charAt(0) == 'r')
							loadRobot(loadScanner, loadString, robotLoadList, asteroidLoadList);
						else
							loadAlien(loadScanner, loadString, alienLoadList, asteroidLoadList, inventoryLoadList);
						
						break;
				}
			}
			
			loadScanner.close();
			
			for (Sun sun : sunLoadList)
				suns.add(sun);
			
			for (Asteroid asteroid : asteroidLoadList)
				asteroids.add(asteroid);
			
			for (Settler settler : settlerLoadList)
				settlers.add(settler);
			
			for (Alien alien : alienLoadList)
				nonPlayers.add(alien);
			
			for (Robot robot : robotLoadList)
				nonPlayers.add(robot);
		} 
		catch (FileNotFoundException e) {
			System.out.println("Can't load game.");
		}
	}
	
	public void loadSun(Scanner scanner, List<Sun> sunLoadList, List<Asteroid> asteroidLoadList) {
		String loadString = scanner.nextLine();
		
		Boolean foundSun = false;
		Sun s = new Sun();
		
		// for clarity
		for (Sun sun : sunLoadList) {
			if (sun.getId().equals(loadString.split(" ")[1])) {
				foundSun = true;
				s = sun;
			}
		}
		
		if (!foundSun) {
			sunLoadList.add(s);
			s.setId(loadString.split(" ")[1]);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			s.setIsRandom(true);
		else
			s.setIsRandom(false);
		
		loadString = scanner.nextLine();
		
		while (loadString.equals("AsteroidId:")) {
			Boolean foundAsteroid = false;
			
			for (Asteroid asteroid : asteroidLoadList) {
				if (asteroid.getId().equals(loadString.split(" ")[1])) {
					foundAsteroid = true;
					s.addAsteroid(asteroid);
				}
			}
				
			if (!foundAsteroid) {
				Asteroid a = new Asteroid();
				a.setId(loadString.split(" ")[1]);
					
				s.addAsteroid(a);
					
				asteroidLoadList.add(a);
			}
			
			loadString = scanner.nextLine();
		}
		
		s.setOwner(this);
	}
	
	public void loadAsteroid(Scanner scanner, List<Asteroid> asteroidLoadList, List<Settler> settlerLoadList, List<Material> materialLoadList, List<Robot> robotLoadList, List<Alien> alienLoadList, List<Base> baseLoadList, List<StarGate> starGateLoadList) {
		String loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		Asteroid a = new Asteroid();
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				a = asteroid;
			}
		}
		
		if (!foundAsteroid) {
			asteroidLoadList.add(a);
			a.setId(loadString.split(" ")[1]);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			a.setIsRandom(true);
		else
			a.setIsRandom(false);
		
		loadString = scanner.nextLine();
		
		a.setRockThickness(Integer.parseInt(loadString.split(" ")[1]));
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			a.setCloseToSun(true);
		else
			a.setCloseToSun(false);
		
		loadString = scanner.nextLine();
		
		a.setCapacity(Integer.parseInt(loadString.split(" ")[1]));
		
		loadString = scanner.nextLine();
		
		while (loadString.split(" ")[0].equals("settlerId:")) {
			Boolean foundSettler = false;
			
			for (Settler settler : settlerLoadList) {
				if (settler.getId().equals(loadString.split(" ")[1])) {
					foundSettler = true;
					a.addSettler(settler);
				}
			}
			
			if (!foundSettler) {
				Settler settler = new Settler();
				settler.setId(loadString.split(" ")[1]);
				
				a.addSettler(settler);
				
				settlerLoadList.add(settler);
			}
			
			loadString = scanner.nextLine();
		}
		
		while (loadString.split(" ")[0].equals("nonPlayerId:")) {
			Boolean foundNonPlayer = false;
			
			for (Alien alien : alienLoadList) {
				if (alien.getId().equals(loadString.split(" ")[1])) {
					foundNonPlayer = true;
					a.addNonPlayer(alien);
				}
			}
			
			for (Robot robot : robotLoadList) {
				if (robot.getId().equals(loadString.split(" ")[1])) {
					foundNonPlayer = true;
					a.addNonPlayer(robot);
				}
			}
			
			if (!foundNonPlayer) {
				if (loadString.split(" ")[1].charAt(0) == 'r') {
					Robot robot = new Robot();
					robot.setId(loadString.split(" ")[1]);
					
					a.addNonPlayer(robot);
					
					robotLoadList.add(robot);
				}
				else
				{
					Alien alien = new Alien();
					alien.setId(loadString.split(" ")[1]);
					
					a.addNonPlayer(alien);
					
					alienLoadList.add(alien);
				}
			}
			
			loadString = scanner.nextLine();
		}

		while (loadString.split(" ")[0].equals("MaterialId:")) {
			Boolean foundMaterial = false;
			
			for (Material material : materialLoadList) {
				if (material.getId().equals(loadString.split(" ")[1])) {
					foundMaterial = true;
					a.addMaterial(material);
				}
			}
			
			if (!foundMaterial) {
				if (loadString.split(" ")[1].charAt(0) == 'i') {
					if (loadString.split(" ")[1].charAt(1) == 'c') {
						Ice ice = new Ice();
						ice.setId(loadString.split(" ")[1]);
						
						a.addMaterial(ice);
						
						materialLoadList.add(ice);
					}
					else {
						Iron iron = new Iron();
						iron.setId(loadString.split(" ")[1]);
						
						a.addMaterial(iron);
						
						materialLoadList.add(iron);
					}
				}
				else if (loadString.split(" ")[1].charAt(0) == 'u') {
					Uran uran = new Uran();
					uran.setId(loadString.split(" ")[1]);
					
					a.addMaterial(uran);
					
					materialLoadList.add(uran);
				}
				else {
					Coal coal = new Coal();
					coal.setId(loadString.split(" ")[1]);
					
					a.addMaterial(coal);
					
					materialLoadList.add(coal);
				}
			}
			
			loadString = scanner.nextLine();
		}

		while (loadString.split(" ")[0].equals("NeighbourId:")) {
			Boolean foundNeighbour = false;
			
			for (Asteroid asteroid : asteroidLoadList) {
				if (asteroid.getId().equals(loadString.split(" ")[1])) {
					foundNeighbour = true;
					a.addNeighbour(asteroid);
				}
			}
			
			if (!foundNeighbour) {
				Asteroid asteroid = new Asteroid();
				asteroid.setId(loadString.split(" ")[1]);
				
				a.addNeighbour(asteroid);
				
				asteroidLoadList.add(asteroid);
			}
			
			loadString = scanner.nextLine();
		}

		while (loadString.split(" ")[0].equals("BuildingId:")) {
			Boolean foundBuilding = false;
			
			for (Base base : baseLoadList) {
				if (base.getId().equals(loadString.split(" ")[1])) {
					foundBuilding = true;
					a.addBuilding(base);
				}
			}
			
			for (StarGate starGate : starGateLoadList) {
				if (starGate.getId().equals(loadString.split(" ")[1])) {
					foundBuilding = true;
					a.addBuilding(starGate);
				}
			}
			
			if (!foundBuilding) {
				if (loadString.split(" ")[1].charAt(0) == 's') {
					StarGate starGate = new StarGate();
					starGate.setId(loadString.split(" ")[1]);
					
					a.addBuilding(starGate);
					
					starGateLoadList.add(starGate);
				}
				else {
					Base base = new Base();
					base.setId(loadString.split(" ")[1]);
					
					a.addBuilding(base);
					
					baseLoadList.add(base);
				}
			}
			
			loadString = scanner.nextLine();
		}
		
		a.setOwner(this);
	}
	
	public void loadMaterial(Material m, Scanner scanner, List<Material> materialLoadList) {
		String loadString = scanner.nextLine();
		
		Boolean foundMaterial = false;
		
		for (Material material : materialLoadList) {
			if (material.getId().equals(loadString.split(" ")[1])) {
				foundMaterial = true;
				m = material;
			}
		}
		
		if (!foundMaterial) {
			materialLoadList.add(m);
			m.setId(loadString.split(" ")[1]);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			m.setRadio(true);
		else
			m.setRadio(false);
		
		loadString = scanner.nextLine(); 
		
		m.setName(loadString.split(" ")[1]);
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			m.setCanEvaporate(true);
		else
			m.setCanEvaporate(false);
		
		loadString = scanner.nextLine();
		
		m.setExposedCounter(Integer.parseInt(loadString.split(" ")[1]));
		
		loadString = scanner.nextLine();
	}
	
	public void loadStarGate(Scanner scanner, String loadString, List<StarGate> starGateLoadList, List<Asteroid> asteroidLoadList) {
		Boolean foundStarGate = false;
		StarGate sg = new StarGate();
		
		for (StarGate starGate : starGateLoadList) {
			if (starGate.getId().equals(loadString.split(" ")[1])) {
				foundStarGate = true;
				sg = starGate;
			}
		}
		
		if (!foundStarGate) {
			sg.setId(loadString.split(" ")[1]);
			starGateLoadList.add(sg);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				sg.setAsteroid(asteroid);
			}
		}
		
		if (!foundAsteroid) {
			Asteroid asteroid = new Asteroid();
			asteroid.setId(loadString.split(" ")[1]);
			
			sg.setAsteroid(asteroid);
			
			asteroidLoadList.add(asteroid);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundNeighbour = false;
		
		for (StarGate neighbour : starGateLoadList) {
			if (neighbour.getId().equals(loadString.split(" ")[1])) {
				foundNeighbour = true;
				sg.setNeighbour(neighbour);
			}
		}
		
		if (!foundNeighbour) {
			StarGate neighbour = new StarGate();
			neighbour.setId(loadString.split(" ")[1]);
			
			sg.setNeighbour(neighbour);
			
			starGateLoadList.add(neighbour);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			sg.setWorks(true);
		else
			sg.setWorks(false);
			
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			sg.setWasInSunFlare(true);
		else
			sg.setWasInSunFlare(false);
			
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			sg.setRandom(true);
		else
			sg.setRandom(false);
		
		loadString = scanner.nextLine();
		
		sg.setOwner(this);
	}
	
	public void loadBase(Scanner scanner, String loadString, List<Base> baseLoadList, List<Asteroid> asteroidLoadList) {
		Boolean foundBase = false;
		Base b = new Base();
		
		for (Base base : baseLoadList) {
			if (base.getId().equals(loadString.split(" ")[1])) {
				foundBase = true;
				b = base;
			}
		}
		
		if (!foundBase) {
			b.setId(loadString.split(" ")[1]);
			baseLoadList.add(b);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				b.setAsteroid(asteroid);
			}
		}
		
		if (!foundAsteroid) {
			Asteroid asteroid = new Asteroid();
			asteroid.setId(loadString.split(" ")[1]);
			
			b.setAsteroid(asteroid);
			
			asteroidLoadList.add(asteroid);
		}
		
		loadString = scanner.nextLine();
		
		b.setOwner(this);
	}
	
	public void loadSettler(Scanner scanner, List<Settler> settlerLoadList, List<Asteroid> asteroidLoadList, List<Inventory> inventoryLoadList) {
		String loadString = scanner.nextLine();
		
		Boolean foundSettler = false;
		Settler s = new Settler();
		
		for (Settler settler : settlerLoadList) {
			if (settler.getId().equals(loadString.split(" ")[1])) {
				foundSettler = true;
				s = settler;
			}
		}
		
		if (!foundSettler) {
			s.setId(loadString.split(" ")[1]);
			settlerLoadList.add(s);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			s.setHadAction(true);
		else
			s.setHadAction(false);
		
		loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				s.setAsteroid(asteroid);
			}
		}
		
		if (!foundAsteroid) {
			Asteroid asteroid = new Asteroid();
			asteroid.setId(loadString.split(" ")[1]);
			
			s.setAsteroid(asteroid);
			
			asteroidLoadList.add(asteroid);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundInventory = false;
		
		for (Inventory inventory : inventoryLoadList) {
			if (inventory.getId().equals(loadString.split(" ")[1])) {
				foundInventory = true;
				s.setInventory(inventory);
			}
		}
		
		if (!foundInventory) {
			Inventory inventory = new Inventory();
			inventory.setId(loadString.split(" ")[1]);
			
			s.setInventory(inventory);
			
			inventoryLoadList.add(inventory);
		}
		
		loadString = scanner.nextLine();
		
		s.setOwner(this);
	}
	
	public void loadInventory(Scanner scanner, List<Inventory> inventoryLoadList, List<Material> materialLoadList, List<StarGate> starGateLoadList) {
		String loadString = scanner.nextLine();
		
		Boolean foundInventory = false;
		Inventory i = new Inventory();
		
		for (Inventory inventory : inventoryLoadList) {
			if (inventory.getId().equals(loadString.split(" ")[1])) {
				foundInventory = true;
				i = inventory;
			}
		}
		
		if (!foundInventory) {
			i.setId(loadString.split(" ")[1]);
			inventoryLoadList.add(i);
		}
		
		loadString = scanner.nextLine();
		
		//i.setCapacityM(Integer.parseInt(loadString.split(" ")[1]));
		
		loadString = scanner.nextLine();
		
		//i.setCapacitySG(Integer.parseInt(loadString.split(" ")[1]));
		
		loadString = scanner.nextLine();
		
		while (loadString.split(" ")[0].equals("MaterialId:")) {
			Boolean foundMaterial = false;
			
			if (loadString.split(" ")[1].charAt(0) == 'i') {
				if (loadString.split(" ")[1].charAt(1) == 'r') {
					Iron iron = new Iron();
					
					for (Material material : materialLoadList) {
						if (material.getId().equals(loadString.split(" ")[1])) {
							foundMaterial = true;
							iron = (Iron)material;
						}
					}
					
					if (!foundMaterial) {
						iron.setId(loadString.split(" ")[1]);
						i.addMaterial(iron);
						
						materialLoadList.add(iron);
					}
				} else {
					Ice ice = new Ice();
					
					for (Material material : materialLoadList) {
						if (material.getId().equals(loadString.split(" ")[1])) {
							foundMaterial = true;
							ice = (Ice)material;
						}
					}
					
					if (!foundMaterial) {
						ice.setId(loadString.split(" ")[1]);
						i.addMaterial(ice);
						
						materialLoadList.add(ice);
					}
				}
			}
			else if(loadString.split(" ")[1].charAt(0) == 'u') {
				Uran uran = new Uran();
				
				for (Material material : materialLoadList) {
					if (material.getId().equals(loadString.split(" ")[1])) {
						foundMaterial = true;
						uran = (Uran)material;
					}
				}
				
				if (!foundMaterial) {
					uran.setId(loadString.split(" ")[1]);
					i.addMaterial(uran);
					
					materialLoadList.add(uran);
				}
			} else {
				Coal coal = new Coal();
				
				for (Material material : materialLoadList) {
					if (material.getId().equals(loadString.split(" ")[1])) {
						foundMaterial = true;
						coal = (Coal)material;
					}
				}
				
				if (!foundMaterial) {
					coal.setId(loadString.split(" ")[1]);
					i.addMaterial(coal);
					
					materialLoadList.add(coal);
				}
			}
		}
		
		while (loadString.split(" ")[0].equals("StarGateId:")) {
			Boolean foundStarGate = false;
			
			StarGate sg = new StarGate();
			
			for (StarGate starGate : starGateLoadList) {
				if (starGate.getId().equals(loadString.split(" ")[1])) {
					foundStarGate = true;
					sg = starGate;
				}
			}
			
			if (!foundStarGate) {
				sg.setId(loadString.split(" ")[1]);
				starGateLoadList.add(sg);
				
				i.addStarGate(sg);
			}
			
			loadString = scanner.nextLine();
		}
		
		loadString = scanner.nextLine();
	}
	
	public void loadRobot(Scanner scanner, String loadString, List<Robot> robotLoadList, List<Asteroid> asteroidLoadList) {
		Boolean foundRobot = false;
		Robot r = new Robot();
		
		for (Robot robot : robotLoadList) {
			if (robot.getId().equals(loadString.split(" ")[1])) {
				foundRobot = true;
				r = robot;
			}
		}
		
		if (!foundRobot) {
			r.setId(loadString.split(" ")[1]);
			robotLoadList.add(r);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				r.setAsteroid(asteroid);
			}
		}
		
		if (!foundAsteroid) {
			Asteroid asteroid = new Asteroid();
			asteroid.setId(loadString.split(" ")[1]);
			
			r.setAsteroid(asteroid);
			
			asteroidLoadList.add(asteroid);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			r.setIsRandom(true);
		else
			r.setIsRandom(false);
		
		loadString = scanner.nextLine();
		
		r.setOwner(this);
	}
	
	public void loadAlien(Scanner scanner, String loadString, List<Alien> alienLoadList, List<Asteroid> asteroidLoadList, List<Inventory> inventoryLoadList) {
		Boolean foundAlien = false;
		Alien a = new Alien();
		
		for (Alien alien : alienLoadList) {
			if (alien.getId().equals(loadString.split(" ")[1])) {
				foundAlien = true;
				a = alien;
			}
		}
		
		if (!foundAlien) {
			a.setId(loadString.split(" ")[1]);
			alienLoadList.add(a);
		}
		
		loadString = scanner.nextLine();
		
		Boolean foundAsteroid = false;
		
		for (Asteroid asteroid : asteroidLoadList) {
			if (asteroid.getId().equals(loadString.split(" ")[1])) {
				foundAsteroid = true;
				a.setAsteroid(asteroid);
			}
		}
		
		if (!foundAsteroid) {
			Asteroid asteroid = new Asteroid();
			asteroid.setId(loadString.split(" ")[1]);
			
			a.setAsteroid(asteroid);
			
			asteroidLoadList.add(asteroid);
		}
		
		loadString = scanner.nextLine();
		
		if (loadString.split(" ")[1].equals("t"))
			a.setIsRandom(true);
		else
			a.setIsRandom(false);
		
		loadString = scanner.nextLine();
		
		Boolean foundInventory = false;
		
		for (Inventory inventory : inventoryLoadList) {
			if (inventory.getId().equals(loadString.split(" ")[1])) {
				foundInventory = true;
				a.setInventory(inventory);
			}
		}
		
		if (!foundInventory) {
			Inventory inventory = new Inventory();
			inventory.setId(loadString.split(" ")[1]);
			
			a.setInventory(inventory);
			
			inventoryLoadList.add(inventory);
		}
		
		loadString = scanner.nextLine();
		
		a.setOwner(this);
	}
	
	public void saveGame(String fileName) {
		String saveString = "";
		
		for (Sun sun : suns) {
			saveString += "Sun:\n" + sun.genSaveString() + "\n----------------------------------------------------\n";
			
			for (Asteroid asteroid : sun.getAsteroids()) {
				saveString += "Asteroid:\n" + asteroid.genSaveString() + "\n----------------------------------------------------\n";
				
				for (Material material : asteroid.getMaterials()) {
					saveString += "Material:\n" + material.genUIString() + "\n----------------------------------------------------\n";
				}
				
				for (Building building : asteroid.getBuildings()) {
					saveString += "Building:\n" + building.genSaveString() + "\n----------------------------------------------------\n";
				}
				
				for (Settler settler : asteroid.getSettelrs()) {
					saveString += "Settler:\n" + settler.genSaveString() + "\n----------------------------------------------------\n";
					
					saveString += "Inventory:\n" + settler.getInventory().genSaveString() + "\n----------------------------------------------------\n";
					
					for (Material material : settler.getInventory().getMaterials()) {
						saveString += "Material:\n" + material.genSaveString() + "\n----------------------------------------------------\n";
					}
					
					for (StarGate stargate : settler.getInventory().getStarGates()) {
						saveString += "Building:\n" + stargate.genSaveString() + "\n----------------------------------------------------\n";
					}
				}
				
				for (NonPlayer nonPlayer : asteroid.getNonPlayers()) {
					saveString += "NonPlayer:" + nonPlayer.genSaveString() + "\n----------------------------------------------------\n";
					
					if (((Alien)nonPlayer).getInventory() != null) {
						for (Material material : ((Alien)nonPlayer).getInventory().getMaterials()) {
							saveString += "Material:\n" + material.genSaveString() + "\n----------------------------------------------------\n";
						}
					}
						
				}
			}
		}
		
		try {
			FileWriter saveWriter = new FileWriter(fileName);
			saveWriter.write(saveString);
			saveWriter.close();
		} 
		catch (IOException e) {
		    System.out.println("Can't save game.");
		}
	}
	
	public Settler getSettlerWithId(String id) {
		for (Settler settler : settlers)
			if (settler.getId().equals(id))
				return settler;
		
		ui.displayMessage("No such settler found");
		return null;
	}
	
	public Asteroid getAsteroidWithId(String id) {
		for (Asteroid asteroid : asteroids)
			if (asteroid.getId().equals(id))
				return asteroid;
		
		ui.displayMessage("No such asteroid found");
		return null;
	}
	
	public Sun getSunWithId(String id) {
		for (Sun sun : suns)
			if (sun.getId().equals(id))
				return sun;
		
		ui.displayMessage("No such sun found");
		return null;
	}
	
	public Alien getAlienWithId(String id) {
		for (NonPlayer nonPlayer : nonPlayers)
			if (nonPlayer.getId().equals(id))
				return (Alien)nonPlayer;
		
		ui.displayMessage("No such alien found");
		return null;
	}
	
	public Robot getRobotWithId(String id) {
		for (NonPlayer nonPlayer : nonPlayers)
			if (nonPlayer.getId().equals(id))
				return (Robot)nonPlayer;
		
		ui.displayMessage("No such robot found");
		return null;
	}
	
	public StarGate getStarGateWithId(String id) {
		for (Settler settler : settlers) {
			for (StarGate starGate : settler.getInventory().getStarGates())
				if (starGate.getId().equals(id))
					return starGate;
		}
		
		for (Asteroid asteroid : asteroids) {
			for (Building starGate : asteroid.getBuildings())
				if (starGate.getId().equals(id))
					return (StarGate)starGate;
		}
		
		ui.displayMessage("No such stargate found");
		return null;
	}
	
	public Base getBaseWithId(String id) {
		for (Asteroid asteroid : asteroids) {
			for (Building base : asteroid.getBuildings())
				if (base.getId().equals(id))
					return (Base)base;
		}
		
		ui.displayMessage("No such base found");
		return null;
	}
	
	public Material getMaterialWithId(String id) {
		for (Settler settler : settlers) {
			for (Material material : settler.getInventory().getMaterials())
				if (material.getId().equals(id))
					return material;
		}
		
		for (Asteroid asteroid : asteroids) {
			for (Material material : asteroid.getMaterials())
				if (material.getId().equals(id))
					return material;
		}
		
		ui.displayMessage("No such material found");
		return null;
	}
}
