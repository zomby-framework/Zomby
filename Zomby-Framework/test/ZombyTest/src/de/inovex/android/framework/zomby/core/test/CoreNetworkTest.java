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
import de.inovex.android.framework.zomby.core.CoreNetwork.Latency;
import de.inovex.android.framework.zomby.core.CoreNetwork.NetworkSpeed;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class CoreNetworkTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {

	private Solo solo;
	
	public CoreNetworkTest() {
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
	
	public void testCapture() throws Exception {
		Zomby.getCoreNetwork().startCapture("captureFile");
		Zomby.getCoreNetwork().stopCapture();
	}
	
	public void testDelay() throws Exception {
		Zomby.getCoreNetwork().delay(Latency.UMTS);	
	}
	
	public void testSpeed() throws Exception {
		Zomby.getCoreNetwork().speed(NetworkSpeed.GPRS);
		Zomby.getCoreNetwork().speed(NetworkSpeed.HSCSD);
		Zomby.getCoreNetwork().speed(NetworkSpeed.GSM);
		Zomby.getCoreNetwork().speed(NetworkSpeed.EDGE);
		Zomby.getCoreNetwork().speed(NetworkSpeed.UMTS);
		Zomby.getCoreNetwork().speed(NetworkSpeed.HSDPA);
		
		//Zomby.getCoreNetwork().speed(NetworkSpeed.GSM, NetworkSpeed.EDGE);
	}
}
