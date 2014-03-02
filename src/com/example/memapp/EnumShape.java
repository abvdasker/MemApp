package com.example.memapp;

public enum EnumShape {
	
	TRIANGLE ("triangle"),
	SQUARE ("square"),
	CIRCLE ("circle"),
	DIAMOND ("diamond"),
	HOURGLASS ("hourglass");
	
	public String name;
	private EnumShape(String s) {
		name = s;
	}
	
	public static EnumShape intToEnum(int i) {
		EnumShape[] v = values();
		for (int j = 0; j < v.length; j++) {
			if(i == j)
				return v[j];
		}
		return null;
	}

}
