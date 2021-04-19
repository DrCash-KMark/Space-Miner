package main;

abstract public class Printable {
	protected static int nextId;
	protected String id;

//Get/Set--------------------------------------------------

	/**
	 * A getter for nextId
	 * @return the value of nextId
	 */
	public static int getNextId() {
		return nextId;
	}

	/**
	 * A setter for nextId
	 * @param nxtId this will be set as the value of nextId
	 */
	public static void setNextId(int nxtId) {
		Printable.nextId = nxtId;
	}

	/**
	 * A getter for id
	 * @return the value of id
	 */
	public String getId() {
		return id;
	}

	/**
	 * A setter for id
	 * @param id this will be set as the value of id
	 */
	public void setId(String id) {
		this.id = id;
	}

//Methods:------------------------------------------------
	
	/**
	 * An abstract function that returns the UI String of the given object
	 * @return the UI String that will be returned
	 */
	abstract public String genUIString();
	
	/**
	 * An abstract function that returns the Save String of the given object
	 * @return the Save String that will be returned
	 */
	abstract public String genSaveString();
}
