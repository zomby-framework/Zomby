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

package de.inovex.android.framework.zomby.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import de.inovex.android.framework.zomby.Zomby;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class SensorTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public SensorTest() {
		super(ZombyRecorderActivity.class);
	}
	
	@Override
	public void setUp() throws Exception {
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	@Override
	public void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	public void testSetPortraitMode() throws Exception {
		Zomby.getSensor().setPortraitMode();
	}
	
	public void testSetLandscapeMode() throws Exception {
		Zomby.getSensor().setLandscapeMode();
	}
	
	public void testShowToNorth() throws Exception {
		Zomby.getSensor().showToNorth();
	}
	
	public void testShowToNorthEast() throws Exception {
		Zomby.getSensor().showToNorthEast();
	}
	
	public void testShowToEast() throws Exception {
		Zomby.getSensor().showToEast();
	}
	
	public void testShowSouthEast() throws Exception {
		Zomby.getSensor().showToSouthEast();
	}
	
	public void testShowSouth() throws Exception {
		Zomby.getSensor().showToSouth();
	}
	
	public void testShowSouthWest() throws Exception {
		Zomby.getSensor().showToWest();
	}
	
	public void testShowWest() throws Exception {
		Zomby.getSensor().showToWest();
	}
	
	public void testShowNorthWest() throws Exception {
		Zomby.getSensor().showToNorthWest();
	}
	
	public void testTurnAroundInACircle() throws Exception {
		Zomby.getSensor().turnAroundInACircle(3000);
		synchronized(this) {
			wait(3500);
		}
	}
	
	public void testSimulateTemperature() throws Exception {
		Zomby.getSensor().simulateTemperaturDrifting(0, 60, 1000);
		synchronized(this) {
			wait(1500);
		}
	}
}
