package main;

public class Controller {
	Game game;
	View view;
	
	Settler boundSettler;
	Asteroid boundAsteroid;
	
	public Controller() {
		boundSettler = null;
		boundAsteroid = null;
	}
	
	public void setGame(Game g) {
		game = g;
	}
	
	public void setView(View v) {
		view = v;
	}
	
	public Settler getBoundSettler() {
		return boundSettler;
	}
	
	public Asteroid getBoundAsteroid() {
		return boundAsteroid;
	}
	
	public void handdleNextTurn() {
		game.startTurn();
	}
	
	public void handleNew() {
		game.initGame(false);
	}
	
	public void handleLoad() {
		game.loadGame();
	}
	
	public void handleSave() {
		game.saveGame();
	}
	
	public void handleExit() {
		System.exit(0);
	}
	
	public void handleMove(String settlerID, String destType, String destID) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		if (destType.equals("asteroid"))
			settler.move(game.getAsteroidWithId(destID));
		else
			settler.move(game.getStarGateWithId(destID));
	}
	
	public void handleDrill(String settlerID) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		settler.drill();
	}
	
	public void handleMine(String settlerID) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		settler.mine();
	}
	
	public void handleBuild(String settlerID, String buildType) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		switch (buildType) {
			case "robot":
				settler.buildRobot();
				
				break;
			case "stargate":
				settler.buildStarGate();
				
				break;
			case "base":
				settler.buildBase();
				
				break;
		}
	}
	
	public void handlePlace(String settlerID, String StarGateID) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		settler.placeStarGate(game.getStarGateWithId(StarGateID));
	}
	
	public void handleDrop(String settlerID, String MaterialID) {
		Settler settler = game.getSettlerWithId(settlerID);
		
		settler.dropMaterial(game.getMaterialWithId(MaterialID));
	}
	
	public void handleBind(String ID, String bindType) {
		if (bindType.equals("asteroid")) {
			boundAsteroid = game.getAsteroidWithId(ID);
			boundSettler = null;
		}
		else {
			boundAsteroid = null;
			boundSettler = game.getSettlerWithId(ID);
		}
	}
}
