package com.gosolar2.utils;

/**
 * Created by Joshua King on 4/18/17.
 */
public class Utils {
	public static Integer sum (Integer i1, Integer i2) {
		return i1 + i2;
	}

	public static boolean notNull (Object o) {
		return !isNull(o);
	}

	public static boolean isNull (Object o) {
		return o == null;
	}
}
