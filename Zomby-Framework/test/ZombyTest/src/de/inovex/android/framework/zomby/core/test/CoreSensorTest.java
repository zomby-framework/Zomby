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

package de.inovex.android.framework.zomby.core.test;

import android.test.ActivityInstrumentationTestCase2;

import com.jayway.android.robotium.solo.Solo;

import de.inovex.android.framework.zomby.Zomby;
import de.inovex.android.framework.zomby.core.CoreSensor.Sensorname;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class CoreSensorTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public CoreSensorTest() {
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
	
	public void testAccelerationSensor() throws Exception {
		Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1);
		Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1, 2);
		Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1, 2, 3);
	}
	
	public void testMagneticFieldSensor() throws Exception {
		Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1);
		Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1, 2);
		Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1, 2, 3);
	}
	
	public void testOrientationSensor() throws Exception {
		Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1);
		Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1, 2);
		Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1, 2, 3);
	}
	
	public void testProximitySensor() throws Exception {
		Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1);
		Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1, 2);
		Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1, 2, 3);
	}
	
	public void testTemperatureSensor() throws Exception {
		Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1);
		Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1, 2);
		Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1, 2, 3);
	}
}
