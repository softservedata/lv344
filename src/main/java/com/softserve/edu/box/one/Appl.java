package com.softserve.edu.box.one;

public class Appl {
	public static void main(String[] args) {
		Box box = new Box();
		box.set(123);
		// ...
		box.set("info");
		// ...
		Integer data = (Integer) box.get(); // Cast Explicit, Code Small 
		System.out.println("dada = " + data);
	}
}
