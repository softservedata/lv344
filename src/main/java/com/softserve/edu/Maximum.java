package com.softserve.edu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import sun.misc.Unsafe;

public class Maximum {
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int x = 1, y = 1; // ???
		try {
			System.out.print("x=");
			x = Integer.parseInt(br.readLine()); // =Float.parseFloat(…);
			System.out.print("y=");
			y = Integer.parseInt(br.readLine());
		} catch (Exception e) { // NumberFormatException
			System.out.println("I/O Error.");
		}
		if (x > y) {
			System.out.println("x is maximum, x=" + x);
		} else {
			System.out.println("y is maximum, y=" + y);
		}
		int k = 1234;
		String s = String.valueOf(k);
		String s1 = new Integer(k).toString();
		System.out.println("s.equals(s1) = " + s.equals(s1));
		//
		//Integer k20 = Integer.valueOf(k);
		Long k20 = Long.valueOf(k);
		Long k21 = Long.valueOf(k);
		Long k22 = new Long(k);
		System.out.println("addr0 k20 = " + ((Object) k20).toString());
		//Unsafe unsafe = Unsafe.getUnsafe();
		//System.out.println("addr k20 = " + unsafe.getAddress(k20.hashCode()));
	}
}
