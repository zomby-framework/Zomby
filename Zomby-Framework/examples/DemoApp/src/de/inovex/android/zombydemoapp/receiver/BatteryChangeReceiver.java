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

package de.inovex.android.zombydemoapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.SeekBar;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class BatteryChangeReceiver extends BroadcastReceiver {
	

	private SeekBar seekBar;

	public BatteryChangeReceiver(SeekBar seekBar) {
		this.seekBar = seekBar;
	}

	@Override
	public void onReceive(Context context, Intent batteryStatusIntent) {
		
		// battery level
		int batteryLevel = batteryStatusIntent.getIntExtra("level", 0);
		seekBar.setProgress(batteryLevel);
	}
}