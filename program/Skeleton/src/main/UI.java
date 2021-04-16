package main;

public class UI {
	private Game game;
	
	public void displayMessage(String s)
	{
		System.out.println(s);
	}
	
	public void execute(String s)
	{
		String splitString[] = s.split(" ");
		
		switch (splitString[0]) {
			case "init":
				if (splitString[1] == "manually")
					game.initGame(true);
				else
					game.initGame(false);
				
				break;
			case "start":
				if (splitString[1] == "game")
					game.startGame();
				else
					game.startTurn();
				
				break;
			case "load":
				game.loadGame(splitString[1]);
				
				break;
			case "save":
				game.saveGame(splitString[1]);
				
				break;
			case "create":
				switch (splitString[1]) {
					case "settler":
						Settler settler = new Settler();
						
						game.addSettler(settler);
						game.getAsteroidWithId(splitString[4]).addSettler(settler);
						
						break;
					case "robot":
						Robot robot = new Robot();
						
						game.addNonPlayer(robot);
						game.getAsteroidWithId(splitString[4]).addNonPlayer(robot);
						
						break;
					case "alien":
						Alien alien = new Alien();
						
						game.addNonPlayer(alien);
						game.getAsteroidWithId(splitString[4]).addNonPlayer(alien);
						
						break;
					case "asteroid":
						Asteroid asteroid = new Asteroid();
						
						game.addAsteroid(asteroid);
						game.getSunWithId(splitString[4]).addAsteroid(asteroid);
						
						break;
					case "sun":
						Sun sun = new Sun();
						
						game.addSun(sun);
						
						break;
					case "inventory":
						Inventory inventory = new Inventory();
						
						if (splitString[3] == "settler")
							game.getSettlerWithId(splitString[4]).setInventory(inventory);
						else
							game.getAlienWithId(splitString[4]).setInventory(inventory);
						
						break;
					case "coal":
						Coal coal = new Coal();
						
						switch (splitString[3]) {
							case "asteroid":
								game.getAsteroidWithId(splitString[4]).addMaterial(coal);
								
								break;
							case "alien":
								game.getAlienWithId(splitString[4]).getInventory().addMaterial(coal);
								
								break;
							case "settler":
								game.getSettlerWithId(splitString[4]).getInventory().addMaterial(coal);
								
								break;
						}
						
						break;
					case "ice":
						Ice ice = new Ice();
						
						switch (splitString[3]) {
						case "asteroid":
							game.getAsteroidWithId(splitString[4]).addMaterial(ice);
							
							break;
						case "alien":
							game.getAlienWithId(splitString[4]).getInventory().addMaterial(ice);
							
							break;
						case "settler":
							game.getSettlerWithId(splitString[4]).getInventory().addMaterial(ice);
							
							break;
						}
						
						break;
					case "iron":
						Iron iron = new Iron();
						
						switch (splitString[3]) {
						case "asteroid":
							game.getAsteroidWithId(splitString[4]).addMaterial(iron);
							
							break;
						case "alien":
							game.getAlienWithId(splitString[4]).getInventory().addMaterial(iron);
							
							break;
						case "settler":
							game.getSettlerWithId(splitString[4]).getInventory().addMaterial(iron);
							
							break;
						}
						
						break;
					case "uran":
						Uran uran = new Uran();
						
						switch (splitString[3]) {
						case "asteroid":
							game.getAsteroidWithId(splitString[4]).addMaterial(uran);
							
							break;
						case "alien":
							game.getAlienWithId(splitString[4]).getInventory().addMaterial(uran);
							
							break;
						case "settler":
							game.getSettlerWithId(splitString[4]).getInventory().addMaterial(uran);
							
							break;
						}
						
						break;
					case "stargate":
						StarGate starGate = new StarGate();
						
						if (splitString[3] == "asteroid")
							game.getAsteroidWithId(splitString[4]).addBuilding(starGate);
						else
							game.getSettlerWithId(splitString[4]).getInventory().addStarGate(starGate);
						
						break;
					case "base":
						Base base = new Base();
						
						game.getAsteroidWithId(splitString[4]).addBuilding(base);
						
						break;
				}
				
				break;
			case "set":
				switch (splitString[1]) {
					case "settler":
						Settler settler = game.getSettlerWithId(splitString[3]);
						
						switch(splitString[2]) {
							case "id":
								settler.setId(splitString[4]);
								
								break;
							case "asteroid":
								settler.setAsteroid(game.getAsteroidWithId(splitString[4]));
								
								break;
							case "hadActionThisTurn":
								if (splitString[4] == "t")
									settler.setHadAction(true);
								else
									settler.setHadAction(false);
								
								break;
						}
						
						break;
					case "robot":
						Robot robot = game.getRobotWithId(splitString[3]);
						
						switch(splitString[2]) {
							case "id":
								robot.setId(splitString[4]);
								
								break;
							case "asteroid":
								robot.setAsteroid(game.getAsteroidWithId(splitString[4]));
								
								break;
							case "isRandom":
								if (splitString[4] == "t")
									robot.setIsRandom(true);
								else
									robot.setIsRandom(false);
								
								break;
						}
						
						break;
					case "alien":
						Alien alien = game.getAlienWithId(splitString[3]);
						
						switch(splitString[2]) {
							case "id":
								alien.setId(splitString[4]);
								
								break;
							case "asteroid":
								alien.setAsteroid(game.getAsteroidWithId(splitString[4]));
								
								break;
						}
						
						break;
					case "base":
						Base base = game.getBaseWithId(splitString[3]);
						
						switch (splitString[2]) {
							case "id":
								base.setId(splitString[4]);
								
								break;
							case "asteroid":
								base.setAsteroid(game.getAsteroidWithId(splitString[4]));
								
								break;
						}
						
						break;
					case "stargate":
						StarGate starGate = game.getStarGateWithId(splitString[3]);
						
						switch (splitString[2]) {
							case "id":
								starGate.setId(splitString[4]);
								
								break;
							case "asteroid":
								starGate.setAsteroid(game.getAsteroidWithId(splitString[4]));
								
								break;
							case "neighbour":
								starGate.setNeighbour(game.getStarGateWithId(splitString[4]));
								
								break;
							case "works":
								if (splitString[4] == "t")
									starGate.setWorks(true);
								else
									starGate.setWorks(false);
								
								break;
							case "wasInSunFlare":
								if (splitString[4] == "t")
									starGate.setWasInSunFlare(true);
								else
									starGate.setWasInSunFlare(false);
								
								break;
							case "isRandom":
								if (splitString[4] == "t")
									starGate.setRandom(true);
								else
									starGate.setRandom(false);
								
								break;
						}
						
						break;
					case "sun":
						Sun sun = game.getSunWithId(splitString[3]);
						
						switch (splitString[2]) {
							case "id":
								sun.setId(splitString[4]);
							
								break;
							case "isRandom":
								if (splitString[4] == "t")
									sun.setIsRandom(true);
								else
									sun.setIsRandom(false);
								
								break;
						}
						
						break;
					case "asteroid":
						Asteroid asteroid = game.getAsteroidWithId(splitString[3]);
						
						switch (splitString[2]) {
							case "id":
								asteroid.setId(splitString[4]);
								
								break;
							case "rockThickness":
								asteroid.setRockThickness(Integer.parseInt(splitString[4]));
								
								break;
							case "capacity":
								asteroid.setCapacity(Integer.parseInt(splitString[4]));
								
								break;
							case "closeToSun":
								if (splitString[4] == "t")
									asteroid.setCloseToSun(true);
								else
									asteroid.setCloseToSun(false);
								
								break;
							case "isRandom":
								if (splitString[4] == "t")
									asteroid.setIsRandom(true);
								else
									asteroid.setIsRandom(false);
								
								break;
						}
						
						break;
					case "inventory":
						// stinky
						
						break;
					case "material":
						Material material = game.getMaterialWithId(splitString[3]);
						
						switch (splitString[2]) {
							case "id":
								material.setId(splitString[4]);
								
								break;
							case "isRadio":
								if (splitString[4] == "t")
									material.setRadio(true);
								else
									material.setRadio(false);
								
								break;
							case "canEvaporate":
								if (splitString[4] == "t")
									material.setRadio(true);
								else
									material.setRadio(false);
								
								break;
							case "exposedCounter":
								material.setExposedCounter(Integer.parseInt(splitString[4]));
								
								break;
						}
						
						break;
				}
				
				break;
			case "add":
				switch (splitString[1]) {
					case "sun":
						Sun sun = game.getSunWithId(splitString[2]);
						
						sun.addAsteroid(game.getAsteroidWithId(splitString[4]));
						
						break;
					case "asteroid":
						Asteroid asteroid = game.getAsteroidWithId(splitString[2]);
						
						switch (splitString[3]) {
							case "neighbour":
								asteroid.addNeighbour(game.getAsteroidWithId(splitString[4]));
								
								break;
							case "material":
								asteroid.addMaterial(game.getMaterialWithId(splitString[4]));
								
								break;
							case "settler":
								asteroid.addSettler(game.getSettlerWithId(splitString[4]));
								
								break;
							case "robot":
								asteroid.addNonPlayer(game.getRobotWithId(splitString[4]));
								
								break;
							case "alien":
								asteroid.addNonPlayer(game.getAlienWithId(splitString[4]));
								
								break;
							case "base":
								asteroid.addBuilding(game.getBaseWithId(splitString[4]));
								
								break;
							case "stargate":
								asteroid.addBuilding(game.getStarGateWithId(splitString[4]));
								
								break;
						}
						
						break;
					case "inventory":
						// TODO
						
						break;
				}
				
				break;
			case "quit":
				System.exit(0);
				
				break;
			case "list":
				switch (splitString[1]) {
					case "all":
						game.listAllSettlers();
						
						break;
					case "sun":
						displayMessage(game.getSunWithId(splitString[2]).genUIString());
						
						break;
					case "asteroid":
						displayMessage(game.getAsteroidWithId(splitString[2]).genUIString());
						
						break;
					case "stargate":
						displayMessage(game.getStarGateWithId(splitString[2]).genUIString());
						
						break;
					case "base":
						displayMessage(game.getBaseWithId(splitString[2]).genUIString());
						
						break;
					case "settler":
						displayMessage(game.getSettlerWithId(splitString[2]).genUIString());
						
						break;
					case "robot":
						displayMessage(game.getRobotWithId(splitString[2]).genUIString());
						
						break;
					case "alien":
						displayMessage(game.getAlienWithId(splitString[2]).genUIString());
						
						break;
				}
				
				break;
			case "move":
				switch (splitString[4]) {
					case "asteroid":
						Asteroid a = game.getAsteroidWithId(splitString[5]);
						
						switch (splitString[1]) {
						case "settler":
							game.getSettlerWithId(splitString[2]).move(a);
							
							break;
						case "robot":
							game.getRobotWithId(splitString[2]).move(a);
							
							break;
						case "alien":
							game.getAlienWithId(splitString[2]).move(a);
							
							break;
						}
					
						break;
					case "stargate":
						StarGate sg = game.getStarGateWithId(splitString[5]);
						
						switch (splitString[1]) {
						case "settler":
							game.getSettlerWithId(splitString[2]).move(sg);
							
							break;
						case "robot":
							game.getRobotWithId(splitString[2]).move(sg);
							
							break;
						case "alien":
							game.getAlienWithId(splitString[2]).move(sg);
							
							break;
						}
						
						break;
				}
				
				break;
			case "drill":
				switch (splitString[1]) {
					case "settler":
						game.getSettlerWithId(splitString[2]).drill();
						
						break;
					case "robot":
						game.getRobotWithId(splitString[2]).drill();
						
						break;
				}
				
				break;
			case "mine":
				switch (splitString[1]) {
					case "settler":
						game.getSettlerWithId(splitString[2]).mine();
						
						break;
					case "alien":
						game.getAlienWithId(splitString[2]).mine();
						
						break;
				}
				
				break;
			case "drop":
				game.getSettlerWithId(splitString[2]).dropMaterial(game.getMaterialWithId(splitString[4]));
				
				break;
			case "build":
				switch (splitString[3]) {
					case "robot":
						game.getSettlerWithId(splitString[2]).buildRobot();
						
						break;
					case "stargate":
						game.getSettlerWithId(splitString[2]).buildStarGate();
						
						break;
					case "base":
						game.getSettlerWithId(splitString[2]).buildBase();
						
						break;
				}
				
				break;
			case "place":
				game.getSettlerWithId(splitString[2]).placeStarGate(game.getStarGateWithId(splitString[4]));
				
				break;
			case "sunflare":
				game.getSunWithId(splitString[1]).sunFlare();
				
				break;
		}
	}
}
