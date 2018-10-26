package com.softserve.edu.opencart.data;

public enum ShowLimits {
	x15("//option[text()='15']"), 				
	x25("//option[text()='25']"), 				
	x50("//option[text()='50']"),				
	x75("//option[text()='75']"),	
	x100("//option[text()='100']");

	private String name;

	private ShowLimits(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}