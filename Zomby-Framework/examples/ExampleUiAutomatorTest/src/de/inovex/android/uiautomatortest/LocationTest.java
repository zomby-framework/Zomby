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

package de.inovex.android.uiautomatortest;

import com.android.uiautomator.testrunner.UiAutomatorTestCase;

import de.inovex.android.framework.zomby.Zomby;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class LocationTest extends UiAutomatorTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getUiDevice().pressHome();
		Launcher.launchApp("Maps");
	}
	
	public void testWalker() throws Exception {
		double point1Longitude = 7.185295;
		double point1Latitude = 50.779822;

		double point2Longitude = 7.18523;
		double point2Latitude = 50.779867;

		double point3Longitude = 7.185254;
		double point3Latitude = 50.78000;
		
		double point4Longitude = 7.18511;
		double point4Latitude = 50.780017;

		synchronized (this) {
			Zomby.getGeo().simulateGoingWalker(point1Longitude, point1Latitude, point2Longitude, point2Latitude);
			wait(7500);
			Zomby.getGeo().simulateGoingWalker(point2Longitude, point2Latitude, point3Longitude, point3Latitude);
			wait(12000);
			Zomby.getGeo().simulateGoingWalker(point3Longitude, point3Latitude, point4Longitude, point4Latitude);
			wait(12000);
		}
	}
}