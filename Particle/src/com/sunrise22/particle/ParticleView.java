package com.sunrise22.particle;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class ParticleView extends SurfaceView implements Callback {
	
	public static final int DIE_OUT_LINE = 300;
	DrawThread dt;
	ParticleSet ps;
	ParticleThread pt;
	String fps = "FPS:N/A";
	
	public ParticleView(Context context) {
		super(context);
		this.getHolder().addCallback(this);
		dt = new DrawThread(this, getHolder());
		ps = new ParticleSet();
		pt = new ParticleThread(this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}
	
	public void doDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		ArrayList<Particle> particleSet = ps.particleSet;
		Paint paint = new Paint();
		for (int i = 0; i < particleSet.size(); i++) {
			Particle p = particleSet.get(i);
			paint.setColor(p.color);
			int tempX = p.x;
			int tempY = p.y;
			int tempRadius = p.r;
			RectF oval = new RectF(tempX, tempY, tempX + 2 * tempRadius, tempY + 2 * tempRadius);
			canvas.drawOval(oval, paint);
		}
		paint.setColor(Color.WHITE);
		paint.setTextSize(18);
		paint.setAntiAlias(true);
		canvas.drawText(fps, 15, 15, paint);
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!dt.isAlive())
			dt.start();
		if (!pt.isAlive())
			pt.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		dt.flag =false;
		dt = null;
		pt.flag = false;
		pt = null;
	}

}
