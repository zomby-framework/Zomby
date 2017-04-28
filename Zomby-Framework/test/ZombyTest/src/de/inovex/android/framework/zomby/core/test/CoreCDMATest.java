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
import de.inovex.android.framework.zomby.core.CoreCDMA.Source;
import de.inovex.android.framework.zomby.core.CoreNetwork.Latency;
import de.inovex.android.framework.zomby.core.CoreNetwork.NetworkSpeed;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class CoreCDMATest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	
	public CoreCDMATest() {
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
		Zomby.getCoreCDMA().startCapture("captureFile");
		Zomby.getCoreCDMA().stopCapture();
	}
	
	public void testDelay() throws Exception {
		Zomby.getCoreCDMA().delay(Latency.UMTS);
	}
	
	public void testSpeed() throws Exception {
		Zomby.getCoreCDMA().speed(NetworkSpeed.EDGE);
		Zomby.getCoreCDMA().speed(NetworkSpeed.GPRS, NetworkSpeed.UMTS);
	}
	
	public void testSsource() throws Exception {
		Zomby.getCoreCDMA().ssource(Source.NON_VOLATILE_RAM);
		Zomby.getCoreCDMA().ssource(Source.RUIM);
	}
}