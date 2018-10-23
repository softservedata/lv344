package com.softserve.edu.interfaces.split;

public class APage implements IAPage {

	public APage() {
		System.out.println("APage()");
	}

	// Page Object
	
	public void clickAtomic_APage() {
		System.out.println("clickAtomic_APage()");
	}

	public void clearAtomic_APage() {
		System.out.println("clearAtomic_APage()");
	}

	public void typeAtomic_APage() {
		System.out.println("typeAtomic_APage()");
	}

	// Business Logic

	public APage getAPage() {
		return this;
	}

	public IBPage goto_BPage() {
		System.out.println("goto_BPage()");
		return new BPage();
	}

}
