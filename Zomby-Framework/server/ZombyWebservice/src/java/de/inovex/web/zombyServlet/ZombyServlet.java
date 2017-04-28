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

package de.inovex.web.zombyServlet;

import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.EmulatorConsole;
import com.android.ddmlib.IDevice;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Manuel Schmidt
 */
@WebServlet(name = "ZombyServlet", urlPatterns = {"/ZombyServlet"})
public class ZombyServlet extends HttpServlet {

    private static String ADB_PATH = null;
    private  String errorMessage = "";
    
    private static IDevice currentDevice;
    private static AndroidDebugBridge bridge;
    private static EmulatorConsole eCon;
    
    @Override
    public void init() throws ServletException {
        super.init();
        setADBPath();
        initEmulatorConsole();
    }
    
    private void setADBPath() throws ServletException {
        Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
            System.out.println("keyname: " + envName);
            if (envName.equalsIgnoreCase("ADB_PATH")) {
                ADB_PATH = env.get(envName) + "/adb";
                // Pfad prüfen
                File file = new File(ADB_PATH);
                if(!(file.exists() && file.canRead()))
                    errorMessage = "The ADB_PATH is wrong";                              
                return;
            }
        }
        
        if(ADB_PATH == null)
            errorMessage = "The ADB_PATH is not set";
    }
    
    private static void initEmulatorConsole() {
        try {
            if(bridge == null) {
                AndroidDebugBridge.init(false);
                bridge = AndroidDebugBridge.createBridge(ADB_PATH, true);
                waitForDevices();
            }

            // select first device by default
            if (bridge.getDevices().length == 0)
                throw new Exception("There are no attached devices");            
            currentDevice = bridge.getDevices()[0];
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (currentDevice != null)
            // initial a emulator console
            eCon = EmulatorConsole.getConsole(currentDevice);
        else
            System.out.println("Device not found");
    }
 
    private static void waitForDevices() throws Exception {
        int x = 0;
        while (bridge.hasInitialDeviceList() == false) {
            Thread.sleep(100);
            x++;

            if (x > 50) {
                throw new Exception("Could not get device list");
            }
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        
        if(!errorMessage.isEmpty()) {            
            out.println(errorMessage);
            out.close();
            throw new ServletException(errorMessage);
        }
        
        System.out.println("ADB_PATH: " + ADB_PATH);
        
        if (bridge.getDevices().length == 0)
            init();
        
        if(bridge.getDevices()[0]!= null) {
            if(currentDevice != bridge.getDevices()[0])
                init();
        }     
        
        // telnet commands are excecuted
        String telnetAnswer = "";
        String[] telnetOrder = request.getParameterValues("telnet");
        for (String order : telnetOrder) {

            telnetAnswer = eCon.processCommand(order);
            try {
                if(telnetAnswer != EmulatorConsole.RESULT_OK)
                    out.println(telnetAnswer);                                                
                else 
                    out.println("TELNET_COMMAND_WAS_SUCCESSFUL");                    
            } finally {
                out.close();
            }
            System.out.println("Post-Parameter: " + order);;
        }
    }
}
