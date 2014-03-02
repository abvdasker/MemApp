package com.example.memapp;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class Circle extends MyShape {

	String name;
	final float PERCENT_SIZE = .84f;
	float[] cxy = new float[2];
	float rad;

	public Circle(EnumShape s, EnumColor c, boolean filled, int limdim, float[] anch) {
		super(anch);
		// super fields
		color = c;
		shape = s;
		fill = filled;

		size(limdim, anch);
		
	}
	
	public void size(int limdim, float[] anch) {
		cxy[0] = limdim/2f + anch[0];
		cxy[1] = limdim/2f + anch[1];
		rad = (limdim/2f)*PERCENT_SIZE;
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
		
		canvas.drawCircle(cxy[0], cxy[1], rad, paint);
		//canvas.drawLines(pts, paint);
	}

}
