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

package de.inovex.android.uiautomatortest;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiScrollable;
import com.android.uiautomator.core.UiSelector;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class Launcher {
	public static UiObject launchApp(String appName) throws UiObjectNotFoundException {
		UiScrollable appViews = showApps();
		UiObject androidApp = appViews.getChildByText(new UiSelector()
				.className(android.widget.TextView.class.getName()), appName);
		androidApp.clickAndWaitForNewWindow();
		return androidApp;
	}

	private static UiScrollable showApps() throws UiObjectNotFoundException {
		UiObject allAppsButton = new UiObject(
				new UiSelector().description("Apps"));

		// Simulate a click to bring up the All Apps screen.
		allAppsButton.clickAndWaitForNewWindow();

		// In the All Apps screen, the Settings app is located in
		// the Apps tab. To simulate the user bringing up the Apps tab,
		// we create a UiSelector to find a tab with the text
		// label “Apps”.
		UiObject appsTab = new UiObject(new UiSelector().text("Apps"));

		// Simulate a click to enter the Apps tab.
		appsTab.click();

		// Next, in the apps tabs, we can simulate a user swiping until
		// they come to the Settings app icon. Since the container view
		// is scrollable, we can use a UiScrollable object.
		UiScrollable appViews = new UiScrollable(
				new UiSelector().scrollable(true));
		return appViews;
	}
}
