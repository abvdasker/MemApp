package com.example.memapp;

import java.util.LinkedList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class ChoiceView extends SurfaceView implements SurfaceHolder.Callback {

	MyShape[][] shapeGrid;
	boolean[][] toggleGrid;
	LinkedList<MyShape> shapes;
	LinkedList<MyShape> madeShapes;
	DrawThread _thread;
	int width;
	int height;
	final int columns = 5;
	int rows;
	int cell_size;
	Paint paint;
	Paint background;
	boolean shouldConfirm = false;
	ChoiceActivity parent;

	public ChoiceView(Context ctx, AttributeSet set) {
		super(ctx, set);
		shapes = AppVars.getShapes();

		madeShapes = new LinkedList<MyShape>();

		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setStrokeWidth(3f);
		background = new Paint();
		background.setColor(Color.WHITE);

		getHolder().addCallback(this);
	}

	@Override
	public void onDraw(Canvas canvas) {
		if (shapeGrid != null) {
			//Log.d("trying to draw", "columns: " + shapeGrid[0].length+", rows: "+shapeGrid.length);
			canvas.drawPaint(background);
	
			for (int i = 0; i < shapeGrid.length; i++) {
				for (int j = 0; j < shapeGrid[i].length; j++) {
					//Log.d("DREW", "row: "+j+", column: "+i);
					shapeGrid[i][j].draw(canvas, paint);
	
					if (toggleGrid[i][j]) {
						paint.setColor(Color.BLACK);
						paint.setStyle(Paint.Style.STROKE);
						canvas.drawRect(j*cell_size, i*cell_size, (j+1)*cell_size, (i+1)*cell_size, paint);
					}
				}
			}
	
			paint.setColor(Color.BLACK);
			canvas.drawLine(0f, height, width, height, paint);
		}
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		this.width = width;
		this.height = height;
	}

	public void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w;
		height = h;
	}

	public void confirm() {
		MyShape ms;
		int foundShapes = 0;
		int selectedShapes = 0;
		for (int i = 0; i < toggleGrid.length; i++ ) {
			for (int j = 0; j < toggleGrid[i].length; j++) {
				if (toggleGrid[i][j]) {
					ms = shapeGrid[i][j];
					if (shapes.contains(ms)) {
						foundShapes++;
					}
					selectedShapes++;
				}
			}
		}

		if (foundShapes == selectedShapes && selectedShapes > 0) {
			try {
				_thread.setRunning(false);
				_thread.join();
			} catch (InterruptedException e) {
			}
			parent.correct();
		} else {
			try {
				_thread.setRunning(false);
				_thread.join();
			} catch (InterruptedException e) {
			}
			parent.incorrect();
		}
		//selection_correct = true;
	}

	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		//Log.d("DIMENSIONS", "width: "+width+", height: "+height);
		makeSelectionGrid();

		_thread = new DrawThread(this.getHolder(), this);
		_thread.setRunning(true);
		_thread.start();
	}

	public void makeSelectionGrid() {
		cell_size = width/columns;
		rows = (int) (height/cell_size);
		int cells = rows*columns;

		shapeGrid = new MyShape[rows][columns];
		toggleGrid = new boolean[rows][columns];

		boolean[] shuffle = shuffleOrder(cells);

		int shapeCounter = 0;
		int k = 0;
		MyShape temp;
		float[] a = new float[2];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++, k++) {
				a[0] = j*cell_size;
				a[1] = i*cell_size;
				if (shuffle[k]) {
					temp = shapes.get(shapeCounter++);
					temp.size(cell_size, a);
					madeShapes.add(temp);
					shapeGrid[i][j] = temp;
				} else {
					temp = MyShape.randomShape(cell_size, a[0], a[1]);
					while (madeShapes.contains(temp) || shapes.contains(temp))
						temp = MyShape.randomShape(cell_size, a[0], a[1]);
					madeShapes.add(temp);
					shapeGrid[i][j] = temp;
				}
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent e) {

		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			float x = e.getX();
			float y = e.getY();
			if (y < rows*cell_size && x < columns*cell_size) {
				int touch_row = (int) y/cell_size;
				int touch_column = (int) x/cell_size;
				toggleGrid[touch_row][touch_column] = !toggleGrid[touch_row][touch_column];
			}
			break;
		}
		return true;
	}

	public boolean[] shuffleOrder(int cells){
		boolean[] order = new boolean[cells];
		for (int i = 0; i < shapes.size(); i++) {
			order[i] = true;
		}

		boolean b;
		int ind;
		for (int i = order.length - 1; i > 1; i--) {
			ind =(int) (Math.random()*(i+1));
			b = order[ind];
			order[ind] = order[i];
			order[i] = b;
		}

		return order;
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

		restoreShapes();
	}

	public void restoreShapes() {
		float[] a = {0f, (height - width)/2f};
		int limdim = width < height ? width : height;
		for (MyShape s : shapes) {
			s.size(limdim, a);
		}
		AppVars.setShapes(shapes);
	}

}
