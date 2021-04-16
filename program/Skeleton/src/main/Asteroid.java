package main;

import java.util.*;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Asteroid.java
//  @ Date : 17/03/2021
//  @ Author : Karpati
//
//

/**
 * List are only available in java7 or older.
 *
 * @author Karpati
 */
public class Asteroid extends Planet implements Controllable {
    private boolean isRandom;
    private int rockThickness;
    private Boolean closeToSun;
    private int capacity;

    private List<Settler> settlers;
    private List<NonPlayer> nonPlayers;

    private List<Material> materials;
    private List<Asteroid> neighbours;
    private List<Building> buildings;

    Random rnd = new Random();

//Constructors:----------------------------------------------------------------

    /**
     * constructor for Asteroid without parameters
     */
    public Asteroid() {
        this.id = "Ast" + String.valueOf(nextId);
        nextId++;

        this.nonPlayers = new LinkedList<>();
        this.settlers = new LinkedList<>();

        this.neighbours = new LinkedList<>();
        this.buildings = new LinkedList<>();
        this.materials = new LinkedList<>();

        this.rockThickness = 0;
    }

    /**
     * Constructor for Asteroid which is usefully during loading in data
     *
     * @param rockThickness
     * @param capacity
     * @param closeToSun
     * @param isRandom
     * @param nonPlayers
     * @param settlers
     * @param materials
     * @param neighbours
     * @param buildings
     */
    public Asteroid(String id, boolean isRandom, int rockThickness, int capacity, boolean closeToSun, List<Settler> settlers, List<NonPlayer> nonPlayers, List<Material> materials, List<Asteroid> neighbours, List<Building> buildings) {
        this.id = id;
        this.isRandom = isRandom;
        this.rockThickness = rockThickness;
        this.closeToSun = closeToSun;
        this.capacity = capacity;
        this.settlers = settlers;
        this.nonPlayers = nonPlayers;
        this.materials = materials;
        this.neighbours = neighbours;
        this.buildings = buildings;
    }

//Get/Set-----------------------------------------------------------------

    public void addNonPlayer(NonPlayer nonPlayer) {
        nonPlayers.add(nonPlayer);
    }

    public void removeNonPlayer(NonPlayer nonPlayer) {
        nonPlayers.remove(nonPlayer);
    }

    public int getRockThickness() {
        return rockThickness;
    }

    public void setRockThickness(int rockThickness) {
        this.rockThickness = rockThickness;
    }

    public Boolean getCloseToSun() {
        return closeToSun;
    }

    public void setCloseToSun(Boolean closeToSun) {
        this.closeToSun = closeToSun;
    }

    public List<Settler> getSettelrs() {
        return settlers;
    }

    public void setSettlers(List<Settler> settlers) {
        this.settlers = settlers;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<Asteroid> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Asteroid> neighbours) {
        this.neighbours = neighbours;
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<Building> buildings) {
        this.buildings = buildings;
    }

    public List<NonPlayer> getNonPlayers() {
        return nonPlayers;
    }

    public void setCapacity(int c) {
        this.capacity = c;
    }

