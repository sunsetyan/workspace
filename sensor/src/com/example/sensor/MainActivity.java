package com.example.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	SensorManager mySensorManager;
	TextView tvX;
	TextView tvY;
	TextView tvZ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tvX = (TextView) findViewById(R.id.tvX);
		tvY = (TextView) findViewById(R.id.tvY);
		tvZ = (TextView) findViewById(R.id.tvZ);
		mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
	}
	
	private SensorEventListener mySensorEventListener = new SensorEventListener() {
		
		@Override
		public void onSensorChanged(SensorEvent event) {
			if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
				float[] values = event.values;
				tvX.setText("X" + values[0]);
				tvY.setText("Y" + values[1]);
				tvZ.setText("Z" +values[2]);
			}
		}
		
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		}
	};
	
	@Override
	protected void onResume() {
		mySensorManager.registerListener(mySensorEventListener, mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_UI);
		super.onResume();
	}

	@Override
	protected void onPause() {
		mySensorManager.unregisterListener(mySensorEventListener);
		super.onPause();
	}
	
	// private SensorListener mySensorListener = new SensorListener() {
	//
	// @Override
	// public void onAccuracyChanged(int sensor, int accuracy) {
	//
	// }
	//
	// @Override
	// public void onSensorChanged(int sensor, float[] values) {
	// if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
	// tvX.setText("X" +values[0]);
	// tvY.setText("Y" +values[1]);
	// tvZ.setText("Z" +values[2]);
	// }
	// }
	//
	// };
	//
	// @Override
	// protected void onResume() {
	// mySensorManager.registerListener(mySensorListener,
	// SensorManager.SENSOR_ACCELEROMETER,
	// SensorManager.SENSOR_DELAY_UI);
	// super.onResume();
	// }
	//
	// @Override
	// protected void onPause() {
	// mySensorManager.unregisterListener(mySensorListener);
	// super.onPause();
	// }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
