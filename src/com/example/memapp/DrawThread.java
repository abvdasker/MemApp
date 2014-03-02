package com.example.memapp;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawThread extends Thread {

	private SurfaceHolder _surfaceH;
	private MemGameView _mem_view;
	private ChoiceView _choice_view;
	private boolean _run = false;
	private boolean is_choice = false;
	//private Timer t;

	public DrawThread(SurfaceHolder h, SurfaceView mv) {
		_surfaceH = h;
		
		if (mv instanceof MemGameView) {
			_mem_view = (MemGameView) mv;
			is_choice = false;
		} else if (mv instanceof ChoiceView) {
			_choice_view = (ChoiceView) mv;
			is_choice = true;
		}
	}

	public void setRunning(boolean b) {
		//Log.d("STOP THREAD", "stop the thread");
		_run = b;
	}

	public void run() {

		Canvas c;
		while (_run) {
			//	Log.d("RENDER LOOP", "try to render?");
			c = null;

			try {

				c = _surfaceH.lockCanvas(null);
				synchronized (_surfaceH) {
					//Log.d("RUN THREAD", "thread iteration");
					if (is_choice) {
						_choice_view.onDraw(c);
					} else {
						//_mem_view.postInvalidate();
						_mem_view.onDraw(c);
					}
				}
			} finally {
				if (c != null) {
					_surfaceH.unlockCanvasAndPost(c);
				}
			}
		}

	}
}
