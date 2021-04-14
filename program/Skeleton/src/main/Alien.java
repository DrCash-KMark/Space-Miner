package main;

import java.util.List;
import java.util.Random;

public class Alien extends NonPlayer implements Mining {
	Inventory inventory;

//Constructors:-----------------------------------------------

	public Alien() {
		inventory = new Inventory();
		isRandom = true;
	}
	
//Inherited:-------------------------------------------------------	
	
	@Override
	public String genUIString() {
		return "Alien id: " + id + "\n"
				+ "isRandom: " + Tools.bool(isRandom) + "\n"
				+ "asteroid: " + Tools.id(asteroid) + "\n";
	}

	@Override
	public void getNotifiedAboutSunflare() {
		if (!asteroid.isHollow()) {
			this.die();
		}
	}
	
	@Override
	public void move(Asteroid destination) {
		if (asteroid != null) {
			if (asteroid.getNeighbours().contains(destination)) {
				asteroid.removeNonPlayer(this);
				destination.addNonPlayer(this);
				asteroid = destination;
			}
		}
	}

	@Override
	public void move(StarGate destination) {
		if (destination.getWorks()) {
			Asteroid destAsteroid = destination.getNeighbour().getAsteroid();
			if (destAsteroid != null) {
				if (asteroid != null) {
					asteroid.removeNonPlayer(this);
				}
				destAsteroid.addNonPlayer(this);
				asteroid = destAsteroid;
			}			
		}
	}

	@Override
	public void die() {
		asteroid.removeNonPlayer(this);		
		owner.removeNonPlayer(this);
	}

	@Override
	public void asteroidExploded() {
		this.die();
	}

	private boolean doMine = true;
	
	@Override
	public void onTurn() {
		if (isRandom) {
			if (doMine) {
				mine();
			}
			else {
				List<Asteroid> list = asteroid.getNeighbours();
				Random rand = new Random();
				int destI = rand.nextInt(list.size()); 
				move(list.get(destI));
			}
			doMine = !doMine;
		}
	}
	
	@Override
	public void mine() {
		if (0 == asteroid.getRockThickness()) {
			inventory.addMaterial(asteroid.removeMaterial());
		}
	}

}
