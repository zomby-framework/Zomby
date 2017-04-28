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

package de.inovex.android.zombydemoapp.test;

import java.util.ArrayList;

import de.inovex.android.framework.zomby.Zomby;
import de.inovex.android.framework.zomby.core.CoreNetwork.NetworkSpeed;
import de.inovex.android.zombydemoapp.NetworkSpeedRating;
import de.inovex.android.zombydemoapp.ZombyDemoAppActivity;

import com.jayway.android.robotium.solo.Solo;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.RatingBar;
import android.widget.SeekBar;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class ZombyDemoAppTest extends ActivityInstrumentationTestCase2<ZombyDemoAppActivity>{
	private Solo solo;
	private ArrayList<SeekBar> seekBarList;
	private ArrayList<RatingBar> ratingBarList;
	private SeekBar seekBar;
	private RatingBar ratingBar;
	
	int startBatteryCapacity = 100;
	NetworkSpeedRating startRating = NetworkSpeedRating.EDGE;

	public ZombyDemoAppTest() {
		super(ZombyDemoAppActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
		
		// start conditions
		Zomby.getCorePower().capacity(startBatteryCapacity);
		Zomby.getCoreNetwork().speed(NetworkSpeed.EDGE);
	}

	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}

	public void testSeekBar() throws Exception {
		solo.waitForActivity("MainActivity");
		
		seekBarList = solo.getCurrentViews(SeekBar.class);
		if(!seekBarList.isEmpty())
			seekBar = seekBarList.get(0);
		else
			throw new Exception("seekBar object was null");
		
		assertEquals(startBatteryCapacity, seekBar.getProgress());
		
		int currentBatteryCapacity = 83;
		Zomby.getCorePower().capacity(currentBatteryCapacity);
		
		assertEquals(currentBatteryCapacity, seekBar.getProgress());
	}
	
	// Note: Test will run fail because of emulator network speed switch bug
	public void testRatingBar() throws Exception {
		solo.waitForActivity("MainActivity");
		
		ratingBarList = solo.getCurrentViews(RatingBar.class);
		if(!ratingBarList.isEmpty())
			ratingBar = ratingBarList.get(0);
		else
			throw new Exception("ratingBar object was null");
		
		assertEquals(startRating.getValue(), ratingBar.getProgress());
		
		Zomby.getCoreNetwork().speed(NetworkSpeed.UMTS);
		
		NetworkSpeedRating currentRating = NetworkSpeedRating.UMTS;
		assertEquals(currentRating.getValue(), ratingBar.getProgress());
	}
}
