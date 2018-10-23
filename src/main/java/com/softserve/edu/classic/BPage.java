package com.softserve.edu.classic;

public class BPage extends APage {

	public BPage() {
		System.out.println("BPage()");
	}

	// Page Object
	
	public void clickAtomic_BPage() {
		System.out.println("clickAtomic_BPage()");
	}

	public void clearAtomic_BPage() {
		System.out.println("clearAtomic_BPage()");
	}

	public void typeAtomic_BPage() {
		System.out.println("typeAtomic_BPage()");
	}

	// Business Logic

	public CPage goto_CPage() {
		System.out.println("goto_CPage()");
		return new CPage();
	}

}
