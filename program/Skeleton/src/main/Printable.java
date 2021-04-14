package main;

abstract public class Printable {
	private static int nextId;
	private String id;
	
	abstract public String genUIString();
}
