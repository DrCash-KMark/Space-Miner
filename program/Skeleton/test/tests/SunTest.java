package tests;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import main.*;

public class SunTest {

	private static Sun sun;
	private static Game owner;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		sun = new Sun();
		owner = new Game();
		Main main = new Main();
		sun.setOwner(owner);
		sun.setMain(main);
		owner.setMain(main);
		owner.addPlanet(sun);
	}

	@Test
	public void testSunFlare() {
		sun.sunFlare();
	}

	@Test
	public void testOnTurn() {
		sun.onTurn();
	}

	@Test
	public void testGetOwner() {
		assertEquals(owner, sun.getOwner());
	}

	@Test
	public void testSetOwner() {
		Game owner2 = new Game();
		sun.setOwner(owner2);
		assertEquals(owner2, sun.getOwner());
	}

	@Test
	public void testGetNotifiedAboutSunflare() {
		sun.getNotifiedAboutSunflare();
	}

}
