package com.example.memapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;
import android.util.Log;

public class MyShape extends Shape {

	private static final EnumColor[] colorAr = EnumColor.values();
	private static final EnumShape[] shapeAr = EnumShape.values();
	private static final int numColors = colorAr.length;
	private static final int numShapes = shapeAr.length;
	
	EnumShape shape;
	boolean fill;
	EnumColor color;
	float[] anchor = new float[2];
	
	public MyShape(float[] a) {
		super();
		anchor = a;
	}
	
	public void size(int limdim, float[] a) {
		// override
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
	}
	
	public boolean equals(Object o) {
		MyShape ms;
		if (o instanceof MyShape)
			ms = (MyShape) o;
		else return false;
		
		if (!ms.shape.name.equals(this.shape.name))
			return false;
		if (ms.fill != this.fill)
			return false;
		if (!(ms.color.color == this.color.color))
			return false;
		return true;
	}
	
	@Override
	public String toString(){
		return "shape: "+this.shape.name+", color: "+this.color.name()+", fill: "+this.fill;
	}
	
	public static MyShape randomShape(int limdim, float x, float y) {
		float[] anch = {x, y};
		
		int s = (int) (Math.random()*numShapes);
		int c = (int) (Math.random()*numColors);

		boolean b;
		int i = (int) (Math.random()*2);
		b = i == 0 ? false : true;

		MyShape ms = null;

		switch (s) {
		case 0 :
			ms = new Triangle(shapeAr[s], colorAr[c], b, limdim, anch);
			break;
		case 1:
			ms = new Square(shapeAr[s], colorAr[c], b, limdim, anch);
			break;
		case 2:
			ms = new Circle(shapeAr[s], colorAr[c], b, limdim, anch);
			break;
		case 3:
			ms = new Diamond(shapeAr[s], colorAr[c], b, limdim, anch);
			break;
		case 4:
			ms = new Hourglass(shapeAr[s], colorAr[c], b, limdim, anch);
			break;
		}
		
		Log.d("RANDOM SHAPE", ms.toString());
		
		return ms;
	}

}
