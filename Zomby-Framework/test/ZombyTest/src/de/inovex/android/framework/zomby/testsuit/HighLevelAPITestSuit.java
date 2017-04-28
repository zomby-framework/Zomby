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

import de.inovex.android.framework.zomby.test.AVDTest;
import de.inovex.android.framework.zomby.test.CDMATest;
import de.inovex.android.framework.zomby.test.EventTest;
import de.inovex.android.framework.zomby.test.GSMTest;
import de.inovex.android.framework.zomby.test.GeoTest;
import de.inovex.android.framework.zomby.test.NetworkTest;
import de.inovex.android.framework.zomby.test.PowerTest;
import de.inovex.android.framework.zomby.test.SensorTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class HighLevelAPITestSuit {

	public static Test suite() {
		TestSuite suite = new TestSuite(HighLevelAPITestSuit.class.getName());
		//$JUnit-BEGIN$
		//suite.addTestSuite(AVDTest.class);
		suite.addTestSuite(CDMATest.class);
		suite.addTestSuite(EventTest.class);
		suite.addTestSuite(GeoTest.class);
		suite.addTestSuite(GSMTest.class);
		suite.addTestSuite(NetworkTest.class);
		suite.addTestSuite(PowerTest.class);
		suite.addTestSuite(SensorTest.class);
		//$JUnit-END$
		return suite;
	}

}
