package main;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Sun.java
//  @ Date : 17/03/2021
//  @ Author : Karpati Mark
//
//


import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Represents the Star in the game. It can cause sun flares.
 *
 * @author Simon, Karpati
 */
public class Sun extends Planet implements Controllable {

    Boolean isRandom;
    List<Asteroid> asteroids;
    Random rand;

//Constructors:----------------------------------------------------------------

    public Sun() {
        this.id = "Sun" + String.valueOf(nextId);
        nextId++;
        this.isRandom = false;
        this.asteroids = new LinkedList<Asteroid>();

    }

    public Sun(boolean isRandom, List<Asteroid> asteroids) {
        this.id = "Sun" + String.valueOf(nextId);
        nextId++;
        this.isRandom = isRandom;
        this.asteroids = asteroids;
    }

//Get/Set-----------------------------------------------------------------

    public String getID() {
        return this.id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public boolean getIsRandom() {
        return this.isRandom;
    }

    public void setIsRandom(boolean newIsRandom) {
        this.isRandom = newIsRandom;
    }


//Inherited:-----------------------------------------------------------------------------

    //Controllable

    /**
     * Implements autonomous behaviour performed in every game turn.
     * in case sun is not random a sunFlare is called every turn
     * in case sun is random than there is a chance to have sunFlare
     */
    @Override
    public void onTurn() {
        if (isRandom) {
            if (rand.nextInt(100) < 20) {
                this.sunFlare();
            }
        }

    }

    //Printable

    /**
     * creates a string with this class' all important data.
     *
     * @return string containing all the important information for the user
     */
    @Override
    public String genUIString() {
        return "Sun id: " + id + "\n"
                + "isRandom: " + Tools.bool(isRandom) + "\n";
    }

//Own methods:----------------------------------------------------------------------------

    /**
     * Causes sun flare. Notifies all the asteroids about sun flare.
     */
    public void sunFlare() {
        for (Asteroid a : asteroids) {
            a.getNotifiedAboutSunflare();
        }
    }

    public void addAsteroid(Asteroid a) {
        asteroids.add(a);
    }

    public void removeAsteroid(Asteroid a) {
        asteroids.remove(a);
    }

}
