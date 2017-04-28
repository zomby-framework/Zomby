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

package de.inovex.android.zombyrecorder.util;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.regex.Pattern;

import de.inovex.android.framework.zomby.core.CorePower.BatteryStatus;
import de.inovex.android.framework.zomby.player.ZombyPlayer.DataElement;
import de.inovex.android.framework.zomby.util.ZombyException;
import de.inovex.android.zombyrecorder.model.ZombyData;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

/**
 * 
 * @author Manuel Schmidt
 *
 */
public class ZombyFileAsync extends AsyncTask<ZombyDataList, String, String> {
	
	private String TAG = "ZombyFileAsync";

	public static final int DIALOG_SAVE_PROGRESS = 0;
	
	private String filename;
	private FileOutputStream outputStream;
	private InputStream inputStream;
	private Activity activity;
	
	public ZombyFileAsync(Activity activity, String filename) {
		this.activity = activity;
		this.filename = filename;
	}
	
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.showDialog(DIALOG_SAVE_PROGRESS);
    }

    @Override
    protected String doInBackground(ZombyDataList... aList) {
    	ZombyDataList zombyDataList = aList[0];
    	int lengthOfFile = zombyDataList.size()*2;
    	int total = 0;
	    Log.d(TAG , "Lenght of file: " + lengthOfFile);
	
	    // write as zomby file
 		try {
 			outputStream = activity.openFileOutput(filename + ".zf", Context.MODE_PRIVATE);
 			for(ZombyData zombyData : zombyDataList) {
 				outputStream.write(zombyData.toString().getBytes());
 				outputStream.write(new String("\n").getBytes());
 				total++;
 				publishProgress("" + (int)((total*100)/lengthOfFile));
 			}
 			outputStream.flush();
 			outputStream.close();
 		} catch(Exception e) {
 			e.printStackTrace();
 		}
 		
 		// write as java file
 		try {
 			String space = "   ";
 			String javaFile = "";
 			
 			outputStream = activity.openFileOutput(filename + ".java", Context.MODE_PRIVATE);
 			
 			String zombyFileInput = "public void test" + filename + "() throws Exception {\n";
 			outputStream.write(zombyFileInput.getBytes());	 		
 			
 			try {
 				String dataString;
 				long oldTimeStamp = 0;
 				
 				for(ZombyData zombyData : zombyDataList) {
 					javaFile = "";
 					
 					dataString = zombyData.toString();
	 				String [] field = dataString.split(Pattern.quote(";"));
 					if(field.length != 3)
 						throw new ZombyException("invalid values in record file");
 					
 					long timeStamp = Long.valueOf(field[0]);
 					String dataTag = field[1];
 					String parameter = field[2];
 					
 					if(oldTimeStamp != 0) {
 						long timeToWait = Math.round((timeStamp - oldTimeStamp));
 						
 						if(timeToWait > 5)
 							javaFile += "  waitForRealtime(" + timeToWait + ");\n";
 						else
 							javaFile += "\n";
 					}
 							
 					oldTimeStamp = timeStamp;
 					
 					switch(DataElement.valueOf(dataTag)) {
 						case BATTERY:
 							String [] batteryData = parameter.split(Pattern.quote(","));
 							if(batteryData.length == 2) {
 								int capacityValue = Integer.valueOf(batteryData[0]);
 								BatteryStatus batteryStatus = BatteryStatus.valueOf(batteryData[1]);
 								javaFile += space + "Zomby.getCorePower().capacity(" + capacityValue + ");\n";
 								javaFile += space + "Zomby.getCorePower().status(BatteryState." + batteryStatus + ");";
 							} 
 							break;
 							
 						case NETWORK:	
 							javaFile += space + "Zomby.getCoreNetwork().speed(NetworkSpeed.valueOf(\"" + parameter + "\"));";
 							break;
 							
 						case LOCATION:
 							String [] locationData = parameter.split(Pattern.quote(","));
 							if(locationData.length == 2) {
 								double longitude = Double.valueOf(locationData[0]);
 								double latitude = Double.valueOf(locationData[1]);
 								javaFile += space + "Zomby.getCoreGeo().fix(" + longitude + ", " + latitude + ");";
 							} 
 							break;
 							
 						case ACCELEROMETER:
 							String [] accelerometerData = parameter.split(Pattern.quote(","));
 							if(accelerometerData.length == 3) {
 								float x = Float.valueOf(accelerometerData[0]);
 								float y = Float.valueOf(accelerometerData[1]);
 								float z = Float.valueOf(accelerometerData[2]);
 								javaFile += space + "Zomby.getCoreSensor().set(Sensorname.ACCELERATION, " + x + ", " + y + ", " + z + ");";
 							} 
 							break;
 						
 						case MAGNETIC_FIELD:
 							String [] magneticFieldData = parameter.split(Pattern.quote(","));
 							if(magneticFieldData.length == 3) {
 								float x = Float.valueOf(magneticFieldData[0]);
 								float y = Float.valueOf(magneticFieldData[1]);
 								float z = Float.valueOf(magneticFieldData[2]);
 								javaFile += space + "Zomby.getCoreSensor().set(Sensorname.MAGNETIC_FIELD, " + x + ", " + y + ", " + z + ");";
 							} 
 							break;
 						
 						
 						case ORIENTATION:
 							String [] orientationData = parameter.split(Pattern.quote(","));
 							if(orientationData.length == 3) {
 								float x = Float.valueOf(orientationData[0]);
 								float y = Float.valueOf(orientationData[1]);
 								float z = Float.valueOf(orientationData[2]);
 								javaFile += space + "Zomby.getCoreSensor().set(Sensorname.ORIENTATION, " + x + ", " + y + ", " + z + ");";
 							} 
 							break;
 					
 						case PROXIMITY:
 							javaFile += space + "Zomby.getCoreSensor().set(Sensorname.PROXIMITY, " + parameter + ");";
 							break;
 						
 						case TEMPERATURE:
 							javaFile += space + "Zomby.getCoreSensor().set(Sensorname.TEMPERATURE, " + parameter + ");";
 							break;
 					}
 					outputStream.write(javaFile.getBytes());
 	
 					total++;
	 				publishProgress("" + (int)((total*100)/lengthOfFile));
	 			}
 			} catch (Exception e) {
 				e.printStackTrace();
 			}	 			
 			
 			String end = "\n}\n\n";
 			outputStream.write(end.getBytes());	 							
 			

 			String waitForRealtime = "private void waitForRealtime(long time) throws InterruptedException {\n" +
 					"   double realtimeFactor = 1.0;\n" +
 					"   long timeToWait = Math.round(time*realtimeFactor);\n" +
 					"   synchronized(this) {\n" +
 					"      if(timeToWait>10)\n" +
 					"         wait(timeToWait);\n" +
 					"   }\n}";
 			outputStream.write(waitForRealtime.getBytes());
 			
 			outputStream.flush();
 			outputStream.close();
 			inputStream.close();
	
    } catch (Exception e) {}
    return null;

    }
    protected void onProgressUpdate(String... progress) {
         activity.setProgress(Integer.parseInt(progress[0]));
         Log.d(TAG, "total: "+Integer.parseInt(progress[0]));
    }

    @Override
    protected void onPostExecute(String unused) {
    	activity.dismissDialog(DIALOG_SAVE_PROGRESS);
    }
}

