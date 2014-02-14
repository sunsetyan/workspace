package com.sunrise22.particle;

import java.util.ArrayList;
import java.util.Random;

public class ParticleThread extends Thread {
	
	boolean flag;
	ParticleView father;
	int sleepSpan = 80;
	double time = 0;
	double span = 0.15;
	
	public ParticleThread(ParticleView father) {
		this.father = father;
		this.flag = true;
	}
	
	public void run() {
		Random rand = new Random(17);
		while (flag) {
			father.ps.add(rand.nextInt(10), time);
			ArrayList<Particle> tempSet = father.ps.particleSet;
			int count = tempSet.size();
			for (int i = 0; i < count; i++) {
				Particle particle = tempSet.get(i);
				double timeSpan = time - particle.startTime;
				int tempx = (int) (particle.startX + particle.horizontal_v * timeSpan);
				int tempy = (int) (particle.startY + 4.9 * timeSpan * timeSpan + particle.vertical_v * timeSpan);
				
				if (tempy > 300) {
					tempSet.remove(particle);
					count = tempSet.size();
				}
				particle.x = tempx;
				particle.y = tempy;
			}
			time += span;
			try {
				Thread.sleep(sleepSpan);
			} catch (Exception e) {}
		}
	}

}
