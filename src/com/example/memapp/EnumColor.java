package com.example.memapp;

import android.graphics.Color;

public enum EnumColor {
	BLUE (Color.BLUE), 
	RED (Color.RED), 
	GREEN (Color.GREEN), 
	YELLOW (Color.YELLOW),
	ORANGE (Color.argb(255, 255, 140, 0)),
	BLACK (Color.BLACK), 
	GRAY (Color.GRAY), 
	CYAN (Color.CYAN), 
	PURPLE (Color.MAGENTA);
	
	public final int color;
	
    private EnumColor (int c) {
    	color = c;
    }
    
    public static EnumColor intToEnum(int c) {
    	for (EnumColor e : EnumColor.values()) {
    		if (e.color == c)
    			return e;
    	}
    	return null;
    }
    
}
