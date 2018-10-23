package com.softserve.edu.interfaces;

public class CPage extends BPage implements ICPage {

	public CPage() {
		System.out.println("CPage()");
	}

	// Page Object
	
	public void clickAtomic_CPage() {
		System.out.println("clickAtomic_CPage()");
	}

	public void clearAtomic_CPage() {
		System.out.println("clearAtomic_CPage()");
	}

	public void typeAtomic_CPage() {
		System.out.println("typeAtomic_CPage()");
	}

	// Business Logic

	public CPage getCPage() {
		return this;
	}

	public IAPage goto_APage() {
		System.out.println("goto_APage()");
		return new APage();
	}


}
