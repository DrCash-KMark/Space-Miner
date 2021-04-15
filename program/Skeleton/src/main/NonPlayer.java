package main;

public abstract class NonPlayer extends Entity implements Controllable {
	protected boolean isRandom;
	protected int counter;
	/**
	 * Constructor without parameters.
	 */
	public NonPlayer() {
		super();
	}
	/**
	 * Constructor with parameters.
	 */
	public NonPlayer(String _id, Game _owner, Asteroid _asteroid, boolean random) {
		super(_id, _owner, _asteroid);
		isRandom = random;
		counter = 0;
	}
	/**
	 * Getter of isRandom
	 * @return boolean: isRandom
	 */
	public boolean getIsRand() {
		return isRandom;
	}
	/**
	 * Setter of isRandom;
	 * @param boolean: isRandom
	 */
	public void setIsRand(boolean newisrandom) {
		isRandom = newisrandom;
	}
	/**
	 * Move the NonPlayer to the selected neighbour asteroid.
	 * @param destination: Asteroid: The selected neighbour asteroid.
	 */
	public void move(Asteroid destination) {
		if(asteroid.getNeighbours().contains(destination)) {
			asteroid.removeNonPlayer(this);
			this.asteroid = destination;
			destination.addNonPlayer(this);
		}
	}
	/**
	 * Move the NonPlayer to the asteroid of the pair stargate of the selected stargate on the current asteroid.
	 * Also checks if the stargates work.
	 * @param destination: StarGate: The selected stargate on the current asteroid.   
	 */
	public void move(StarGate destination) {
		if(asteroid.getBuildings().contains(destination)) {
			if(destination.getWorks() == true && destination.getWorks() == true) {
				asteroid.removeNonPlayer(this);
				this.asteroid = destination.getNeighbour().getAsteroid();
				this.asteroid.addNonPlayer(this);
			}
		}
	}
	/**
	 * The nonPlayer dies.
	 */
	public void die() {
		asteroid.removeNonPlayer(this);
		owner.removeNonPlayer(this);
	}
	/**
	 * The NonPlayer takes his turn.
	 */
	public abstract void onTurn();
}
