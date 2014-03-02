package com.example.memapp;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MemGameView extends SurfaceView implements SurfaceHolder.Callback {

	DrawThread _thread;
	LinkedList<MyShape> shapes;
	Timer t;
	EnumColor[] colorAr;
	EnumShape[] shapeAr;
	int numColors = EnumColor.values().length;
	int numShapes = EnumShape.values().length;
	int width;
	int height;
	int currentShape;
	Paint paint;
	Paint background;
	StartActivity parent;
	boolean transition;

	public MemGameView(Context ctx, AttributeSet set) {
		super(ctx, set);

		/*TypedArray a = context.getTheme().obtainStyledAttributes(
		        attrs,
		        R.styleable.PieChart,
		        0, 0);
		try {
			mShowText = a.getBoolean(R.styleable.PieChart_showText, false);
			mTextPos = a.getInteger(R.styleable.PieChart_labelPosition, 0);
		} finally {
		  	a.recycle();
		}*/
		//shapes = new LinkedList<MyShape>();
		colorAr = EnumColor.values();
		shapeAr = EnumShape.values();

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStrokeWidth(5f);
		paint.setAntiAlias(true);
		background = new Paint();
		background.setColor(Color.WHITE);
		//reUp();
		//transition = false;
		//currentShape = -1;

		t = new Timer();

		getHolder().addCallback(this);

	}

	public void reUp() {
		if (AppVars.getShapes() == null)
			AppVars.setShapes(new LinkedList<MyShape>());
		shapes = AppVars.getShapes();

		transition = false;
		currentShape = -1;
	}

	@Override
	public void onDraw(Canvas canvas) {
		
		if (shapes != null) {
			Log.d("ONDRAW CALL", "number of shapes: " + shapes.size()+ ", current shape index: "+ currentShape);
	
		
			if (currentShape < shapes.size() && currentShape > -1 && !transition) {
			
				canvas.drawPaint(background);
				MyShape s = shapes.get(currentShape);
				Log.d("drawstate", "DREW SHAPE: " + s.toString());
				s.draw(canvas, paint);
			} else {
				Log.d("drawstate", "DID NOT DRAW SHAPE");
			}

			paint.setColor(Color.BLACK);
			canvas.drawLine(0f, height, width, height, paint);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		this.width = width;
		this.height = height;
	}

	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		
		if (AppVars.getShapes() == null)
			AppVars.setShapes(new LinkedList<MyShape>());
		shapes = AppVars.getShapes();

		transition = false;
		currentShape = -1;
		
		int limdim = width < height ? width : height;
		MyShape ms = MyShape.randomShape(limdim, 0f, (height - width)/2f);
		while (shapes.contains(ms))
			ms = MyShape.randomShape(limdim, 0f, (height - width)/2f);
		shapes.add(ms);

		Log.d("shapes size after creation", shapes.size() + "");

		setWillNotDraw(false);
		// TODO Auto-generated method stub
		_thread = new DrawThread(getHolder(), this);
		_thread.setRunning(true);
		_thread.start();

		spawnTimer();
	}

	public void spawnTimer(){
		Log.d("spawned timer", "timer spawned");
		t.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				//randomShape();
				if (currentShape < shapes.size() - 1) {
					postInvalidate();
					currentShape++;
				} else {
					transition = true;
					AppVars.setShapes(shapes);
					t.cancel();
					t.purge();
					Log.d("ACTIVITY CHANGE", "made move");
					try {
						_thread.setRunning(false);
						_thread.join();
					} catch (InterruptedException e) {
					}
					parent.moveToChoice();
					//}
				}
			}
		}, 0, 2500);
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
	}

}
