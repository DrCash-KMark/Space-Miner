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
				// ???
				
				break;
			case "set":
				break;
			case "add":
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
				Asteroid a;
				StarGate sg;
				
				Boolean isAsteroid;
				
				if (splitString[4].charAt(0) == 'a') {
					isAsteroid = true;
					a = game.getAsteroidWithId(splitString[4]);
				}
				else {
					isAsteroid = false;
					sg = game.getStarGateWithId(splitString[4]);
				}
					
				
				switch (splitString[1]) {
					case "settler":
						if (isAsteroid)
							game.getSettlerWithId(splitString[2]).move(a);
						else
							game.getSettlerWithId(splitString[2]).move(sg);
						
						break;
					case "robot":
						if (isAsteroid)
							game.getRobotWithId(splitString[2]).move(a);
						else
							game.getRobotWithId(splitString[2]).move(sg);
						
						break;
					case "alien":
						if (isAsteroid)
							game.getAlienWithId(splitString[2]).move(a);
						else
							game.getAlienWithId(splitString[2]).move(sg);
						
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
