package com.example.melone;

import java.io.*;

import java.net.*;
import java.util.*;

//classe main del server di tipo thread
public class Server extends Thread {
    ServerSocket server = null;
    Socket client = null;
    String stringaricevuta = null;
    String stringamodificata = null;
    BufferedReader indalclient;
    DataOutputStream outversoclient;
    ArrayClient x;
    int conta;
    ArrayList<Double> lista=new ArrayList<Double>();

    public Server(Socket socket, ServerSocket server, ArrayClient x) {
        this.client = socket;
        this.server = server;
        this.x = x;
    }

    public void run() {
        try {

            comunica();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    // main server
    public static void main(String[] args) {
        MultiServer tcpserver = new MultiServer();
        tcpserver.start();
    }

    // passa la stringa al server e la mette in maiuscol
    public void comunica() throws Exception 
    {

        indalclient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outversoclient = new DataOutputStream(client.getOutputStream());
        for (;;) {
            stringaricevuta = indalclient.readLine();
            if (stringaricevuta == null || stringaricevuta.equals("FINE") || stringaricevuta.equals("STOP")) {

                outversoclient.writeBytes(stringaricevuta + " server in chiusura ... " + '\n');
                System.out.println("Echo sul server in chiusura: " + stringaricevuta);
                break;
            } else 
            
            {
                
                double numero=Double.parseDouble(stringaricevuta);
                stringamodificata = ""+numero;
                lista.add(numero);
                outversoclient.writeBytes("" + '\n');
                
                
                Collections.sort(lista);
                outversoclient.writeBytes("RISULTATO LISTA:"+lista);
                
                for (int i = 0; i < lista.size() - 1; i++) 
                {
     
                    if (lista.get(i) == lista.get(i + 1) - 1) 
                    {
                        conta++;
                    }
                        
                    
                    if (conta>5)
                    {
                        outversoclient.writeBytes("Hai vinto!"+"\n");
                    }
                }
  
                System.out.println("6 Echo sul server: " + stringamodificata);
            }
        }
        outversoclient.close(); //chiusura output
        indalclient.close();    //chiusura input
        System.out.println("9 chiusura socket" + client);

        client.close(); //chiusura socketclient
    }




}
