package com.sunrise22.particle;

import android.graphics.Canvas;//ÒýÈëÏà¹ØÀà
import android.view.SurfaceHolder;//ÒýÈëÏà¹ØÀà

public class DrawThread extends Thread {
	ParticleView pv; // ParticleView¶ÔÏóÒýÓÃ
	SurfaceHolder surfaceHolder; // SurfaceHolder¶ÔÏóÒýÓÃ
	boolean flag; // Ïß³ÌÖŽÐÐ±êÖŸÎ»
	int sleepSpan = 15; // Ë¯ÃßÊ±Œä
	long start = System.nanoTime(); // ŒÇÂŒÆðÊŒÊ±Œä£¬žÃ±äÁ¿ÓÃÓÚŒÆËãÖ¡ËÙÂÊ
	int count = 0; // ŒÇÂŒÖ¡Êý£¬žÃ±äÁ¿ÓÃÓÚŒÆËãÖ¡ËÙÂÊ

	// ¹¹ÔìÆ÷£¬³õÊŒ»¯Ö÷Òª³ÉÔ±±äÁ¿
	public DrawThread(ParticleView pv, SurfaceHolder surfaceHolder) {
		this.pv = pv;
		this.surfaceHolder = surfaceHolder;
		this.flag = true; // ÉèÖÃÏß³ÌÖŽÐÐ±êÖŸÎ»Îªtrue
	}

	// Ïß³ÌÖŽÐÐ·œ·š
	public void run() {
		Canvas canvas = null; // ÉùÃ÷Ò»žöCanvas¶ÔÏó
		while (flag) {
			try {
				canvas = surfaceHolder.lockCanvas(null);// »ñÈ¡ParticleViewµÄ»­²Œ
				synchronized (surfaceHolder) {
					pv.doDraw(canvas); // µ÷ÓÃParticleViewµÄdoDraw·œ·šœøÐÐ»æÖÆ
				}
			} catch (Exception e) {
				e.printStackTrace(); // ²¶»ñ²¢ŽòÓ¡Òì³£
			} finally {
				if (canvas != null) { // Èç¹ûcanvas²»Îª¿Õ
					surfaceHolder.unlockCanvasAndPost(canvas);// surfaceHolderœâËø²¢œ«»­²Œ¶ÔÏóŽ«»Ø
				}
			}
			this.count++;
			if (count == 20) { // Èç¹ûŒÆÂú20Ö¡
				count = 0; // Çå¿ÕŒÆÊýÆ÷
				long tempStamp = System.nanoTime();// »ñÈ¡µ±Ç°Ê±Œä
				long span = tempStamp - start; // »ñÈ¡Ê±ŒäŒäžô
				start = tempStamp; // ÎªstartÖØÐÂž³Öµ
				double fps = Math.round(100000000000.0 / span * 20) / 100.0;// ŒÆËãÖ¡ËÙÂÊ
				pv.fps = "FPS:" + fps;// œ«ŒÆËã³öµÄÖ¡ËÙÂÊÉèÖÃµœBallViewµÄÏàÓŠ×Ö·ûŽ®¶ÔÏóÖÐ
			}
			try {
				Thread.sleep(sleepSpan);// Ïß³ÌÐÝÃßÒ»¶ÎÊ±Œä
			} catch (Exception e) {
				e.printStackTrace();// ²¶»ñ²¢ŽòÓ¡Òì³£
			}
		}
	}
}
