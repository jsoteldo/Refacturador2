/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mifarma.cpe.epos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.StringTokenizer;


import mifarma.cpe.Cronometro;

import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TelnetNotificationHandler;
import org.apache.commons.net.telnet.SimpleOptionHandler;
import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;


/***
 * This is a simple example of use of TelnetClient.
 * An external option handler (SimpleTelnetOptionHandler) is used.
 * Initial configuration requested by TelnetClient will be:
 * WILL ECHO, WILL SUPPRESS-GA, DO SUPPRESS-GA.
 * VT100 terminal type will be subnegotiated.
 * <p>
 * Also, use of the sendAYT(), getLocalOptionState(), getRemoteOptionState()
 * is demonstrated.
 * When connected, type AYT to send an AYT command to the server and see
 * the result.
 * Type OPT to see a report of the state of the first 25 options.
 ***/
public class TestEPOS implements Runnable, TelnetNotificationHandler{
    private static TelnetClient tc = null;
    private static boolean terminoPrueba = false;
    private static String remoteip;
    private static int remoteport;
    private static boolean telnetExitoso = false;
    
    /***
     * Main for the TelnetClientExample.
     * @param args input params
     * @throws Exception on error
     ***/
    public static boolean realizarTelnet(String remoteIP, int remotePort, int timeOut) {
        telnetExitoso = false;
        boolean cumplioTimeOut = false;
        try{
            remoteip = remoteIP;
            remoteport = remotePort;//(new Integer(remotePort)).intValue();;
            tc = new TelnetClient();
            TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
            EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
            SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);
            try{
                tc.addOptionHandler(ttopt);
                tc.addOptionHandler(echoopt);
                tc.addOptionHandler(gaopt);
            }catch (InvalidTelnetOptionException e){
                System.err.println("Error registering option handlers: " + e.getMessage());
            }
            terminoPrueba = false;
            Cronometro cronometro = new Cronometro();
            cronometro.contar();
            Thread reader = new Thread (new TestEPOS());
            reader.start();
            do{
                if(cronometro.getSegundos()>timeOut)
                    cumplioTimeOut = true;
            }while(!terminoPrueba && !cumplioTimeOut);
            cronometro.Detener();
            tc = null;
            System.out.println("Tiempo de demora: "+cronometro.getSegundos());
        }catch(Exception ex){
            telnetExitoso = false;
        }
        return telnetExitoso;
    }


    /***
     * Callback method called when TelnetClient receives an option
     * negotiation command.
     *
     * @param negotiation_code - type of negotiation command received
     * (RECEIVED_DO, RECEIVED_DONT, RECEIVED_WILL, RECEIVED_WONT, RECEIVED_COMMAND)
     * @param option_code - code of the option negotiated
     ***/
    @Override
    public void receivedNegotiation(int negotiation_code, int option_code){
        String command = null;
        switch (negotiation_code) {
            case TelnetNotificationHandler.RECEIVED_DO:
                command = "DO";
                break;
            case TelnetNotificationHandler.RECEIVED_DONT:
                command = "DONT";
                break;
            case TelnetNotificationHandler.RECEIVED_WILL:
                command = "WILL";
                break;
            case TelnetNotificationHandler.RECEIVED_WONT:
                command = "WONT";
                break;
            case TelnetNotificationHandler.RECEIVED_COMMAND:
                command = "COMMAND";
                break;
            default:
                command = Integer.toString(negotiation_code); // Should not happen
                break;
        }
        System.out.println("Received " + command + " for option code " + option_code);
   }

    /***
     * Reader thread.
     * Reads lines from the TelnetClient and echoes them
     * on the screen.
     ***/
    
    @Override
    public void run(){
        terminoPrueba = false;
        try{
            tc.connect(remoteip, remoteport);
            tc.registerNotifHandler(new TestEPOS());
            InputStream instr = tc.getInputStream();
    
            try{
                byte[] buff = new byte[1024];
                int ret_read = 0;
                do{
                    ret_read = instr.read(buff);
                    if(ret_read > 0){
                        System.out.println(new String(buff, 0, ret_read));
                    }
                }while (ret_read >= 0);
            }catch (IOException e){
                System.err.println("Exception while reading socket:" + e.getMessage());
            }
            telnetExitoso = true;
            try{
                tc.disconnect();
            }catch (IOException e){
                System.err.println("Exception while closing telnet:" + e.getMessage());
            }
        }catch (IOException e){
            System.err.println("Exception while connecting:" + e.getMessage());
            telnetExitoso = false;
        }
        terminoPrueba = true;
    }
}