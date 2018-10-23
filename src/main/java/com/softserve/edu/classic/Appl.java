package com.softserve.edu.classic;

public class Appl {
	public static void main(String[] args) {
		APage a = new APage();
		CPage c = a.goto_BPage().goto_CPage();
		a= c.goto_APage(); // Too many visible methods
	}
}
