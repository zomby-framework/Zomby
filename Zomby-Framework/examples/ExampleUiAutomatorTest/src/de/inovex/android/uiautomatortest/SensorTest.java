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
public class SensorTest extends UiAutomatorTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getUiDevice().pressHome();
		Launcher.launchApp("Android Sensor Box");
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		getUiDevice().pressHome();
	}

	public void testTemperatureSensor() throws Exception {
		// click temperature
		getUiDevice().click(180, 357);
		
		Zomby.getSensor().simulateTemperaturDrifting(0, 40, 3000);
		synchronized(this) {
			wait(4000);
		}
		
		Zomby.getSensor().simulateTemperaturDrifting(40,1, 3000);
		synchronized(this) {
			wait(4000);
		}
		
		getUiDevice().pressBack();
	}
	
	public void testOrientationSensor() throws Exception {
		
		//getUiDevice().click(340, 116);
			
		Zomby.getSensor().turnAroundInACircle(3000);
		synchronized(this) {
			wait(3600);
		}
		
		//getUiDevice().pressBack();
	}
	
	public void testAccelerometerSensor() throws Exception {

		getUiDevice().click(20, 116);
			
		//
		
		getUiDevice().pressBack();
	}
}