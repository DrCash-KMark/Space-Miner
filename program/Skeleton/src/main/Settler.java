package main;

import java.util.LinkedList;
import java.util.List;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Space-Miner
//  @ File Name : Settler.java
//  @ Date : 17/03/2021
//  @ Author : Tadam
//
//


/**
 * Settler class, extends Entity
 *
 * @author Tadam
 */
public class Settler extends Entity implements Drilling, Mining {
    private Inventory inventory;
    private Inventory BASE_RECIPE;
    private Inventory ROBOT_RECIPE;
    private Inventory STARGATE_RECIPE;
    private boolean hadactionthisturn;

    /**
     * Default Constructor without parameters.
     */
    public Settler() {
        super();
        inventory = new Inventory();
        BASE_RECIPE = new Inventory();
        BASE_RECIPE.setCapacityM(12);
        BASE_RECIPE.addMaterial(new Iron("recept"));
        BASE_RECIPE.addMaterial(new Iron("recept"));
        BASE_RECIPE.addMaterial(new Iron("recept"));
        BASE_RECIPE.addMaterial(new Uran("recept"));
        BASE_RECIPE.addMaterial(new Uran("recept"));
        BASE_RECIPE.addMaterial(new Uran("recept"));
        BASE_RECIPE.addMaterial(new Coal("recept"));
        BASE_RECIPE.addMaterial(new Coal("recept"));
        BASE_RECIPE.addMaterial(new Coal("recept"));
        BASE_RECIPE.addMaterial(new Ice("recept"));
        BASE_RECIPE.addMaterial(new Ice("recept"));
        BASE_RECIPE.addMaterial(new Ice("recept"));
        ROBOT_RECIPE = new Inventory();
        ROBOT_RECIPE.addMaterial(new Iron("recept"));
        ROBOT_RECIPE.addMaterial(new Uran("recept"));
        ROBOT_RECIPE.addMaterial(new Coal("recept"));
        STARGATE_RECIPE = new Inventory();
        STARGATE_RECIPE.addMaterial(new Iron("recept"));
        STARGATE_RECIPE.addMaterial(new Iron("recept"));
        STARGATE_RECIPE.addMaterial(new Uran("recept"));
        STARGATE_RECIPE.addMaterial(new Ice("recept"));
        hadactionthisturn = false;
        this.setId("set" + String.valueOf(nextId));
        nextId++;
    }

    /**
     * Constructor with parameters.
     *
     * @param asteroid: Asteroid: The Asteroid which this Settler is standing on.
     * @param owner:    Game: The game which this Settler belongs to.
     */
    public Settler(String id, Asteroid asteroid, Game owner) {
        super(id, owner, asteroid);
        inventory = new Inventory();
        BASE_RECIPE = new Inventory();
        ROBOT_RECIPE = new Inventory();
        ROBOT_RECIPE.addMaterial(new Iron());
        ROBOT_RECIPE.addMaterial(new Uran());
        ROBOT_RECIPE.addMaterial(new Coal());
        STARGATE_RECIPE = new Inventory();
        hadactionthisturn = false;
    }

    /**
     * The setter of the inventory.
     *
     * @param i: Inventory: The inventory of the settler.
     */
    public void setInventory(Inventory i) {
        inventory = i;
    }

    /**
     * The getter of the settler's inventory
     *
     * @return inventory: Inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * The setter of the hadaction.
     *
     * @param hadaction: boolean
     */
    public void setHadAction(boolean hadaction) {
        hadactionthisturn = hadaction;
    }

    /**
     * The getter of the hadaction.
     *
     * @retrun boolean
     */
    public boolean getHadAction() {
        return hadactionthisturn;
    }

    /**
     * The setter of the STARGATE_RECIPE
     *
     * @param i: Inventory: Contains the required materials
     */
    public void setSTARGATE_RECIPE(Inventory i) {
        STARGATE_RECIPE = i;
    }

    /**
     * The setter of the ROBOT_RECIPE
     *
     * @param i: Inventory: Contains the required materials
     */
    public void setROBOT_RECIPE(Inventory i) {
        ROBOT_RECIPE = i;
    }

    /**
     * The setter of the BASE_RECIPE
     *
     * @param i: Inventory: Contains the required materials
     */
    public void setBASE_RECIPE(Inventory i) {
        BASE_RECIPE = i;
    }

    /**
     * The settler drill his asteroid.
     */
    public void drill() {
        this.asteroid.drilling();
        this.hadactionthisturn = true;
    }

