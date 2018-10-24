package com.softserve.edu.box.two;

import com.softserve.edu.box.one.Box;

public class BoxWrapper {

	private Box box;

	public BoxWrapper() {
		box = new Box();
	}

	public String get() {
		return (String) box.get(); // Clean
	}

	public void set(String data) {
		box.set(data);
	}

}
