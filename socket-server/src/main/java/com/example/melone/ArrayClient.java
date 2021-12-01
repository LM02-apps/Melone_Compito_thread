package com.example.melone;

import javax.swing.*;
import java.net.*;
import java.util.ArrayList;
import java.io.*;


public class ArrayClient extends Thread
{
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private MultiServer server;
    ArrayList<Socket> client = new ArrayList<Socket>();

    public ArrayClient() 
    {
        
    }

    public void AggiungiClient(Socket x)
    {
        client.add(x);
    }


    public ArrayList<Socket> getClient() {
        return this.client;
    }

    public void setClient(ArrayList<Socket> client) {
        this.client = client;
    }
    

}