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

package de.inovex.android.framework.zomby.testsuit;

import de.inovex.android.framework.zomby.core.test.CoreAVDTest;
import de.inovex.android.framework.zomby.core.test.CoreCDMATest;
import de.inovex.android.framework.zomby.core.test.CoreEventTest;
import de.inovex.android.framework.zomby.core.test.CoreGSMTest;
import de.inovex.android.framework.zomby.core.test.CoreGeoTest;
import de.inovex.android.framework.zomby.core.test.CoreNetworkTest;
import de.inovex.android.framework.zomby.core.test.CorePowerTest;
import de.inovex.android.framework.zomby.core.test.CoreSensorTest;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class LowLevelAPITestSuit {

	public static Test suite() {
		TestSuite suite = new TestSuite(LowLevelAPITestSuit.class.getName());
		//$JUnit-BEGIN$
		//suite.addTestSuite(CoreAVDTest.class);
		suite.addTestSuite(CoreCDMATest.class);
		//suite.addTestSuite(CoreEventTest.class);
		suite.addTestSuite(CoreGeoTest.class);
		suite.addTestSuite(CoreGSMTest.class);
		suite.addTestSuite(CoreNetworkTest.class);
		//suite.addTestSuite(CorePowerTest.class);
		suite.addTestSuite(CoreSensorTest.class);
		//suite.addTestSuite(CoreSMSTest.class);
		//$JUnit-END$
		return suite;
	}

}
