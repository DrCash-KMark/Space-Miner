package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Uran.java
//  @ Date : 17/03/2021
//  @ Author : Barkanyi
//
//




public class Uran extends Material {
	
	/**
	 * Constructor for Uran.
	 */
	Uran(){
		super(true, "Uran", false);
	}
	
	/**
	 * Blow up the asteroid which contains the material.
	 */
	public void exposedAndCloseToSun(Asteroid a) {
		this.main.log(false, this.name, this.getClass().getName(), "exposedAndCloseToSun(" + a.getName() + ":" + (a.getClass().getName()));
		a.explode();
		this.main.log(true, "void", "void", "");
	}
}