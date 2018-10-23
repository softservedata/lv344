package com.softserve.edu.inner;

public class BPage extends APage {

	public class BPageAtomic extends APageAtomic {

		public BPageAtomic() {
			System.out.println("\tBPageAtomic()");
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

	}
	
	private BPageAtomic bPageAtomic;
	
	public BPage() {
		System.out.println("BPage()");
		bPageAtomic = new BPageAtomic();
	}

	// Business Logic

	public BPageAtomic getBPageAtomic() {
		return bPageAtomic;
	}
	
	public CPage goto_CPage() {
		System.out.println("goto_CPage()");
		return new CPage();
	}

}
