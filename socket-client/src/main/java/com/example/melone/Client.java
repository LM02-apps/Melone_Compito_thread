package com.example.melone;

import java.io.*;
import java.net.*;

public class Client {
    static String nomeserver = "localhost";
    int portaserver = 6789;
    Socket msocket;
    BufferedReader tastiera;
    String stringautente;
    String stringarispostaserver;
    DataOutputStream outversoserver;
    BufferedReader indalserver;

    

    // main client
    public static void main(String[] args) 
    {
        Client mclient = new Client();
        

        mclient.connetti();
        mclient.comunica();
    }
    //usare il metodo isconnected sul socket per sapere se il server è chiuso oppure no, 
    //ogni 2 secondi con thread.sleep fa il controllo

    public Socket connetti() {

        System.out.println("Client in esecuzione");
        try {
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            msocket = new Socket(nomeserver, portaserver);
            outversoserver = new DataOutputStream(msocket.getOutputStream());
            indalserver = new BufferedReader(new InputStreamReader(msocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore connessione");
            System.exit(1);
        }
        return msocket;
    }

    public void comunica() 
    {
        for (;;) 
        {
            try 
            {
                
                System.out.println("Inserisci il numero" + '\n');
                stringautente = tastiera.readLine();
                //il client se è connesso riporta la stringa 

                System.out.println("invio stringa");
                outversoserver.writeBytes(stringautente + '\n');

                stringarispostaserver = indalserver.readLine();
                System.out.println("risposta dal server" + '\n' + stringarispostaserver);
                if (stringarispostaserver.contains("Hai vinto!") ) 
                {
                    System.out.println("client termina istruzioni e chiude connessione");
                    msocket.close();
                    break;
                }
                

            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }

    }


}
