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
import de.inovex.android.framework.zomby.util.ZombyException;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class PowerTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public PowerTest() {
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
	
	public void testSetFullPower() throws Exception {	
		Zomby.getPower().setFullPower();
	}
	
	public void testSetHalfPower() throws Exception {	
		Zomby.getPower().setHalfPower();
	}
	
	public void testSetUncriticalPower() throws Exception {	
		Zomby.getPower().setUncriticalPower();
	}
	
	public void testSetCrititcalPower() throws Exception {	
		Zomby.getPower().setCriticalPower();
	}
		
	public void testSimulateBatteryLife() throws Exception {	
		Zomby.getPower().simulateBatteryLife(50, 30, 500);
		synchronized(this) {
			wait(1000);
		}
		Zomby.getPower().simulateWholeBatteryLife(1000);
		synchronized(this) {
			wait(1500);
		}
		
		// test invalid values
		try {
			// endCapacity is greater than startCapacity
			Zomby.getPower().simulateBatteryLife(30, 50, 1000);
			throw new ZombyException("simulateBatteryLife except endCapacity is greater than startCapacity");
		} catch(ZombyException e) {
		}
		try {
			// invalid start capacity value
			Zomby.getPower().simulateBatteryLife(200,50, 1000);
			throw new ZombyException("simulateBatteryLife except invalid start capacity value");
		} catch(ZombyException e) {
		}
		try {
			// invalid end capacity value
			Zomby.getPower().simulateBatteryLife(90,-1, 1000);
			throw new ZombyException("simulateBatteryLife except invalid end capacity value");
		} catch(ZombyException e) {
		}
		try {
			// milliseconds to short
			Zomby.getPower().simulateBatteryLife(50, 30, 90);
			throw new ZombyException("simulateBatteryLife except to short milliseconds");
		} catch(ZombyException e) {
		}
	}
	
	public void testSimulateBatteryLoading() throws Exception {	
		Zomby.getPower().simulateBatteryLoading(30, 50, 300);
		synchronized(this) {
			wait(1000);
		}
		Zomby.getPower().simulateWholeBatteryLoading(1000);
		synchronized(this) {
			wait(1500);
		}
		
		// test invalid values
		try {
			// startCapacity is greater than endCapacity
			Zomby.getPower().simulateBatteryLoading(50, 30, 1000);
			throw new ZombyException("simulateBatteryLoading except startCapacity is greater than endCapacity");
		} catch(ZombyException e) {
		}
		try {
			// invalid start capacity value
			Zomby.getPower().simulateBatteryLoading(50,200, 1000);
			throw new ZombyException("simulateBatteryLoading except invalid start capacity value");
		} catch(ZombyException e) {
		}
		try {
			// invalid end capacity value
			Zomby.getPower().simulateBatteryLoading(-1, 90, 1000);
			throw new ZombyException("simulateBatteryLoading except invalid end capacity value");
		} catch(ZombyException e) {
		}
		try {
			// milliseconds to short
			Zomby.getPower().simulateBatteryLoading(30, 50, 90);
			throw new ZombyException("simulateBatteryLoading except to short milliseconds");
		} catch(ZombyException e) {
		}
	}
}
