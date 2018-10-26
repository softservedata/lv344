package com.softserve.edu.opencart.data;

public final class UserUkrNetRepository {

	private UserUkrNetRepository() {
	}

	public static IUserUkrNet defaultUserUkrNet() {
		return testAtqcShop();
	}

	public static IUserUkrNet testAtqcShop() {
		return new UserUkrNet("test-atqc-shop@ukr.net", "pridymaisam");
	}

}
