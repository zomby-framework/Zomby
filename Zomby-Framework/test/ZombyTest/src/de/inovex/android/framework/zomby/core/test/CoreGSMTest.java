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
import de.inovex.android.framework.zomby.core.CoreGSM.GsmMode;
import de.inovex.android.zombyrecorder.ZombyRecorderActivity;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class CoreGSMTest extends ActivityInstrumentationTestCase2<ZombyRecorderActivity> {
	
	private Solo solo;
	String phonenumber = "01223";
	
	public CoreGSMTest() {
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
	
	/*public void testCall() throws Exception {
		Zomby.getCoreGSM().call(phonenumber);
	}
	
	public void testHold() throws Exception {
		Zomby.getCoreGSM().call(phonenumber);
		Zomby.getCoreGSM().hold(phonenumber);
	}
	
	public void testAccept() throws Exception {
		Zomby.getCoreGSM().call(phonenumber);
		Zomby.getCoreGSM().accept(phonenumber);
	}
	
	public void testBusy() throws Exception {
		Zomby.getCoreGSM().call(phonenumber);
		Zomby.getCoreGSM().busy(phonenumber);
	}
	
	public void testCancel() throws Exception {
		Zomby.getCoreGSM().call(phonenumber);
		Zomby.getCoreGSM().cancel(phonenumber);
	}*/
	
	public void testSignal() throws Exception {
		Zomby.getCoreGSM().signal(30);
		Zomby.getCoreGSM().signal(20, 3);
	}
	
	public void testData() throws Exception {
		Zomby.getCoreGSM().data(GsmMode.HOME);		
		Zomby.getCoreGSM().data(GsmMode.ON);
		//Zomby.getCoreGSM().data(GsmMode.OFF);		
		//Zomby.getCoreGSM().data(GsmMode.DENIED);		
		//Zomby.getCoreGSM().data(GsmMode.ROAMING);		
		//Zomby.getCoreGSM().data(GsmMode.SEARCHING);		
		//Zomby.getCoreGSM().data(GsmMode.UNREGISTERED);	
	}
	
	public void testVoice() throws Exception {
		Zomby.getCoreGSM().voice(GsmMode.HOME);		
		Zomby.getCoreGSM().voice(GsmMode.ON);		
		Zomby.getCoreGSM().voice(GsmMode.DENIED);		
		Zomby.getCoreGSM().voice(GsmMode.OFF);		
		//Zomby.getCoreGSM().voice(GsmMode.ROAMING);		
		Zomby.getCoreGSM().voice(GsmMode.SEARCHING);		
		Zomby.getCoreGSM().voice(GsmMode.UNREGISTERED);		
	}
}
