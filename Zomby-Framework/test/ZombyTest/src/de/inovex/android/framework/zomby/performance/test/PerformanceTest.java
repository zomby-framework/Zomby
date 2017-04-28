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

package de.inovex.android.framework.zomby.performance.test;

import com.jayway.android.robotium.solo.Solo;

import android.test.ActivityInstrumentationTestCase2;
import de.inovex.android.framework.zomby.Zomby;
import de.inovex.android.framework.zomby.core.CoreCDMA.Source;
import de.inovex.android.framework.zomby.core.CoreGSM.GsmMode;
import de.inovex.android.framework.zomby.core.CoreGeo.Sentence;
import de.inovex.android.framework.zomby.core.CoreNetwork.Latency;
import de.inovex.android.framework.zomby.core.CoreNetwork.NetworkSpeed;
import de.inovex.android.framework.zomby.core.CorePower.BatteryHealthState;
import de.inovex.android.framework.zomby.core.CorePower.BatteryStatus;
import de.inovex.android.framework.zomby.core.CoreSensor.Sensorname;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class PerformanceTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public PerformanceTest() {
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
	
	public void testWebPerformance() throws Exception {
		
		for(int i=0; i<300; i++) {
			Zomby.getCoreCDMA().startCapture("captureFile");
			Zomby.getCoreCDMA().stopCapture();
			Zomby.getCoreCDMA().delay(Latency.UMTS);
			Zomby.getCoreCDMA().speed(NetworkSpeed.EDGE);
			Zomby.getCoreCDMA().speed(NetworkSpeed.GPRS, NetworkSpeed.UMTS);
			Zomby.getCoreCDMA().ssource(Source.NON_VOLATILE_RAM);
			Zomby.getCoreCDMA().ssource(Source.RUIM);
			Zomby.getCoreGeo().fix(-121.45356, 46.51119, 4392);
			Zomby.getCoreGeo().fix(3, 3);
			Zomby.getCoreGeo().fix(4.5, 3.3, 2.2);
			Zomby.getCoreGeo().fix(10, 10, 11, 12);
			Zomby.getCoreGeo().nmea(Sentence.GPRCM, ",081836,A,3751.65,S,14507.36,E,000.0,360.0,130998,011.3,E*62");
			Zomby.getCoreGSM().signal(30);
			Zomby.getCoreGSM().signal(20, 3);
			Zomby.getCoreGSM().voice(GsmMode.HOME);		
			Zomby.getCoreGSM().voice(GsmMode.ON);		
			Zomby.getCoreGSM().voice(GsmMode.DENIED);		
			Zomby.getCoreGSM().voice(GsmMode.OFF);				
			Zomby.getCoreGSM().voice(GsmMode.SEARCHING);		
			Zomby.getCoreGSM().voice(GsmMode.UNREGISTERED);
			Zomby.getCoreNetwork().startCapture("captureFile");
			Zomby.getCoreNetwork().stopCapture();
			Zomby.getCoreNetwork().delay(Latency.UMTS);	
			Zomby.getCoreNetwork().speed(NetworkSpeed.HSDPA);
			Zomby.getCoreNetwork().speed(NetworkSpeed.GSM, NetworkSpeed.EDGE);
			Zomby.getCorePower().ac(true);
			Zomby.getCorePower().ac(false);
			Zomby.getCorePower().capacity(34);
			Zomby.getCorePower().health(BatteryHealthState.GOOD);
			Zomby.getCorePower().health(BatteryHealthState.DEAD);
			Zomby.getCorePower().health(BatteryHealthState.FAILURE);
			Zomby.getCorePower().health(BatteryHealthState.OVERHEAT);
			Zomby.getCorePower().health(BatteryHealthState.OVERVOLTAGE);
			Zomby.getCorePower().health(BatteryHealthState.UNKNOWN);
			Zomby.getCorePower().present(true);
			Zomby.getCorePower().present(false);
			Zomby.getCorePower().status(BatteryStatus.CHARGING);
			Zomby.getCorePower().status(BatteryStatus.DISCHARGING);
			Zomby.getCorePower().status(BatteryStatus.NOT_CHARGING);
			Zomby.getCorePower().status(BatteryStatus.UNKNOWN);
			Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1);
			Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1, 2);
			Zomby.getCoreSensor().set(Sensorname.ACCELERATION, 1, 2, 3);
			Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1);
			Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1, 2);
			Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, 1, 2, 3);
			Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1);
			Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1, 2);
			Zomby.getCoreSensor().set(Sensorname.ORIENTATION, 1, 2, 3);
			Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1);
			Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1, 2);
			Zomby.getCoreSensor().set(Sensorname.PROXIMITY, 1, 2, 3);
			Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1);
			Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1, 2);
			Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, 1, 2, 3);
		}
			
	}
}
