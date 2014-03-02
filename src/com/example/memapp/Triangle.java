package com.example.memapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

public class Triangle extends MyShape {

	String name;
	float[] x = new float[3];
	float[] y = new float[3];
	Path path;
	final float PERCENT_SIZE = .85f;
	
	public Triangle(EnumShape s, EnumColor c, boolean filled, int limdim, float[] a) {
		super(a);
				
		// super fields
		color = c;
		shape = s;
		fill = filled;
		
		size(limdim, a);
		
	}
	
	public void size(int limdim, float[] a) {
		x[0] = a[0] + limdim/2;
		x[1] = a[0] + limdim*PERCENT_SIZE;
		x[2] = a[0] + limdim*(1f - PERCENT_SIZE);
		
		y[0] = a[1] + limdim*(1f - PERCENT_SIZE);
		y[1] = a[1] + limdim*PERCENT_SIZE;
		
		path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(x[0], y[0]);
		path.lineTo(x[1], y[1]);
		path.lineTo(x[2], y[1]);
		path.lineTo(x[0], y[0]);
		path.close();
	}
	
	@Override
	public void draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub
		//canvas.drawLines(pts, p);

		paint.setColor(color.color);
		if (fill) {
			//Log.d("shape style", "fill");
			paint.setStyle(Paint.Style.FILL);
			//paint.setStyle(Paint.Style.FILL_AND_STROKE);
		} else {
			//Log.d("shape style", "stroke");
			paint.setStyle(Paint.Style.STROKE);
		}
		
		canvas.drawPath(path, paint);
	}

}
