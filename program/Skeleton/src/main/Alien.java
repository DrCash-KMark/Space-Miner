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
	
	public Alien(String _id, Asteroid _asteroid, Boolean _isRandom, Inventory _inventory) {
		id = _id;
		asteroid = _asteroid;
		isRandom = _isRandom;
		inventory = _inventory;
	}
	
//Get/Set:-------------------------------------------------------
	
	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

//Inherited:-------------------------------------------------------		

	@Override
	public String genUIString() {
		return "Alien id: " + id + "\n"
				+ "isRandom: " + Tools.bool(isRandom) + "\n"
				+ "asteroid: " + Tools.id(asteroid) + "\n";
	}

	@Override
	public String genSaveString() {
		return "id: " + id + "\n"
				+ "asteroid: " +  Tools.id(asteroid) + "\n"
				+ "isRandom: " + Tools.bool(isRandom) +"\n"
				+ "inventory: " + Tools.id(inventory);
	}

	
	@Override
	public void getNotifiedAboutSunflare() {
		if (!asteroid.isHollow() || asteroid.getRockThickness() > 0) {
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
	
	@Override
	public void onTurn() {
		if (isRandom) {
			if (counter % 2 == 0) {
				mine();
			}
			else {
				List<Asteroid> list = asteroid.getNeighbours();
				Random rand = new Random();
				int destI = rand.nextInt(list.size()); 
				move(list.get(destI));
			}
		}
		counter++;
	}
	
	@Override
	public void mine() {
		if (0 == asteroid.getRockThickness()) {
			inventory.addMaterial(asteroid.removeMaterial());
		}
	}

}
