package com.example.memapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

public class Hourglass extends MyShape {

	String name;
	final float PERCENT_SIZE = .84f;
	float[] x = new float[3];
	float[] y = new float[3];
	float rad;
	Path path1;
	Path path2;

	public Hourglass(EnumShape s, EnumColor c, boolean filled, int limdim, float[] anch) {
		super(anch);
		// super fields
		color = c;
		shape = s;
		fill = filled;

		size(limdim, anch);
		
	}
	
	public void size(int limdim, float[] anch) {
		
		x[0] = anch[0] + limdim*(1f - PERCENT_SIZE);
		x[1] = anch[0] + limdim*PERCENT_SIZE;
		x[2] = anch[0] + limdim/2f;
		
		y[0] = anch[1] + limdim*(1f - PERCENT_SIZE);
		y[1] = anch[1] + limdim*PERCENT_SIZE;
		y[2] = anch[1] + limdim/2f;
		
		path1 = new Path();
		path1.setFillType(Path.FillType.EVEN_ODD);
		path1.moveTo(x[0], y[0]);
		path1.lineTo(x[1], y[0]);
		path1.lineTo(x[2], y[2]);
		path1.lineTo(x[0], y[0]);
		path1.close();
		
		path2 = new Path();
		path2.setFillType(Path.FillType.EVEN_ODD);
		path2.moveTo(x[0], y[1]);
		path2.lineTo(x[2], y[2]);
		path2.lineTo(x[1], y[1]);
		path2.lineTo(x[0], y[1]);
		path2.close();
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
		
		canvas.drawPath(path1, paint);
		canvas.drawPath(path2, paint);
	}

}
