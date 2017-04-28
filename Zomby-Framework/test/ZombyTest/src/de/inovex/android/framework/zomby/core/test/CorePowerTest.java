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
import de.inovex.android.framework.zomby.core.CorePower.BatteryHealthState;
import de.inovex.android.framework.zomby.core.CorePower.BatteryStatus;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class CorePowerTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public CorePowerTest() {
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
	
	public void testAC() throws Exception {
		Zomby.getCorePower().ac(true);
		Zomby.getCorePower().ac(false);
	}
	
	public void testCapacity() throws Exception {
		Zomby.getCorePower().capacity(34);
	}
	
	public void testHealth() throws Exception {
		Zomby.getCorePower().health(BatteryHealthState.GOOD);
		Zomby.getCorePower().health(BatteryHealthState.DEAD);
		Zomby.getCorePower().health(BatteryHealthState.FAILURE);
		Zomby.getCorePower().health(BatteryHealthState.OVERHEAT);
		Zomby.getCorePower().health(BatteryHealthState.OVERVOLTAGE);
		Zomby.getCorePower().health(BatteryHealthState.UNKNOWN);
	}
	
	public void testPresent() throws Exception {
		Zomby.getCorePower().present(true);
		Zomby.getCorePower().present(false);
	}
	
	public void testStatus() throws Exception {
		Zomby.getCorePower().status(BatteryStatus.CHARGING);
		Zomby.getCorePower().status(BatteryStatus.DISCHARGING);
		Zomby.getCorePower().status(BatteryStatus.FULL);
		Zomby.getCorePower().status(BatteryStatus.NOT_CHARGING);
		Zomby.getCorePower().status(BatteryStatus.UNKNOWN);
	}
}
