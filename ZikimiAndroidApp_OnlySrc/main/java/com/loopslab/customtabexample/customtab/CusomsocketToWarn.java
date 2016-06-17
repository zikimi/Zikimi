package com.loopslab.customtabexample.customtab;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;




public class CusomsocketToWarn {

    public static void main(String[] args) {

        boolean isSound=false;
        try{

            String serverip = "192.168.14.150";


            System.out.println("Connecting to server server ip : "+serverip);

            Socket socket = new Socket(serverip, 7777);


            OutputStream os = socket.getOutputStream();

            OutputStreamWriter osw = new OutputStreamWriter(os);

            if(isSound) {
                osw.write("soundOff");
                isSound=false;
            }
            else {
                osw.write("soundOn");
                isSound=true;
            }

            osw.flush();

            osw.close();

            socket.close();

        }

        catch (Exception e) {

            e.printStackTrace();

        }

    }

}

