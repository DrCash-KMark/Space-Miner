package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Planet.java
//  @ Date : 17/03/2021
//  @ Author : Simon Zolt�n, Karpati Mark
//
//


/**
 * Abstract parent class of different space objects in game.
 *
 * @author simon
 */
public abstract class Planet extends Printable {

    protected Game owner;

    public Game getOwner() {
        return owner;
    }

    public void setOwner(Game owner) {
        this.owner = owner;
    }


}
