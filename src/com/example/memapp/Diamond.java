package com.example.memapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

public class Diamond extends MyShape {

	String name;
	float[] x = new float[3];
	float[] y = new float[3];
	Path path;
	final float PERCENT_SIZE_WIDTH = .77f;
	final float PERCENT_SIZE_HEIGHT = .88f;

	public Diamond(EnumShape s, EnumColor c, boolean filled, int limdim, float[] a) {
		super(a);
		// super fields
		color = c;
		shape = s;
		fill = filled;
	
		size(limdim, a);

	}
	
	public void size(int limdim, float[] a) {

		x[0] = a[0] + limdim*(1f - PERCENT_SIZE_WIDTH);
		x[1] = a[0] + limdim/2f;
		x[2] = a[0] + limdim*PERCENT_SIZE_WIDTH;
		
		y[0] = a[1] + limdim/2f;
		y[1] = a[1] + limdim*(1f - PERCENT_SIZE_HEIGHT);
		y[2] = a[1] + limdim*PERCENT_SIZE_HEIGHT;
		
		path = new Path();
		path.setFillType(Path.FillType.EVEN_ODD);
		path.moveTo(x[0], y[0]);
		path.lineTo(x[1], y[1]);
		path.lineTo(x[2], y[0]);
		path.lineTo(x[1], y[2]);
		path.lineTo(x[0], y[0]);
		path.close();
	}

	@Override
	public void draw(Canvas canvas, Paint paint) {
		// TODO Auto-generated method stub

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

