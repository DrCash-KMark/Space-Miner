package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : StarGate.java
//  @ Date : 17/03/2021
//  @ Author : 
//
//



/**
 * StarGate class. Responsible for creating destroying and managing StarGates.
 */
public class StarGate extends Building {
	private boolean works;
	private StarGate neighbour;
	
	public boolean getWorks() { return works; }
	public void setWorks(boolean b) { works = b; }
	
	public StarGate getNeighbour() { return neighbour; }
	public void setNeighbour(StarGate sg) { neighbour = sg; }
	
	/**
	 * constructor for StarGate without parameter
	 */
	public StarGate() {
		works = false;
	}
	
	/**
	 * function for placing the StarGate on an asteroid
	 * @param a: Asteroid: asteroid on which the StarGate is placed
	 */
	public void onPlace(Asteroid a) {
		main.log(false, name, getClass().getName(), "onPlace(" + a.getName() + ":" + a.getClass().getName() + ")");
		
		asteroid = a;
		
		if (neighbour == null)
		{
			main.log(true, "void", "void", "");
			return;
		}
		
		if (neighbour.getWorks() == false)
		{
			main.log(true, "void", "void", "");
			return;
		}
		
		if (getNeighbourAsteroid() != null);
		{
			asteroid.addNeighbour(getNeighbourAsteroid());
			getNeighbourAsteroid().addNeighbour(asteroid);
		}
		
		main.log(true, "void", "void", "");
	}
	
	/**
	 * function for destroying the StarGate
	 */
	public void destroy() {
		main.log(false, name, getClass().getName(), "destroy()");
		
		neighbour.setWorks(false);
		
		getNeighbourAsteroid().removeNeighbour(asteroid);
		asteroid.removeNeighbour(getNeighbourAsteroid());
		
		neighbour.setNeighbour(null);
		
		main.log(true, "void", "void", "");
	}
	
	/**
	 * function for getting the asteroid on which the neighbouring StarGate is placed
	 * @return neighbour: Asteroid: this is the neighbouring StarGate's asteroid
	 */
	public Asteroid getNeighbourAsteroid() {
		main.log(false, name, getClass().getName(), "getNeighbourAsteroid()");
		
		if (neighbour.getAsteroid() != null)
		{
			main.log(true, neighbour.getAsteroid().getName(), neighbour.getAsteroid().getClass().getName(), "");
			return neighbour.getAsteroid();
		}
		else
		{
			main.log(true, "null", "null", "");
			return null;
		}
	}
}
