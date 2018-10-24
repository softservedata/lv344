package com.softserve.edu.box.three;

public class Box<T> {

	private T data;

	public Box() {
	}

	public T get() {
		return data;
	}

	public void set(T data) {
		this.data = data;
	}

}
