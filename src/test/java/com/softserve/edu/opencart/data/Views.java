package com.softserve.edu.opencart.data;

public enum Views {
	GRID("grid"), 					
	LIST("list");

	private String name;

	private Views(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
