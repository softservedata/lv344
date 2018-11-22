package com.softserve.edu.box.three;
public class Appl {
	public static void main(String[] args) {
		Box<String> box = new Box<String>();
		//box.set(123);
		// ...
		box.set("info");
		// ...
//		Integer data = (Integer) box.get(); // Compile Error
//		System.out.println("dada = " + data);
	}
}