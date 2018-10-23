package com.softserve.edu.inner.split;

public class APage {

	public class APageAtomic {
		
		public APageAtomic() {
			System.out.println("\tAPageAtomic()");
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

	}
	
	private APageAtomic aPageAtomic;
	
	public APage() {
		System.out.println("APage()");
		aPageAtomic = new APageAtomic();
	}

	// Business Logic

	public APageAtomic getAPageAtomic() {
		return aPageAtomic;
	}
	
	public BPage goto_BPage() {
		System.out.println("goto_BPage()");
		return new BPage();
	}

}
