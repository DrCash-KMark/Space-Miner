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
 * Represents the Star in the game. It can cause sun flares and notifies the asteroids around it.
 *
 * @author Simon, Karpati
 */
public class Sun extends Planet implements Controllable {

    Boolean isRandom;
    List<Asteroid> asteroids;
    Random rand;

//Constructors:----------------------------------------------------------------

    /**
     * Simple constructor for sun.
     */
    public Sun() {
        this.id = "sun" + String.valueOf(nextId);
        nextId++;
        this.isRandom = false;
        this.asteroids = new LinkedList<Asteroid>();

    }

    /**
     * Constructor for loading in data
     *
     * @param id
     * @param isRandom
     * @param asteroids
     */
    public Sun(String id, boolean isRandom, List<Asteroid> asteroids) {
        this.id = id;
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

    public List<Asteroid> getAsteroids() {
        return asteroids;
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
        String returnValue = "SunId: " + id + "\n"
                + "isRandom: " + Tools.bool(isRandom) + "\n"
                + "asteroids:\n";
        if (asteroids == null || asteroids.size() == 0) {
            returnValue += "\tasteroid: -";
        } else {
            for (Asteroid a : asteroids) {
                returnValue += "\tasteroid: " + a.id;
            }
        }
        return returnValue;
    }

    /**
     * This function creates a string for saving the current stance of the game.
     *
     * @return the string the contains the important data of this class.
     */
    @Override
    public String genSaveString() {
        String returnValue = "id: " + this.id + "\n" +
                "isRandom: " + Tools.bool(this.isRandom) + "\n";
        for (Asteroid a : asteroids) {
            returnValue += "AsteroidId: " + a.id + "\n";
        }
        return returnValue;
    }

//Own methods:----------------------------------------------------------------------------

    /**
     * Causes sun flare. Notifies all the asteroids about sun flare.
     * tempList is used so when a material is removed the for loop won't break the whole game
     */
    public void sunFlare() {
        this.owner.addTurnEvent("sunflare on sun "+this.id);
        for (Asteroid a : asteroids) {
            a.getNotifiedAboutSunflare();
        }
    }

    /**
     * Adds the given asteroid to the sun.
     *
     * @param a the asteroid that will be added to the sun.
     */
    public void addAsteroid(Asteroid a) {
        asteroids.add(a);
        a.setMySun(this);
    }

    /**
     * removes an asteroid
     *
     * @param a the asteroid that will be removed.
     */
    public void removeAsteroid(Asteroid a) {
        asteroids.remove(a);
    }

}
