/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import main.*;

/**
 * @author simon
 *
 */


public class GameTest {

	private static Game game;
	private static List<Entity> entities = new ArrayList<Entity>();
	private static List<Planet> planets = new ArrayList<Planet>();
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		game = new Game();
		
		Entity temp = new Settler();
		entities.add(temp);
		game.addSettler((Settler)temp);

		temp = new Robot();		
		entities.add(temp);
		game.addRobot((Robot)temp);
		
		planets.add(new Asteroid());
		planets.add(new Sun());
			
		for (Entity entity : entities) {
			entity.setOwner(game);
			entity.setAsteroid((Asteroid)planets.get(0));
		}		
	}

	/**
	 * Test method for {@link main.Game#destroyMe(main.Controllable)}.
	 */
	@Test
	public void testDestroyMe() {
		game.destroyMe((Robot)entities.get(1));
		game.cleanup();
		assertEquals(1, game.getRobots().size());
	
	}

	/**
	 * Test method for {@link main.Game#killMe(main.Settler)}.
	 */
	@Test
	public void testKillMe() {
			int n = game.getSettlers().size();
			game.killMe((Settler)entities.get(0));
			game.cleanup();
			assertEquals(n - 1, game.getSettlers().size());
	}

	/**
	 * Test method for {@link main.Game#notifyAllAboutSunFlare()}.
	 */
	@Test
	public void testNotifyAllAboutSunFlare() {
		Sun sun = new Sun();
		sun.setOwner(game);
		game.addPlanet(sun);
		
		sun.sunFlare();
	}

	/**
	 * Test method for {@link main.Game#gameWon()}.
	 */
	@Test
	public void testGameWon() {
		game.gameWon();
	}

	/**
	 * Test method for {@link main.Game#addSettler(main.Settler)}.
	 */
	@Test
	public void testAddRobot() {
		int n = game.getRobots().size();
		game.addRobot(new Robot());
		assertEquals(n + 1, game.getRobots().size());
	
	}

}
