package main;

public class Tools {

	public static char bool(boolean b) {
		return (b)? 't' : 'f';
	}
	
	public static String id(Printable p) {
		return (p != null)? p.getId() : "-";
	}
	
	
	
}
