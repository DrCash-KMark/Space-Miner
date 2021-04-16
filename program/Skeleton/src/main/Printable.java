package main;

abstract public class Printable {
	protected static int nextId;
	protected String id;

//Get/Set--------------------------------------------------

	public static int getNextId() {
		return nextId;
	}

	public static void setNextId(int nextId) {
		Printable.nextId = nextId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

//Methods:------------------------------------------------
	
	abstract public String genUIString();
	
	abstract public String genSaveString();
}
