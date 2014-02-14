package com.sunrise22.particle;

public class Particle {
	int color;
	int r;
	double vertical_v;
	double horizontal_v;
	int startX;
	int startY;
	int x;
	int y;
	double startTime;
	
	public Particle(int color, int r, double vertical_v, double horizontal_v,
			int x, int y, double startTime) {
		this.color = color;
		this.r = r;
		this.vertical_v = vertical_v;
		this.horizontal_v = horizontal_v;
		this.startX = x;
		this.startY = y;
		this.x = x;
		this.y = y;
		this.startTime = startTime;
	}

}
