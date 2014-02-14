package com.sunrise22.particle;

import java.util.ArrayList;

import android.graphics.Color;

public class ParticleSet {
	
	ArrayList<Particle> particleSet;
	public ParticleSet() {
		particleSet = new ArrayList<Particle>();
	}
	
	public void add(int count, double startTime) {
		for (int i = 0; i < count; i++) {
			int tempColor = this.getColor(i);
			int tempR = 1;
			double tempv_v = -30 + 10 * (Math.random());
			double tempv_h = 10 - 20 * (Math.random());
			int tempX = 160;
			int tempY = (int)(100 - 10 * (Math.random()));
			Particle particle = new Particle(tempColor, tempR, tempv_v, tempv_h, tempX, tempY, startTime);
			particleSet.add(particle);
		}
	}

	public int getColor(int i) {
		int color = Color.RED;
		switch(i % 4) {
		case 0:
			color = Color.RED;
			break;
		case 1:
			color = Color.GREEN;
			break;
		case 2:
			color = Color.YELLOW;
			break;
		case 3:
			color = Color.GRAY;
			break;
		}
		return color;
	}
	

}