    public void setIsRandom(boolean r) {
        this.isRandom = r;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    //Inherited:-----------------------------------------------------------------------------

    //Printable

    /**
     * creates a string with this class' all important data.
     *
     * @return string containing all the important information for the user
     */
    @Override
    public String genUIString() {
        return "Sun id: " + id + "\n"
                + "rockThickness: " + String.valueOf(rockThickness) + "\n";
    }

    /**
     * creates a string storing the most important datas of this class
     *
     * @return
     */
    @Override
    public String genSaveString() {
        String returnValue = "Asteroid\n" +
                "id: " + this.id + "\n" +
                "isRandom:" + Tools.bool(this.isRandom) + "\n" +
                "rockThickness: " + this.rockThickness + "\n" +
                "closeToSun: " + Tools.bool(this.closeToSun) + "\n" +
                "capacity: " + this.capacity + "\n";
        for (Settler s : settlers) {
            returnValue += "settlerId: " + s.id + "\n";
        }
        for (NonPlayer np : nonPlayers) {
            returnValue += "nonPlayerId: " + np.id + "\n";
        }
        for (Material m : materials) {
            returnValue += "MaterialId: " + m.id + "\n";
        }
        for (Asteroid n : neighbours) {
            returnValue += "NeighbourId: " + n.id + "\n";
        }
        for (Building b : buildings) {
            returnValue += "BuildingId: " + b.id + "\n";
        }
        return returnValue + ";";
    }

    //Controllable

    /**
     * Implements autonomous behaviour performed in every game turn.
     * <p>
     * if this is close to sun it reacts to it
     */
    @Override
    public void onTurn() {
        if (rockThickness <= 0 && closeToSun) {
            for (Material m : materials) {
                m.exposedAndCloseToSun(this);
            }
        }
        if (isRandom && rnd.nextInt(100) < 20) {
            this.closeToSun = !this.closeToSun;
        }
    }

    //ReactsToSunFlare

    /**
     * This function kills every entity on the surface of the asteroid, if they can
     * not hide
     */
    public void getNotifiedAboutSunflare() {
        for (Settler s : settlers) {
            s.getNotifiedAboutSunflare();
        }
        for (NonPlayer np : nonPlayers) {
            np.getNotifiedAboutSunflare();
        }
    }

    //Own methods:----------------------------------------------------------------------------

    /**
     * initializes the asteroid
     */
    public void initialize() {
        this.isRandom = true;
        this.capacity = rnd.nextInt(10);
        this.rockThickness = rnd.nextInt(6) + 2;
        this.closeToSun = rnd.nextBoolean();
        //this is hard coded
        this.materials = new LinkedList<Material>();
        int materialType = rnd.nextInt(5);
        for (int i = 0; i < this.capacity; i++) {
            switch (materialType) {
                case 0:
                    materials.add(new Coal());
                    break;
                case 1:
                    materials.add(new Ice());
                    break;
                case 2:
                    materials.add(new Iron());
                    break;
                case 3:
                    materials.add(new Uran());
                    break;
                case 4:
                    break;
            }
        }
    }

    /**
     * Add a new settler to the settlers which are on the asteroid
     *
     * @param s the settler that will be added to the asteroid.
     */
    public void addSettler(Settler s) {
        this.settlers.add(s);
    }


    /**
     * remove a settler to the settlers which are on the asteroid
     *
     * @param s the settler that will be removed from the asteroid.
     */
    public void removeSettler(Settler s) {
        this.settlers.remove(s);
    }

    /**
     * this function notifies the entities, buildings that it have exploded, and
     * removes itself from it's neighbours' neighbours list.
     */
    public void explode() {

        for (Settler s : settlers) {
            s.asteroidExploded();
        }
        for (NonPlayer np : nonPlayers) {
            np.asteroidExploded();
        }
        for (Asteroid n : neighbours) {
            n.removeNeighbour(this);
        }
        for (Building b : buildings) {
            b.destroy();
        }
        owner.removeAsteroid(this);
    }

    /**
     * Decreases the rock's thickness and if the materials gets to the surface
     * notifies it.
     */
    public void drilling() {

        if (rockThickness > 0) {
            this.rockThickness--;
            if (this.materials != null && this.closeToSun && this.rockThickness == 0) {
                for (Material m : materials) {
                    m.exposedAndCloseToSun(this);
                }
            }
        }


    }

    /**
     * this function adds materials to the asteroid if possible and returns whether
     * the operation was successful
     *
     * @param m the materials that the function try to add to the asteroid
     * @return if the materials was successfully added or not.
     */
    public boolean addMaterial(Material m) {
        if (this.materials == null) {
            this.materials = new LinkedList<Material>();
        }
        if (this.materials.size() >= this.capacity || this.rockThickness > 0) {
            return false;
        } else {
            this.materials.add(m);
            m.exposedAndCloseToSun(this);
            return true;
        }


    }

    /**
     * returns the materials, that was in the asteroid, and remove the materials from
     * the asteroid setting it to null. If the asteroids thickness is greater than 0
     * it won't remove the materials
     *
     * @return the materials the was in the asteroid
     */
    public Material removeMaterial() {
        if (this.rockThickness > 0 || materials == null || materials.size() == 0) // checks if the materials can be removed
        {
            return null;
        }
        Material returnValue = this.materials.remove(0);
        return returnValue;
    }

    /**
     * Adds the given asteroid from the neighbours list.
     *
     * @param a the asteroid that will be added to the neighbours list
     */
    public void addNeighbour(Asteroid a) {
        this.neighbours.add(a);
    }

    /**
     * Removes the given asteroid from the neighbours list.
     *
     * @param a the asteroid that will be removed from the neighbours list
     */
    public void removeNeighbour(Asteroid a) {
        this.neighbours.remove(a);
    }

    /**
     * Adds the given building to the asteroid
     *
     * @param b the building the will be added to the asteroid
     */
    public void addBuilding(Building b) {
        this.buildings.add(b);

    }

    /**
     * Removes the given building from the asteroid
     *
     * @param b the building the will be removed from the asteroid
     */
    public void removeBuilding(Building b) {
        this.buildings.remove(b);
    }

    /**
     * Decides whether the asteroid is empty inside.
     *
     * @return whether the asteroid is hollow or not.
     */
    public boolean isHollow() {
        if (this.materials == null || this.materials.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Destroys all the materials which was inside the asteroid
     */
    public void evaporateMaterial() {
        this.materials = null;
    }

}

