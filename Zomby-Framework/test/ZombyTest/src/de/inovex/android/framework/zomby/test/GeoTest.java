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
public class GeoTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	double startPointLongitude = 7.185311;
	double startPointLatitude = 50.780011;
	double endPointLongitude = 7.181947;
	double endPointLatitude = 50.780177;
	
	public GeoTest() {
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
	
	public void testSimulateWalkingPedestrian() throws Exception {
		Zomby.getGeo().simulateWalkingPedestrian(startPointLongitude, startPointLatitude, endPointLongitude, endPointLatitude);
		synchronized(this) {
			wait(1500);
		}
	}
	
	public void testSimulateRunningPedestrian() throws Exception {
		Zomby.getGeo().simulateRunningPedestrian(startPointLongitude, startPointLatitude, endPointLongitude, endPointLatitude);
		synchronized(this) {
			wait(1500);
		}
	}
	public void testSimulateMovingObject() throws Exception {
		Zomby.getGeo().simulateMovingObject(startPointLongitude, startPointLatitude, endPointLongitude, endPointLatitude,60);
		synchronized(this) {
			wait(1500);
		}
	}
}