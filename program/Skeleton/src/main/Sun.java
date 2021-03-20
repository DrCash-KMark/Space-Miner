package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Sun.java
//  @ Date : 17/03/2021
//  @ Author : 
//
//



/**
 * Represents the Star in the game. It can cause sun flares.
 * @author simon
 *
 */
public class Sun extends Planet implements Controllable {
	
	/**
	 * Causes sun flare. Calls back to the owner Game object.
	 */
	public void sunFlare() {
		main.log(false, "sun", "Sun", "notifyAllAboutSunFlare()");
		owner.notifyAllAboutSunFlare();
		main.log(true, "sun", "Sun", "notifyAllAboutSunFlare()");
		
	}

	/**
	 * Implements autonomous behavior performed in every game turn.   
	 */
	@Override
	public void onTurn() {
		sunFlare();
	}
	
}
