package com.softserve.edu.interfaces.split;

public class Appl {
	public static void main(String[] args) {
		IAPage a = new APage();
		ICPage c = a.goto_BPage().goto_CPage();
		//a= c.goto_APage(); // Business Logic only 
		//a= ((CPage)c).goto_APage(); // Explicit, Bad Solution
		//c.getCPage().clearAtomic_CPage();
		c.getIBPage().getIAPage().getAPage().clearAtomic_APage(); // Get Atomic from APage.
	}
}
