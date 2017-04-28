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
public class CoreAVDTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	private String filename = "filename";
	
	public CoreAVDTest() {
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
	
	public void testStart() throws Exception {
		Zomby.getCoreAVD().start();
	}
	
	public void testStop() throws Exception {
		Zomby.getCoreAVD().start();
	}
	
	public void testSaveSnapshot() throws Exception {
		//Zomby.getCoreAVD().saveSnapshot(filename);
	}
	
	public void testLoadSnapshot() throws Exception {
		//Zomby.getCoreAVD().loadSnapshot(filename);
	}
	
	public void testDeleteSnapshot() throws Exception {
		//Zomby.getCoreAVD().deleteSnapshot(filename);
	}
}