    /**
     * This settler mines on his asteroid.
     */
    public void mine() {
        //main.log(false, name, this.getClass().getName(), "mine()");
        if (this.inventory.isFullMaterials() == false) {
            Material m = this.asteroid.removeMaterial();
            inventory.addMaterial(m);
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * This settler builds a Stargate.
     */
    public void buildStarGate() {
        //main.log(false, name, this.getClass().getName(), "buildStarGate()");
        if (this.inventory.isFullStarGates() == false) {
            LinkedList<Material> remainder = STARGATE_RECIPE.subSet(inventory.getMaterials());
            if (remainder.size() == 0 && (inventory.getStarGatesCapacity()-inventory.getStarGatesCount())>=2) {
                STARGATE_RECIPE.subtraction(inventory.getMaterials());
                StarGate s1 = new StarGate();
                //s1.setName("stargate");
                StarGate s2 = new StarGate();
                //s2.setName("neighbour");
                s1.setNeighbour(s2);
                s2.setNeighbour(s1);
                inventory.addStarGate(s1);
                inventory.addStarGate(s2);
                owner.addTurnEvent("stargate crafted " + s1.getId());
                owner.addTurnEvent("stargate crafted " + s2.getId());
            }
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * This settler builds a Robot.
     */
    public void buildRobot() {
        //main.log(false, name, this.getClass().getName(), "buildRobot()");
        LinkedList<Material> remainder = ROBOT_RECIPE.subSet(inventory.getMaterials());
        if (remainder.size() == 0) {
            ROBOT_RECIPE.subtraction(inventory.getMaterials());
            Robot robot = new Robot();
            //robot.setName("robot");
            robot.setAsteroid(asteroid);
            asteroid.addNonPlayer(robot);
            owner.addNonPlayer(robot);
            owner.addTurnEvent("robot crafted " + robot.getId());
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * This settler builds a Base.
     */
    public void buildBase() {
        //main.log(false, name, this.getClass().getName(), "buildBase()");
        List<Settler> settlersOnAsteroid = asteroid.getSettelrs();
        LinkedList<Material> subSet = BASE_RECIPE.subSet(inventory.getMaterials());
        for (Settler settler : settlersOnAsteroid) {
            if (settler.id.equals(this.id)) {
                continue;
            }
            Inventory temp = new Inventory("temp", subSet, new LinkedList<StarGate>(), 10, 3);
            subSet = temp.subSet(subSet);
            if (subSet.size() == 0) {
                break;
            }
        }
        if (subSet.size() == 0) {
            LinkedList<Material> toSubtract = BASE_RECIPE.subtraction(inventory.getMaterials());
            for (Settler settler : settlersOnAsteroid) {
                if (settler.id.equals(this.id)) {
                    continue;
                }
                Inventory temp = new Inventory("temp", toSubtract, new LinkedList<StarGate>(), 10, 3);
                toSubtract = temp.subtraction(settler.getInventory().getMaterials());
            }
            Base base = new Base();
            //base.setName("base");
            //base.setMain(main);
            base.setOwner(owner);
            base.setAsteroid(asteroid);
            base.onPlace();
            asteroid.addBuilding(base);
            owner.addTurnEvent("base crafted " + base.getId());
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * This settler tries to drop a material.
     * If he can drop it, he removes it from his inventory.
     *
     * @param m: Material: The material which the settler wants to drop.
     */
    public void dropMaterial(Material m) {
        //main.log(false, name, this.getClass().getName(), "dropMaterial("+m.getName()+":"+m.getClass().getName()+")");
        boolean b = asteroid.addMaterial(m);
        if (b == true) {
            this.inventory.removeMaterial(m);
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * The settler places a Stargate on the current asteroid.
     */
    public void placeStarGate(StarGate sg) {
        if (this.inventory.isHaveStarGate(sg) == true) {
            StarGate nsg = new StarGate(sg.getId(), sg.getAsteroid(), sg.getNeighbour(), sg.getWorks(), sg.getWasInSunFlare(), sg.getRandom());
            nsg.setAsteroid(asteroid);
            asteroid.addBuilding(nsg);
            nsg.onPlace();
            this.inventory.removeStarGate(sg);
            owner.addTurnEvent("stargate placed " + nsg.getId() + " " + asteroid.getId());
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    /**
     * The settler dies.
     */
    public void die() {
        //main.log(false, name, this.getClass().getName(), "die()");
        asteroid.removeSettler(this);
        owner.removeSettler(this);
        owner.addTurnEvent("settler died " + this.id);
        //main.log(true, "void", "void", "");
    }

    /**
     * The asteroid of this settler has been exploded.
     * The settler dies.
     */
    public void asteroidExploded() {
        //main.log(false, name, this.getClass().getName(), "asteroidExploded()");
        this.die();
        //main.log(true, "void", "void", "");
    }


    public void move(Asteroid destination) {
        //main.log(false, name, this.getClass().getName(), "move("+destination.getName()+":"+destination.getClass().getName()+")");
        if (asteroid.getNeighbours().contains(destination)) {
            destination.addSettler(this);
            asteroid.removeSettler(this);
            this.setAsteroid(destination);
            owner.addTurnEvent("settler move " + this.id + " " + destination.getId());
        }
        this.hadactionthisturn = true;
        //main.log(true, "void", "void", "");
    }

    public void move(StarGate destination) {
        if (asteroid.getBuildings().contains(destination)) {
            if (destination.getWorks() == true && destination.getWorks() == true) {
                asteroid.removeSettler(this);
                this.asteroid = destination.getNeighbour().getAsteroid();
                this.asteroid.addSettler(this);
                owner.addTurnEvent("settler move " + this.id + " " + destination.getAsteroid().getId());
            }
        }
        this.hadactionthisturn = true;
    }

    public String genUIString() {
        String resstring = "Settler id: " + this.id +
                "\n hadAcrtionThisTurn: " + Tools.bool(this.hadactionthisturn) +
                "\n asteroid: " + this.asteroid.getId() +
                "\n inventory: " + this.inventory.genUIString();
        return resstring;
    }

    public String genSaveString() {
        String resstring = "id: " + this.id +
                "\nhadActionThisTurn: " + Tools.bool(this.hadactionthisturn) +
                "\nasteroid: " + this.asteroid.getId() +
                "\ninventory: " + this.inventory.getId();
        return resstring;
    }
}

