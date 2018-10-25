package com.softserve.edu.opencart.data;

public enum Order{
	ASC(1), DESC(-1);
	int order;
	private Order(int order) {
		this.order = order;
	}
}
