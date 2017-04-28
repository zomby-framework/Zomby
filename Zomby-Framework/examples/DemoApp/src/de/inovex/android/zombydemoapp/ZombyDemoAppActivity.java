/* Copyright 2013 inovex GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.inovex.android.zombydemoapp;

import de.inovex.android.zombydemoapp.receiver.BatteryChangeReceiver;
import de.inovex.android.zombydemoapp.receiver.NetworkChangeReceiver;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.RatingBar;
import android.widget.SeekBar;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class ZombyDemoAppActivity extends Activity {
 
	private SeekBar seekBar;
	private RatingBar ratingBar;
	private BatteryChangeReceiver batteryChangeReceiver;
	private NetworkChangeReceiver networkChangeReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		seekBar = (SeekBar) findViewById(R.id.seekBar);
		ratingBar = (RatingBar) findViewById(R.id.ratingBar);
		
		// init BroadcastReceiver
		batteryChangeReceiver = new BatteryChangeReceiver(seekBar);
		networkChangeReceiver = new NetworkChangeReceiver(ratingBar);
	}

	@Override
	protected void onResume() {
		registerReceiver(batteryChangeReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		registerReceiver(networkChangeReceiver, new IntentFilter("android.intent.action.ANY_DATA_STATE"));
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		unregisterReceiver(batteryChangeReceiver);
		unregisterReceiver(networkChangeReceiver);
		super.onPause();
	}
}
