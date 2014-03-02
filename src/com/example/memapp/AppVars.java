package com.example.memapp;

import java.util.LinkedList;

public class AppVars {

	private static LinkedList<MyShape> appShapes;
	
	public static void setShapes(LinkedList<MyShape> s) {
		appShapes = s;
	}
	
	public static LinkedList<MyShape> getShapes() {
		return appShapes;
	}
}
