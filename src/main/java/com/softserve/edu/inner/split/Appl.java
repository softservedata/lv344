package com.softserve.edu.inner.split;

public class Appl {
	public static void main(String[] args) {
		APage a = new APage();
		CPage c = a.goto_BPage().goto_CPage();
		//c.getAPageAtomic().clearAtomic_APage(); // Running Atomic Operation
		c.getCPageAtomic().clearAtomic_CPage(); // APage.class Atomic Operation not Visible
	}
}
