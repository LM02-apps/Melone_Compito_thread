package com.example.melone;

import java.io.*;
import java.net.*;

public class ControlloClient extends Thread
{
    Socket socketclient;

    public ControlloClient(Socket socketclient)
    {
        this.socketclient=socketclient;
    }
    @Override
    public void run() {
        
        try 
        {
            Thread.sleep(2000);
            if (socketclient.isConnected())
            {
                System.out.println("Chiusura client per disconnessione server");
                socketclient.close();
            }

        } 
        catch (InterruptedException e) {
            
            e.printStackTrace();
        } catch (IOException e) {
            
            e.printStackTrace();
        }

    }

}
