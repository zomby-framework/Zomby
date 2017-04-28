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

import de.inovex.android.zombydemoapp.NetworkSpeedRating;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.RatingBar;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
	
	private RatingBar ratingBar;
	
	public NetworkChangeReceiver(RatingBar ratingBar) {
		this.ratingBar = ratingBar;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		
		NetworkInfo mobilInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		
		if (mobilInfo != null) {
			if(mobilInfo.isConnected()) {
				String subTypename = mobilInfo.getSubtypeName();
				
				NetworkSpeedRating networkSpeed = NetworkSpeedRating.valueOf(subTypename);
				ratingBar.setProgress(networkSpeed.getValue());
			}
		}
		else
			ratingBar.setProgress(0);
			
	}
}
