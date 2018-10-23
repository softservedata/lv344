package com.softserve.edu.inner;

public class Appl {
	public static void main(String[] args) {
		APage a = new APage();
		CPage c = a.goto_BPage().goto_CPage();
		//c.getAPageAtomic().clearAtomic_APage(); // Running Atomic Operation
		c.getCPageAtomic().clearAtomic_APage(); // APage.class Atomic Operations are Visible
	}
}
